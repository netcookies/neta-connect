import java.io.FileOutputStream
import java.util.jar.Attributes
import java.util.jar.JarEntry
import java.util.jar.JarOutputStream
import java.util.jar.Manifest
import java.util.zip.ZipFile

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

abstract class CreateWidgetJarTask : DefaultTask() {

    @get:InputFiles
    abstract val classDirs: ConfigurableFileCollection

    @get:InputFile
    abstract val androidJar: RegularFileProperty

    @get:OutputFile
    abstract val outputJar: RegularFileProperty

    @get:Input
    abstract val minApi: Property<String>

    @get:OutputDirectory
    abstract val tempDexDir: DirectoryProperty

    @get:InputDirectory
    abstract val sourceDir: DirectoryProperty

    @get:Classpath
    abstract val compileClasspath: ConfigurableFileCollection

    /**
     * 自动查找实现 WidgetPlugin 接口的类
     */
    private fun findPluginClass(): String {
        val sourceDirFile = sourceDir.get().asFile
        if (!sourceDirFile.exists()) {
            throw GradleException("Source directory not found: $sourceDirFile")
        }

        // 遍历所有 .kt 文件
        sourceDirFile.walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .forEach { file ->
                val content = file.readText()

                // 查找实现 WidgetPlugin 的类
                val classMatch = Regex("""class\s+(\w+)\s*:\s*WidgetPlugin""").find(content)
                if (classMatch != null) {
                    val className = classMatch.groupValues[1]

                    // 提取包名
                    val packageMatch = Regex("""package\s+([\w.]+)""").find(content)
                    val packageName = packageMatch?.groupValues?.get(1)

                    if (packageName != null) {
                        return "$packageName.$className"
                    }
                }
            }

        throw GradleException("No class implementing WidgetPlugin found in $sourceDirFile")
    }

    /**
     * 扫描插件源码中使用的 Material Icons
     * @return icon 路径列表，格式: Icons.Default.BatteryChargingFull
     */
    private fun findUsedIcons(): List<String> {
        val sourceDirFile = sourceDir.get().asFile
        if (!sourceDirFile.exists()) {
            println("⚠️  Source directory not found: $sourceDirFile")
            return emptyList()
        }

        val icons = mutableSetOf<String>()

        // 匹配所有 Icons.* 变体
        val iconsRegex = Regex("""Icons\.([A-Za-z]+(?:\.[A-Za-z]+)*)\.([A-Za-z]+)""")

        sourceDirFile.walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .forEach { file ->
                val content = file.readText()
                iconsRegex.findAll(content).forEach { match ->
                    // match.value: Icons.Default.BatteryChargingFull
                    // match.groupValues[1]: Default 或 AutoMirrored.Filled
                    // match.groupValues[2]: BatteryChargingFull
                    icons.add(match.value)
                }
            }

        return icons.sorted()
    }

    /**
     * 从 Compose 依赖中提取 icon class 文件
     * @param icons icon 路径列表
     * @return 提取的 class 文件列表
     */
    private fun extractIconClasses(icons: List<String>): List<File> {
        if (icons.isEmpty()) return emptyList()

        val extractedClasses = mutableListOf<File>()
        val tempIconDir = File(tempDexDir.get().asFile.parentFile, "icon-classes")
        tempIconDir.mkdirs()

        // 从 classpath 中查找 material-icons-extended JAR
        val iconsJarFile = compileClasspath.files.find {
            it.name.contains("material-icons-extended") && it.extension == "jar"
        }

        if (iconsJarFile == null) {
            println("⚠️  material-icons-extended JAR not found in classpath, skipping icon extraction")
            return emptyList()
        }

        println("📦 Found Material Icons JAR: ${iconsJarFile.name}")

        // 使用 ZipFile 提取 class 文件
        ZipFile(iconsJarFile).use { zip ->
            icons.forEach { iconPath ->
                // 解析 icon 路径: Icons.Default.BatteryChargingFull
                val parts = iconPath.split(".")
                val variant = parts.dropLast(1).drop(1).joinToString(".")
                    .lowercase() // Default → filled, AutoMirrored.Filled → automirrored.filled
                val iconName = parts.last()

                // 映射到包路径
                val packagePath = when {
                    variant == "default" -> "filled"
                    variant.contains("automirrored") -> "automirrored/${variant.substringAfter("automirrored.")}"
                    else -> variant
                }

                // Material Icons 只需要 IconKt.class（Kotlin 顶层扩展属性）
                val className = "${iconName}Kt.class"
                val entryPath = "androidx/compose/material/icons/$packagePath/$className"
                val entry = zip.getEntry(entryPath)

                if (entry != null) {
                    val targetFile = File(tempIconDir, className)
                    zip.getInputStream(entry).use { input ->
                        targetFile.outputStream().use { output ->
                            input.copyTo(output)
                        }
                    }
                    extractedClasses.add(targetFile)
                    println("   ✅ Extracted: $entryPath")
                } else {
                    println("   ⚠️  Not found: $entryPath")
                }
            }
        }

        return extractedClasses
    }

