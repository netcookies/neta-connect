plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.neta.isulewtools.widget"
    compileSdk = 36

    defaultConfig {
        minSdk = 30
        consumerProguardFiles("consumer-rules.pro")
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
}
