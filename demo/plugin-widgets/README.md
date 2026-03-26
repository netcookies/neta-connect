# 🔋 电池(示例) - 动态加载小组件示例模块

这是一个完整的**动态加载小组件示例**，展示如何开发、编译和部署可独立加载的小组件插件。

---

## 📦 模块说明

**plugin-widgets** 是一个独立的 Android Library 模块，它：

- ✅ 实现了 `WidgetPlugin` 接口
- ✅ 可以独立编译为 JAR 文件
- ✅ 在运行时被主应用动态加载
- ✅ 自动支持缩放和透明度（无需手动实现）
- ✅ 完整的元信息和版本管理

---

## 🏗️ 项目结构

```
plugin-widgets/
├── build.gradle.kts          # 构建配置（包含 JAR 生成任务）
├── README.md                 # 本文档
├── DEVELOPMENT_GUIDE.md      # 开发指南
└── src/main/java/com/neta/
    ├── widgets/battery/
    │   ├── BatteryWidgetPlugin.kt          # 插件入口类
    │   └── SimpleBatteryWidget.kt          # 小组件实现
    └── isulewtools/widget/
        ├── WidgetSpec.kt                   # Widget 规格定义（复制）
        └── plugin/WidgetPlugin.kt          # 插件接口（复制）
```

---

## 🎯 核心文件说明

### 1. BatteryWidgetPlugin.kt - 插件入口

```kotlin
class BatteryWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return SimpleBatteryWidgetSpec  // 返回小组件规格
    }

override fun getMetadata(): WidgetPluginMetadata {
    return WidgetPluginMetadata(
        id = "widget-battery-demo",              // 唯一标识
        version = "1.0.0",                  // 版本号
        author = "官方",                    // 作者
        description = "显示电池电量的小组件(动态版)",
        minAppVersion = "2.1.7",            // 最低应用版本基线
        signer = "release-key",             // 可选：预留 signer
        certificateSha256 = "ABCD..."       // 可选：预留证书指纹
    )
}
}
```

**关键点：**

- 必须实现 `WidgetPlugin` 接口
- `id` 用于管理（文件名、数据库记录）
- 插件类名从 JAR 的 MANIFEST.MF 自动读取（由构建脚本自动生成）

### 2. SimpleBatteryWidget.kt - 小组件实现

```kotlin
object SimpleBatteryWidgetSpec : WidgetSpec(
    type = "battery_widget",
    displayName = "电池(示例)",
    paramSchema = listOf(
        // 只定义你自己的参数
        WidgetParamDesc("batteryLevel", "电量(%)", WidgetParamType.FLOAT, 75f),
        WidgetParamDesc("color", "颜色", WidgetParamType.STRING, "#66BB6A")
        // scale 和 alpha 会自动注入！
    ),
    contentComposable = { SimpleBatteryWidgetContent(it) },
    color = Color(0xFF66BB6A),
    icon = Icons.Default.BatteryChargingFull
)
```

**重要特性：**

- ✨ **自动支持缩放和透明度** - 无需手动添加 scale 和 alpha 参数
- 🎨 支持自定义参数（电量、颜色等）
- 📱 使用 Compose UI 构建界面

---

## 🔧 构建和编译

### 构建 JAR 文件

```bash
# 编译 Debug 版本
./gradlew :plugin-widgets:assembleDebug

# 编译 Release 版本
./gradlew :plugin-widgets:assembleRelease
```

### 输出文件

编译成功后，JAR 文件位于：

```
plugin-widgets/build/outputs/widget/
├── debug/widget-battery-demo.jar       # Debug 版本
└── release/widget-battery-demo.jar     # Release 版本
```

---

## 📥 安装和使用

### 方法 1: 通过应用内安装（推荐）

1. 将编译好的 `widget-battery-demo.jar` 文件放到设备上
2. 在主应用中：
    - 打开"小组件商店"
    - 选择"从本地安装"
    - 浏览并选择 JAR 文件
    - 等待安装完成

### 方法 2: 手动放置文件

```bash
# 1. 创建小组件目录
adb shell mkdir -p /sdcard/isulewTools/widgets

# 2. 推送 JAR 文件
adb push plugin-widgets/build/outputs/widget/debug/widget-battery-demo.jar \
    /sdcard/isulewTools/widgets/

# 3. 重启应用以加载新小组件
```

---

## ⚙️ 技术细节

### JAR 自描述机制（MANIFEST.MF）

每个编译的 JAR 文件都包含 `META-INF/MANIFEST.MF`，自动声明插件类：

```
Manifest-Version: 1.0
Plugin-Class: com.neta.widgets.battery.BatteryWidgetPlugin
Plugin-Category: 通用
Plugin-Signer: release-key
Plugin-Certificate-SHA256: ABCD...
```

**优点：**
- ✅ **零配置**：构建时自动扫描源代码，无需手动维护
- ✅ **自描述**：JAR 文件知道自己的入口类
- ✅ **自适应**：重命名或移动类时无需修改构建脚本
- ✅ **标准化**：遵循 Java JAR 规范
- ✅ **防出错**：不需要猜测类名或传递参数

加载器（`WidgetLoader`）会自动读取 `Plugin-Class` 并加载对应的类，无需外部提供类名。

### 信任元数据占位字段

