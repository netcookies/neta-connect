import releaseData from '../src/data/release-data.generated.json';

# 哪吒互联 isulewTools

> 专为哪吒车机设计的高级工具集，兼容原生Android及其他车机系统。通过无障碍服务实现系统级功能增强，无需Root。

## 项目简介

哪吒互联是一个功能强大的车机增强工具，通过先进的无障碍服务技术，为哪吒汽车车主提供智能化的车机使用体验。应用采用现代化的 Android 开发技术栈，支持动态插件系统和自定义仪表盘。

## 核心亮点

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
- **自定义数据源** - 支持公式计算虚拟属性（如：功率 = 电压 × 电流 ÷ 1000）
- **日志查看** - 内置日志系统，便于故障诊断和数据分析
- **自定义标签** - 为关注的车辆属性添加标签，快速过滤显示

## 技术栈

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

## 系统要求

- **Android版本**: Android 11 (API 30) 及以上
- **设备类型**: 哪吒车机（优先支持）/ 原生Android / 其他车机系统
- **权限要求**: 无障碍服务、蓝牙、悬浮窗、通知等

## 版本信息

**当前版本**: {releaseData.version}

本版本进行了大规模架构重构，包括：
- 仪表盘系统完全重写
- 小组件生态增强
- UI/UX统一化
- ViewModel规范化

详细更新内容请查看 [GitHub Releases](https://github.com/netcookies/neta-connect/releases)

## 开源协议

本项目采用 [MIT License](https://github.com/netcookies/neta-connect/blob/main/LICENSE) 开源协议。

## 相关链接

- **项目仓库**: [netcookies/neta-connect](https://github.com/netcookies/neta-connect)
- **问题反馈**: [GitHub Issues](https://github.com/netcookies/neta-connect/issues)
- **官方文档**: [https://neta.nznd.org](https://neta.nznd.org)
