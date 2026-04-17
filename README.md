# 哪吒互联 isulewTools

[![Version](https://img.shields.io/badge/version-1.9.2-blue.svg)](https://github.com/netcookies/isulewTools/releases)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)
[![Android](https://img.shields.io/badge/Android-11%2B-brightgreen.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.1-purple.svg)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-Latest-orange.svg)](https://developer.android.com/jetpack/compose)

> 专为哪吒车机设计的高级工具集，兼容原生Android及其他车机系统。通过无障碍服务实现系统级功能增强，无需Root。

---

## ✨ 核心特性

### 🎯 智能仪表盘系统
- **自定义网格布局** - 灵活的拖拽式仪表盘，支持自由调整位置和大小
- **丰富小组件库** - 信息卡片、图表、网格图标等多种类型小组件
- **可视化编辑** - 实时预览、拖拽排序、一键导入默认配置
- **状态持久化** - 所有布局和配置自动保存，重启后恢复

### 🔌 动态插件系统
- **JAR插件加载** - 支持动态加载第三方小组件插件，无需重新编译主应用
- **开放API** - 完整的Widget API定义，提供插件开发SDK
- **数据源绑定** - 插件可直接访问车辆VHAL属性和自定义数据源
- **即插即用** - 通过MANIFEST.MF自描述机制，插件自动注册和加载
- 👉 [插件开发指南](plugin-widgets/DEVELOPMENT_GUIDE.md)

### 🔧 系统级功能集成
- **智能蓝牙控制** - 基于前台应用自动切换蓝牙，支持自定义规则
- **开机自启动** - 无障碍服务实现系统级自启动和后台常驻
- **按键映射** - 方向盘按键兼容各类音乐APP（网易云、QQ音乐等）
- **语音图标隐藏** - 自动隐藏系统语音助手图标
- **麦克风控制** - 独立麦克风服务，支持精细化权限管理
- **免Root操作** - 所有功能均通过无障碍服务实现，无需刷机

### 📊 实时车辆数据监控
- **悬浮窗显示** - 可移动、可缩放、透明度自适应的车况悬浮窗
- **VHAL属性监控** - 实时读取车辆硬件抽象层数据（电池、速度、温度等）
- **自定义数据源** - 支持公式计算虚拟属性（支持算术、逻辑、比较运算，内置数学函数和类型转换）
- **日志查看** - 内置日志系统，便于故障诊断和数据分析
- **自定义标签** - 为关注的车辆属性添加标签，快速过滤显示

---

## 🚀 快速开始

### 系统要求
- **Android版本**: Android 11 (API 30) 及以上
- **设备类型**: 哪吒车机（优先支持）/ 原生Android / 其他车机系统
- **权限要求**: 无障碍服务、蓝牙、悬浮窗、通知等

### 安装方法
1. 从 [Releases](https://github.com/netcookies/isulewTools/releases) 下载最新APK
2. 在车机或Android设备上安装APK
3. 首次打开应用，根据引导授予所需权限：
   - 无障碍服务（核心功能）
   - 蓝牙权限（自动化控制）
   - 悬浮窗权限（车况显示）
   - 通知权限（状态提示）

### 基础使用
1. **配置蓝牙规则** - 进入"设置"页面，自定义应用包名与蓝牙开关规则
2. **设置仪表盘** - 在"仪表盘"页面添加小组件，拖拽调整布局
3. **启用悬浮窗** - 开启车况悬浮窗，实时监控车辆数据
4. **查看日志** - 在"日志"页面查看系统事件和车辆属性变化
5. **安装插件**（可选）- 导入第三方小组件插件，扩展仪表盘功能

---

## 🛠️ 技术栈

**语言与框架**:
`Kotlin` • `Jetpack Compose` • `Kotlin Coroutines` • `Kotlin Serialization`

**架构与设计**:
`MVVM` • `Repository Pattern` • `Dependency Injection (AppServices)`

**数据存储**:
`Room Database` • `DataStore` • `SharedPreferences`

**系统集成**:
`Accessibility Service` • `Car VHAL (Vehicle HAL)` • `Bluetooth Manager` • `Window Manager`

**构建工具**:
`Gradle 8.9` • `Android Gradle Plugin 8.7` • `Kotlin 2.1`

---

## 🏗️ 项目架构

### 模块结构

| 模块 | 说明 | 类型 |
|------|------|------|
| **app** | 主应用模块，包含UI、业务逻辑和核心服务 | Application |
| **app-privilege** | 系统特权功能模块，处理Root级别操作（可选） | Android Library |
| **stub** | Android系统接口存根，提供隐藏API访问 | Android Library |
| **vendor-stub** | 厂商定制接口存根，适配哪吒车机特有功能 | Android Library |
| **mic-service** | 独立麦克风控制服务，支持细粒度权限管理 | Android Library |
| **widget-api** | 小组件插件API定义，第三方插件开发接口 | Android Library |
| **plugin-widgets** | 动态加载小组件插件示例（JAR插件） | Android Library |

### 架构设计

本项目采用 **MVVM架构** 和 **模块化设计**：

```
┌─────────────────────────────────────────┐
│           UI Layer (Compose)            │
│  ┌──────────┐  ┌──────────┐  ┌────────┐ │
│  │ HomePage │  │ Settings │  │Widgets │ │
│  └──────────┘  └──────────┘  └────────┘ │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│         ViewModel Layer                 │
│  ┌────────────────┐  ┌────────────────┐ │
│  │ HomeViewModel  │  │WidgetViewModel │ │
│  └────────────────┘  └────────────────┘ │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│    Business Logic (AppServices)         │
│  ┌──────────┐  ┌──────────┐  ┌────────┐ │
│  │Bluetooth │  │  VHAL    │  │Widget  │ │
│  │ Manager  │  │ Service  │  │Loader  │ │
│  └──────────┘  └──────────┘  └────────┘ │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│      Data Layer (Repository)            │
│  ┌──────────┐  ┌──────────┐  ┌────────┐ │
│  │   Room   │  │DataStore │  │  VHAL  │ │
│  │ Database │  │ Prefs    │  │  API   │ │
│  └──────────┘  └──────────┘  └────────┘ │
└─────────────────────────────────────────┘
```

**核心设计原则**:
- ViewModel命名规范：Page级用 `XXXViewModel`，Tab级用 `XXXTabViewModel`
- 使用 `AppServices` 进行轻量级依赖管理，避免过度抽象
- UI与ViewModel一对一映射，保持清晰的责任边界
- 所有UI间距使用 `AppSpacing` 统一管理，符合Material Design 3规范

详细架构文档请参考：👉 [ARCHITECTURE.md](docs/ARCHITECTURE.md)（待创建）

---

## 👨‍💻 开发指南

### 对于用户

#### 常见问题
- **Q: 为什么需要无障碍服务权限？**
  A: 无障碍服务是实现蓝牙自动化、按键映射等系统级功能的核心，应用不会获取敏感信息。

- **Q: 应用会影响车机性能吗？**
  A: 应用采用高效的后台服务设计，仅在必要时工作，对车机性能影响极小。

- **Q: 支持哪些车型？**
  A: 优先支持哪吒系列车型，兼容原生Android 11+设备，其他车机需测试验证。

- **Q: 如何备份我的配置？**
  A: 在"设置 > 高级设置"中可导出配置，重装后可恢复。

#### 功能使用技巧
- 仪表盘小组件长按可进入编辑模式
- 悬浮窗双指缩放调整大小，单指拖动调整位置
- 自定义数据源支持丰富的公式计算：算术运算、逻辑判断、条件分支、数学函数(abs/max/min/sqrt/round等)
  、类型转换(int/float/str/bool)
- 主题管理功能可在"设置 > 高级设置"中找到

### 对于开发者

#### 环境准备
```bash
# 克隆仓库
git clone --recurse-submodules https://github.com/netcookies/isulewTools.git
cd isulewTools

# 构建项目
./gradlew assembleDebug

# 安装到设备
./gradlew installDebug
```

#### CBridge 模拟器回放

本地模拟 OEM WebSocket 回放与 `adb forward tcp:17880 tcp:17880` 联调说明见：

- [scripts/cbridge/README.md](scripts/cbridge/README.md)

#### Voice / llama.cpp

当前本地 LLM 运行时位于 `ai-llm` 模块，通过上游 `llama.cpp` submodule 和本地 JNI 桥接层做本地推理；旧的 `whisper.cpp` 链路已经移除，不再参与当前 Android 构建。

当前约定：
- `ai-llm/llama.cpp/` 是上游 `llama.cpp` submodule，作为 `ai-llm` 模块内的 native 构建源码根。
- `ai-llm/src/main/cpp/llama_jni.cpp` 与 `ai-llm/src/main/kotlin/com/neta/isulewtools/ai/llm/runtime/LlamaJni.kt` 是项目内维护的 JNI 桥接层。
- 本地 LLM 模型使用 GGUF 目录包，目录中至少需要 1 个 `.gguf` 文件。
- 公共模型目录以 [`/Users/isulewli/Projects/neta-connect/models/index.json`](/Users/isulewli/Projects/neta-connect/models/index.json) 为准。

构建建议：
- 首次克隆后执行 `git submodule update --init --recursive`，确保 `ai-llm/llama.cpp` 已就绪。
- 常规开发直接执行 `./gradlew assembleDebug` 即可，当前 `llama.cpp` native 构建入口位于 `ai-llm` 模块。

提交约定：
- 建议提交 `ai-llm/llama.cpp` 的 submodule 指针、`ai-llm/src/main/cpp/llama_jni.cpp`、`ai-llm/src/main/kotlin/com/neta/isulewtools/ai/llm/runtime/LlamaJni.kt` 以及必要的构建适配代码。
- 不要提交本地缓存、工具链或临时产物目录。

**开发环境要求**:
- Android Studio Ladybug (2024.2.1) 或更高版本
- JDK 11 或更高版本
- Android SDK 36 (compileSdk 36, targetSdk 36)
- Gradle 8.9+

#### 插件开发

本项目支持动态加载JAR插件扩展小组件功能，开发者可以：

1. 使用 `widget-api` 模块提供的接口定义插件
2. 实现 `AppWidget` 接口，编写自定义Compose UI
3. 配置 `MANIFEST.MF` 自描述元数据
4. 编译为JAR文件并导入到应用

**快速入门**:
```kotlin
class MyCustomWidget : AppWidget {
    override val widgetId: String = "my.custom.widget"
    override val displayName: String = "我的自定义小组件"

    @Composable
    override fun WidgetContent(
        modifier: Modifier,
        scale: Float,
        alpha: Float,
        params: Map<String, Any?>
    ) {
        // 使用Compose编写UI
        Text("Hello Widget!")
    }
}
```

详细插件开发指南：
- 👉 [插件系统概述](plugin-widgets/README.md)
- 👉 [插件开发详细指南](plugin-widgets/DEVELOPMENT_GUIDE.md)

#### 参与贡献

欢迎任何形式的贡献！Fork本仓库并提交Pull Request。

**Git Commit规范** (遵循 [Conventional Commits](https://www.conventionalcommits.org/)):

- `feat:` - 新功能 (对应语义化版本的 MINOR)
- `fix:` - Bug修复 (对应语义化版本的 PATCH)
- `refactor:` - 代码重构（不改变功能逻辑）
- `perf:` - 性能优化
- `style:` - 代码格式调整（缩进、空格等）
- `docs:` - 文档更新
- `test:` - 测试用例修改
- `build:` - 构建系统或依赖项修改
- `ci:` - CI/CD配置修改
- `chore:` - 其他非业务代码修改
- `BREAKING CHANGE:` - 破坏性变更 (对应语义化版本的 MAJOR)

**示例**:
```bash
git commit -m "feat: 添加主题管理功能"
git commit -m "fix: 修复仪表盘缩放变形问题"
git commit -m "refactor(viewmodel): 统一ViewModel命名规范"
```

提交后，自动化工具会根据commit生成变更日志和Release内容。

---

## 📚 文档索引

| 文档 | 说明 |
|------|------|
| [CHANGELOG.md](CHANGELOG.md) | 版本更新日志 |
| [BREAKING_NOTICE.md](BREAKING_NOTICE.md) | 版本 Breaking Change 公告能力说明 |
| [plugin-widgets/README.md](plugin-widgets/README.md) | 小组件插件系统说明 |
| [plugin-widgets/DEVELOPMENT_GUIDE.md](plugin-widgets/DEVELOPMENT_GUIDE.md) | 插件开发详细指南 |

*更多文档正在完善中...*

---

## 🎯 版本亮点 (v1.9.2)

本版本进行了**大规模架构重构**，主要更新包括：

### 核心重构
- **仪表盘系统完全重写** - 网格布局、拖拽排序、状态持久化全面优化
- **小组件生态增强** - 新增信息卡片、网格图标、图表类小组件
- **UI/UX统一化** - 所有一级页面重构，统一间距系统（AppSpacing）
- **ViewModel规范化** - 明确命名规范，清晰UI与ViewModel对应关系

### 新增功能
- 自定义数据源支持公式计算（虚拟属性）
- 主题管理功能（设置 > 高级设置）
- 仪表盘默认导入功能（空仪表盘时）
- 小组件备份与恢复管理

### 性能优化
- 优化小组件边距和布局算法
- 减少不必要的重组，提升Compose性能
- 优化车辆属性读取效率

详细更新内容请查看 [Releases](https://github.com/netcookies/isulewTools/releases)

---

## 📄 许可证

本项目采用 [MIT License](LICENSE) 开源协议。

---

## 🙏 致谢

感谢所有为本项目做出贡献的开发者和用户！

---

<div align="center">

**Made with ❤️ for 哪吒车主社区**

[⬆ 回到顶部](#哪吒互联-isulewtools)

</div>