- `category`、`signer` 与 `certificateSha256` 会沿着 `WidgetPluginMetadata -> MANIFEST.MF -> WidgetLoader/VersionCompatibilityChecker` 链路透传。
- `signer` 与 `certificateSha256` 目前仍是可选占位字段，用于为后续证书链校验预留 manifest/API 契约。
- 构建脚本会在声明这些字段时，把它们写入 `Plugin-Signer` 与 `Plugin-Certificate-SHA256`。
- 未声明时不会阻断现有插件，主应用仍按当前的 SHA256 或 TOFU 迁移策略处理。
- 示例插件与文档当前统一要求 `minAppVersion = "2.1.7"`。

### 依赖管理

```kotlin
dependencies {
    // 使用 compileOnly - 运行时使用主应用的 ClassLoader
    compileOnly("androidx.compose.ui:ui:1.6.1")
    compileOnly("androidx.compose.material3:material3:1.2.0")
    // ... 其他依赖
}
```

**为什么使用 `compileOnly`？**

- 避免重复打包依赖到 JAR
- 减小 JAR 文件体积
- 运行时使用主应用提供的库

### 自动注入机制

`WidgetSpec` 构造函数会自动检测并注入 `scale` 和 `alpha` 参数：

```kotlin
// 你的定义
paramSchema = listOf(
    WidgetParamDesc("batteryLevel", "电量(%)", ...)
)

// 实际注册的 paramSchema（自动注入）
paramSchema = listOf(
    WidgetParamDesc("scale", "缩放", WidgetParamType.SCALE, 1f),      // 自动添加
    WidgetParamDesc("alpha", "透明度", WidgetParamType.ALPHA, 1f),    // 自动添加
    WidgetParamDesc("batteryLevel", "电量(%)", ...)                   // 你的参数
)
```

### 使用注入的参数

```kotlin
@Composable
fun SimpleBatteryWidgetContent(config: WidgetConfig) {
    // 读取自动注入的参数
    val scale = (config.params["scale"] as? Number)?.toFloat() ?: 1f
    val alpha = (config.params["alpha"] as? Number)?.toFloat() ?: 1f

    Box(
        modifier = Modifier
            .size((100 * scale).dp)
            .graphicsLayer(alpha = alpha)
    ) {
        // 小组件内容
    }
}
```

---

## 🚀 开发新的动态小组件

### 快速开始

1. **复制本模块作为模板**
   ```bash
   cp -r plugin-widgets widget-your-name
   ```

2. **修改包名和类名**
    - 修改 `namespace` 为你的包名
    - 重命名 `BatteryWidgetPlugin` 为 `YourWidgetPlugin`
   - 插件类路径会由构建脚本自动检测并写入 MANIFEST.MF

3. **实现你的小组件**
    - 定义 `paramSchema`（只需定义你自己的参数）
    - 实现 `@Composable` 内容函数
    - 应用 scale 和 alpha（它们会自动注入）

4. **编译和测试**
   ```bash
   ./gradlew :widget-your-name:assembleDebug
   ```

### 最佳实践

✅ **DO（推荐做法）：**

- 使用 `compileOnly` 声明依赖
- 在 Composable 中应用 scale 和 alpha
- 提供清晰的参数描述
- 设置合理的默认值
- 遵循命名规范：`{name}_widget`

❌ **DON'T（避免事项）：**

- 不要手动添加 scale 和 alpha 参数（会自动注入）
- 不要使用 `implementation` 打包大型库
- 不要依赖主应用的私有类
- 不要在 JAR 中包含资源文件（目前不支持）

---

## 🐛 故障排除

### JAR 无法加载

**症状：** 日志显示"加载失败"

**可能原因：**

1. **MANIFEST.MF 缺失或错误** - 检查 JAR 是否包含正确的 MANIFEST.MF
2. **插件类不存在** - `Plugin-Class` 指向的类必须存在于 JAR 中
3. 依赖版本不匹配 - 确保使用与主应用相同的库版本
4. 缺少必需参数 - 系统会自动注入 scale 和 alpha

**解决方法：**

```bash
# 查看日志
adb logcat | grep WidgetLoader

# 检查 MANIFEST.MF 内容
unzip -p widget-battery-demo.jar META-INF/MANIFEST.MF

# 检查 JAR 内容
unzip -l widget-battery-demo.jar
```

**检查 MANIFEST.MF：**
```bash
$ unzip -p widget-battery-demo.jar META-INF/MANIFEST.MF
Manifest-Version: 1.0
Plugin-Class: com.neta.widgets.battery.BatteryWidgetPlugin
```

如果 `Plugin-Class` 缺失或错误，重新编译 JAR。

### 编译错误

**症状：** Gradle 构建失败

**常见问题：**

```kotlin
// ❌ 错误：使用了 implementation
dependencies {
    implementation("androidx.compose.ui:ui:1.6.1")  // 会导致 JAR 过大
}

// ✅ 正确：使用 compileOnly
dependencies {
    compileOnly("androidx.compose.ui:ui:1.6.1")
}
```

### 小组件不显示

**检查清单：**

- [ ] JAR 文件是否成功加载？（查看日志）
- [ ] 数据库中是否有记录？（小组件管理页面）
- [ ] 参数配置是否正确？（检查 configJson）
- [ ] scale 和 alpha 是否生效？（使用编辑工具测试）

---

## 📚 相关文档

- [主应用 README](../README.md) - 应用整体架构
- [开发指南](./DEVELOPMENT_GUIDE.md) - 详细开发教程
- [Widget API 文档](../docs/widget-api.md) - API 参考

---

## 🤝 贡献

欢迎提交问题和改进建议！

---

## 📄 许可证

与主应用保持一致