    @TaskAction
    fun createJar() {
        try {
            val outputJarFile = outputJar.get().asFile
            outputJarFile.parentFile.mkdirs()

            // 清理并创建临时 dex 输出目录
            val tempDexDirFile = tempDexDir.get().asFile
            if (tempDexDirFile.exists()) tempDexDirFile.deleteRecursively()
            tempDexDirFile.mkdirs()

            // 收集 class 文件（只包含 widget 自己的代码，排除第三方库）
            val classFiles = mutableListOf<String>()
            val allowedPackages = listOf("com/neta/widgets")  // 只打包这些包下的类

            classDirs.forEach { dir ->
                if (dir.exists()) {
                    dir.walkTopDown()
                        .filter { it.isFile && it.extension == "class" }
                        .forEach { file ->
                            // 检查文件路径是否在允许的包下
                            val relativePath = file.relativeTo(dir).path
                            val shouldInclude = allowedPackages.any { relativePath.startsWith(it) }

                            if (shouldInclude) {
                                classFiles.add(file.absolutePath)
                                println("✅ Including class: ${file.name} (${relativePath})")
                            } else {
                                println("⏭️  Skipping class: ${file.name} (${relativePath})")
                            }
                        }
                }
            }
            if (classFiles.isEmpty()) throw GradleException("No .class files found in $classDirs matching allowed packages: $allowedPackages")

            // 扫描并提取 Material Icons
            val usedIcons = findUsedIcons()
            if (usedIcons.isNotEmpty()) {
                println("🎨 Found ${usedIcons.size} Material Icon(s) in source:")
                usedIcons.forEach { println("   - $it") }

                val iconClasses = extractIconClasses(usedIcons)
                if (iconClasses.isNotEmpty()) {
                    println("📦 Extracted ${iconClasses.size} icon class file(s)")
                    classFiles.addAll(iconClasses.map { it.absolutePath })
                }
            } else {
                println("ℹ️  No Material Icons found in source")
            }

            // D8 生成 dex
            val androidJarFile = androidJar.get().asFile
            val d8Class = Class.forName("com.android.tools.r8.D8")
            val mainMethod = d8Class.getMethod("main", Array<String>::class.java)

            val d8Args = mutableListOf<String>().apply {
                add("--min-api")
                add(minApi.get())
                add("--output")
                add(tempDexDirFile.absolutePath)
                add("--lib")
                add(androidJarFile.absolutePath)
                addAll(classFiles)
            }

            println("Running D8 with args:\n  ${d8Args.joinToString("\n  ")}")
            mainMethod.invoke(null, d8Args.toTypedArray())

            val dexFile = File(tempDexDirFile, "classes.dex")
            if (!dexFile.exists()) throw GradleException("D8 failed to generate classes.dex")
            println("DEX generated at: ${dexFile.absolutePath}")

            // 自动查找实现 WidgetPlugin 接口的类
            val pluginClassName = findPluginClass()
            println("🔍 Detected plugin class: $pluginClassName")

            // 创建 MANIFEST.MF
            val manifest = Manifest()
            manifest.mainAttributes[Attributes.Name.MANIFEST_VERSION] = "1.0"
            manifest.mainAttributes[Attributes.Name("Plugin-Class")] = pluginClassName
            
            // 打包到 JAR
            JarOutputStream(FileOutputStream(outputJarFile), manifest).use { jarOut ->
                val entry = JarEntry("classes.dex")
                jarOut.putNextEntry(entry)
                dexFile.inputStream().use { it.copyTo(jarOut) }
                jarOut.closeEntry()
            }

            println("✅ Widget JAR created at $outputJarFile")
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Failed to create widget JAR", e)
        }
    }
}

