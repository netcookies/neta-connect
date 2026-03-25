plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.neta.isulewtools.widget"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(
                org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget(
                    libs.versions.javaVersion.get()
                )
            )
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // 使用 BOM 确保与主 App 的 Compose 版本一致（关键！）
    // 这对动态加载的插件架构至关重要
    api(platform(libs.androidx.compose.bom))

    // 只包含 API 定义所需的最小依赖
    api(libs.androidx.compose.runtime)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui)

    // Material Icons - 用于 MaterialIconsProvider
    api(libs.androidx.compose.material.icons.extended)

    testImplementation(libs.junit)
}
