# 插件开发指南

本指南介绍如何为哪吒互联开发自定义小组件插件。

---

## 📖 概述

哪吒互联支持通过 JAR 插件动态加载自定义小组件，无需修改主应用代码。插件系统具有以下特点：

- **运行时加载** - 无需重新编译主应用
- **热更新** - 替换 JAR 文件后重启即可更新
- **开放 API** - 完整的Widget API定义
- **数据源绑定** - 直接访问车辆VHAL属性

---

## 🚀 快速开始

### 前置要求

**知识储备**:
- ✅ Kotlin 基础语法
- ✅ Jetpack Compose 基础
- ✅ Android 模块化开发
- ✅ Gradle 构建系统

**开发环境**:
- Android Studio Hedgehog 或更新版本
- JDK 11 或更高
- Android SDK 30+

### 5分钟创建第一个插件

1. **fork 或 直接git clone**

```bash
git clone https://github.com/netcookies/neta-connect
```

2. **开发文档和demo: 可以让AI学习，也可以自己阅读

```bash
demo/plugin-widgets // 小组件商店里发布的官方小组件的源码
demo/widget-api // 哪吒互联的小组件API，所有能用的API都在这，文档不一定最新，但是这一定是最新的
```

3. **实现插件**

创建 `MyWidgetPlugin.kt`:

```kotlin
class MyWidgetPlugin : WidgetPlugin {
    override fun getSpec(): WidgetSpec {
        return MyWidgetSpec
    }

    override fun getMetadata(): WidgetPluginMetadata {
        return WidgetPluginMetadata(
            id = "my-widget",
            version = "1.0.0",
            author = "你的名字",
            description = "我的自定义小组件",
            minAppVersion = "1.7.9"
        )
    }
}
```

4. **定义小组件**

创建 `MyWidget.kt`:

```kotlin
object MyWidgetSpec : WidgetSpec(
    type = "my_widget",
    displayName = "我的小组件",
    paramSchema = listOf(
        WidgetParamDesc(
            key = "title",
            label = "标题",
            type = WidgetParamType.STRING,
            defaultValue = "Hello"
        )
    ),
    contentComposable = { MyWidgetContent(it) },
    color = Color(0xFF2196F3),
    icon = Icons.Default.Star
)

@Composable
fun MyWidgetContent(config: WidgetConfig) {
    val scale = (config.params["scale"] as? Number)?.toFloat() ?: 1f
    val alpha = (config.params["alpha"] as? Number)?.toFloat() ?: 1f
    val title = config.params["title"]?.toString() ?: "Hello"
    
    Box(
        modifier = Modifier
            .size((100 * scale).dp)
            .graphicsLayer(alpha = alpha),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, fontSize = (16 * scale).sp)
    }
}
```

5. **编译和测试**

```bash
# 编译 JAR
./gradlew :widget-mywidget:assembleDebug

# 推送到设备
adb push widget-mywidget/build/outputs/widget/debug/my-widget.jar \
    /sdcard/Download/neta_connect/

# 重启应用加载插件
```

---

## 🔌 核心概念

### WidgetPlugin 接口

插件入口类，提供小组件规格和元数据：

```kotlin
interface WidgetPlugin {
    fun getSpec(): WidgetSpec          // 返回小组件规格
    fun getMetadata(): WidgetPluginMetadata  // 返回元数据
}
```

### WidgetSpec 规格定义

定义小组件的类型、参数和渲染逻辑：

| 字段 | 说明 | 示例 |
|------|------|------|
| `type` | 小组件类型标识 | `"my_widget"` |
| `displayName` | 显示名称 | `"我的小组件"` |
| `paramSchema` | 参数定义列表 | 包含标题、颜色等参数 |
| `contentComposable` | Compose渲染函数 | `{ MyWidgetContent(it) }` |
| `color` | 主题色 | `Color(0xFF2196F3)` |
| `icon` | 图标 | `Icons.Default.Star` |

### WidgetParamDesc 参数定义

定义小组件的可配置参数：

```kotlin
WidgetParamDesc(
    key = "title",              // 参数键
    label = "标题",             // 显示标签
    type = WidgetParamType.STRING,  // 参数类型
    defaultValue = "Hello",     // 默认值
    description = "显示的标题文字"  // 描述（可选）
)
```

**支持的参数类型**:
- `STRING` - 文本
- `INT` - 整数
- `FLOAT` - 浮点数
- `BOOL` - 布尔值
- `COLOR` - 颜色选择器
- `DATA_SOURCE` - 数据源绑定
- `ENUM` - 枚举选项

### 自动注入参数

所有小组件自动获得 `scale` 和 `alpha` 参数：

- **scale**: 缩放比例（1.0 = 100%）
- **alpha**: 透明度（0.0-1.0）

**无需手动定义**，系统自动注入：

```kotlin
// ✅ 正确 - 不定义 scale 和 alpha
paramSchema = listOf(
    WidgetParamDesc("title", "标题", WidgetParamType.STRING, "Hello")
    // scale 和 alpha 自动添加
)

// ❌ 错误 - 不要手动定义
paramSchema = listOf(
    WidgetParamDesc("scale", ...), // 会被自动覆盖
    WidgetParamDesc("alpha", ...), // 会被自动覆盖
    WidgetParamDesc("title", ...)
)
```

---

## 🎨 开发实践

### 读取参数

