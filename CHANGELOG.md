# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html) and to [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).

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

