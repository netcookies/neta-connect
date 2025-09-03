# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html) and to [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).

## v1.4.0 (2025-09-03)

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
- Bump Version [`bd4ab50`](https://github.com/netcookies/isulewTools/commit/bd4ab50)
- Bump Version [`93e63de`](https://github.com/netcookies/isulewTools/commit/93e63de)

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

### Other
- Bump Version [`0ccac83`](https://github.com/netcookies/isulewTools/commit/0ccac83)

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

### Other
- Bump Version [`5451ef5`](https://github.com/netcookies/isulewTools/commit/5451ef5)

## v1.3.7 (2025-08-28)

### Test
- 更新电量测试 [`5070710`](https://github.com/netcookies/isulewTools/commit/5070710)

### Other
- Bump Version [`7bfd26f`](https://github.com/netcookies/isulewTools/commit/7bfd26f)

## v1.3.6 (2025-08-28)

### Feature
- 新增车辆属性清单 [`3911632`](https://github.com/netcookies/isulewTools/commit/3911632)

### Bug Fixes
- 更新权限 [`59d97ca`](https://github.com/netcookies/isulewTools/commit/59d97ca)
- 更新内容设置最小宽度 [`edf4155`](https://github.com/netcookies/isulewTools/commit/edf4155)
- 悬浮窗太小时缩在一起的问题 [`8614a13`](https://github.com/netcookies/isulewTools/commit/8614a13)

### Test
- 电量测试 [`43e71eb`](https://github.com/netcookies/isulewTools/commit/43e71eb)

### Other
- Bump Version [`993b8c9`](https://github.com/netcookies/isulewTools/commit/993b8c9)

## v1.3.5 (2025-08-27)

### Feature
- 增加更新日志的按钮 [`cbd72f9`](https://github.com/netcookies/isulewTools/commit/cbd72f9)
- 跨版本升级返回多版本日志 [`d9a0736`](https://github.com/netcookies/isulewTools/commit/d9a0736)
- 苹果圆角 [`4a73a4a`](https://github.com/netcookies/isulewTools/commit/4a73a4a)
- 悬浮窗支持加载和保存样式 [`226967f`](https://github.com/netcookies/isulewTools/commit/226967f)

### Bug Fixes
- 移除无用的import [`1ac3f72`](https://github.com/netcookies/isulewTools/commit/1ac3f72)

### Other
- Bump Version [`05ab471`](https://github.com/netcookies/isulewTools/commit/05ab471)

## v1.3.4 (2025-08-26)

### Bug Fixes
- 修复自动更新问题 [`1f2f209`](https://github.com/netcookies/isulewTools/commit/1f2f209)

### Other
- Bump Version [`fdb6a75`](https://github.com/netcookies/isulewTools/commit/fdb6a75)

## v1.3.3 (2025-08-26)

### Bug Fixes
- 修复空指针问题 [`dfd9834`](https://github.com/netcookies/isulewTools/commit/dfd9834)

### Test
- 测试蓝牙功能(shizuku) [`51960bc`](https://github.com/netcookies/isulewTools/commit/51960bc)

### Other
- Bump Version [`22d9cee`](https://github.com/netcookies/isulewTools/commit/22d9cee)

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

### Other
- Bump Version [`f7e9d77`](https://github.com/netcookies/isulewTools/commit/f7e9d77)

## v1.3.1 (2025-08-25)

### Bug Fixes
- 修复日志参数问题 [`8811661`](https://github.com/netcookies/isulewTools/commit/8811661)

### Build
- 整理代码 [`3a7b2d5`](https://github.com/netcookies/isulewTools/commit/3a7b2d5)

### Other
- Bump Version [`cfda0d2`](https://github.com/netcookies/isulewTools/commit/cfda0d2)

## v1.3.0 (2025-08-24)

### Bug Fixes
- 优化悬浮窗数据的匹配效率 [`fa53594`](https://github.com/netcookies/isulewTools/commit/fa53594)
- update toast display issue [`030ed18`](https://github.com/netcookies/isulewTools/commit/030ed18)
- update toast display issue [`69ddf6b`](https://github.com/netcookies/isulewTools/commit/69ddf6b)
- update toast display issue [`49c3f33`](https://github.com/netcookies/isulewTools/commit/49c3f33)

### Other
- Bump Version [`33c52f9`](https://github.com/netcookies/isulewTools/commit/33c52f9)
- Bump [`e56432d`](https://github.com/netcookies/isulewTools/commit/e56432d)
- Bump Gradle Version [`c7758c6`](https://github.com/netcookies/isulewTools/commit/c7758c6)

## v1.2.9 (2025-08-22)

### Feature
- 添加获取电量测试 [`eaa2bb4`](https://github.com/netcookies/isulewTools/commit/eaa2bb4)
- 添加反射调用car service工具类 [`c6a2b6c`](https://github.com/netcookies/isulewTools/commit/c6a2b6c)

### Bug Fixes
- 速度不会按需显示 [`e345b9f`](https://github.com/netcookies/isulewTools/commit/e345b9f)

### Other
- Bump Version [`182b3f9`](https://github.com/netcookies/isulewTools/commit/182b3f9)

## v1.2.8 (2025-08-22)

### Feature
- 增加悬浮窗字体透明度调整 [`3474a4d`](https://github.com/netcookies/isulewTools/commit/3474a4d)
- 适配shizuku [`760af8f`](https://github.com/netcookies/isulewTools/commit/760af8f)
- 添加实验性功能 [`ee01655`](https://github.com/netcookies/isulewTools/commit/ee01655)

### Bug Fixes
- 修复滚动问题 [`d53d986`](https://github.com/netcookies/isulewTools/commit/d53d986)
- 完善适配日志 [`f2c432d`](https://github.com/netcookies/isulewTools/commit/f2c432d)
- 修复圆角 [`7d80e7b`](https://github.com/netcookies/isulewTools/commit/7d80e7b)

### Other
- Bump Version [`37feca9`](https://github.com/netcookies/isulewTools/commit/37feca9)

## v1.2.7 (2025-08-20)

### Feature
- 添加shizuku工具类和 root 工具类 [`191b5df`](https://github.com/netcookies/isulewTools/commit/191b5df)
- 添加root调用方法 [`6b392d7`](https://github.com/netcookies/isulewTools/commit/6b392d7)

### Bug Fixes
- 按需显示为30也时隐藏 [`9d98771`](https://github.com/netcookies/isulewTools/commit/9d98771)
- 按需显示为 0 时隐藏 [`4f9e57b`](https://github.com/netcookies/isulewTools/commit/4f9e57b)
- 去除无用的Emoji [`7612a0b`](https://github.com/netcookies/isulewTools/commit/7612a0b)

### Other
- Bump Version [`ef5f64b`](https://github.com/netcookies/isulewTools/commit/ef5f64b)

## v1.2.6 (2025-08-20)

### Bug Fixes
- 删除无用变量 [`4398aa8`](https://github.com/netcookies/isulewTools/commit/4398aa8)
- 弹出窗口过大，导致按钮无法显示 [`f6924de`](https://github.com/netcookies/isulewTools/commit/f6924de)

### Other
- Bump Version [`54862f4`](https://github.com/netcookies/isulewTools/commit/54862f4)
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

### Other
- Bump Version [`9f0260c`](https://github.com/netcookies/isulewTools/commit/9f0260c)

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

### Other
- Bump Version [`56b9c33`](https://github.com/netcookies/isulewTools/commit/56b9c33)

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

### Other
- Bump Version [`e95207b`](https://github.com/netcookies/isulewTools/commit/e95207b)

## v1.2.1 (2025-08-11)

### Bug Fixes
- 下载地址走github加速 [`01a0713`](https://github.com/netcookies/isulewTools/commit/01a0713)
- 添加“请立即接管车辆”classname进悬浮窗白名单 [`a187958`](https://github.com/netcookies/isulewTools/commit/a187958)
- 添加“请立即接管车辆”classname进悬浮窗白名单 [`ed264ac`](https://github.com/netcookies/isulewTools/commit/ed264ac)

### Other
- Bump Version [`3c68694`](https://github.com/netcookies/isulewTools/commit/3c68694)

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
- Bump Version [`7e3016e`](https://github.com/netcookies/isulewTools/commit/7e3016e)
- Bump Version [`a4a1ca0`](https://github.com/netcookies/isulewTools/commit/a4a1ca0)
- todo: 快速上手 [`fbef7e6`](https://github.com/netcookies/isulewTools/commit/fbef7e6)
- Bump Version [`9e81bdf`](https://github.com/netcookies/isulewTools/commit/9e81bdf)
- Bump Version [`ed46cfc`](https://github.com/netcookies/isulewTools/commit/ed46cfc)

## v1.1.9 (2025-08-11)

### Continuous Integration
- update release.yml [`b98752e`](https://github.com/netcookies/isulewTools/commit/b98752e)
- Update release.yml [`020cbcf`](https://github.com/netcookies/isulewTools/commit/020cbcf)

### Other
- Bump Version [`b413b82`](https://github.com/netcookies/isulewTools/commit/b413b82)

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

### Other
- Bump Version [`1f02cf1`](https://github.com/netcookies/isulewTools/commit/1f02cf1)

## v1.1.7 (2025-08-10)

### Continuous Integration
- 完善公开仓 CHANGELOG 生成 [`32535dd`](https://github.com/netcookies/isulewTools/commit/32535dd)

### Other
- Bump Version [`65e4e94`](https://github.com/netcookies/isulewTools/commit/65e4e94)
- Bump Version [`da03542`](https://github.com/netcookies/isulewTools/commit/da03542)

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
- Bump Version [`57887fa`](https://github.com/netcookies/isulewTools/commit/57887fa)
- Merge remote-tracking branch 'origin/main' [`0703736`](https://github.com/netcookies/isulewTools/commit/0703736)
- Bump Version [`730528f`](https://github.com/netcookies/isulewTools/commit/730528f)

## v1.1.5 (2025-08-10)

### Bug Fixes
- c1365e2 Bump Version fix: 完善更新逻辑 [`c1365e2`](https://github.com/netcookies/isulewTools/commit/c1365e2)
- ec0b6d8 Bump Version fix: permission missing [`ec0b6d8`](https://github.com/netcookies/isulewTools/commit/ec0b6d8)

### Chore
- update release action [`47eb5e1`](https://github.com/netcookies/isulewTools/commit/47eb5e1)

### Other
- Bump Version [`05f6e3f`](https://github.com/netcookies/isulewTools/commit/05f6e3f)
- Bump Version [`8e5ee83`](https://github.com/netcookies/isulewTools/commit/8e5ee83)

## v1.1.4 (2025-08-10)

### Feature
- 9843f69 Bump Version feat: 增加检查更新逻辑 fix: 一些小的修复 [`9843f69`](https://github.com/netcookies/isulewTools/commit/9843f69)

### Bug Fixes
- 9843f69 Bump Version feat: 增加检查更新逻辑 fix: 一些小的修复 [`9843f69`](https://github.com/netcookies/isulewTools/commit/9843f69)

### Chore
- update release action [`5c2776a`](https://github.com/netcookies/isulewTools/commit/5c2776a)

### Other
- Bump Version [`bf0a066`](https://github.com/netcookies/isulewTools/commit/bf0a066)
- Bump Version [`84fda01`](https://github.com/netcookies/isulewTools/commit/84fda01)
- Bump Version [`108af64`](https://github.com/netcookies/isulewTools/commit/108af64)
- Bump Version [`8629dee`](https://github.com/netcookies/isulewTools/commit/8629dee)
- Bump Version [`d9f1266`](https://github.com/netcookies/isulewTools/commit/d9f1266)
- Bump Version [`55250e2`](https://github.com/netcookies/isulewTools/commit/55250e2)
- Bump Version [`af0ac60`](https://github.com/netcookies/isulewTools/commit/af0ac60)
- Bump Version [`723bae3`](https://github.com/netcookies/isulewTools/commit/723bae3)
- Bump Version [`cda3ebe`](https://github.com/netcookies/isulewTools/commit/cda3ebe)
- Bump Version [`7f1b9d1`](https://github.com/netcookies/isulewTools/commit/7f1b9d1)
- Bump Version [`3052be0`](https://github.com/netcookies/isulewTools/commit/3052be0)
- Bump Version [`8f9153a`](https://github.com/netcookies/isulewTools/commit/8f9153a)

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
- Bump Version [`4ef2c1f`](https://github.com/netcookies/isulewTools/commit/4ef2c1f)
- Bump Version [`b0976cb`](https://github.com/netcookies/isulewTools/commit/b0976cb)
- Bump Version [`b731103`](https://github.com/netcookies/isulewTools/commit/b731103)
- Bump Version [`41c9c9d`](https://github.com/netcookies/isulewTools/commit/41c9c9d)
- update yml [`3dffade`](https://github.com/netcookies/isulewTools/commit/3dffade)
- Del CHANGELOG [`3e2ca09`](https://github.com/netcookies/isulewTools/commit/3e2ca09)
- Bump Version [`4429aa7`](https://github.com/netcookies/isulewTools/commit/4429aa7)
- Bump Version [`cd3b5f0`](https://github.com/netcookies/isulewTools/commit/cd3b5f0)
- Bump Version [`ce8827a`](https://github.com/netcookies/isulewTools/commit/ce8827a)
- Bump Version [`1b5075b`](https://github.com/netcookies/isulewTools/commit/1b5075b)
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
- Bump Version [`c61f7ea`](https://github.com/netcookies/isulewTools/commit/c61f7ea)
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
- Bump Version [`623d52c`](https://github.com/netcookies/isulewTools/commit/623d52c)
- Bump Version [`7068e78`](https://github.com/netcookies/isulewTools/commit/7068e78)
- Bump Version [`26dcc5e`](https://github.com/netcookies/isulewTools/commit/26dcc5e)
- Bump Version [`a48ef09`](https://github.com/netcookies/isulewTools/commit/a48ef09)
- Bump Version [`3b32849`](https://github.com/netcookies/isulewTools/commit/3b32849)
- Bump Version [`2cc2432`](https://github.com/netcookies/isulewTools/commit/2cc2432)
- Update release.yml [`25c690a`](https://github.com/netcookies/isulewTools/commit/25c690a)
- Bump Version [`ee4cd5a`](https://github.com/netcookies/isulewTools/commit/ee4cd5a)
- Bump Version [`8faa1fd`](https://github.com/netcookies/isulewTools/commit/8faa1fd)
- Bump Version [`055bec7`](https://github.com/netcookies/isulewTools/commit/055bec7)
- Bump Version [`c465892`](https://github.com/netcookies/isulewTools/commit/c465892)
- Bump Version [`4ecfbb7`](https://github.com/netcookies/isulewTools/commit/4ecfbb7)
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
- Bump Versions [`07faadc`](https://github.com/netcookies/isulewTools/commit/07faadc)
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
- Bump Version.添加权限检测。 [`03108a0`](https://github.com/netcookies/isulewTools/commit/03108a0)
- Bump Version.添加亮屏、launcher ready事件。 [`23934db`](https://github.com/netcookies/isulewTools/commit/23934db)
- Bump Version.添加日志抓取 [`f42bda7`](https://github.com/netcookies/isulewTools/commit/f42bda7)
- Bump Version.修复亮屏时蓝牙导致的wifi开关无法打开 [`56746d4`](https://github.com/netcookies/isulewTools/commit/56746d4)
- Bump Version. FIX: default set override though is empty by manual [`4a22bbd`](https://github.com/netcookies/isulewTools/commit/4a22bbd)
- version 0.1.5基本可用，等待弹窗车机测试 [`fccee3c`](https://github.com/netcookies/isulewTools/commit/fccee3c)
- version 0.1.5基本可用，等待弹窗车机测试 [`b6ccd76`](https://github.com/netcookies/isulewTools/commit/b6ccd76)
- Adust TabRow margin bottom [`481453d`](https://github.com/netcookies/isulewTools/commit/481453d)
- Bump Version [`4d01c9e`](https://github.com/netcookies/isulewTools/commit/4d01c9e)
- 测试跳过usb功能。完善日志 [`ac9e2be`](https://github.com/netcookies/isulewTools/commit/ac9e2be)
- Remove release directory from Git and ignore it [`0c86f95`](https://github.com/netcookies/isulewTools/commit/0c86f95)
- Add usb handler tab [`738cd8f`](https://github.com/netcookies/isulewTools/commit/738cd8f)
- add tab [`05f0d6d`](https://github.com/netcookies/isulewTools/commit/05f0d6d)
- Add tabrow [`3389706`](https://github.com/netcookies/isulewTools/commit/3389706)
- Update [`645ca07`](https://github.com/netcookies/isulewTools/commit/645ca07)
- Update [`de128bb`](https://github.com/netcookies/isulewTools/commit/de128bb)
- Update [`ef71ee5`](https://github.com/netcookies/isulewTools/commit/ef71ee5)
- Init [`f96ea85`](https://github.com/netcookies/isulewTools/commit/f96ea85)

