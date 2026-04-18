# Probe API

`probe-api` 是通用探针体系的最小 contract 模块，只承载宿主与探针之间稳定的接口定义，不包含任何 OEM 逻辑、Root 启动链或具体探针实现。

## 模块职责

- 提供 `ProbeEntry`
- 提供 `ProbeContext`
- 提供 `ProbeResult`
- 提供 `ProbeErrorCode`
- 作为自定义 probe JAR 与宿主 runtime 之间的稳定 ABI

## 适用场景

- 第三方开发者只想实现自定义 `ProbeEntry`
- 需要把 probe 逻辑单独编译成 APK / JAR，再通过宿主 `ProbeHostExecutor` 动态加载
- 需要一个可以独立开 Android Studio 工作区开发的最小 SDK

## 公开边界

`probe-api` 适合像 `widget-api` 一样对外公开，因为它只暴露 contract，不包含：

- OEM 私有 binder 协议
- Root / `app_process` / privilege starter 细节
- 车机厂商包名、action、transaction code

`probe-runtime` 不适合直接公开同步，因为它包含：

- 宿主执行器 `ProbeHostMain` / `ProbeHostExecutor`
- OEM 参考实现 `OemAccessorProbe`
- 与当前主应用、Root 链路、OEM 接口强绑定的实现细节

## 在主工程内使用

主工程中的依赖关系是：

```text
custom probe implementation -> :probe-api
main app / probe host runtime -> :probe-runtime -> :probe-api
```

如果你要在本仓库里新增一个探针实现，通常只需要：

1. 依赖 `:probe-api`
2. 实现一个 `ProbeEntry`
3. 将该实现打进你自己的 APK / JAR
4. 用 `scripts/oem/probe_run.py` 触发宿主运行

## 最小实现示例

```kotlin
package demo.probe

import com.neta.isulewtools.probe.contract.ProbeContext
import com.neta.isulewtools.probe.contract.ProbeEntry
import com.neta.isulewtools.probe.contract.ProbeResult

class DemoProbe : ProbeEntry {
    override fun run(
        context: ProbeContext,
        args: List<String>
    ): ProbeResult {
        return ProbeResult.success(
            probeId = context.probeId,
            summary = "demo probe completed",
            details = linkedMapOf(
                "argCount" to args.size.toString(),
                "uid" to context.uid.toString(),
                "pid" to context.pid.toString(),
                "seLinuxContext" to context.seLinuxContext
            )
        )
    }
}
```

## 独立 Android Studio 工作区

如果开发者只拿到 `probe-api/` 目录，也可以单独创建一个最小工作区。最小目录结构示例：

```text
probe-sdk-workspace/
├── settings.gradle.kts
├── build.gradle.kts
├── gradle/libs.versions.toml
└── probe-api/
```

### `settings.gradle.kts`

```kotlin
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "probe-sdk-workspace"
include(":probe-api")
```

### 根 `build.gradle.kts`

```kotlin
plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
}
```

### `gradle/libs.versions.toml`

```toml
[versions]
agp = "8.13.2"
kotlin = "2.3.20"
compileSdk = "36"
minSdk = "30"
javaVersion = "11"

[libraries]
androidx-core-ktx = { module = "androidx.core:core-ktx", version = "1.17.0" }
junit = { module = "junit:junit", version = "4.13.2" }
robolectric = { module = "org.robolectric:robolectric", version = "4.14.1" }
androidx-test-core-ktx = { module = "androidx.test:core-ktx", version = "1.6.1" }

[plugins]
android-library = { id = "com.android.library", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
```

这样开发者把 `probe-api/` 放进去后，就可以直接在 Android Studio 中打开该 workspace。

## 测试

在当前仓库中：

```bash
./gradlew :probe-api:testDebugUnitTest \
  --tests 'com.neta.isulewtools.probe.ProbeContractTest'
```

## Public Demo Sync

The public demo workspace is expected to include:

- `settings.gradle.kts`
- `build.gradle.kts`
- `gradle/libs.versions.toml`
- `probe-api/`
- `my-probe/README.md`

These files are synchronized by the public demo workflow so the demo workspace
can be opened directly in Android Studio without private companion documents.
