# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html) and to [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).

## v1.8.9 (2025-11-12)

### Feature
- 小组件颜色可配置白天黑夜不同颜色 [`a635717`](https://github.com/netcookies/isulewTools/commit/a635717)
- 添加小组件禁用功能（总是显示｜按需显示｜禁止显示） [`ad64368`](https://github.com/netcookies/isulewTools/commit/ad64368)
- 添加本地加载功能，供开发者测试用 [`6310e1f`](https://github.com/netcookies/isulewTools/commit/6310e1f)
- 添加小组件最低版本要求检查 [`0bd4587`](https://github.com/netcookies/isulewTools/commit/0bd4587)
- 小组件api支持自定义字体加载 [`c15fda7`](https://github.com/netcookies/isulewTools/commit/c15fda7)
- 添加高级设置 - 管理权限功能，用于管理本 APP 权限 [`aa00cc3`](https://github.com/netcookies/isulewTools/commit/aa00cc3)
- 智驾悬浮窗和小组件统一采用json备份在Download/neta_connect目录下，卸载程序不会清空 [`4dea75e`](https://github.com/netcookies/isulewTools/commit/4dea75e)

### Bug Fixes
- 修改保存小组件会正确更新属性引用 [`89efe79`](https://github.com/netcookies/isulewTools/commit/89efe79)
- 修复属性订阅类型转换问题（胎压胎温无法显示） [`60552ea`](https://github.com/netcookies/isulewTools/commit/60552ea)
- ci脚本环境变量错误 [`c1d1c13`](https://github.com/netcookies/isulewTools/commit/c1d1c13)

### Performance Improvements
- 小组件的基础设置也参与滚动 [`a859f34`](https://github.com/netcookies/isulewTools/commit/a859f34)
- 优化import [`59ca9ad`](https://github.com/netcookies/isulewTools/commit/59ca9ad)
- 优化import [`8f5f693`](https://github.com/netcookies/isulewTools/commit/8f5f693)
- 下电太久后，尝试恢复特权服务 [`04daabd`](https://github.com/netcookies/isulewTools/commit/04daabd)

### Build
- update ci [`b29e5aa`](https://github.com/netcookies/isulewTools/commit/b29e5aa)
- update ci [`ca56860`](https://github.com/netcookies/isulewTools/commit/ca56860)

### Refactor
- 小组件架构优化，更简洁 [`7b6914d`](https://github.com/netcookies/isulewTools/commit/7b6914d)
- 迁移弧形仪表盘、电池、进度条、温度剂至小组件商店 [`a7a2539`](https://github.com/netcookies/isulewTools/commit/a7a2539)
- 重构智驾悬浮窗的配置保存与载入功能，卸载程序不会清空配置了。保存目录：Download/neta_connect [`d45481f`](https://github.com/netcookies/isulewTools/commit/d45481f)

### Other
- BUMP VERSION [`6cd4c29`](https://github.com/netcookies/isulewTools/commit/6cd4c29)

## v1.8.8 (2025-11-05)

### Feature
- 添加备份和载入小组件配置的功能 [`2a8ede3`](https://github.com/netcookies/isulewTools/commit/2a8ede3)
- 添加备份和载入小组件配置的功能 [`9236345`](https://github.com/netcookies/isulewTools/commit/9236345)
- 辅助编辑工具增加步进调整按钮，移动更加精细。辅助编辑器状态不再持久化 [`c76aa2f`](https://github.com/netcookies/isulewTools/commit/c76aa2f)

### Bug Fixes
- 辅助编辑工具不再强制显示通知栏 [`de9ebd7`](https://github.com/netcookies/isulewTools/commit/de9ebd7)
- 修复胎压换算问题 [`dabdfda`](https://github.com/netcookies/isulewTools/commit/dabdfda)

## v1.8.7 (2025-11-05)

### Feature
- 数据源支持注入，并提供各种类型的便捷函数简化插件编写 [`fcb319e`](https://github.com/netcookies/isulewTools/commit/fcb319e)

### Build
- 测试构建脚本 [`257458c`](https://github.com/netcookies/isulewTools/commit/257458c)
- 发布小组件 [`081d9ed`](https://github.com/netcookies/isulewTools/commit/081d9ed)

## v1.8.6 (2025-11-05)

### Feature
- 合并速度指示器、油表指示器为弧形仪表盘 [`51b3883`](https://github.com/netcookies/isulewTools/commit/51b3883)

### Bug Fixes
- 修复车辆属性 areaId 只有 0的问题 [`bf1a84b`](https://github.com/netcookies/isulewTools/commit/bf1a84b)

### Performance Improvements
- 完善弧形仪表盘等组件 [`1c67941`](https://github.com/netcookies/isulewTools/commit/1c67941)
- 完善刻度表现 [`ff296d0`](https://github.com/netcookies/isulewTools/commit/ff296d0)
- 电池默认颜色白色还是更好看 [`613c92c`](https://github.com/netcookies/isulewTools/commit/613c92c)

### Refactor
- 重构油量小组件 [`55ef7cc`](https://github.com/netcookies/isulewTools/commit/55ef7cc)
- 重构时间小组件 [`d5f3f15`](https://github.com/netcookies/isulewTools/commit/d5f3f15)

## v1.8.5 (2025-11-04)

### Feature
- 采用新的 color picker [`8b02423`](https://github.com/netcookies/isulewTools/commit/8b02423)

### Bug Fixes
- 修复颜色解析错误 [`6881916`](https://github.com/netcookies/isulewTools/commit/6881916)
- 添加mic jni 文件到.gitignore [`f078eaf`](https://github.com/netcookies/isulewTools/commit/f078eaf)

### Performance Improvements
- 优化电池配色 [`a089381`](https://github.com/netcookies/isulewTools/commit/a089381)
- 完善字体和图标大小 [`28bc327`](https://github.com/netcookies/isulewTools/commit/28bc327)

### Test
- 升级动态小组件示例 [`786ab90`](https://github.com/netcookies/isulewTools/commit/786ab90)
- 测试小组件升级版本，编译脚本自带需要的图标 [`77e9eef`](https://github.com/netcookies/isulewTools/commit/77e9eef)

### Build
- 减少包大小，优化Compose混淆规则，插件的 icons 由其 jar 包自己持有 [`49637da`](https://github.com/netcookies/isulewTools/commit/49637da)
- 减少包大小，优化Compose混淆规则，插件的 icons 由其 jar 包自己持有 [`07feb61`](https://github.com/netcookies/isulewTools/commit/07feb61)
- 移除ARM 和 X86的 JNI 支持，减少包的大小 [`95af655`](https://github.com/netcookies/isulewTools/commit/95af655)
- update depends [`7a569a3`](https://github.com/netcookies/isulewTools/commit/7a569a3)

### Refactor
- 所有小组件默认值统一管理，更好配置 [`82b6272`](https://github.com/netcookies/isulewTools/commit/82b6272)
- 重构电池小组件 [`cdd7f6c`](https://github.com/netcookies/isulewTools/commit/cdd7f6c)
- 重构按钮小组件 [`321d2ef`](https://github.com/netcookies/isulewTools/commit/321d2ef)

## v1.8.4 (2025-11-03)

### Feature
- 添加 vhal 属性的配置类型 [`1fcbba4`](https://github.com/netcookies/isulewTools/commit/1fcbba4)

### Bug Fixes
- 按钮可正常缩放 [`926daa4`](https://github.com/netcookies/isulewTools/commit/926daa4)
- 辅助编辑工具选择小组件切换时，高亮效果能正常切换 [`51b5e00`](https://github.com/netcookies/isulewTools/commit/51b5e00)
- 辅助编辑工具无法选中开关和按钮的问题 [`3e94492`](https://github.com/netcookies/isulewTools/commit/3e94492)
- 修复开关和按钮无法拖动的问题 [`2f90d42`](https://github.com/netcookies/isulewTools/commit/2f90d42)

### Performance Improvements
- 完善车辆属性 id 的选择 [`b46f3cb`](https://github.com/netcookies/isulewTools/commit/b46f3cb)
- 点击保存支持自动滚动到错误提示 [`6ec8b2b`](https://github.com/netcookies/isulewTools/commit/6ec8b2b)
- 确认INT\FLOAT可以正确转换 [`d769923`](https://github.com/netcookies/isulewTools/commit/d769923)
- 属性支持根据其他属性动态显示，如方形时才显示边角弧度 [`7841577`](https://github.com/netcookies/isulewTools/commit/7841577)
- 添加图标选择器 [`bf08465`](https://github.com/netcookies/isulewTools/commit/bf08465)
- 提供图标列表 [`a96e54d`](https://github.com/netcookies/isulewTools/commit/a96e54d)
- 优化button [`87ac5a9`](https://github.com/netcookies/isulewTools/commit/87ac5a9)
- 小组件创建和编辑空间布局调整 [`3e3e1b4`](https://github.com/netcookies/isulewTools/commit/3e3e1b4)
- 小组件创建和编辑节目标题更醒目 [`09db576`](https://github.com/netcookies/isulewTools/commit/09db576)
- 将getAlpha()和getScale()移至widget-api [`ffd22b2`](https://github.com/netcookies/isulewTools/commit/ffd22b2)
- 优化SegmentedButtonRow视觉表现 [`4e70065`](https://github.com/netcookies/isulewTools/commit/4e70065)

## v1.8.3 (2025-10-31)

### Bug Fixes
- 修复混淆导致的依赖缺失问题 [`e55bc36`](https://github.com/netcookies/isulewTools/commit/e55bc36)
- 预览编译错误 [`defe90e`](https://github.com/netcookies/isulewTools/commit/defe90e)

### Performance Improvements
- 换一种判断模拟器的方式 [`29aa9fc`](https://github.com/netcookies/isulewTools/commit/29aa9fc)
- 优化小组件架构，减少插件 jar 包大小 [`5c51914`](https://github.com/netcookies/isulewTools/commit/5c51914)

### Test
- 升级示例小程序的版本 [`41e1dbd`](https://github.com/netcookies/isulewTools/commit/41e1dbd)

### Build
- 优化编译配置 [`54db09e`](https://github.com/netcookies/isulewTools/commit/54db09e)

### Other
- plugin: 显示指定插件默认参数 [`ad58d72`](https://github.com/netcookies/isulewTools/commit/ad58d72)

## v1.8.2 (2025-10-28)

### Feature
- 示例插件添加预览示例 [`dfcc8e2`](https://github.com/netcookies/isulewTools/commit/dfcc8e2)

### Bug Fixes
- 修复ci脚本编译错误 [`caf1d83`](https://github.com/netcookies/isulewTools/commit/caf1d83)

### Performance Improvements
- 更新示例小组件版本 [`57032d1`](https://github.com/netcookies/isulewTools/commit/57032d1)
- 电池字体完善 [`3f51644`](https://github.com/netcookies/isulewTools/commit/3f51644)

### Other
- doc: 文档更新 [`bc153f2`](https://github.com/netcookies/isulewTools/commit/bc153f2)

## v1.8.1 (2025-10-27)

### Bug Fixes
- 修复应用商店插件无法安装问题 [`e7ae14a`](https://github.com/netcookies/isulewTools/commit/e7ae14a)
- 修复应用商店插件无法安装问题 [`baa708a`](https://github.com/netcookies/isulewTools/commit/baa708a)
- 小组件自动化发布脚本错误 [`980b8cc`](https://github.com/netcookies/isulewTools/commit/980b8cc)

### Build
- 更新混淆规则 [`aef910c`](https://github.com/netcookies/isulewTools/commit/aef910c)

## v1.8.0 (2025-10-27)

### Feature
- 完善小组件发布流程 [`cd3a036`](https://github.com/netcookies/isulewTools/commit/cd3a036)
- 透明度、缩放自动注入 [`b245231`](https://github.com/netcookies/isulewTools/commit/b245231)
- 添加高级功能 - 日志状态查看功能 [`d6deec5`](https://github.com/netcookies/isulewTools/commit/d6deec5)
- 新增辅助小工具 [`864ca90`](https://github.com/netcookies/isulewTools/commit/864ca90)
- 新增辅助小工具 [`bdb0d1e`](https://github.com/netcookies/isulewTools/commit/bdb0d1e)
- 完成小组件商店功能 [`f426b06`](https://github.com/netcookies/isulewTools/commit/f426b06)
- 小组件商店: 业务逻辑与测试UI完成 [`7301eec`](https://github.com/netcookies/isulewTools/commit/7301eec)
- 小组件商店: 业务逻辑与测试UI完成 [`eefd08f`](https://github.com/netcookies/isulewTools/commit/eefd08f)
- 音量只能 15 [`3952dc0`](https://github.com/netcookies/isulewTools/commit/3952dc0)
- 添加麦克风 jni 模块 [`e03ff0d`](https://github.com/netcookies/isulewTools/commit/e03ff0d)
- 日志支持暂停和扫码分享 [`ea9567e`](https://github.com/netcookies/isulewTools/commit/ea9567e)
- log统一调用入口 [`900d791`](https://github.com/netcookies/isulewTools/commit/900d791)
- 实现系统级悬浮窗（可覆盖状态栏） [`d168a7a`](https://github.com/netcookies/isulewTools/commit/d168a7a)
- 实现参考线逻辑 [`7548ff8`](https://github.com/netcookies/isulewTools/commit/7548ff8)
- 实现参考线逻辑 [`b69b287`](https://github.com/netcookies/isulewTools/commit/b69b287)
- 添加小组件按需显示功能 [`2786b43`](https://github.com/netcookies/isulewTools/commit/2786b43)
- 高级设置添加结束应用的按钮 [`5629876`](https://github.com/netcookies/isulewTools/commit/5629876)

### Bug Fixes
- 小组件不可以再被拖出仪表盘 [`a49d1f7`](https://github.com/netcookies/isulewTools/commit/a49d1f7)
- 修复按需显示的选择应用弹窗 [`0f57f0c`](https://github.com/netcookies/isulewTools/commit/0f57f0c)
- 仪表盘页面小组件消失 [`90914ec`](https://github.com/netcookies/isulewTools/commit/90914ec)
- 修复缺少的函数参数 [`9d26b59`](https://github.com/netcookies/isulewTools/commit/9d26b59)
- 修复缺少的函数参数 [`631080c`](https://github.com/netcookies/isulewTools/commit/631080c)
- viewModel用到时才初始化 [`627fcdc`](https://github.com/netcookies/isulewTools/commit/627fcdc)
- 档位调节 [`7bf3470`](https://github.com/netcookies/isulewTools/commit/7bf3470)
- 麦克风事件分发 [`7f76abc`](https://github.com/netcookies/isulewTools/commit/7f76abc)
- 对齐麦克风电量事件，移除没必要的轮询 [`4209567`](https://github.com/netcookies/isulewTools/commit/4209567)
- 麦克风状态检测 [`06aff95`](https://github.com/netcookies/isulewTools/commit/06aff95)
- 修复小组件闪烁问题 [`d319cf6`](https://github.com/netcookies/isulewTools/commit/d319cf6)
- add annotation [`2608b77`](https://github.com/netcookies/isulewTools/commit/2608b77)
- 移除掉服务端空实现的代码 [`1fc2790`](https://github.com/netcookies/isulewTools/commit/1fc2790)
- 修复麦克风 aidl 错误 [`6088f18`](https://github.com/netcookies/isulewTools/commit/6088f18)
- 复现雷石麦克风初始化逻辑 [`d88e529`](https://github.com/netcookies/isulewTools/commit/d88e529)
- 修复小组件初始化时序问题 [`a78f134`](https://github.com/netcookies/isulewTools/commit/a78f134)
- 修复按需显示的逻辑 [`de3c94d`](https://github.com/netcookies/isulewTools/commit/de3c94d)
- 修复按需显示包名弹窗列表 [`659d8a6`](https://github.com/netcookies/isulewTools/commit/659d8a6)
- 修复 root 进程日志回调问题 [`1d3e850`](https://github.com/netcookies/isulewTools/commit/1d3e850)

### Performance Improvements
- 优化onSecondary\onError颜色 [`6ec1f8d`](https://github.com/netcookies/isulewTools/commit/6ec1f8d)
- fix redundant package [`a48a243`](https://github.com/netcookies/isulewTools/commit/a48a243)
- TopStatusBar 优化 [`b9aec7f`](https://github.com/netcookies/isulewTools/commit/b9aec7f)
- 修复多余的 padding(视觉上) [`7a527b0`](https://github.com/netcookies/isulewTools/commit/7a527b0)
- 手动触发麦克风系统检查 [`737bd64`](https://github.com/netcookies/isulewTools/commit/737bd64)
- 优化特权日志初始化顺序 [`9d579a7`](https://github.com/netcookies/isulewTools/commit/9d579a7)
- 完善授权检测逻辑 [`8bf03f0`](https://github.com/netcookies/isulewTools/commit/8bf03f0)
- 调整黑夜主题的颜色，使文字更易可见 [`ca31f40`](https://github.com/netcookies/isulewTools/commit/ca31f40)
- 优化按钮布局逻辑 [`720a259`](https://github.com/netcookies/isulewTools/commit/720a259)
- 优化小组件管理页面 UI [`3fd8593`](https://github.com/netcookies/isulewTools/commit/3fd8593)

### Test
- 增加麦克风调试日志 [`c5bc778`](https://github.com/netcookies/isulewTools/commit/c5bc778)
- 初始化检测 [`c9f36cc`](https://github.com/netcookies/isulewTools/commit/c9f36cc)
- 尝试用 root 进程启动loopback [`33a9a4a`](https://github.com/netcookies/isulewTools/commit/33a9a4a)
- 注释掉 AudioTrack/Record 做测试 [`a2bc186`](https://github.com/netcookies/isulewTools/commit/a2bc186)
- 修复mic初始化错误 [`33d7b36`](https://github.com/netcookies/isulewTools/commit/33d7b36)
- 测试 UI 的优化 [`a5c9567`](https://github.com/netcookies/isulewTools/commit/a5c9567)
- 换一种方式绑定麦克风服务 [`80688f3`](https://github.com/netcookies/isulewTools/commit/80688f3)
- 测试混响配置 [`906a20c`](https://github.com/netcookies/isulewTools/commit/906a20c)
- 完善麦克风测试逻辑 [`ae8f653`](https://github.com/netcookies/isulewTools/commit/ae8f653)
- 完善麦克风测试逻辑 [`73cb271`](https://github.com/netcookies/isulewTools/commit/73cb271)
- 完善麦克风测试逻辑 [`89e6df2`](https://github.com/netcookies/isulewTools/commit/89e6df2)

### Build
- 完善小组件发布流程 [`c8a38a7`](https://github.com/netcookies/isulewTools/commit/c8a38a7)
- 移除掉无用的调试步骤 [`314463b`](https://github.com/netcookies/isulewTools/commit/314463b)
- 移除没用的 stub，清理无法使用的功能（系统层级悬浮窗口） [`2aa8b42`](https://github.com/netcookies/isulewTools/commit/2aa8b42)
- 添加 agent 配置 [`551340c`](https://github.com/netcookies/isulewTools/commit/551340c)
- 排除掉无用的文件 [`05ea81c`](https://github.com/netcookies/isulewTools/commit/05ea81c)

### Style
- 小组件页面优化 [`560f115`](https://github.com/netcookies/isulewTools/commit/560f115)
- 对齐上边距 [`ca8133c`](https://github.com/netcookies/isulewTools/commit/ca8133c)

### Refactor
- 重构弹窗 UI [`7872d0b`](https://github.com/netcookies/isulewTools/commit/7872d0b)

### Other
- Merge branch 'main' of https://github.com/netcookies/isulewTools [`e3740bf`](https://github.com/netcookies/isulewTools/commit/e3740bf)
- Merge pull request #8 from netcookies/widget-remote [`863d397`](https://github.com/netcookies/isulewTools/commit/863d397)
- branch init [`e21c899`](https://github.com/netcookies/isulewTools/commit/e21c899)

## v1.7.9 (2025-10-19)

### Feature
- 添加悬浮窗数据源模式切换按钮 [`2195ad5`](https://github.com/netcookies/isulewTools/commit/2195ad5)
- 添加无障碍模式按钮 [`668ed65`](https://github.com/netcookies/isulewTools/commit/668ed65)
- 添加麦克风功能测试 - 实验性功能 [`0bf84e7`](https://github.com/netcookies/isulewTools/commit/0bf84e7)
- 添加雷石麦克风 sdk [`8c45690`](https://github.com/netcookies/isulewTools/commit/8c45690)

### Bug Fixes
- 无障碍跳转问题 [`ffe361f`](https://github.com/netcookies/isulewTools/commit/ffe361f)

### Performance Improvements
- 麦克风调试添加冻结系统应用，避免闪退 [`316c3c4`](https://github.com/netcookies/isulewTools/commit/316c3c4)
- 翻译车辆属性 [`74da707`](https://github.com/netcookies/isulewTools/commit/74da707)

### Test
- 尝试通过冻结，接管麦克风服务 [`b288ecb`](https://github.com/netcookies/isulewTools/commit/b288ecb)
- 调整实验性功能：监测安装包 [`868c997`](https://github.com/netcookies/isulewTools/commit/868c997)

### Refactor
- 梳理特权服务包结构 [`fba108f`](https://github.com/netcookies/isulewTools/commit/fba108f)
- 梳理特权服务包结构 [`bcb0e21`](https://github.com/netcookies/isulewTools/commit/bcb0e21)

## v1.7.8 (2025-10-17)

### Feature
- 修复app_process闪退问题 [`a845717`](https://github.com/netcookies/isulewTools/commit/a845717)
- 实现通知机制提升重启后特权服务的获取速度 [`7805f87`](https://github.com/netcookies/isulewTools/commit/7805f87)
- 实现 JNI 服务端 [`ea871d2`](https://github.com/netcookies/isulewTools/commit/ea871d2)

### Bug Fixes
- 修复无障碍的自动恢复 [`4b42bc1`](https://github.com/netcookies/isulewTools/commit/4b42bc1)

### Other
- fix：确保不要误杀进程 [`1e651c5`](https://github.com/netcookies/isulewTools/commit/1e651c5)

## v1.7.7 (2025-10-17)

### Feature
- 实现 ContainProvider 传递特权服务 [`d36bacc`](https://github.com/netcookies/isulewTools/commit/d36bacc)

### Bug Fixes
- 缓存启动脚本用于手动触发重连 [`5554ef0`](https://github.com/netcookies/isulewTools/commit/5554ef0)
- 重试失败后重置isStarting [`c23dffe`](https://github.com/netcookies/isulewTools/commit/c23dffe)
- 修复悬浮窗数据源切换导致的不断重启订阅问题 [`b68dd74`](https://github.com/netcookies/isulewTools/commit/b68dd74)
- 修复悬浮窗数据源切换错误 [`559ad7e`](https://github.com/netcookies/isulewTools/commit/559ad7e)

### Performance Improvements
- 现在无障碍和特权服务不再阻碍用户进入主页面 [`efe8f4f`](https://github.com/netcookies/isulewTools/commit/efe8f4f)

## v1.7.6 (2025-10-16)

### Bug Fixes
- 修复无障碍服务健康监测 [`a1b3218`](https://github.com/netcookies/isulewTools/commit/a1b3218)
- 修复 adb stream 锁死问题 [`a005a97`](https://github.com/netcookies/isulewTools/commit/a005a97)
- 修复过过早调用日志实例的问题 [`070f606`](https://github.com/netcookies/isulewTools/commit/070f606)

### Performance Improvements
- 完善 adb 重连机制 [`77a1544`](https://github.com/netcookies/isulewTools/commit/77a1544)
- 优化启动检查 [`16235f4`](https://github.com/netcookies/isulewTools/commit/16235f4)
- 优化启动流程 [`5e3173b`](https://github.com/netcookies/isulewTools/commit/5e3173b)

### Refactor
- 重构 adb 模块，放弃import，太坑了 [`44df5b1`](https://github.com/netcookies/isulewTools/commit/44df5b1)

## v1.7.5 (2025-10-14)

### Feature
- 增加高级设置页签，将重启、安装卸载美式、实验性功能迁移到这个页 [`bbea527`](https://github.com/netcookies/isulewTools/commit/bbea527)
- 增加折叠侧边栏按钮 [`29d336c`](https://github.com/netcookies/isulewTools/commit/29d336c)

## v1.7.4 (2025-10-14)

### Feature
- 彻底移除shizuku [`36b86ce`](https://github.com/netcookies/isulewTools/commit/36b86ce)
- vhal 服务适配特权服务 [`45d53b7`](https://github.com/netcookies/isulewTools/commit/45d53b7)
- 特权服务静默安装 [`c943254`](https://github.com/netcookies/isulewTools/commit/c943254)
- 特权服务静默安装 [`b22b59c`](https://github.com/netcookies/isulewTools/commit/b22b59c)
- 特权服务静默安装 [`51d56bc`](https://github.com/netcookies/isulewTools/commit/51d56bc)
- 特权服务静默安装 [`73642ae`](https://github.com/netcookies/isulewTools/commit/73642ae)
- 特权服务静默安装 [`afa01bf`](https://github.com/netcookies/isulewTools/commit/afa01bf)
- 添加IBinder、IInterface包装方法 [`7be908f`](https://github.com/netcookies/isulewTools/commit/7be908f)
- 无障碍自动保活 [`60de296`](https://github.com/netcookies/isulewTools/commit/60de296)
- 自动提权迁移到特权服务 [`8b1c394`](https://github.com/netcookies/isulewTools/commit/8b1c394)
- 开始迁移安装和授权服务 [`8b94751`](https://github.com/netcookies/isulewTools/commit/8b94751)
- 开始迁移 shizuku 服务 [`6ca1d16`](https://github.com/netcookies/isulewTools/commit/6ca1d16)
- 采用 DER 生成密钥 [`dda5971`](https://github.com/netcookies/isulewTools/commit/dda5971)
- 采用 DER 生成密钥 [`dcd2a6f`](https://github.com/netcookies/isulewTools/commit/dcd2a6f)

### Bug Fixes
- 悬浮窗数据源切换错误 [`7422d0b`](https://github.com/netcookies/isulewTools/commit/7422d0b)
- 初始化错误 [`eebd188`](https://github.com/netcookies/isulewTools/commit/eebd188)
- 编译警告 [`755dd38`](https://github.com/netcookies/isulewTools/commit/755dd38)
- 优化订阅数据日志到事件流 [`5f7cfd0`](https://github.com/netcookies/isulewTools/commit/5f7cfd0)
- remoteLogger 单例初始化错误的问题 [`f1e76c3`](https://github.com/netcookies/isulewTools/commit/f1e76c3)
- 修复 vhal 初始化问题 [`a1daee7`](https://github.com/netcookies/isulewTools/commit/a1daee7)
- 日志过高的问题 [`3705312`](https://github.com/netcookies/isulewTools/commit/3705312)
- 悬浮窗补齐参数 [`f48639a`](https://github.com/netcookies/isulewTools/commit/f48639a)
- 修复安装权限问题 [`a4c73e6`](https://github.com/netcookies/isulewTools/commit/a4c73e6)
- manifest.xml [`589eac3`](https://github.com/netcookies/isulewTools/commit/589eac3)
- 修复静默安装方法 [`fd6ba8b`](https://github.com/netcookies/isulewTools/commit/fd6ba8b)
- 修复美式下载链接 [`30b094c`](https://github.com/netcookies/isulewTools/commit/30b094c)
- 修复特权服务重连问题 [`2a9673a`](https://github.com/netcookies/isulewTools/commit/2a9673a)
- 修复特权服务问题 [`b384106`](https://github.com/netcookies/isulewTools/commit/b384106)
- 修复安装问题 [`6e4972e`](https://github.com/netcookies/isulewTools/commit/6e4972e)
- 修复特权服务协程问题 [`4c10f12`](https://github.com/netcookies/isulewTools/commit/4c10f12)
- 修复返回值判断错误 [`9999706`](https://github.com/netcookies/isulewTools/commit/9999706)
- 优化特权服务 [`fef3470`](https://github.com/netcookies/isulewTools/commit/fef3470)

### Performance Improvements
- 添加是否adb auth 过，防止美式挂了 [`97a0409`](https://github.com/netcookies/isulewTools/commit/97a0409)
- 增加sampleRate的输入 [`3de2cf8`](https://github.com/netcookies/isulewTools/commit/3de2cf8)
- 优化vhal管理器 [`9e5fcf4`](https://github.com/netcookies/isulewTools/commit/9e5fcf4)
- cleanup code [`d56a02d`](https://github.com/netcookies/isulewTools/commit/d56a02d)
- 完善AnyBinderProxy [`edd540c`](https://github.com/netcookies/isulewTools/commit/edd540c)
- 调整包结构 [`ab96c8f`](https://github.com/netcookies/isulewTools/commit/ab96c8f)
- 归集一部分常量 [`28fa798`](https://github.com/netcookies/isulewTools/commit/28fa798)
- 防止adb多次启动 [`367417c`](https://github.com/netcookies/isulewTools/commit/367417c)
- 防止多次启动 [`62761c8`](https://github.com/netcookies/isulewTools/commit/62761c8)
- 防止多次启动 [`233dd31`](https://github.com/netcookies/isulewTools/commit/233dd31)
- 清理无用的媒体功能 [`48b6074`](https://github.com/netcookies/isulewTools/commit/48b6074)

### Test
- 添加日志断点 [`22edb8c`](https://github.com/netcookies/isulewTools/commit/22edb8c)
- 测试安装服务 [`c637e9d`](https://github.com/netcookies/isulewTools/commit/c637e9d)
- 日志迁移完毕，开始测试 [`de40c52`](https://github.com/netcookies/isulewTools/commit/de40c52)
- 日志迁移完毕，开始测试 [`3522635`](https://github.com/netcookies/isulewTools/commit/3522635)

### Build
- 优化gradle [`302d301`](https://github.com/netcookies/isulewTools/commit/302d301)
- 添加Stub [`9e47142`](https://github.com/netcookies/isulewTools/commit/9e47142)
- 添加Stub [`a476c1b`](https://github.com/netcookies/isulewTools/commit/a476c1b)
- 添加Stub [`45efc2d`](https://github.com/netcookies/isulewTools/commit/45efc2d)
- 适配特权服务 [`a1f8141`](https://github.com/netcookies/isulewTools/commit/a1f8141)
- 适配特权服务 [`cb9720d`](https://github.com/netcookies/isulewTools/commit/cb9720d)
- 适配特权服务 [`dfa4a78`](https://github.com/netcookies/isulewTools/commit/dfa4a78)
- 适配特权服务 [`57a9ce2`](https://github.com/netcookies/isulewTools/commit/57a9ce2)
- 适配特权服务 [`0748d99`](https://github.com/netcookies/isulewTools/commit/0748d99)
- 适配特权服务 [`4bea3b9`](https://github.com/netcookies/isulewTools/commit/4bea3b9)
- 适配特权服务 [`5183e82`](https://github.com/netcookies/isulewTools/commit/5183e82)
- 适配特权服务 [`cee7156`](https://github.com/netcookies/isulewTools/commit/cee7156)
- 适配特权服务 [`5f0e6ed`](https://github.com/netcookies/isulewTools/commit/5f0e6ed)
- 开始适配美式提供的超级特权服务 [`ee5746d`](https://github.com/netcookies/isulewTools/commit/ee5746d)
- 开始适配美式提供的超级特权服务 [`bd62229`](https://github.com/netcookies/isulewTools/commit/bd62229)

### Other
- Merge pull request #7 from netcookies/super-privileged [`a4b26d2`](https://github.com/netcookies/isulewTools/commit/a4b26d2)
- pear: 完善无障碍服务的健康机制 [`54f3607`](https://github.com/netcookies/isulewTools/commit/54f3607)
- pear: 完善无障碍服务的健康机制 [`9c8ce00`](https://github.com/netcookies/isulewTools/commit/9c8ce00)
- pear: 解耦各项日志子服务 [`92f2568`](https://github.com/netcookies/isulewTools/commit/92f2568)
- pear: 解耦各项特权子服务 [`42c9ee9`](https://github.com/netcookies/isulewTools/commit/42c9ee9)
- 防止疯狂点击重连 [`d8d328a`](https://github.com/netcookies/isulewTools/commit/d8d328a)
- pref: 优化初始化速度 [`2c8fc67`](https://github.com/netcookies/isulewTools/commit/2c8fc67)

## v1.7.3 (2025-10-04)

### Test
- AI 添加了几个小组件供大家测试 [`a7e5fd0`](https://github.com/netcookies/isulewTools/commit/a7e5fd0)
- AI 添加了几个小组件供大家测试 [`ee6ef34`](https://github.com/netcookies/isulewTools/commit/ee6ef34)

## v1.7.2 (2025-10-04)

### Bug Fixes
- 修复 NPE 错误导致的程序闪退 [`575594b`](https://github.com/netcookies/isulewTools/commit/575594b)

## v1.7.1 (2025-10-03)

### Feature
- 方控支持关闭 [`0de0240`](https://github.com/netcookies/isulewTools/commit/0de0240)
- 添加个按钮示例 [`2300fe1`](https://github.com/netcookies/isulewTools/commit/2300fe1)
- 添加胎压监测小组件 @原想s1160 [`623501a`](https://github.com/netcookies/isulewTools/commit/623501a)
- 添加胎压监测小组件 @原想s1160 [`261f009`](https://github.com/netcookies/isulewTools/commit/261f009)
- 每个订阅独立协程，确保某个订阅出问题不会影响到其他协程 [`0dc744b`](https://github.com/netcookies/isulewTools/commit/0dc744b)
- 解耦小组件配置的调用 [`1fb578a`](https://github.com/netcookies/isulewTools/commit/1fb578a)
- 从widget抽象出flow函数 [`0bdc569`](https://github.com/netcookies/isulewTools/commit/0bdc569)

### Bug Fixes
- flow 类型错误 [`5013a3b`](https://github.com/netcookies/isulewTools/commit/5013a3b)
- 添加调试日志 [`bed7399`](https://github.com/netcookies/isulewTools/commit/bed7399)

### Performance Improvements
- 优化订阅服务 [`044d017`](https://github.com/netcookies/isulewTools/commit/044d017)

### Build
- add lint [`4c77b5c`](https://github.com/netcookies/isulewTools/commit/4c77b5c)

## v1.7.0 (2025-10-02)

### Feature
- 数据库升级时会清空小组件！ BREAKING CHANGE: 数据库升级时会清空小组件！ [`0e47893`](https://github.com/netcookies/isulewTools/commit/0e47893)
- 智驾改用统一的车辆属性订阅 [`15e4027`](https://github.com/netcookies/isulewTools/commit/15e4027)

### Bug Fixes
- 修复智驾悬浮窗数据引用问题 [`9434878`](https://github.com/netcookies/isulewTools/commit/9434878)
- 修复保存后再拖动设置还原的问题 [`543ad54`](https://github.com/netcookies/isulewTools/commit/543ad54)

### Test
- mock 智驾数据 [`c486372`](https://github.com/netcookies/isulewTools/commit/c486372)

## v1.6.9 (2025-10-02)

### Feature
- 记忆仪表盘和悬浮窗选项 [`7d44013`](https://github.com/netcookies/isulewTools/commit/7d44013)
- 被引用不再实时计算，改用持久化实现 [`f93c259`](https://github.com/netcookies/isulewTools/commit/f93c259)
- 移除智驾悬浮窗里的电池 [`40fea21`](https://github.com/netcookies/isulewTools/commit/40fea21)

### Bug Fixes
- 添加线程锁，修复重复显示悬浮窗的 bug [`9a549ef`](https://github.com/netcookies/isulewTools/commit/9a549ef)
- 主动推送流，而不是悬浮窗被动获取 [`20e1a35`](https://github.com/netcookies/isulewTools/commit/20e1a35)

### Performance Improvements
- 去重持久化。且当去重切换时互联小组件引用关系。 [`1a40a9e`](https://github.com/netcookies/isulewTools/commit/1a40a9e)
- LogService 等待 Shizuku 上线。 [`5e01287`](https://github.com/netcookies/isulewTools/commit/5e01287)
- Shizuku就绪时立即重连Vhal [`4368959`](https://github.com/netcookies/isulewTools/commit/4368959)

## v1.6.8 (2025-10-01)

### Feature
- 添加SliderSetting.kt [`169ab75`](https://github.com/netcookies/isulewTools/commit/169ab75)
- 完成小组件悬浮窗后端功能 [`a7b05b8`](https://github.com/netcookies/isulewTools/commit/a7b05b8)
- 悬浮窗服务合并 [`3c8bdcc`](https://github.com/netcookies/isulewTools/commit/3c8bdcc)
- 添加 Mock config [`4d8b160`](https://github.com/netcookies/isulewTools/commit/4d8b160)
- 添加 Mock config [`7dfbe60`](https://github.com/netcookies/isulewTools/commit/7dfbe60)
- 添加 Mock 数据供测试 [`ed7802a`](https://github.com/netcookies/isulewTools/commit/ed7802a)

### Bug Fixes
- 修复拖动协程错误 [`a974bfd`](https://github.com/netcookies/isulewTools/commit/a974bfd)
- 移除没用的方法 [`dd3131a`](https://github.com/netcookies/isulewTools/commit/dd3131a)
- 悬浮窗开关逻辑错误 [`986e1fa`](https://github.com/netcookies/isulewTools/commit/986e1fa)
- 修复放大后圆角不圆的问题 [`6002d89`](https://github.com/netcookies/isulewTools/commit/6002d89)
- 修复小组件编辑后，引用消失 [`22e2621`](https://github.com/netcookies/isulewTools/commit/22e2621)
- 修复去重功能 [`07e8b2e`](https://github.com/netcookies/isulewTools/commit/07e8b2e)

### Performance Improvements
- 完善小组件悬浮窗逻辑 [`80d9c00`](https://github.com/netcookies/isulewTools/commit/80d9c00)
- 悬浮窗开关持久化 [`9566099`](https://github.com/netcookies/isulewTools/commit/9566099)
- 组件小浮窗拖动位置持久化 [`6c66cc8`](https://github.com/netcookies/isulewTools/commit/6c66cc8)
- 添加小组件悬浮窗开关持久化 [`7f11bfc`](https://github.com/netcookies/isulewTools/commit/7f11bfc)
- 优化智驾悬浮窗页面的 UI [`630fad5`](https://github.com/netcookies/isulewTools/commit/630fad5)
- 缩放和透明度，显示两位小数 [`e94f110`](https://github.com/netcookies/isulewTools/commit/e94f110)
- 创建小组件时赋予默认值 [`b8a4a5e`](https://github.com/netcookies/isulewTools/commit/b8a4a5e)
- 优化电池配置 [`e8a9717`](https://github.com/netcookies/isulewTools/commit/e8a9717)
- 增加透明度和缩放两种类型 [`769390b`](https://github.com/netcookies/isulewTools/commit/769390b)
- 完善修改逻辑 [`7a9c59e`](https://github.com/netcookies/isulewTools/commit/7a9c59e)
- 实现拖动 [`35f09e2`](https://github.com/netcookies/isulewTools/commit/35f09e2)
- 适配小组件悬浮窗的开启和隐藏命令 [`5c4f2ad`](https://github.com/netcookies/isulewTools/commit/5c4f2ad)
- 补齐mock数据 [`908053a`](https://github.com/netcookies/isulewTools/commit/908053a)
- 适配原智驾悬浮窗服务 [`3a3d7c7`](https://github.com/netcookies/isulewTools/commit/3a3d7c7)
- 添加应用加载屏 [`6bb06b2`](https://github.com/netcookies/isulewTools/commit/6bb06b2)
- 确保进入车辆属性页时属性已加载 [`747aef9`](https://github.com/netcookies/isulewTools/commit/747aef9)
- 调整服务和viewmodel的初始化顺序 [`09a30e6`](https://github.com/netcookies/isulewTools/commit/09a30e6)
- 添加电池预览 [`49b2c14`](https://github.com/netcookies/isulewTools/commit/49b2c14)
- 完善小组件页面 [`a1ad6f5`](https://github.com/netcookies/isulewTools/commit/a1ad6f5)
- 完善小组件页面 [`8fad485`](https://github.com/netcookies/isulewTools/commit/8fad485)
- 完善电池小组件 [`aefb484`](https://github.com/netcookies/isulewTools/commit/aefb484)

### Build
- 包位置调整 [`67c0a28`](https://github.com/netcookies/isulewTools/commit/67c0a28)

### Other

## v1.6.6 (2025-09-29)

### Feature
- 由AppService统一持有数据库实例 feat: 被小组件引用的属性禁止取消订阅 [`fbda6ef`](https://github.com/netcookies/isulewTools/commit/fbda6ef)
- 添加数据引用功能 [`096f710`](https://github.com/netcookies/isulewTools/commit/096f710)

### Bug Fixes
- 修复被引用无法点击 [`ab403d8`](https://github.com/netcookies/isulewTools/commit/ab403d8)
- 修复去重：开功能的无限循环 [`a1598df`](https://github.com/netcookies/isulewTools/commit/a1598df)
- 恢复误删除的代码 [`0b63e12`](https://github.com/netcookies/isulewTools/commit/0b63e12)
- 修复数据库升级错误 [`399db27`](https://github.com/netcookies/isulewTools/commit/399db27)

### Performance Improvements
- 移除重连按钮，需要重连的话点右上角状态栏图标。 [`f530a2d`](https://github.com/netcookies/isulewTools/commit/f530a2d)
- 移除重连按钮，需要重连的话点右上角状态栏图标。 [`ff752a2`](https://github.com/netcookies/isulewTools/commit/ff752a2)
- 移除去重模式，现在去重已经没有意义 [`85b975b`](https://github.com/netcookies/isulewTools/commit/85b975b)
- 小组件支持必填项 [`bbecad2`](https://github.com/netcookies/isulewTools/commit/bbecad2)
- 按钮样式优化 [`3db8e74`](https://github.com/netcookies/isulewTools/commit/3db8e74)
- 按钮布局优化 [`6b1cd5f`](https://github.com/netcookies/isulewTools/commit/6b1cd5f)
- 车辆属性列表高度优化 [`b5d7bbf`](https://github.com/netcookies/isulewTools/commit/b5d7bbf)
- 将和无障碍无关的服务从无障碍中解耦 [`751dd38`](https://github.com/netcookies/isulewTools/commit/751dd38)

### Build
- 移除Jetifier [`6fe27a4`](https://github.com/netcookies/isulewTools/commit/6fe27a4)

### Other

## v1.6.4 (2025-09-28)

### Feature
- 添加安装和卸载美式的按钮 [`ad4b506`](https://github.com/netcookies/isulewTools/commit/ad4b506)
- 实现属性数据也分页懒加载 fix: 修复置顶、过滤、订阅等无法显示 [`cfc5599`](https://github.com/netcookies/isulewTools/commit/cfc5599)
- 添加以表盘 perf: 合并数据库 build: 添加文档 [`3a6468c`](https://github.com/netcookies/isulewTools/commit/3a6468c)
- 小组件功能初版 [`3e0ffdf`](https://github.com/netcookies/isulewTools/commit/3e0ffdf)

### Bug Fixes
- cfc5599 feat: 实现属性数据也分页懒加载 fix: 修复置顶、过滤、订阅等无法显示 [`cfc5599`](https://github.com/netcookies/isulewTools/commit/cfc5599)
- 修复属性列表缓存加载问题 [`3d9d663`](https://github.com/netcookies/isulewTools/commit/3d9d663)
- 修复数据源绑定问题 [`31d495a`](https://github.com/netcookies/isulewTools/commit/31d495a)
- 修复数据源绑定问题 [`32d5fd3`](https://github.com/netcookies/isulewTools/commit/32d5fd3)
- 修复数据源绑定问题 [`337179e`](https://github.com/netcookies/isulewTools/commit/337179e)
- 0dc37d1 perf: 支持颜色选择器 fix: 属性数据源保存问题 [`0dc37d1`](https://github.com/netcookies/isulewTools/commit/0dc37d1)
- 数据库升级错误 [`3c4719f`](https://github.com/netcookies/isulewTools/commit/3c4719f)

### Performance Improvements
- 每页显示 25 条数据，重连按钮图标改成文字 [`0c4f647`](https://github.com/netcookies/isulewTools/commit/0c4f647)
- 优化电池小组件 [`ca20e2d`](https://github.com/netcookies/isulewTools/commit/ca20e2d)
- 优化弹窗 [`011f905`](https://github.com/netcookies/isulewTools/commit/011f905)
- 3a6468c feat: 添加以表盘 perf: 合并数据库 build: 添加文档 [`3a6468c`](https://github.com/netcookies/isulewTools/commit/3a6468c)
- 更新gitignore [`5f87a53`](https://github.com/netcookies/isulewTools/commit/5f87a53)
- 支持颜色选择器 fix: 属性数据源保存问题 [`0dc37d1`](https://github.com/netcookies/isulewTools/commit/0dc37d1)
- 统一按钮样式 [`9ebfe3a`](https://github.com/netcookies/isulewTools/commit/9ebfe3a)
- 统一按钮样式 [`5a6d993`](https://github.com/netcookies/isulewTools/commit/5a6d993)
- 清理无用的context [`e19f35e`](https://github.com/netcookies/isulewTools/commit/e19f35e)
- 完善数据绑定逻辑 [`7ef832f`](https://github.com/netcookies/isulewTools/commit/7ef832f)
- 调整数据源 key [`33f4452`](https://github.com/netcookies/isulewTools/commit/33f4452)
- 完成主要功能框架 [`c5f50af`](https://github.com/netcookies/isulewTools/commit/c5f50af)
- 添加LCC 暂停时，手动加速的状态 [`36b5da9`](https://github.com/netcookies/isulewTools/commit/36b5da9)
- 补齐PropertySubscriptionService的参数 [`c0589e2`](https://github.com/netcookies/isulewTools/commit/c0589e2)
- 由AppServices全局持有PropertySubscriptionService [`c38d272`](https://github.com/netcookies/isulewTools/commit/c38d272)

### Build
- update gitignore [`e121791`](https://github.com/netcookies/isulewTools/commit/e121791)
- 3a6468c feat: 添加以表盘 perf: 合并数据库 build: 添加文档 [`3a6468c`](https://github.com/netcookies/isulewTools/commit/3a6468c)
- idea stuff [`28e72a0`](https://github.com/netcookies/isulewTools/commit/28e72a0)

### Other
- debug: 增加临时调试日志 [`361620c`](https://github.com/netcookies/isulewTools/commit/361620c)
- Merge branch 'main' of https://github.com/netcookies/isulewTools [`cd1692e`](https://github.com/netcookies/isulewTools/commit/cd1692e)
- Merge pull request #6 [`64ef41c`](https://github.com/netcookies/isulewTools/commit/64ef41c)

## v1.6.3 (2025-09-24)

### Feature
- 增加取消全部订阅按钮 fix: 修复高度问题 [`2801a0a`](https://github.com/netcookies/isulewTools/commit/2801a0a)

### Bug Fixes
- 修复viewModel初始化问题 [`3f8f8bf`](https://github.com/netcookies/isulewTools/commit/3f8f8bf)
- 修复写入的结果值总是true [`a8ebabb`](https://github.com/netcookies/isulewTools/commit/a8ebabb)
- 2801a0a feat: 增加取消全部订阅按钮 fix: 修复高度问题 [`2801a0a`](https://github.com/netcookies/isulewTools/commit/2801a0a)

### Performance Improvements
- 回显居中显示 [`f3376ea`](https://github.com/netcookies/isulewTools/commit/f3376ea)
- 统一按钮样式 [`fcbb2f0`](https://github.com/netcookies/isulewTools/commit/fcbb2f0)
- 统一按钮样式 [`9abc0ef`](https://github.com/netcookies/isulewTools/commit/9abc0ef)
- 优化回显效果 [`014da0a`](https://github.com/netcookies/isulewTools/commit/014da0a)

## v1.6.2 (2025-09-24)

### Feature
- 提供统一的订阅池管理。为后续功能做准备 [`fcf1c44`](https://github.com/netcookies/isulewTools/commit/fcf1c44)
- 订阅持久化 [`be92271`](https://github.com/netcookies/isulewTools/commit/be92271)

### Bug Fixes
- 已订阅的属性置顶显示 [`827e506`](https://github.com/netcookies/isulewTools/commit/827e506)
- 修复事件流颜色 [`f626301`](https://github.com/netcookies/isulewTools/commit/f626301)

### Performance Improvements
- 优化交互逻辑 [`7304bc0`](https://github.com/netcookies/isulewTools/commit/7304bc0)
- 禁止状态 [`dd8ec3b`](https://github.com/netcookies/isulewTools/commit/dd8ec3b)
- 写入时显示示例 [`66bcf2c`](https://github.com/netcookies/isulewTools/commit/66bcf2c)
- UI颜色优化 [`6110843`](https://github.com/netcookies/isulewTools/commit/6110843)
- 统一速度的颜色 [`f35e46c`](https://github.com/netcookies/isulewTools/commit/f35e46c)
- 添加跟车、暂停的颜色 [`d432be2`](https://github.com/netcookies/isulewTools/commit/d432be2)

### Test
- 增加测试模式 [`5b35c49`](https://github.com/netcookies/isulewTools/commit/5b35c49)

### Build
- code clean up [`1cf88c0`](https://github.com/netcookies/isulewTools/commit/1cf88c0)

### Refactor
- 重构车辆状态 UI [`f7e69a0`](https://github.com/netcookies/isulewTools/commit/f7e69a0)

## v1.6.1 (2025-09-22)

### Bug Fixes
- 修复悬浮窗显示，如果有发现触发紫色背景的请报告到群里@我 [`026d629`](https://github.com/netcookies/isulewTools/commit/026d629)
- 移除枚举值，改用常量 [`dcc941f`](https://github.com/netcookies/isulewTools/commit/dcc941f)
- 移除枚举值，改用常量 [`66f8445`](https://github.com/netcookies/isulewTools/commit/66f8445)

### Performance Improvements
- 事件窗口优化 [`1b3906e`](https://github.com/netcookies/isulewTools/commit/1b3906e)

### Test
- 媒体卡片绑定测试 [`2e2edc4`](https://github.com/netcookies/isulewTools/commit/2e2edc4)

## v1.6.0 (2025-09-22)

### Bug Fixes
- 修补中文名缺失问题 [`527a281`](https://github.com/netcookies/isulewTools/commit/527a281)
- 修补参数问题 [`6749ea8`](https://github.com/netcookies/isulewTools/commit/6749ea8)

### Performance Improvements
- 事件倒序与自动滚动 [`0139dfc`](https://github.com/netcookies/isulewTools/commit/0139dfc)
- vhal 默认自动重连 [`8ab2609`](https://github.com/netcookies/isulewTools/commit/8ab2609)
- 事件分类 [`7703b09`](https://github.com/netcookies/isulewTools/commit/7703b09)
- 添加中文显示 [`348ea11`](https://github.com/netcookies/isulewTools/commit/348ea11)
- 时间格式添加毫秒 [`5b02aba`](https://github.com/netcookies/isulewTools/commit/5b02aba)
- 优化 acc 和 lcc枚举值 [`391be1c`](https://github.com/netcookies/isulewTools/commit/391be1c)
- 去除赞赏二维码 [`e447e63`](https://github.com/netcookies/isulewTools/commit/e447e63)

### Test
- adas state for test [`df3f711`](https://github.com/netcookies/isulewTools/commit/df3f711)

### Build
- code cleanup [`cef6b90`](https://github.com/netcookies/isulewTools/commit/cef6b90)
- 移除多余的图片和字符 [`677c60c`](https://github.com/netcookies/isulewTools/commit/677c60c)

## v1.5.9 (2025-09-21)

### Feature
- 日志过滤功能 [`e9c5286`](https://github.com/netcookies/isulewTools/commit/e9c5286)

### Bug Fixes
- 补齐两种悬浮窗状态 [`cb18370`](https://github.com/netcookies/isulewTools/commit/cb18370)

### Performance Improvements
- 日志倒序并自动滚动 [`dfb9624`](https://github.com/netcookies/isulewTools/commit/dfb9624)

## v1.5.8 (2025-09-21)

### Feature
- 添加多媒体卡片功能（ui 还没完成） [`3bb94ba`](https://github.com/netcookies/isulewTools/commit/3bb94ba)

### Bug Fixes
- 修复Vhal 悬浮窗 [`0a53655`](https://github.com/netcookies/isulewTools/commit/0a53655)
- vhal binder 断开提示 [`66f7ffc`](https://github.com/netcookies/isulewTools/commit/66f7ffc)

### Performance Improvements
- 反馈改成按钮，增大日志显示范围 [`4e3136c`](https://github.com/netcookies/isulewTools/commit/4e3136c)
- 日志样式美化 [`38b4eae`](https://github.com/netcookies/isulewTools/commit/38b4eae)
- 优化日志性能 [`ab81105`](https://github.com/netcookies/isulewTools/commit/ab81105)
- 添加accCode = 3 提速和accCode = 7 不可用两种状态 [`5d7c442`](https://github.com/netcookies/isulewTools/commit/5d7c442)

### Test
- 添加媒体卡片测试 [`880414b`](https://github.com/netcookies/isulewTools/commit/880414b)
- 实验性功能加入测试按钮 [`51d907b`](https://github.com/netcookies/isulewTools/commit/51d907b)

### Build
- 升级lib [`c83920e`](https://github.com/netcookies/isulewTools/commit/c83920e)

## v1.5.7 (2025-09-19)

### Feature
- 电量颜色参考 ios 风格 [`5b24846`](https://github.com/netcookies/isulewTools/commit/5b24846)
- 重构添加电池显示 [`ad71fbe`](https://github.com/netcookies/isulewTools/commit/ad71fbe)
- 事件流加上名字 [`f7e0aca`](https://github.com/netcookies/isulewTools/commit/f7e0aca)
- 实现动态切换悬浮窗数据源，vhal 有权限时会自动切到 VHAL，否则切回日志 [`e963a5d`](https://github.com/netcookies/isulewTools/commit/e963a5d)
- 加入电量图标 [`fbede01`](https://github.com/netcookies/isulewTools/commit/fbede01)
- 添加悬浮窗数据源 [`dd42362`](https://github.com/netcookies/isulewTools/commit/dd42362)
- 将PilotData抽象成接口 [`aa6f438`](https://github.com/netcookies/isulewTools/commit/aa6f438)
- 添加行的状态 Chip，方便后期调试 [`99d7af6`](https://github.com/netcookies/isulewTools/commit/99d7af6)

### Performance Improvements
- 适配行的悬浮窗数据源 [`88eb002`](https://github.com/netcookies/isulewTools/commit/88eb002)

### Build
- Cleanup Code [`791cfc6`](https://github.com/netcookies/isulewTools/commit/791cfc6)

## v1.5.6 (2025-09-17)

### Feature
- 车辆属性订阅可以用啦！🎉 [`8fbc81c`](https://github.com/netcookies/isulewTools/commit/8fbc81c)
- 切换 tab 时保存界面上的车辆状态值 [`66d20ac`](https://github.com/netcookies/isulewTools/commit/66d20ac)
- property 属性采用dump car_service 方式获取。效率提升 1000 倍！ [`8383589`](https://github.com/netcookies/isulewTools/commit/8383589)

### Bug Fixes
- 增强版的读取和写入 field [`d5d357d`](https://github.com/netcookies/isulewTools/commit/d5d357d)
- 讲property包添加入白名单 [`0e4f9d8`](https://github.com/netcookies/isulewTools/commit/0e4f9d8)
- 尝试修复订阅 flags = 0错误 [`99059d2`](https://github.com/netcookies/isulewTools/commit/99059d2)
- 测试模式改为静态检测 [`0378b1b`](https://github.com/netcookies/isulewTools/commit/0378b1b)
- 修改悬浮窗默认值为关闭 [`1fe6c6b`](https://github.com/netcookies/isulewTools/commit/1fe6c6b)
- 悬浮窗兜底策略修复 [`a058ad1`](https://github.com/netcookies/isulewTools/commit/a058ad1)
- 修复 getPropConfigs 签名错误 [`bce3723`](https://github.com/netcookies/isulewTools/commit/bce3723)
- 修复 getPropConfigs 签名错误 [`a0c9b92`](https://github.com/netcookies/isulewTools/commit/a0c9b92)
- 加入 changemode 调试信息 [`c873bf8`](https://github.com/netcookies/isulewTools/commit/c873bf8)
- 悬浮窗加一个兜底，免得掉下来 [`fcf3700`](https://github.com/netcookies/isulewTools/commit/fcf3700)

### Test
- 单元测试通过 [`e51b810`](https://github.com/netcookies/isulewTools/commit/e51b810)
- 采用模版驱动的方式实现匹配 [`56e0a63`](https://github.com/netcookies/isulewTools/commit/56e0a63)
- 更新测试用例 [`7f501ee`](https://github.com/netcookies/isulewTools/commit/7f501ee)
- 更新测试用例 [`0979c70`](https://github.com/netcookies/isulewTools/commit/0979c70)
- 添加单元测试方法 [`82a5b71`](https://github.com/netcookies/isulewTools/commit/82a5b71)
- 添加单元测试方法 [`84dff5c`](https://github.com/netcookies/isulewTools/commit/84dff5c)

### Build
- 适配CarStatusTab [`ba37f74`](https://github.com/netcookies/isulewTools/commit/ba37f74)
- 适配CarStatusTab [`91214de`](https://github.com/netcookies/isulewTools/commit/91214de)
- 适配CarStatusViewModel [`6b3d1d3`](https://github.com/netcookies/isulewTools/commit/6b3d1d3)
- code cleanup [`124bc21`](https://github.com/netcookies/isulewTools/commit/124bc21)

## v1.5.5 (2025-09-15)

### Feature
- Shizuku每次更新授权一次 [`129675c`](https://github.com/netcookies/isulewTools/commit/129675c)
- 无障碍判断该用 kotlin 的状态驱动 [`b1047a1`](https://github.com/netcookies/isulewTools/commit/b1047a1)
- 安装器注册成独立的安装器，其他程序可以调用。 [`3e9c627`](https://github.com/netcookies/isulewTools/commit/3e9c627)
- 去除外部存储权限依赖。 [`c589172`](https://github.com/netcookies/isulewTools/commit/c589172)

### Bug Fixes
- remove unused fun [`7722e48`](https://github.com/netcookies/isulewTools/commit/7722e48)

### Build
- code cleanup [`ace3da2`](https://github.com/netcookies/isulewTools/commit/ace3da2)

## v1.5.4 (2025-09-14)

### Bug Fixes
- 修复亮屏启动（测试通过） [`255e379`](https://github.com/netcookies/isulewTools/commit/255e379)
- 修复亮屏启动 [`5a12b12`](https://github.com/netcookies/isulewTools/commit/5a12b12)
- 修复亮屏启动 [`8dd96b2`](https://github.com/netcookies/isulewTools/commit/8dd96b2)

## v1.5.3 (2025-09-13)

### Feature
- 重构类型转换逻辑 [`b1561d4`](https://github.com/netcookies/isulewTools/commit/b1561d4)
- 处理mixed type [`1ee989c`](https://github.com/netcookies/isulewTools/commit/1ee989c)

### Bug Fixes
- 修正 UI 显示错误 [`27796d5`](https://github.com/netcookies/isulewTools/commit/27796d5)
- 修复包名错误 [`b0b9584`](https://github.com/netcookies/isulewTools/commit/b0b9584)

### Build
- code cleanup [`7781bd2`](https://github.com/netcookies/isulewTools/commit/7781bd2)
- 合并mixed type code [`337dbf9`](https://github.com/netcookies/isulewTools/commit/337dbf9)

### Other
- fix：对齐property type todo: mix type [`c6721ad`](https://github.com/netcookies/isulewTools/commit/c6721ad)
- fix：修复订阅 flag问题 [`c31d9a7`](https://github.com/netcookies/isulewTools/commit/c31d9a7)

## v1.5.2 (2025-09-13)

### Bug Fixes
- 对齐 aidl [`dedc376`](https://github.com/netcookies/isulewTools/commit/dedc376)
- 添加unuse tag [`f04e9c5`](https://github.com/netcookies/isulewTools/commit/f04e9c5)
- 添加缺失的常量 [`ed9614c`](https://github.com/netcookies/isulewTools/commit/ed9614c)
- 事件流窗口的背景色问题 [`7240db8`](https://github.com/netcookies/isulewTools/commit/7240db8)
- 黑夜模式字体颜色问题 [`d9ebf83`](https://github.com/netcookies/isulewTools/commit/d9ebf83)

## v1.5.1 (2025-09-12)

### Bug Fixes
- 更新车辆属性卡片布局 [`c098bd9`](https://github.com/netcookies/isulewTools/commit/c098bd9)
- 修复因传入错误 id 导致的崩溃。只在vhal 连接状态变化时更新状态值。回退到单 dex 模式 [`854a5a7`](https://github.com/netcookies/isulewTools/commit/854a5a7)

## v1.5.0 (2025-09-11)

### Bug Fixes
- 修复bridge连接问题 [`6513e1c`](https://github.com/netcookies/isulewTools/commit/6513e1c)
- 修复安装权限检测 [`789dc45`](https://github.com/netcookies/isulewTools/commit/789dc45)

## v1.4.9 (2025-09-11)

### Bug Fixes
- 增加读取和写入的覆盖类型 [`5ae6840`](https://github.com/netcookies/isulewTools/commit/5ae6840)

## v1.4.8 (2025-09-11)

### Bug Fixes
- 完善车辆状态测试 UI [`92a53a3`](https://github.com/netcookies/isulewTools/commit/92a53a3)

## v1.4.7 (2025-09-11)

### Feature
- 日志写入文件，方便 shizuku 进程调试 [`afe4e53`](https://github.com/netcookies/isulewTools/commit/afe4e53)

### Bug Fixes
- 修复反射方法错误 [`35818c5`](https://github.com/netcookies/isulewTools/commit/35818c5)
- 修复方控问题 [`0ec8d6b`](https://github.com/netcookies/isulewTools/commit/0ec8d6b)

## v1.4.6 (2025-09-11)

### Bug Fixes
- 测试界面修复 [`55d36f1`](https://github.com/netcookies/isulewTools/commit/55d36f1)
- 小 bug 修复 [`2a2eda9`](https://github.com/netcookies/isulewTools/commit/2a2eda9)

## v1.4.5 (2025-09-10)

### Bug Fixes
- 更新窗口错误时无法关闭的问题 [`98a167a`](https://github.com/netcookies/isulewTools/commit/98a167a)

## v1.4.4 (2025-09-10)

### Feature
- 添加property测试 [`7c44a99`](https://github.com/netcookies/isulewTools/commit/7c44a99)
- 动态代理注册回调无效，改用dexmaker [`aa59738`](https://github.com/netcookies/isulewTools/commit/aa59738)

### Bug Fixes
- 完善vhal模块 [`9302ec0`](https://github.com/netcookies/isulewTools/commit/9302ec0)
- 适配Shizuku UserService [`d3a63f9`](https://github.com/netcookies/isulewTools/commit/d3a63f9)
- shizuku 没权限时跳过部分初始化 [`82987a7`](https://github.com/netcookies/isulewTools/commit/82987a7)
- 修复拿铁美式跳转错误 [`491cf4a`](https://github.com/netcookies/isulewTools/commit/491cf4a)
- 修复启动速度慢 [`3ce3e0b`](https://github.com/netcookies/isulewTools/commit/3ce3e0b)
- 默认隐藏语音图标 [`7bd15d2`](https://github.com/netcookies/isulewTools/commit/7bd15d2)
- 无障碍判断加上对应子服务是否启动 [`dea4f04`](https://github.com/netcookies/isulewTools/commit/dea4f04)
- User Service 日志修复 [`dfd425f`](https://github.com/netcookies/isulewTools/commit/dfd425f)

### Build
- add property modules [`d9c33af`](https://github.com/netcookies/isulewTools/commit/d9c33af)
- add property modules [`d61d53b`](https://github.com/netcookies/isulewTools/commit/d61d53b)
- 统一viewmodel管理 [`726f87e`](https://github.com/netcookies/isulewTools/commit/726f87e)
- 提供统一的无障碍服务编排 [`de448bd`](https://github.com/netcookies/isulewTools/commit/de448bd)
- 修复调用错误 [`99de7df`](https://github.com/netcookies/isulewTools/commit/99de7df)
- 修复变异错误 [`8ab771c`](https://github.com/netcookies/isulewTools/commit/8ab771c)
- 添加dexmaker [`d209fec`](https://github.com/netcookies/isulewTools/commit/d209fec)
- 加入proxy的debug信息 [`b8ec864`](https://github.com/netcookies/isulewTools/commit/b8ec864)
- change agp version to stable [`e09ac0a`](https://github.com/netcookies/isulewTools/commit/e09ac0a)

### Chore
- remove build/manifest; keep aidl/kt/res; README retained per review (#4) [`6363235`](https://github.com/netcookies/isulewTools/commit/6363235)

### Other
- Merge branch 'main' of https://github.com/netcookies/isulewTools [`a0962b4`](https://github.com/netcookies/isulewTools/commit/a0962b4)
- Merge pull request #4 from netcookies/copilot/add-vhal-reflection-bridge [`411f05c`](https://github.com/netcookies/isulewTools/commit/411f05c)
- Complete VHAL reflection bridge implementation [`6da1dc3`](https://github.com/netcookies/isulewTools/commit/6da1dc3)
- Add AIDL interfaces and core reflection components [`a333487`](https://github.com/netcookies/isulewTools/commit/a333487)
- Initial commit for VHAL bridge planning [`25b2809`](https://github.com/netcookies/isulewTools/commit/25b2809)
- Initial plan [`3a22fbe`](https://github.com/netcookies/isulewTools/commit/3a22fbe)
- Merge pull request #3 from netcookies/copilot/add-vhal-bridge-reflection-service [`ba21b3a`](https://github.com/netcookies/isulewTools/commit/ba21b3a)
- Initial plan [`98088f4`](https://github.com/netcookies/isulewTools/commit/98088f4)
- code: cleanup [`353f6f8`](https://github.com/netcookies/isulewTools/commit/353f6f8)
- test：分组绑定messagetyps [`32e3ff1`](https://github.com/netcookies/isulewTools/commit/32e3ff1)

## v1.4.3 (2025-09-06)

### Documentation
- 添加调试信息，可以不更新 [`15c613a`](https://github.com/netcookies/isulewTools/commit/15c613a)

## v1.4.2 (2025-09-06)

### Bug Fixes
- 紧急修复下载地址错误 [`5a874d9`](https://github.com/netcookies/isulewTools/commit/5a874d9)
- 紧急修复下载地址错误 [`ef2f58a`](https://github.com/netcookies/isulewTools/commit/ef2f58a)

## v1.4.1 (2025-09-06)

### Feature
- 添加CarLanBridge [`edc6225`](https://github.com/netcookies/isulewTools/commit/edc6225)

### Bug Fixes
- 实现shizuku user service [`e6d4b6e`](https://github.com/netcookies/isulewTools/commit/e6d4b6e)
- NPE [`a6c16a7`](https://github.com/netcookies/isulewTools/commit/a6c16a7)
- 完善CarLanBridge [`4ba4537`](https://github.com/netcookies/isulewTools/commit/4ba4537)

### Test
- 完善测试vehicle proxy逻辑 [`820d9e9`](https://github.com/netcookies/isulewTools/commit/820d9e9)

### Build
- 升级版本，添加parcelize [`3623148`](https://github.com/netcookies/isulewTools/commit/3623148)

### Style
- cleanup code [`dffffa5`](https://github.com/netcookies/isulewTools/commit/dffffa5)

## v1.4.0 (2025-09-04)

### Feature
- 新增车辆状态页（测试中） cleanup: 去除旧的无用的代码 [`0885912`](https://github.com/netcookies/isulewTools/commit/0885912)
- CarLanManager reversion [`7aa74f7`](https://github.com/netcookies/isulewTools/commit/7aa74f7)
- CommonProxy reversion [`0b8a42a`](https://github.com/netcookies/isulewTools/commit/0b8a42a)
- 添加车辆状态页 [`74a34d5`](https://github.com/netcookies/isulewTools/commit/74a34d5)
- 添加车机属性测试页 [`aaaf062`](https://github.com/netcookies/isulewTools/commit/aaaf062)
- 添加vehicleProxy [`0a63696`](https://github.com/netcookies/isulewTools/commit/0a63696)
- carlan代码集成（root可调用） [`63e4ddb`](https://github.com/netcookies/isulewTools/commit/63e4ddb)
- 添加CarLanService [`0f77f68`](https://github.com/netcookies/isulewTools/commit/0f77f68)

### Bug Fixes
- 优化悬浮窗开关逻辑 [`76779a4`](https://github.com/netcookies/isulewTools/commit/76779a4)
- CarLanManager typo [`3ab0edd`](https://github.com/netcookies/isulewTools/commit/3ab0edd)
- 无障碍服务就不用进到页面去再开关了。直接不让用 :) [`44cfc19`](https://github.com/netcookies/isulewTools/commit/44cfc19)
- hidl server反射无法获取的问题 [`fedc764`](https://github.com/netcookies/isulewTools/commit/fedc764)
- 减小更新窗口 [`7bef014`](https://github.com/netcookies/isulewTools/commit/7bef014)
- 修复调用多种无障碍管理器 [`39341d6`](https://github.com/netcookies/isulewTools/commit/39341d6)
- cleanup code [`30f8c37`](https://github.com/netcookies/isulewTools/commit/30f8c37)
- 移除掉无用import [`783981b`](https://github.com/netcookies/isulewTools/commit/783981b)
- 修复 flow 调用 [`62b2688`](https://github.com/netcookies/isulewTools/commit/62b2688)
- 采用反射的方式获取车辆属性 [`8d7a472`](https://github.com/netcookies/isulewTools/commit/8d7a472)
- 遇到不支持属性直接关闭订阅 [`bc8ce32`](https://github.com/netcookies/isulewTools/commit/bc8ce32)

### Documentation
- 规范命名 [`b41403b`](https://github.com/netcookies/isulewTools/commit/b41403b)
- 完善代码 [`fe98dd1`](https://github.com/netcookies/isulewTools/commit/fe98dd1)

### Build
- 移除HwServiceManager [`ad1d8b2`](https://github.com/netcookies/isulewTools/commit/ad1d8b2)
- cleanup code [`6b6dedc`](https://github.com/netcookies/isulewTools/commit/6b6dedc)
- 添加CarLanManager [`b2611ca`](https://github.com/netcookies/isulewTools/commit/b2611ca)
- 添加CommonProxy [`e2b9809`](https://github.com/netcookies/isulewTools/commit/e2b9809)
- 添加stub service [`b74a6bc`](https://github.com/netcookies/isulewTools/commit/b74a6bc)
- 添加stub [`d61b7d9`](https://github.com/netcookies/isulewTools/commit/d61b7d9)

### Other

## v1.3.9 (2025-08-31)

### Feature
- 支持多区域属性 [`354836c`](https://github.com/netcookies/isulewTools/commit/354836c)

### Bug Fixes
- 处理混合属性类型 [`422a247`](https://github.com/netcookies/isulewTools/commit/422a247)
- 跳过混合属性类型 [`e02e38c`](https://github.com/netcookies/isulewTools/commit/e02e38c)
- 增强混合属性类型 [`816f13e`](https://github.com/netcookies/isulewTools/commit/816f13e)
- 增强代码调试 [`37b0031`](https://github.com/netcookies/isulewTools/commit/37b0031)
- 显示Shizuku是否 root [`42f7096`](https://github.com/netcookies/isulewTools/commit/42f7096)
- 每次切回主画面都提权改为只调用一次 [`961e48d`](https://github.com/netcookies/isulewTools/commit/961e48d)

### Test
- 更新新的测试方法 [`b06aca9`](https://github.com/netcookies/isulewTools/commit/b06aca9)
- 添加CarPropertyConfig.kt的dump [`eef0530`](https://github.com/netcookies/isulewTools/commit/eef0530)

### Build
- Code Cleanup [`b79a345`](https://github.com/netcookies/isulewTools/commit/b79a345)

## v1.3.8 (2025-08-30)

### Feature
- 增加常用车辆属性的便捷方法 [`a093ff2`](https://github.com/netcookies/isulewTools/commit/a093ff2)
- 单独实现car service [`bfe542f`](https://github.com/netcookies/isulewTools/commit/bfe542f)
- 兼容跳转多种无障碍管理器，完善提示信息 [`0d47fac`](https://github.com/netcookies/isulewTools/commit/0d47fac)

### Bug Fixes
- area config [`ced211c`](https://github.com/netcookies/isulewTools/commit/ced211c)
- 修复序列化问题 [`208ff52`](https://github.com/netcookies/isulewTools/commit/208ff52)
- 日志页面的字体太小 [`7ce3933`](https://github.com/netcookies/isulewTools/commit/7ce3933)
- 尝试修复byte 数组转换问题 [`de1f655`](https://github.com/netcookies/isulewTools/commit/de1f655)
- 尝试修复byte 数组转换问题 [`65571a7`](https://github.com/netcookies/isulewTools/commit/65571a7)

### Test
- 添加调用读取常用车辆属性的实验性功能 [`213e0b0`](https://github.com/netcookies/isulewTools/commit/213e0b0)

## v1.3.7 (2025-08-28)

### Test
- 更新电量测试 [`5070710`](https://github.com/netcookies/isulewTools/commit/5070710)

## v1.3.6 (2025-08-28)

### Feature
- 新增车辆属性清单 [`3911632`](https://github.com/netcookies/isulewTools/commit/3911632)

### Bug Fixes
- 更新权限 [`59d97ca`](https://github.com/netcookies/isulewTools/commit/59d97ca)
- 更新内容设置最小宽度 [`edf4155`](https://github.com/netcookies/isulewTools/commit/edf4155)
- 悬浮窗太小时缩在一起的问题 [`8614a13`](https://github.com/netcookies/isulewTools/commit/8614a13)

### Test
- 电量测试 [`43e71eb`](https://github.com/netcookies/isulewTools/commit/43e71eb)

## v1.3.5 (2025-08-27)

### Feature
- 增加更新日志的按钮 [`cbd72f9`](https://github.com/netcookies/isulewTools/commit/cbd72f9)
- 跨版本升级返回多版本日志 [`d9a0736`](https://github.com/netcookies/isulewTools/commit/d9a0736)
- 苹果圆角 [`4a73a4a`](https://github.com/netcookies/isulewTools/commit/4a73a4a)
- 悬浮窗支持加载和保存样式 [`226967f`](https://github.com/netcookies/isulewTools/commit/226967f)

### Bug Fixes
- 移除无用的import [`1ac3f72`](https://github.com/netcookies/isulewTools/commit/1ac3f72)

## v1.3.4 (2025-08-26)

### Bug Fixes
- 修复自动更新问题 [`1f2f209`](https://github.com/netcookies/isulewTools/commit/1f2f209)

## v1.3.3 (2025-08-26)

### Bug Fixes
- 修复空指针问题 [`dfd9834`](https://github.com/netcookies/isulewTools/commit/dfd9834)

### Test
- 测试蓝牙功能(shizuku) [`51960bc`](https://github.com/netcookies/isulewTools/commit/51960bc)

## v1.3.2 (2025-08-26)

### Feature
- 亮屏后后台检测一次更新 [`d9a7ccc`](https://github.com/netcookies/isulewTools/commit/d9a7ccc)
- 日志类重构 [`2a5768b`](https://github.com/netcookies/isulewTools/commit/2a5768b)
- 清理代码。完善后端服务逻辑。 [`e5b3f8f`](https://github.com/netcookies/isulewTools/commit/e5b3f8f)
- 完善Shizuku 权限和校验机制。添加Shizuku 日志、蓝牙AIDL 服务 [`2b112d9`](https://github.com/netcookies/isulewTools/commit/2b112d9)
- 添加User Service [`56c5806`](https://github.com/netcookies/isulewTools/commit/56c5806)
- 增加shizuku反射调用设置的BtService和WlanService [`3501387`](https://github.com/netcookies/isulewTools/commit/3501387)

### Bug Fixes
- 时距有时会为 0 [`e84ec08`](https://github.com/netcookies/isulewTools/commit/e84ec08)
- 清理冗余的日志，移至debug [`094bdaa`](https://github.com/netcookies/isulewTools/commit/094bdaa)
- 清理日志工具类的调用 [`186797a`](https://github.com/netcookies/isulewTools/commit/186797a)
- 没有异常信息时会多输出一个 Null [`0822ff6`](https://github.com/netcookies/isulewTools/commit/0822ff6)
- remove redundant qualifier name [`8f08884`](https://github.com/netcookies/isulewTools/commit/8f08884)
- Shizuku User Service [`d1c3b30`](https://github.com/netcookies/isulewTools/commit/d1c3b30)
- Shizuku User Service [`5a34fd3`](https://github.com/netcookies/isulewTools/commit/5a34fd3)
- 安装完成显示toast [`bfed3a6`](https://github.com/netcookies/isulewTools/commit/bfed3a6)
- 优化使用kotlin的协程替换thread [`36ea8df`](https://github.com/netcookies/isulewTools/commit/36ea8df)

### Build
- 合并代码 [`04317ca`](https://github.com/netcookies/isulewTools/commit/04317ca)

## v1.3.1 (2025-08-25)

### Bug Fixes
- 修复日志参数问题 [`8811661`](https://github.com/netcookies/isulewTools/commit/8811661)

### Build
- 整理代码 [`3a7b2d5`](https://github.com/netcookies/isulewTools/commit/3a7b2d5)

## v1.3.0 (2025-08-24)

### Bug Fixes
- 优化悬浮窗数据的匹配效率 [`fa53594`](https://github.com/netcookies/isulewTools/commit/fa53594)
- update toast display issue [`030ed18`](https://github.com/netcookies/isulewTools/commit/030ed18)
- update toast display issue [`69ddf6b`](https://github.com/netcookies/isulewTools/commit/69ddf6b)
- update toast display issue [`49c3f33`](https://github.com/netcookies/isulewTools/commit/49c3f33)

### Other
- Bump [`e56432d`](https://github.com/netcookies/isulewTools/commit/e56432d)
- Bump Gradle Version [`c7758c6`](https://github.com/netcookies/isulewTools/commit/c7758c6)

## v1.2.9 (2025-08-22)

### Feature
- 添加获取电量测试 [`eaa2bb4`](https://github.com/netcookies/isulewTools/commit/eaa2bb4)
- 添加反射调用car service工具类 [`c6a2b6c`](https://github.com/netcookies/isulewTools/commit/c6a2b6c)

### Bug Fixes
- 速度不会按需显示 [`e345b9f`](https://github.com/netcookies/isulewTools/commit/e345b9f)

## v1.2.8 (2025-08-22)

### Feature
- 增加悬浮窗字体透明度调整 [`3474a4d`](https://github.com/netcookies/isulewTools/commit/3474a4d)
- 适配shizuku [`760af8f`](https://github.com/netcookies/isulewTools/commit/760af8f)
- 添加实验性功能 [`ee01655`](https://github.com/netcookies/isulewTools/commit/ee01655)

### Bug Fixes
- 修复滚动问题 [`d53d986`](https://github.com/netcookies/isulewTools/commit/d53d986)
- 完善适配日志 [`f2c432d`](https://github.com/netcookies/isulewTools/commit/f2c432d)
- 修复圆角 [`7d80e7b`](https://github.com/netcookies/isulewTools/commit/7d80e7b)

## v1.2.7 (2025-08-20)

### Feature
- 添加shizuku工具类和 root 工具类 [`191b5df`](https://github.com/netcookies/isulewTools/commit/191b5df)
- 添加root调用方法 [`6b392d7`](https://github.com/netcookies/isulewTools/commit/6b392d7)

### Bug Fixes
- 按需显示为30也时隐藏 [`9d98771`](https://github.com/netcookies/isulewTools/commit/9d98771)
- 按需显示为 0 时隐藏 [`4f9e57b`](https://github.com/netcookies/isulewTools/commit/4f9e57b)
- 去除无用的Emoji [`7612a0b`](https://github.com/netcookies/isulewTools/commit/7612a0b)

## v1.2.6 (2025-08-20)

### Bug Fixes
- 删除无用变量 [`4398aa8`](https://github.com/netcookies/isulewTools/commit/4398aa8)
- 弹出窗口过大，导致按钮无法显示 [`f6924de`](https://github.com/netcookies/isulewTools/commit/f6924de)

### Other
- Bump sdk version [`b84dcea`](https://github.com/netcookies/isulewTools/commit/b84dcea)
- Bump sdk version [`eebd81f`](https://github.com/netcookies/isulewTools/commit/eebd81f)
- Bump gradle version [`b554871`](https://github.com/netcookies/isulewTools/commit/b554871)
- Merge pull request #2 [`cbcce14`](https://github.com/netcookies/isulewTools/commit/cbcce14)

## v1.2.5 (2025-08-18)

### Feature
- 增加方形悬浮窗 [`c6b6c5a`](https://github.com/netcookies/isulewTools/commit/c6b6c5a)
- 适配黑夜与白天的颜色 [`792fec7`](https://github.com/netcookies/isulewTools/commit/792fec7)
- 字体大小微调 [`882515f`](https://github.com/netcookies/isulewTools/commit/882515f)
- 字符资源化 [`eec7f6e`](https://github.com/netcookies/isulewTools/commit/eec7f6e)
- ui全部重构完成 TODO: 电池图标 适配 adb root [`d439e7d`](https://github.com/netcookies/isulewTools/commit/d439e7d)
- 完成列表业务逻辑绑定 [`4eea4cd`](https://github.com/netcookies/isulewTools/commit/4eea4cd)
- 完成列表业务逻辑绑定 [`4b89158`](https://github.com/netcookies/isulewTools/commit/4b89158)
- 完成关于页面 [`16d5a1e`](https://github.com/netcookies/isulewTools/commit/16d5a1e)
- 重构 ui [`77011ec`](https://github.com/netcookies/isulewTools/commit/77011ec)
- 重构 ui [`487bc2d`](https://github.com/netcookies/isulewTools/commit/487bc2d)

### Bug Fixes
- 完善悬浮窗按需显示的逻辑 [`55efc3b`](https://github.com/netcookies/isulewTools/commit/55efc3b)
- 增强颜色对比度 [`d0a33f6`](https://github.com/netcookies/isulewTools/commit/d0a33f6)
- 修复亮屏启动顺序上下逻辑 [`03d7699`](https://github.com/netcookies/isulewTools/commit/03d7699)

### Documentation
- 更新文字说明 [`3b84cf8`](https://github.com/netcookies/isulewTools/commit/3b84cf8)

### Build
- 颜色整理 [`690a04e`](https://github.com/netcookies/isulewTools/commit/690a04e)
- Color.White替换 [`90989a1`](https://github.com/netcookies/isulewTools/commit/90989a1)
- theme的颜色替换(fix) [`0650116`](https://github.com/netcookies/isulewTools/commit/0650116)
- theme的颜色替换 [`f5a1c9f`](https://github.com/netcookies/isulewTools/commit/f5a1c9f)
- theme的字体引用完成 [`816d4c2`](https://github.com/netcookies/isulewTools/commit/816d4c2)
- 整理theme [`31b753a`](https://github.com/netcookies/isulewTools/commit/31b753a)
- config cache enabled [`b17e467`](https://github.com/netcookies/isulewTools/commit/b17e467)
- 修复悬浮窗权限检测 TODO: About 页面联系人 [`15283dc`](https://github.com/netcookies/isulewTools/commit/15283dc)
- 修复完所有页面逻辑。修复 monitorLabelMap -> monitorList [`bc5b23f`](https://github.com/netcookies/isulewTools/commit/bc5b23f)
- 引导页修复完成 [`7da2bd0`](https://github.com/netcookies/isulewTools/commit/7da2bd0)
- 代码整理 [`655eef5`](https://github.com/netcookies/isulewTools/commit/655eef5)

## v1.2.3 (2025-08-14)

### Feature
- 修复安装器的权限问题 [`eb0d0ac`](https://github.com/netcookies/isulewTools/commit/eb0d0ac)
- 优化log [`d7faad0`](https://github.com/netcookies/isulewTools/commit/d7faad0)
- 实现日志上传功能 [`cc98290`](https://github.com/netcookies/isulewTools/commit/cc98290)
- 实现日志上传功能 [`09ecd08`](https://github.com/netcookies/isulewTools/commit/09ecd08)

### Bug Fixes
- 完善日志逻辑 [`dd60bc1`](https://github.com/netcookies/isulewTools/commit/dd60bc1)
- 完善日志上传功能 [`eecdb4e`](https://github.com/netcookies/isulewTools/commit/eecdb4e)
- 回退安装方式的实现 [`1cd3443`](https://github.com/netcookies/isulewTools/commit/1cd3443)

### Test
- 尝试指定系统installer [`88433dc`](https://github.com/netcookies/isulewTools/commit/88433dc)
- 测试构造的安装器 [`d8705e7`](https://github.com/netcookies/isulewTools/commit/d8705e7)

### Build
- 删除无用的测试代码 [`03fa8c9`](https://github.com/netcookies/isulewTools/commit/03fa8c9)
- 删除无用的测试代码 [`49b4c1e`](https://github.com/netcookies/isulewTools/commit/49b4c1e)
- format code [`4af7c1b`](https://github.com/netcookies/isulewTools/commit/4af7c1b)

## v1.2.2 (2025-08-12)

### Feature
- 新增白名单fun，统一维护 [`f870a52`](https://github.com/netcookies/isulewTools/commit/f870a52)
- 压缩代码，减小包的体积 [`d4c7fa2`](https://github.com/netcookies/isulewTools/commit/d4c7fa2)

### Bug Fixes
- 修复部分 emoji 显示错误 [`2f6ec0d`](https://github.com/netcookies/isulewTools/commit/2f6ec0d)

### Build
- 优化编译文件 [`4af1d52`](https://github.com/netcookies/isulewTools/commit/4af1d52)
- 优化编译文件 [`45dd787`](https://github.com/netcookies/isulewTools/commit/45dd787)

### Style
- 对齐 switch [`de87d54`](https://github.com/netcookies/isulewTools/commit/de87d54)

## v1.2.1 (2025-08-11)

### Bug Fixes
- 下载地址走github加速 [`01a0713`](https://github.com/netcookies/isulewTools/commit/01a0713)
- 添加“请立即接管车辆”classname进悬浮窗白名单 [`a187958`](https://github.com/netcookies/isulewTools/commit/a187958)
- 添加“请立即接管车辆”classname进悬浮窗白名单 [`ed264ac`](https://github.com/netcookies/isulewTools/commit/ed264ac)

## v1.2.0 (2025-08-11)

### Feature
- 兼容安卓 11 [`5930f7f`](https://github.com/netcookies/isulewTools/commit/5930f7f)
- 单例初始化放在onServiceConnected里 [`c0c4bec`](https://github.com/netcookies/isulewTools/commit/c0c4bec)
- 新增跟车距离 [`68437d9`](https://github.com/netcookies/isulewTools/commit/68437d9)
- 新增跟车距离 [`a5d6e28`](https://github.com/netcookies/isulewTools/commit/a5d6e28)
- all emoji! [`fc21509`](https://github.com/netcookies/isulewTools/commit/fc21509)
- 新增下载和安装失败的提醒 [`7a47e09`](https://github.com/netcookies/isulewTools/commit/7a47e09)
- 添加个不透明的状态栏背景 [`b79617a`](https://github.com/netcookies/isulewTools/commit/b79617a)

### Bug Fixes
- 背景美化，颜控！ [`24130a3`](https://github.com/netcookies/isulewTools/commit/24130a3)
- 添加手势软件和哪吒美式到悬浮窗白名单 [`cfad0cc`](https://github.com/netcookies/isulewTools/commit/cfad0cc)
- 修复方控开关无效 [`24f5233`](https://github.com/netcookies/isulewTools/commit/24f5233)
- 完善检查更新的点击逻辑 [`48f9a3d`](https://github.com/netcookies/isulewTools/commit/48f9a3d)
- 界面优化 [`db00e4c`](https://github.com/netcookies/isulewTools/commit/db00e4c)

### Documentation
- :robot: changelog file generated [`76543d6`](https://github.com/netcookies/isulewTools/commit/76543d6)

### Test
- 检查更新功能测试通过 [`50bec59`](https://github.com/netcookies/isulewTools/commit/50bec59)

### Refactor
- 重构安装的 fun [`118ebe3`](https://github.com/netcookies/isulewTools/commit/118ebe3)

### Other
- todo: 快速上手 [`fbef7e6`](https://github.com/netcookies/isulewTools/commit/fbef7e6)

## v1.1.9 (2025-08-11)

### Continuous Integration
- update release.yml [`b98752e`](https://github.com/netcookies/isulewTools/commit/b98752e)
- Update release.yml [`020cbcf`](https://github.com/netcookies/isulewTools/commit/020cbcf)

## v1.1.8 (2025-08-11)

### Feature
- 检查更新的弹窗 [`bdc9c1a`](https://github.com/netcookies/isulewTools/commit/bdc9c1a)

### Continuous Integration
- 更改CHANGELOG的action [`6c1f451`](https://github.com/netcookies/isulewTools/commit/6c1f451)

### Documentation
- 所有新增字符资源化 [`1436a2b`](https://github.com/netcookies/isulewTools/commit/1436a2b)
- 更新README [`5df4803`](https://github.com/netcookies/isulewTools/commit/5df4803)

### Build
- 新增依赖库解析Markdown/Release Notes [`aaa43ea`](https://github.com/netcookies/isulewTools/commit/aaa43ea)

## v1.1.7 (2025-08-10)

### Continuous Integration
- 完善公开仓 CHANGELOG 生成 [`32535dd`](https://github.com/netcookies/isulewTools/commit/32535dd)

### Other

## v1.1.6 (2025-08-10)

### Bug Fixes
- 修复模拟器判断 [`4b8b93f`](https://github.com/netcookies/isulewTools/commit/4b8b93f)
- 添加超时处理 [`160487c`](https://github.com/netcookies/isulewTools/commit/160487c)

### Continuous Integration
- Update release.yml [`4da17c6`](https://github.com/netcookies/isulewTools/commit/4da17c6)
- Update release.yml [`acf7d37`](https://github.com/netcookies/isulewTools/commit/acf7d37)

### Style
- 优化界面 [`a978b32`](https://github.com/netcookies/isulewTools/commit/a978b32)
- 优化界面 [`780d13d`](https://github.com/netcookies/isulewTools/commit/780d13d)

### Other
- Merge remote-tracking branch 'origin/main' [`0703736`](https://github.com/netcookies/isulewTools/commit/0703736)

## v1.1.5 (2025-08-10)

### Bug Fixes
- c1365e2 Bump Version fix: 完善更新逻辑 [`c1365e2`](https://github.com/netcookies/isulewTools/commit/c1365e2)
- ec0b6d8 Bump Version fix: permission missing [`ec0b6d8`](https://github.com/netcookies/isulewTools/commit/ec0b6d8)

### Chore
- update release action [`47eb5e1`](https://github.com/netcookies/isulewTools/commit/47eb5e1)

### Other

## v1.1.4 (2025-08-10)

### Feature
- 9843f69 Bump Version feat: 增加检查更新逻辑 fix: 一些小的修复 [`9843f69`](https://github.com/netcookies/isulewTools/commit/9843f69)

### Bug Fixes
- 9843f69 Bump Version feat: 增加检查更新逻辑 fix: 一些小的修复 [`9843f69`](https://github.com/netcookies/isulewTools/commit/9843f69)

### Chore
- update release action [`5c2776a`](https://github.com/netcookies/isulewTools/commit/5c2776a)

### Other

## v1.1.3 (2025-08-10)

### Feature
- add public repo for release [`07ece14`](https://github.com/netcookies/isulewTools/commit/07ece14)
- 增加about页面 [`4100a4d`](https://github.com/netcookies/isulewTools/commit/4100a4d)
- 增加车控开关 fix: 完善权限弹窗说明 fix: 修复蓝牙控制编译警告 [`e359850`](https://github.com/netcookies/isulewTools/commit/e359850)
- fix release yml [`088eb86`](https://github.com/netcookies/isulewTools/commit/088eb86)

### Bug Fixes
- 移除没用的变量。 [`04ebf70`](https://github.com/netcookies/isulewTools/commit/04ebf70)
- e359850 feat: 增加车控开关 fix: 完善权限弹窗说明 fix: 修复蓝牙控制编译警告 [`e359850`](https://github.com/netcookies/isulewTools/commit/e359850)

### Documentation
- update changelog for v1.1.3 [skip ci] [`8cb33b0`](https://github.com/netcookies/isulewTools/commit/8cb33b0)
- update changelog for main [skip ci] [`8400766`](https://github.com/netcookies/isulewTools/commit/8400766)

### Other
- update yml [`3dffade`](https://github.com/netcookies/isulewTools/commit/3dffade)
- Del CHANGELOG [`3e2ca09`](https://github.com/netcookies/isulewTools/commit/3e2ca09)
- Changelog [`1eee60c`](https://github.com/netcookies/isulewTools/commit/1eee60c)
- Merged [`e4a12cf`](https://github.com/netcookies/isulewTools/commit/e4a12cf)

## v1.1.2 (2025-08-09)

### Feature
- 尝试注册方控接收器 [`0f672cd`](https://github.com/netcookies/isulewTools/commit/0f672cd)
- added key mapper [`b10075e`](https://github.com/netcookies/isulewTools/commit/b10075e)

### Bug Fixes
- 5c0e706 Bump Version fix: remove warning [`5c0e706`](https://github.com/netcookies/isulewTools/commit/5c0e706)
- remove unused import。 [`62d0858`](https://github.com/netcookies/isulewTools/commit/62d0858)

### Documentation
- update changelog for main [skip ci] [`123b735`](https://github.com/netcookies/isulewTools/commit/123b735)

### Other
- Merge branch 'main' of https://github.com/netcookies/isulewTools [`f13d807`](https://github.com/netcookies/isulewTools/commit/f13d807)
- Update bump-version-tag.yml [`901f68a`](https://github.com/netcookies/isulewTools/commit/901f68a)

## v1.1.1 (2025-08-08)

### Feature
- 新增一个透明的activity用于后台启动 app fix: 蓝牙关闭的触发开关修复。 [`dfd0d59`](https://github.com/netcookies/isulewTools/commit/dfd0d59)
- 新增一个透明的activity用于后台启动 app fix: 蓝牙关闭的触发开关修复。 [`942461a`](https://github.com/netcookies/isulewTools/commit/942461a)
- 清除无用的import [`574bd11`](https://github.com/netcookies/isulewTools/commit/574bd11)
- 优化无障碍服务结构 [`4847e7f`](https://github.com/netcookies/isulewTools/commit/4847e7f)

### Bug Fixes
- e4a2382 Bump Version fix: 移除一些WARNING。 [`e4a2382`](https://github.com/netcookies/isulewTools/commit/e4a2382)
- LaunchProxyActivity.kt不够透明的 bug🐶。 [`4084739`](https://github.com/netcookies/isulewTools/commit/4084739)
- dfd0d59 feat: 新增一个透明的activity用于后台启动 app fix: 蓝牙关闭的触发开关修复。 [`dfd0d59`](https://github.com/netcookies/isulewTools/commit/dfd0d59)
- 942461a feat: 新增一个透明的activity用于后台启动 app fix: 蓝牙关闭的触发开关修复。 [`942461a`](https://github.com/netcookies/isulewTools/commit/942461a)

### Documentation
- update changelog for main [skip ci] [`0c488ad`](https://github.com/netcookies/isulewTools/commit/0c488ad)

### Other
- Merge remote-tracking branch 'refs/remotes/origin/main' Bump Version [`8d748b3`](https://github.com/netcookies/isulewTools/commit/8d748b3)

## v1.1.0 (2025-08-07)

### Bug Fixes
- 75903da Bump Version fix: 隐藏桌上角图标调整点位置 [`75903da`](https://github.com/netcookies/isulewTools/commit/75903da)
- 431f4b9 Bump Version fix: 隐藏桌上角图标调整点位置 [`431f4b9`](https://github.com/netcookies/isulewTools/commit/431f4b9)

### Documentation
- update changelog for main [skip ci] [`671d9c1`](https://github.com/netcookies/isulewTools/commit/671d9c1)

### Other
- Merge remote-tracking branch 'origin/main' [`cefe6e7`](https://github.com/netcookies/isulewTools/commit/cefe6e7)

## v1.0.9 (2025-08-07)

### Feature
- ae0b51a Bump Version feat: 新增 ACC/LCC 未打开时隐藏（透明度为 0） fix: 隐藏桌上角图标调整点位置 [`ae0b51a`](https://github.com/netcookies/isulewTools/commit/ae0b51a)
- f9828c2 Bump Version feat: 新增 ACC/LCC 未打开时隐藏（透明度为 0） fix: 隐藏桌上角图标调整点位置 [`f9828c2`](https://github.com/netcookies/isulewTools/commit/f9828c2)

### Bug Fixes
- ae0b51a Bump Version feat: 新增 ACC/LCC 未打开时隐藏（透明度为 0） fix: 隐藏桌上角图标调整点位置 [`ae0b51a`](https://github.com/netcookies/isulewTools/commit/ae0b51a)
- f9828c2 Bump Version feat: 新增 ACC/LCC 未打开时隐藏（透明度为 0） fix: 隐藏桌上角图标调整点位置 [`f9828c2`](https://github.com/netcookies/isulewTools/commit/f9828c2)

### Other
- Update bump-version-tag.yml [`80c573c`](https://github.com/netcookies/isulewTools/commit/80c573c)

## v1.0.8 (2025-08-07)

### Feature
- 新增一种悬浮窗颜色逻辑。Acc 可开，Lcc 不可开 [`90dae4f`](https://github.com/netcookies/isulewTools/commit/90dae4f)

### Bug Fixes
- 修复悬浮窗无法关闭的 bug。优化性能。 [`87f3bc8`](https://github.com/netcookies/isulewTools/commit/87f3bc8)
- 隐藏悬浮窗权限问题 [`ee08cd5`](https://github.com/netcookies/isulewTools/commit/ee08cd5)

### Other
- Merged [`2fd05e9`](https://github.com/netcookies/isulewTools/commit/2fd05e9)
- Bump sdk to 36 [`896ec89`](https://github.com/netcookies/isulewTools/commit/896ec89)

## v1.0.7 (2025-08-07)

### Feature
- add an action for creating a tag when commit has text “Bump Version” [`e48350c`](https://github.com/netcookies/isulewTools/commit/e48350c)

### Bug Fixes
- ForegroundAppMonitorService.kt [`cf0609b`](https://github.com/netcookies/isulewTools/commit/cf0609b)
- Update CarInfoOverlay.kt [`927ed99`](https://github.com/netcookies/isulewTools/commit/927ed99)
- Update FloatCarInfoWindow.kt [`b17fbf9`](https://github.com/netcookies/isulewTools/commit/b17fbf9)
- Update CarInfoOverlay.kt [`9f90059`](https://github.com/netcookies/isulewTools/commit/9f90059)
- Update LogcatCarInfoMonitor.kt [`d3f86db`](https://github.com/netcookies/isulewTools/commit/d3f86db)
- Update LogcatCollector.kt [`5ffea55`](https://github.com/netcookies/isulewTools/commit/5ffea55)
- release.yml [`79a0495`](https://github.com/netcookies/isulewTools/commit/79a0495)
- Update release.yml [`1b349b7`](https://github.com/netcookies/isulewTools/commit/1b349b7)
- release.yml [`2cbaf8c`](https://github.com/netcookies/isulewTools/commit/2cbaf8c)

### Documentation
- update changelog for main [skip ci] [`e24dd02`](https://github.com/netcookies/isulewTools/commit/e24dd02)
- update changelog for main [skip ci] [`301afd2`](https://github.com/netcookies/isulewTools/commit/301afd2)
- update changelog for main [skip ci] [`846919a`](https://github.com/netcookies/isulewTools/commit/846919a)
- update changelog for main [skip ci] [`7d38359`](https://github.com/netcookies/isulewTools/commit/7d38359)
- update changelog for main [skip ci] [`79e7bca`](https://github.com/netcookies/isulewTools/commit/79e7bca)
- update changelog for main [skip ci] [`f1b5795`](https://github.com/netcookies/isulewTools/commit/f1b5795)
- update changelog for main [`738c268`](https://github.com/netcookies/isulewTools/commit/738c268)

### Other
- Update release.yml [`25c690a`](https://github.com/netcookies/isulewTools/commit/25c690a)
- Merge pull request #1 from netcookies/copilot/fix-0f139135-3750-46bd-9428-c4c3a497d786 [`0b65d7e`](https://github.com/netcookies/isulewTools/commit/0b65d7e)
- Initial plan [`268c994`](https://github.com/netcookies/isulewTools/commit/268c994)
- Fix: Update FloatCarInfoWindow.kt [`00cd556`](https://github.com/netcookies/isulewTools/commit/00cd556)
- Delete CHANGELOG.md [`db7110d`](https://github.com/netcookies/isulewTools/commit/db7110d)
- Update release.yml [`a8199c6`](https://github.com/netcookies/isulewTools/commit/a8199c6)
- Delete CHANGELOG.md [`8bb28e0`](https://github.com/netcookies/isulewTools/commit/8bb28e0)
- Update release.yml [`997d1a4`](https://github.com/netcookies/isulewTools/commit/997d1a4)
- Update release.yml [`db3556a`](https://github.com/netcookies/isulewTools/commit/db3556a)
- Update release.yml [`1413fbc`](https://github.com/netcookies/isulewTools/commit/1413fbc)
- Update release.yml [`9cc6fdd`](https://github.com/netcookies/isulewTools/commit/9cc6fdd)
- Update release.yml [`b693ea7`](https://github.com/netcookies/isulewTools/commit/b693ea7)
- Update release.yml [`aff85ab`](https://github.com/netcookies/isulewTools/commit/aff85ab)
- Update release.yml [`8d8f4a6`](https://github.com/netcookies/isulewTools/commit/8d8f4a6)
- Update release.yml [`a0fcde4`](https://github.com/netcookies/isulewTools/commit/a0fcde4)
- Update release.yml [`ec45a89`](https://github.com/netcookies/isulewTools/commit/ec45a89)
- Update release.yml [`21d7823`](https://github.com/netcookies/isulewTools/commit/21d7823)
- Update release.yml [`349d75e`](https://github.com/netcookies/isulewTools/commit/349d75e)
- Update README.md [`74adccd`](https://github.com/netcookies/isulewTools/commit/74adccd)
- Update release.yml [`dd872f1`](https://github.com/netcookies/isulewTools/commit/dd872f1)
- Update README.md [`126a1ef`](https://github.com/netcookies/isulewTools/commit/126a1ef)
- Update release.yml [`f3b7fc2`](https://github.com/netcookies/isulewTools/commit/f3b7fc2)
- Update release.yml [`810f89a`](https://github.com/netcookies/isulewTools/commit/810f89a)
- Update release.yml [`4bab610`](https://github.com/netcookies/isulewTools/commit/4bab610)
- Update release.yml [`6743a2f`](https://github.com/netcookies/isulewTools/commit/6743a2f)
- Update release.yml [`4fe606d`](https://github.com/netcookies/isulewTools/commit/4fe606d)

## v1.0.6 (2025-08-06)

### Feature
- c83052b docs: 增加README feat: 增加Release流程 [`c83052b`](https://github.com/netcookies/isulewTools/commit/c83052b)
- 添加保存拖动后的位置。 feat: 监控前台包名，若为弹窗页签的包名，滑动下桌上角隐藏语音小图标 [`f4cf58c`](https://github.com/netcookies/isulewTools/commit/f4cf58c)

### Documentation
- 增加MIT LICENSE [`127a264`](https://github.com/netcookies/isulewTools/commit/127a264)
- 增加README feat: 增加Release流程 [`c83052b`](https://github.com/netcookies/isulewTools/commit/c83052b)

### Other
- Merge remote-tracking branch 'refs/remotes/origin/main' [`9db78bc`](https://github.com/netcookies/isulewTools/commit/9db78bc)
- Create README file with project guide [`15fdc27`](https://github.com/netcookies/isulewTools/commit/15fdc27)
- Update release.yml to integrate CHANGELOG.md generation and Release Notes functionality [`fdbf555`](https://github.com/netcookies/isulewTools/commit/fdbf555)
- Add GitHub Actions workflow for APK release [`6ebdc25`](https://github.com/netcookies/isulewTools/commit/6ebdc25)
- Update CarInfoOverlay.kt [`86d4ecd`](https://github.com/netcookies/isulewTools/commit/86d4ecd)
- Update ForegroundAppMonitorService.kt [`99d5684`](https://github.com/netcookies/isulewTools/commit/99d5684)
- Update ForegroundAppMonitorService.kt [`e008781`](https://github.com/netcookies/isulewTools/commit/e008781)
- 添加人脸认证的包名到悬浮窗。 [`2565c8f`](https://github.com/netcookies/isulewTools/commit/2565c8f)
- 添加人脸认证的包名到悬浮窗。 [`83cae6b`](https://github.com/netcookies/isulewTools/commit/83cae6b)
- 添加人脸认证的包名到悬浮窗。 [`a974a99`](https://github.com/netcookies/isulewTools/commit/a974a99)
- 添加人脸认证的包名到悬浮窗。 [`aaa8727`](https://github.com/netcookies/isulewTools/commit/aaa8727)
- 添加转弯的包名到悬浮窗。 [`a604495`](https://github.com/netcookies/isulewTools/commit/a604495)
- 悬浮窗可用。发布第一版 [`03a8dd0`](https://github.com/netcookies/isulewTools/commit/03a8dd0)
- 修自启动问题（启动线程移至服务） [`d5a9545`](https://github.com/netcookies/isulewTools/commit/d5a9545)
- 修自启动问题（启动线程移至服务） [`f21b614`](https://github.com/netcookies/isulewTools/commit/f21b614)
- 修自启动问题（启动线程移至服务） [`b0a34b7`](https://github.com/netcookies/isulewTools/commit/b0a34b7)
- bump version. [`2c231a9`](https://github.com/netcookies/isulewTools/commit/2c231a9)
- bump version. [`4fa3dc7`](https://github.com/netcookies/isulewTools/commit/4fa3dc7)
- 添加自启动的日志 [`878d99e`](https://github.com/netcookies/isulewTools/commit/878d99e)
- 修复一堆单例的问题。美化日志 [`b838d0c`](https://github.com/netcookies/isulewTools/commit/b838d0c)
- 修复布局默认值问题。 修复蓝牙页签跳居中问题。 [`51c0a8e`](https://github.com/netcookies/isulewTools/commit/51c0a8e)
- 用composeview 实现悬浮窗 ui [`cb22e9a`](https://github.com/netcookies/isulewTools/commit/cb22e9a)
- 重构悬浮窗 ui [`4a108af`](https://github.com/netcookies/isulewTools/commit/4a108af)
- 修复小bug，资源化。 [`e5a1034`](https://github.com/netcookies/isulewTools/commit/e5a1034)
- 生成新图标。 [`6b34d8e`](https://github.com/netcookies/isulewTools/commit/6b34d8e)
- 尝试修复开机的 wifibug [`351eead`](https://github.com/netcookies/isulewTools/commit/351eead)
- 尝试修复开机的 wifibug [`6d89a04`](https://github.com/netcookies/isulewTools/commit/6d89a04)
- 尝试修复开机的 wifibug [`902efe6`](https://github.com/netcookies/isulewTools/commit/902efe6)
- 删除无用的文件 [`c8c1f54`](https://github.com/netcookies/isulewTools/commit/c8c1f54)
- 删除无用的文件 [`6c7bad6`](https://github.com/netcookies/isulewTools/commit/6c7bad6)
- 完善悬浮窗逻辑 [`5795e9d`](https://github.com/netcookies/isulewTools/commit/5795e9d)
- 实现悬浮窗功能 [`b8f03c8`](https://github.com/netcookies/isulewTools/commit/b8f03c8)
- 代码优化。 [`76da5aa`](https://github.com/netcookies/isulewTools/commit/76da5aa)
- 彻底修复日志问题。 [`c672402`](https://github.com/netcookies/isulewTools/commit/c672402)
- 继续优化代码。 [`9ea202d`](https://github.com/netcookies/isulewTools/commit/9ea202d)
- 优化代码。修复日志重复打印问题 [`2c3172c`](https://github.com/netcookies/isulewTools/commit/2c3172c)
- 日志监听修复bug [`825d09a`](https://github.com/netcookies/isulewTools/commit/825d09a)
- 尝试修复日志 bug [`a99cbe6`](https://github.com/netcookies/isulewTools/commit/a99cbe6)
- 修复添加按钮消失的问题 [`5f05f50`](https://github.com/netcookies/isulewTools/commit/5f05f50)
- 优化图标显示和下拉框 [`2c8b7fe`](https://github.com/netcookies/isulewTools/commit/2c8b7fe)
- 修复悬浮开关配置被override。 [`15f10e7`](https://github.com/netcookies/isulewTools/commit/15f10e7)
- 代码优化完成 [`5af4c2b`](https://github.com/netcookies/isulewTools/commit/5af4c2b)
- 重构MainActivity [`2790226`](https://github.com/netcookies/isulewTools/commit/2790226)
- 重构MainActivity [`b2ba299`](https://github.com/netcookies/isulewTools/commit/b2ba299)
- 重构。还差MainActivity [`60d813f`](https://github.com/netcookies/isulewTools/commit/60d813f)
- 升级版本 [`6a39491`](https://github.com/netcookies/isulewTools/commit/6a39491)
- 增加悬浮窗开关。 [`7f9a0f5`](https://github.com/netcookies/isulewTools/commit/7f9a0f5)
- 提升健壮性。 [`39eef6d`](https://github.com/netcookies/isulewTools/commit/39eef6d)
- 添加亮屏自启动功能。 [`0c0dd5e`](https://github.com/netcookies/isulewTools/commit/0c0dd5e)
- 移除launcher ready事件。无效 [`fccd41f`](https://github.com/netcookies/isulewTools/commit/fccd41f)
- Bug fixs.更改日志输出路径。 [`0a88f89`](https://github.com/netcookies/isulewTools/commit/0a88f89)
- version 0.1.5基本可用，等待弹窗车机测试 [`fccee3c`](https://github.com/netcookies/isulewTools/commit/fccee3c)
- version 0.1.5基本可用，等待弹窗车机测试 [`b6ccd76`](https://github.com/netcookies/isulewTools/commit/b6ccd76)
- Adust TabRow margin bottom [`481453d`](https://github.com/netcookies/isulewTools/commit/481453d)
- 测试跳过usb功能。完善日志 [`ac9e2be`](https://github.com/netcookies/isulewTools/commit/ac9e2be)
- Remove release directory from Git and ignore it [`0c86f95`](https://github.com/netcookies/isulewTools/commit/0c86f95)
- Add usb handler tab [`738cd8f`](https://github.com/netcookies/isulewTools/commit/738cd8f)
- add tab [`05f0d6d`](https://github.com/netcookies/isulewTools/commit/05f0d6d)
- Add tabrow [`3389706`](https://github.com/netcookies/isulewTools/commit/3389706)
- Update [`645ca07`](https://github.com/netcookies/isulewTools/commit/645ca07)
- Update [`de128bb`](https://github.com/netcookies/isulewTools/commit/de128bb)
- Update [`ef71ee5`](https://github.com/netcookies/isulewTools/commit/ef71ee5)
- Init [`f96ea85`](https://github.com/netcookies/isulewTools/commit/f96ea85)