```kotlin
@Composable
fun MyWidgetContent(config: WidgetConfig) {
    // 读取自动注入的参数
    val scale = (config.params["scale"] as? Number)?.toFloat() ?: 1f
    val alpha = (config.params["alpha"] as? Number)?.toFloat() ?: 1f
    
    // 读取自定义参数
    val title = config.params["title"]?.toString() ?: "Default"
    val color = try {
        val colorStr = config.params["color"]?.toString() ?: "#2196F3"
        Color(android.graphics.Color.parseColor(colorStr))
    } catch (e: Exception) {
        Color(0xFF2196F3)
    }
    
    // 渲染 UI
}
```

### 应用缩放和透明度

```kotlin
Box(
    modifier = Modifier
        .size((100 * scale).dp)        // 应用缩放
        .graphicsLayer(alpha = alpha)  // 应用透明度
) {
    Text(
        text = title,
        fontSize = (16 * scale).sp      // 字体也要缩放
    )
}
```

### 数据源绑定

如果小组件需要访问车辆数据：

```kotlin
// 1. 定义数据源参数
paramSchema = listOf(
    WidgetParamDesc(
        key = "datasource",
        label = "数据源",
        type = WidgetParamType.DATA_SOURCE,
        defaultValue = null,
        required = true
    )
)

// 2. 读取数据源值
@Composable
fun MyWidgetContent(config: WidgetConfig) {
    val dataValue = remember(config) {
        config.params["datasource"]?.let {
            // 从数据源获取值
            (it as? Float) ?: 0f
        } ?: 0f
    }
    
    Text("数据: $dataValue")
}
```

---

## 📦 构建和发布

### 编译 JAR

```bash
# 清理并编译
./gradlew :widget-mywidget:clean :widget-mywidget:assembleDebug

# 查找生成的 JAR
ls -lh widget-mywidget/build/outputs/widget/debug/
```

### 本地测试

```bash
# 创建测试目录
adb shell mkdir -p /sdcard/Download/neta_connect

# 推送 JAR
adb push widget-mywidget/build/outputs/widget/debug/my-widget.jar \
    /sdcard/Download/neta_connect/

# 查看日志
adb logcat | grep -E "WidgetLoader|WidgetManager"
```

### 发布到小组件商店

详细发布流程请参考 [widgets/README.md](https://github.com/netcookies/neta-connect/blob/main/widgets/README.md)

简要步骤：

1. **编译 Release 版本**

```bash
./gradlew :widget-mywidget:assembleRelease
```

2. **准备文件**

```bash
# 创建版本目录
mkdir -p widgets/my-widget/1.0.0

# 复制 JAR
cp widget-mywidget/build/outputs/widget/release/my-widget.jar \
   widgets/my-widget/1.0.0/
```

3. **更新 index.json**

在 `widgets/index.json` 中添加小组件信息。

4. **提交到仓库**

```bash
git add widgets/
git commit -m "feat: 添加 my-widget v1.0.0"
git push
```

---

## 📚 完整开发指南

本文档是快速入门版本，完整的开发指南请参考：

- **详细开发指南**: [demo/plugin-widgets/DEVELOPMENT_GUIDE.md](https://github.com/netcookies/neta-connect/blob/main/demo/plugin-widgets/DEVELOPMENT_GUIDE.md)
- **插件系统说明**: [plugin-widgets/README.md](https://github.com/netcookies/neta-connect/blob/main/demo/plugin-widgets/README.md)
- **小组件仓库**: [widgets/README.md](https://github.com/netcookies/neta-connect/blob/main/widgets/README.md)

完整指南包含：
- 高级参数类型（ENUM、COLOR等）
- 数据源架构详解
- 性能优化技巧
- 错误处理最佳实践
- 响应式布局
- MANIFEST.MF 自动生成
- 故障排查

---

## 🔧 常见问题

### Q: JAR 无法加载？

**A**: 检查以下几点：
1. JAR 文件路径是否正确（`/sdcard/Download/neta_connect/`）
2. 查看日志确认加载状态
3. 确保插件类实现了 `WidgetPlugin` 接口
4. 检查依赖版本是否匹配

### Q: 小组件不显示？

**A**: 
1. 确认 JAR 已成功加载（查看日志）
2. 检查 `scale` 和 `alpha` 是否正确应用
3. 确认 Composable 函数没有抛出异常
4. 使用 `@Preview` 在 Android Studio 中预览

### Q: 如何调试插件？

**A**:
```kotlin
@Composable
fun MyWidgetContent(config: WidgetConfig) {
    println("Widget params: ${config.params}")
    println("Scale: ${config.params["scale"]}")
    
    // 渲染内容
}
```

### Q: 可以使用哪些 Android API？

**A**: 
- ✅ Jetpack Compose UI
- ✅ Kotlin 标准库
- ✅ Android 基础类（Color, Canvas 等）
- ❌ 资源文件（R.drawable, R.string）
- ❌ 主应用的私有类

---

## 📞 获取帮助

如果遇到开发问题：

- **GitHub Issues**: [提交问题](https://github.com/netcookies/neta-connect/issues)
- **完整文档**: [DEVELOPMENT_GUIDE.md](https://github.com/netcookies/neta-connect/blob/main/demo/plugin-widgets/DEVELOPMENT_GUIDE.md)
- **示例代码**: [plugin-widgets](https://github.com/netcookies/neta-connect/tree/main/demo/plugin-widgets)

---

祝你开发愉快！🎉
