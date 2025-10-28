plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.neta.isulewtools.widget"
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

dependencies {
    // 只包含 API 定义所需的最小依赖
    api("androidx.compose.runtime:runtime:1.9.4")
    api("androidx.compose.ui:ui-graphics:1.9.4")
    api("androidx.compose.ui:ui:1.9.4")  // 需要 ImageVector
}
