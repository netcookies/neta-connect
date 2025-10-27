import java.io.FileOutputStream
import java.util.jar.JarEntry
import java.util.jar.JarOutputStream

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

    @TaskAction
    fun createJar() {
        try {
            val outputJarFile = outputJar.get().asFile
            outputJarFile.parentFile.mkdirs()

            // 清理并创建临时 dex 输出目录
            val tempDexDirFile = tempDexDir.get().asFile
            if (tempDexDirFile.exists()) tempDexDirFile.deleteRecursively()
            tempDexDirFile.mkdirs()

            // 收集 class 文件
            val classFiles = mutableListOf<String>()
            classDirs.forEach { dir ->
                if (dir.exists()) {
                    dir.walkTopDown()
                        .filter { it.isFile && it.extension == "class" }
                        .forEach {
                            classFiles.add(it.absolutePath)
                            println("Including class: ${it.name}")
                        }
                }
            }
            if (classFiles.isEmpty()) throw GradleException("No .class files found in $classDirs")

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

            // 打包到 JAR
            JarOutputStream(FileOutputStream(outputJarFile)).use { jarOut ->
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

        val generatedDir = layout.buildDirectory.dir("outputs/widget/${variantName}")
        outputJar.set(layout.file(generatedDir.map { it.asFile.resolve("widget-battery-demo.jar") }))
        tempDexDir.set(layout.buildDirectory.dir("tmp/widget-dex/${variantName}"))
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

dependencies {
    // 只依赖必要的编译时依赖
    // 运行时会使用主 App 的 ClassLoader,所以这里用 compileOnly
    compileOnly("androidx.compose.ui:ui:1.9.4")
    compileOnly("androidx.compose.material3:material3:1.4.0")
    compileOnly("androidx.compose.ui:ui-graphics:1.9.4")
    compileOnly("androidx.compose.foundation:foundation:1.9.4")
    compileOnly("androidx.compose.material:material-icons-extended:1.7.8")
    compileOnly("androidx.core:core-ktx:1.17.0")
}
