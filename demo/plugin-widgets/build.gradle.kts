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

    @get:Input
    abstract val widgetPackagePath: Property<String>

    /**
     * 插件元数据
     */
    data class PluginInfo(
        val fullClassName: String,
        val id: String?,
        val version: String?,
        val author: String?,
        val description: String?,
        val minAppVersion: String?
    )

    /**
     * 自动查找实现 WidgetPlugin 接口的类并提取元数据
     */
    private fun findPluginInfo(): PluginInfo {
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
                        val fullClassName = "$packageName.$className"

                        // 提取 WidgetPluginMetadata 中的字段
                        val id = Regex("""id\s*=\s*"([^"]+)"""").find(content)?.groupValues?.get(1)
                        val version =
                            Regex("""version\s*=\s*"([^"]+)"""").find(content)?.groupValues?.get(1)
                        val author =
                            Regex("""author\s*=\s*"([^"]+)"""").find(content)?.groupValues?.get(1)
                        val description =
                            Regex("""description\s*=\s*"([^"]+)"""").find(content)?.groupValues?.get(
                                1
                            )
                        val minAppVersion =
                            Regex("""minAppVersion\s*=\s*"([^"]+)"""").find(content)?.groupValues?.get(
                                1
                            )

                        return PluginInfo(
                            fullClassName = fullClassName,
                            id = id,
                            version = version,
                            author = author,
                            description = description,
                            minAppVersion = minAppVersion
                        )
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

            // 收集 class 文件（只包含当前 widget 的代码）
            val classFiles = mutableListOf<String>()
            val targetPackagePath = widgetPackagePath.get()
            println("📦 Filtering classes for package: $targetPackagePath")

            classDirs.forEach { dir ->
                if (dir.exists()) {
                    dir.walkTopDown()
                        .filter { it.isFile && it.extension == "class" }
                        .forEach { file ->
                            // 检查文件路径是否在目标 widget 的包下
                            val relativePath = file.relativeTo(dir).path
                            val shouldInclude = relativePath.startsWith(targetPackagePath)

                            if (shouldInclude) {
                                classFiles.add(file.absolutePath)
                                println("✅ Including class: ${file.name}")
                            }
                        }
                }
            }
            if (classFiles.isEmpty()) throw GradleException("No .class files found in $classDirs for package: $targetPackagePath")

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

            // 自动查找实现 WidgetPlugin 接口的类并提取元数据
            val pluginInfo = findPluginInfo()
            println("🔍 Detected plugin: ${pluginInfo.fullClassName}")
            println("   ID: ${pluginInfo.id}")
            println("   Version: ${pluginInfo.version}")
            println("   MinAppVersion: ${pluginInfo.minAppVersion}")

            // 创建 MANIFEST.MF
            val manifest = Manifest()
            manifest.mainAttributes[Attributes.Name.MANIFEST_VERSION] = "1.0"
            manifest.mainAttributes[Attributes.Name("Plugin-Class")] = pluginInfo.fullClassName

            // 写入插件元数据
            pluginInfo.id?.let { manifest.mainAttributes[Attributes.Name("Plugin-ID")] = it }
            pluginInfo.version?.let {
                manifest.mainAttributes[Attributes.Name("Plugin-Version")] = it
            }
            pluginInfo.author?.let {
                manifest.mainAttributes[Attributes.Name("Plugin-Author")] = it
            }
            pluginInfo.description?.let {
                manifest.mainAttributes[Attributes.Name("Plugin-Description")] = it
            }
            pluginInfo.minAppVersion?.let {
                manifest.mainAttributes[Attributes.Name("Min-App-Version")] = it
            }
            
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

// 扫描所有 widget 组件
data class WidgetInfo(
    val name: String,
    val id: String,
    val packagePath: String,
    val sourceDir: File
)

fun discoverWidgets(): List<WidgetInfo> {
    val widgets = mutableListOf<WidgetInfo>()
    val widgetsBaseDir = layout.projectDirectory.dir("src/main/java/com/neta/widgets").asFile

    if (!widgetsBaseDir.exists()) {
        println("⚠️  Widgets directory not found: $widgetsBaseDir")
        return emptyList()
    }

    widgetsBaseDir.listFiles()?.filter { it.isDirectory }?.forEach { widgetDir ->
        val widgetName = widgetDir.name

        // 查找 WidgetPlugin 文件
        widgetDir.walkTopDown()
            .filter { it.isFile && it.extension == "kt" && it.name.endsWith("WidgetPlugin.kt") }
            .forEach { pluginFile ->
                val content = pluginFile.readText()

                val classMatch = Regex("""class\s+(\w+)\s*:\s*WidgetPlugin""").find(content)
                val idMatch = Regex("""id\s*=\s*"([^"]+)"""").find(content)

                if (classMatch != null && idMatch != null) {
                    val widgetId = idMatch.groupValues[1]
                    val packagePath = "com/neta/widgets/$widgetName"

                    widgets.add(WidgetInfo(widgetName, widgetId, packagePath, widgetDir))
                    println("🎯 Discovered widget: $widgetName (id: $widgetId)")
                }
            }
    }

    return widgets
}

val discoveredWidgets = discoverWidgets()

android.buildTypes.forEach { buildType ->
    val variantName = buildType.name
    val variantCapped = variantName.replaceFirstChar { it.uppercaseChar() }

    // 为每个 widget 创建独立的 JAR 任务
    val widgetJarTasks = discoveredWidgets.map { widget ->
        tasks.register<CreateWidgetJarTask>("create${variantCapped}${widget.name.replaceFirstChar { it.uppercaseChar() }}WidgetJar") {
            classDirs.from(
                layout.buildDirectory.dir("intermediates/javac/${variantName}/compile${variantCapped}JavaWithJavac/classes"),
                layout.buildDirectory.dir("tmp/kotlin-classes/${variantName}")
            )

            val sdkDir = android.sdkDirectory.path
            androidJar.set(
                File(
                    File(sdkDir, "platforms/android-${android.compileSdk}"),
                    "android.jar"
                )
            )

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
            outputJar.set(layout.file(generatedDir.map { it.asFile.resolve("${widget.id}.jar") }))
            tempDexDir.set(layout.buildDirectory.dir("tmp/widget-dex/${variantName}/${widget.name}"))
            sourceDir.set(widget.sourceDir)
            widgetPackagePath.set(widget.packagePath)
            minApi.set("30")

            dependsOn(
                tasks.named("compile${variantCapped}JavaWithJavac"),
                tasks.named("compile${variantCapped}Kotlin")
            )

            println("✅ Registered task: create${variantCapped}${widget.name.replaceFirstChar { it.uppercaseChar() }}WidgetJar -> ${widget.id}.jar")
        }
    }

    // 创建聚合任务，一次性构建所有 widget
    val aggregateTask = tasks.register("create${variantCapped}WidgetJar") {
        dependsOn(widgetJarTasks)
        group = "widget"
        description = "Build all widget JARs for $variantName variant"
    }

    tasks.named("assemble").configure {
        dependsOn(aggregateTask)
    }
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