android {
    namespace = "com.neta.widgets.battery"
    compileSdk = 36

    defaultConfig {
        minSdk = 30
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }

    buildFeatures {
        compose = true
    }
}

android.buildTypes.forEach { buildType ->
    val variantName = buildType.name
    val variantCapped = variantName.replaceFirstChar { it.uppercaseChar() }

    val jarTask = tasks.register<CreateWidgetJarTask>("create${variantCapped}WidgetJar") {
        classDirs.from(
            layout.buildDirectory.dir("intermediates/javac/${variantName}/compile${variantCapped}JavaWithJavac/classes"),
            layout.buildDirectory.dir("tmp/kotlin-classes/${variantName}")
        )

        val sdkDir = android.sdkDirectory.path
        androidJar.set(File(File(sdkDir, "platforms/android-${android.compileSdk}"), "android.jar"))

        // 只包含外部依赖的 JAR（排除项目依赖避免 variant 歧义）
        // 同时请求 jar 和 android-classes-jar 类型以覆盖所有情况
        compileClasspath.from(
            configurations.getByName("${variantName}CompileClasspath").incoming
                .artifactView {
                    attributes {
                        attribute(
                            Attribute.of("artifactType", String::class.java),
                            "android-classes-jar"
                        )
                    }
                    componentFilter { it is ModuleComponentIdentifier }
                }.artifacts.artifactFiles,
            configurations.getByName("${variantName}CompileClasspath").incoming
                .artifactView {
                    attributes {
                        attribute(Attribute.of("artifactType", String::class.java), "jar")
                    }
                    componentFilter { it is ModuleComponentIdentifier }
                }.artifacts.artifactFiles
        )

        val generatedDir = layout.buildDirectory.dir("outputs/widget/${variantName}")
        outputJar.set(layout.file(generatedDir.map { it.asFile.resolve("widget-battery-demo.jar") }))
        tempDexDir.set(layout.buildDirectory.dir("tmp/widget-dex/${variantName}"))
        sourceDir.set(layout.projectDirectory.dir("src/main/java"))
        minApi.set("30")
    }

    jarTask.configure {
        dependsOn(
            tasks.named("compile${variantCapped}JavaWithJavac"),
            tasks.named("compile${variantCapped}Kotlin")
        )
    }

    tasks.named("assemble").configure {
        dependsOn(jarTask)
    }

    println("✅ Registered task: create${variantCapped}WidgetJar")
}

//noinspection UseTomlInstead
dependencies {
    // Widget API - 使用 compileOnly，运行时由主 App 提供
    compileOnly(project(":widget-api"))

    // Compose 依赖 - 支持预览，打包时会被过滤
    implementation("androidx.compose.ui:ui:1.9.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.9.4")
    implementation("androidx.compose.ui:ui-tooling:1.9.4")
    implementation("androidx.compose.material3:material3:1.4.0")
    implementation("androidx.compose.ui:ui-graphics:1.9.4")
    implementation("androidx.compose.foundation:foundation:1.9.4")
    implementation("androidx.compose.material:material-icons-extended:1.7.8")
    implementation("androidx.core:core-ktx:1.17.0")
}
