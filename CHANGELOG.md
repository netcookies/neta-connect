# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html) and to [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).

## v2.1.3 (2026-03-05)

### Feature
- 新增亮屏恢复播放与歌词封面自适应颜色 [`5d139dae`](https://github.com/netcookies/isulewTools/commit/5d139dae)
- 新增自定义操作与评分兜底支持 [`eeaea6f0`](https://github.com/netcookies/isulewTools/commit/eeaea6f0)
- 实现歌词超长行布局期预分段换行（方案 B） [`d9e3c632`](https://github.com/netcookies/isulewTools/commit/d9e3c632)

### Bug Fixes
- 修复电话会话覆盖音乐封面（电话过滤 + per-song 缓存） [`44e05645`](https://github.com/netcookies/isulewTools/commit/44e05645)
- 修复 isTrackChanging 锁泄漏与封面降级缺失 [`a4df997e`](https://github.com/netcookies/isulewTools/commit/a4df997e)
- 修复分段歌词第二段误高亮问题 [`f278f150`](https://github.com/netcookies/isulewTools/commit/f278f150)
- 修复 VhalManager 日志过滤在应用启动阶段未生效的问题 [`0d81a57b`](https://github.com/netcookies/isulewTools/commit/0d81a57b)
- 修复 netamade 全屏时悬浮窗线程崩溃及重启后重现问题 [`448b3ebf`](https://github.com/netcookies/isulewTools/commit/448b3ebf)
- 修复 CI 编译错误 [`8c0c2fe8`](https://github.com/netcookies/isulewTools/commit/8c0c2fe8)

### Continuous Integration
- 修复 release workflow 中的 shell 注入和 here-doc 安全问题 [`e782483a`](https://github.com/netcookies/isulewTools/commit/e782483a)
- avoid here-doc in AI prompt (use jq --arg) [`814e8c7f`](https://github.com/netcookies/isulewTools/commit/814e8c7f)
- escape AI prompt and build safe request [`4a020fdf`](https://github.com/netcookies/isulewTools/commit/4a020fdf)

### Chore
- 更新 .gitignore 并精简 CLAUDE.md [`31bdaa67`](https://github.com/netcookies/isulewTools/commit/31bdaa67)

### Other
- Merge pull request #13 from netcookies/fix/escape-ai-prompt [`f9d7b7a2`](https://github.com/netcookies/isulewTools/commit/f9d7b7a2)
- Merge branch 'main' into fix/escape-ai-prompt [`a385b2ed`](https://github.com/netcookies/isulewTools/commit/a385b2ed)
- Update libs.versions.toml [`4a5baafd`](https://github.com/netcookies/isulewTools/commit/4a5baafd)
- Update release.yml with new configuration [`8e62de11`](https://github.com/netcookies/isulewTools/commit/8e62de11)
- Merge pull request #12 from netcookies/fix/escape-ai-prompt [`48c220bf`](https://github.com/netcookies/isulewTools/commit/48c220bf)

## v2.1.2 (2026-02-27)

### Feature
- 新增 per-tag 日志级别过滤，VhalManager 日志可在高级设置中控制 [`6f521195`](https://github.com/netcookies/isulewTools/commit/6f521195)
- 新增仅歌词版布局，重命名简约为单行版本，歌词字体使用自定义字体 [`feeb9d54`](https://github.com/netcookies/isulewTools/commit/feeb9d54)
- 媒体控制小组件动态检测已安装音乐App并修复空MediaSession点击唤醒 [`afb643ca`](https://github.com/netcookies/isulewTools/commit/afb643ca)
- 为封面图片添加磁盘文件缓存与7天TTL清理机制 [`bfd47568`](https://github.com/netcookies/isulewTools/commit/bfd47568)
- 添加 MFi 硬件认证代理服务器 [`fd1b23c7`](https://github.com/netcookies/isulewTools/commit/fd1b23c7)
- 接入 AudioTrack compress offload 实现 ALAC 硬件解码 [`4d9f1d39`](https://github.com/netcookies/isulewTools/commit/4d9f1d39)
- 声明 RAOP 使用 ALAC 并启用音频硬件加速 [`dfecb855`](https://github.com/netcookies/isulewTools/commit/dfecb855)
- 实现 MFi 认证软件层 [`8b72a1ff`](https://github.com/netcookies/isulewTools/commit/8b72a1ff)
- 添加 Apple MFi 证书和测试 [`bbfe71d5`](https://github.com/netcookies/isulewTools/commit/bbfe71d5)
- 实现 iAP2 EAP 层（CarPlay 音频控制） [`f942e6c6`](https://github.com/netcookies/isulewTools/commit/f942e6c6)
- 实现 iAP2 完整协议栈（MFi Stub） [`3cd2f8b4`](https://github.com/netcookies/isulewTools/commit/3cd2f8b4)
- 完成 Vulkan YUV 渲染管线与 async upload 优化 [`36e58083`](https://github.com/netcookies/isulewTools/commit/36e58083)
- 新增音频 A/V 同步延迟配置项 [`b3ff15ad`](https://github.com/netcookies/isulewTools/commit/b3ff15ad)
- 网络自适应与队列丢帧策略增强 [`a12e1d1f`](https://github.com/netcookies/isulewTools/commit/a12e1d1f)
- 实现独占全屏投屏模式和 Native 断开连接通知 [`c8b4ca8d`](https://github.com/netcookies/isulewTools/commit/c8b4ca8d)
- 集成 Oboe 低延迟音频播放器 [`143a25c8`](https://github.com/netcookies/isulewTools/commit/143a25c8)
- Stage 3-5 架构重构、协程迁移和稳定性增强 [`e78098c2`](https://github.com/netcookies/isulewTools/commit/e78098c2)
- 实现 FDK-AAC 音频解码后端 [`97dc1f46`](https://github.com/netcookies/isulewTools/commit/97dc1f46)
- 添加 fdk-aac 子模块用于 AAC 解码 [`a0646338`](https://github.com/netcookies/isulewTools/commit/a0646338)
- 从 plist 提取 constantDuration 和推断 mode [`d5608f9b`](https://github.com/netcookies/isulewTools/commit/d5608f9b)
- Phase 5 Part 1 - V2 配置支持和 SDP 参数传递 [`cea8b48b`](https://github.com/netcookies/isulewTools/commit/cea8b48b)
- 动态 ASC 生成 - 基于 SDP 参数 (Phase 4) [`92a44520`](https://github.com/netcookies/isulewTools/commit/92a44520)
- Java 配置模型扩展 - SDP 参数传递 (Phase 3) [`44c8c0f6`](https://github.com/netcookies/isulewTools/commit/44c8c0f6)
- 实现 RTSP ANNOUNCE 和 SDP 解析 (Phase 1-2) [`fc34592f`](https://github.com/netcookies/isulewTools/commit/fc34592f)
- 完成 S-1 协议栈优化与验收收敛 [`b190998a`](https://github.com/netcookies/isulewTools/commit/b190998a)
- 完成 MobileSR 推理链路落地 [`e125afa9`](https://github.com/netcookies/isulewTools/commit/e125afa9)
- 完成 S-4 Vulkan 渲染集成与兼容方案 [`47bc4ad4`](https://github.com/netcookies/isulewTools/commit/47bc4ad4)
- 实现 AI 视频增强管线（超分辨率 + 降噪） [`62ec7170`](https://github.com/netcookies/isulewTools/commit/62ec7170)
- 实现自适应码率调整优化网络适应性 [`5117f6e9`](https://github.com/netcookies/isulewTools/commit/5117f6e9)
- 实现批量帧处理优化 JNI 调用 [`a0b10bfa`](https://github.com/netcookies/isulewTools/commit/a0b10bfa)
- 集成 Perfetto 追踪与性能验证链路 [`2cd68956`](https://github.com/netcookies/isulewTools/commit/2cd68956)
- 完成 Snapdragon 8155 平台性能优化 [`cb7c1ac8`](https://github.com/netcookies/isulewTools/commit/cb7c1ac8)
- 添加视频硬件解码支持 [`f9c26165`](https://github.com/netcookies/isulewTools/commit/f9c26165)
- 实现 CarPlay 协议栈高性能优化 [`aa2f29a3`](https://github.com/netcookies/isulewTools/commit/aa2f29a3)
- 完成 S-1 CarPlay 协议栈修复与增强 [`e2667d4c`](https://github.com/netcookies/isulewTools/commit/e2667d4c)
- 优化 AirPlay 镜像 UX/UI 并修复触摸事件递归崩溃 [`18636fb8`](https://github.com/netcookies/isulewTools/commit/18636fb8)
- 在 RaopServer 中集成视频回调 [`36afc8b6`](https://github.com/netcookies/isulewTools/commit/36afc8b6)
- 修复视频传输问题并实现 MediaCodec 解码 [`03e98449`](https://github.com/netcookies/isulewTools/commit/03e98449)
- 集成 libplist 库修复协议解析 [`64ac045b`](https://github.com/netcookies/isulewTools/commit/64ac045b)
- 实现完整的 plist 序列化和解析库 [`abd95f30`](https://github.com/netcookies/isulewTools/commit/abd95f30)
- 使用 Android NsdManager 实现 mDNS 服务发现 [`6052a2f4`](https://github.com/netcookies/isulewTools/commit/6052a2f4)
- 实现 RAOP 动态端口分配 [`9b9ac992`](https://github.com/netcookies/isulewTools/commit/9b9ac992)
- 迁移到 RPiPlay 实现完整 AirPlay 协议 [`f0045260`](https://github.com/netcookies/isulewTools/commit/f0045260)
- 对齐 Slave-in-the-Magic-Mirror 实现完整镜像协议 [`e401eb5a`](https://github.com/netcookies/isulewTools/commit/e401eb5a)
- 添加 Mirror HTTP Server 基础实现 [`ba72fb9f`](https://github.com/netcookies/isulewTools/commit/ba72fb9f)
- 添加 bplist SETUP 解析与 RECORD 响应头 [`52f060c2`](https://github.com/netcookies/isulewTools/commit/52f060c2)
- 根据 AirPlay mode 使用不同的 Server 头 [`5c423981`](https://github.com/netcookies/isulewTools/commit/5c423981)
- 添加 /fp-setup 端点（简化版本） [`b8c5fa8a`](https://github.com/netcookies/isulewTools/commit/b8c5fa8a)
- 实现完整的 /pair-verify 握手协议 [`576c345e`](https://github.com/netcookies/isulewTools/commit/576c345e)
- 添加 X25519/ED25519 加密函数 [`e0e85020`](https://github.com/netcookies/isulewTools/commit/e0e85020)
- 实现动态 RTP 缓冲区配置 [`665099c7`](https://github.com/netcookies/isulewTools/commit/665099c7)
- 实现 RTP 3-socket 架构和 P2 优化完善 [`d4bb8205`](https://github.com/netcookies/isulewTools/commit/d4bb8205)
- 实现 AirPlay 2 配对验证和加密基础设施 [`52942ec9`](https://github.com/netcookies/isulewTools/commit/52942ec9)
- 实现 Apple-Challenge/Response 认证和调试优化 [`09456ba9`](https://github.com/netcookies/isulewTools/commit/09456ba9)
- 实现 CarPlay mDNS 服务发现和独立测试应用 [`d542ea67`](https://github.com/netcookies/isulewTools/commit/d542ea67)
- 在应用控制页面添加 CarPlay 启动入口 [`ab7ba3f6`](https://github.com/netcookies/isulewTools/commit/ab7ba3f6)
- 实现纯软件 CarPlay JNI 协议栈 [`44a0676a`](https://github.com/netcookies/isulewTools/commit/44a0676a)

### Bug Fixes
- 修正 luna 关键字映射为汽水音乐 [`806910a2`](https://github.com/netcookies/isulewTools/commit/806910a2)
- 清理 AdaptiveVideoDecoder 中遗漏的 AI 残留引用 [`c59b1b16`](https://github.com/netcookies/isulewTools/commit/c59b1b16)
- 修复 onAudioReady 状态泄漏并加速 buffer 收敛 [`dc7704ee`](https://github.com/netcookies/isulewTools/commit/dc7704ee)
- 修复 OboeAudioSink underrun 正反馈循环导致音频渐进卡死 [`43eadff0`](https://github.com/netcookies/isulewTools/commit/43eadff0)
- 修正 AndroidManifest 与跨模块引用中的旧类路径 [`1e38730d`](https://github.com/netcookies/isulewTools/commit/1e38730d)
- 修正 JNI FindClass 中 NativeInterface 的类路径字符串 [`7b18f23f`](https://github.com/netcookies/isulewTools/commit/7b18f23f)
- 修正 JNI 符号名以匹配新的 .core 子包路径 [`d5859b72`](https://github.com/netcookies/isulewTools/commit/d5859b72)
- 修复 LegacyDecoderCompat 中 DecodeMode/DecoderBackend 的包路径引用 [`06bacdf0`](https://github.com/netcookies/isulewTools/commit/06bacdf0)
- 修复 AAC-ELD 镜像模式间歇性爆音（PLC + 抖动缓冲 + Oboe） [`5c3885e9`](https://github.com/netcookies/isulewTools/commit/5c3885e9)
- 对齐 iAP2 Link 帧格式与 Apple iAP2 R10 Spec [`57af00b3`](https://github.com/netcookies/isulewTools/commit/57af00b3)
- 对齐 iAP2 协议与 oligo 逆向分析（3 处偏差） [`84817d7b`](https://github.com/netcookies/isulewTools/commit/84817d7b)
- TEARDOWN 对齐 RPiPlay 行为，不停 mirror 线程 [`94ea3cdd`](https://github.com/netcookies/isulewTools/commit/94ea3cdd)
- TEARDOWN 使用非阻塞 signal_stop 立即响应 200 OK [`b01202e9`](https://github.com/netcookies/isulewTools/commit/b01202e9)
- TEARDOWN 保持 RTSP 连接，允许 iOS 重新 SETUP [`a964513f`](https://github.com/netcookies/isulewTools/commit/a964513f)
- TEARDOWN 后延迟 4s 退出，等待 iOS 重新 ANNOUNCE [`b86d64ff`](https://github.com/netcookies/isulewTools/commit/b86d64ff)
- TEARDOWN 后主动关闭 TCP 连接，触发 iOS 重新 ANNOUNCE [`ddeeab2b`](https://github.com/netcookies/isulewTools/commit/ddeeab2b)
- 消除 TEARDOWN/FLUSH 产生的误导性 "No handler found" 警告 [`dc527dd8`](https://github.com/netcookies/isulewTools/commit/dc527dd8)
- TEARDOWN 响应添加 Connection: close 修复视频冻结 [`5b06f170`](https://github.com/netcookies/isulewTools/commit/5b06f170)
- 添加 POST /audioMode 处理，修复旋转后 iOS 立即 TEARDOWN [`0379196b`](https://github.com/netcookies/isulewTools/commit/0379196b)
- 修复 raop.c conn_destroy 括号缺失导致编译失败 [`365a3e95`](https://github.com/netcookies/isulewTools/commit/365a3e95)
- 修复竖屏旋转卡死、画面拉伸、断开 UI 及视频 flush 链路 [`eb607983`](https://github.com/netcookies/isulewTools/commit/eb607983)
- 修复 java.util.ArrayDeque 无 removeLastOrNull 的编译错误 [`8c98ad30`](https://github.com/netcookies/isulewTools/commit/8c98ad30)
- 用延迟 FIFO 队列实现音频 A/V 同步延迟 [`a933f910`](https://github.com/netcookies/isulewTools/commit/a933f910)
- 修复视频解码器使用正确的 PTS，实现 A/V 同步 [`a620fb8e`](https://github.com/netcookies/isulewTools/commit/a620fb8e)
- 修复 NTP 时间同步的 IPv6 支持 [`173ab170`](https://github.com/netcookies/isulewTools/commit/173ab170)
- 移除强制 IPv4 覆盖，支持 IPv6 链路本地地址连接 [`c97a1455`](https://github.com/netcookies/isulewTools/commit/c97a1455)
- 修复 TEARDOWN 销毁音频 RTP 和 eventPort=0 两个连接断开问题 [`15d6d816`](https://github.com/netcookies/isulewTools/commit/15d6d816)
- 修复 RAOP 日志不可见和 NTP NULL 崩溃问题 [`5b5e33df`](https://github.com/netcookies/isulewTools/commit/5b5e33df)
- 修复无画面无声音的两处根本原因 [`42664b74`](https://github.com/netcookies/isulewTools/commit/42664b74)
- 修复 IPv6 连接时 NTP/RTP 地址解析导致投屏崩溃 [`90477bd2`](https://github.com/netcookies/isulewTools/commit/90477bd2)
- 修复 P2-5 导致投屏失败的 IPv6 回归 [`56b09e9c`](https://github.com/netcookies/isulewTools/commit/56b09e9c)
- 修复 RAOP IPv6 支持与本地地址回退 [`6f3a56f8`](https://github.com/netcookies/isulewTools/commit/6f3a56f8)
- 异步化 UI 线程视频 Surface 释放 [`1875cbbd`](https://github.com/netcookies/isulewTools/commit/1875cbbd)
- 统一 Oboe 线程安全调用路径 [`b5380c81`](https://github.com/netcookies/isulewTools/commit/b5380c81)
- 修复 P1 审查发现的并发与状态问题 [`41b2be6d`](https://github.com/netcookies/isulewTools/commit/41b2be6d)
- 音频 PTS 透传与回退日志增强 [`298a2a6f`](https://github.com/netcookies/isulewTools/commit/298a2a6f)
- 修复 8 个失败测试，测试通过率达 100% [`5721b635`](https://github.com/netcookies/isulewTools/commit/5721b635)
- 修复 P0 关键问题，优化内存与性能 [`ec27b586`](https://github.com/netcookies/isulewTools/commit/ec27b586)
- 修复 Gradle 8.x 兼容性问题 [`4b39a699`](https://github.com/netcookies/isulewTools/commit/4b39a699)
- 修复 Oboe Prefab 配置导致的 CMake 构建失败 [`9258ae2d`](https://github.com/netcookies/isulewTools/commit/9258ae2d)
- 修复 5 个编译警告，提升代码质量 [`9206c219`](https://github.com/netcookies/isulewTools/commit/9206c219)
- AAC-ELD 参数硬编码覆盖和视频解码日志增强 [`000629e3`](https://github.com/netcookies/isulewTools/commit/000629e3)
- 修正 OPTIONS 响应，添加 POST 和 GET 方法 [`10161b77`](https://github.com/netcookies/isulewTools/commit/10161b77)
- 回退错误的 plist 参数推断并启用 INFO 日志 [`79f3de9c`](https://github.com/netcookies/isulewTools/commit/79f3de9c)
- 从 plist 提取音频参数，支持无 ANNOUNCE 流程 [`b4c5c59b`](https://github.com/netcookies/isulewTools/commit/b4c5c59b)
- 修复 MediaCodec 能力检测和 AAC-ELD ASC 生成 [`7d2b9722`](https://github.com/netcookies/isulewTools/commit/7d2b9722)
- 恢复 ASC 配置使用实际协商声道数 [`e8044a28`](https://github.com/netcookies/isulewTools/commit/e8044a28)
- 修复 Native Lib 安全问题 (SEC-004, SEC-005) [`8e7f8117`](https://github.com/netcookies/isulewTools/commit/8e7f8117)
- 修复 CallbackGuard 并发安全问题 (SEC-003) [`bd805758`](https://github.com/netcookies/isulewTools/commit/bd805758)
- 全局状态缺少线程安全保护 SEC-002 [`47ec5a00`](https://github.com/netcookies/isulewTools/commit/47ec5a00)
- 修复回调销毁竞态并补充线程安全测试 [`063ac04d`](https://github.com/netcookies/isulewTools/commit/063ac04d)
- 修复 litert 与 tensorflow-lite 依赖冲突 [`f834b18a`](https://github.com/netcookies/isulewTools/commit/f834b18a)
- 修复 Perfetto 工具链格式兼容性 [`1125783e`](https://github.com/netcookies/isulewTools/commit/1125783e)
- 修复自适应码率网络统计与延迟计算 [`3e9168e6`](https://github.com/netcookies/isulewTools/commit/3e9168e6)
- 修复 AAC-ELD 解码错误并添加退避机制防止死循环 [`200fcdcc`](https://github.com/netcookies/isulewTools/commit/200fcdcc)
- 修复 JNI 方法签名不匹配导致的闪退 [`97a63b8e`](https://github.com/netcookies/isulewTools/commit/97a63b8e)
- 在 CarPlayActivity 中调用 NativeInterface.init() [`1c9d972b`](https://github.com/netcookies/isulewTools/commit/1c9d972b)
- 修复 Java 回调对象注册问题 [`10973adf`](https://github.com/netcookies/isulewTools/commit/10973adf)
- 添加 plist 序列化详细诊断日志 [`e534d133`](https://github.com/netcookies/isulewTools/commit/e534d133)
- 修复 /info 端点 features 硬编码问题 [`b478f30e`](https://github.com/netcookies/isulewTools/commit/b478f30e)
- 修复设备信息不匹配导致连接断开问题 [`9d9cfc78`](https://github.com/netcookies/isulewTools/commit/9d9cfc78)
- 修复 Android 平台 RAOP 连接崩溃问题 [`c55da7d0`](https://github.com/netcookies/isulewTools/commit/c55da7d0)
- 修复 NsdManager listener 重复使用导致的崩溃 [`b5ead8c3`](https://github.com/netcookies/isulewTools/commit/b5ead8c3)
- 修复 /pair-verify 签名加密问题 [`0c224618`](https://github.com/netcookies/isulewTools/commit/0c224618)
- 实现缺失的 AirPlay 协议端点 [`b26fba34`](https://github.com/netcookies/isulewTools/commit/b26fba34)
- 修正 AirPlay Mirror features 值 [`28e951df`](https://github.com/netcookies/isulewTools/commit/28e951df)
- 修改默认模式为 AirPlay Mirror [`8ff8e319`](https://github.com/netcookies/isulewTools/commit/8ff8e319)
- 应用 AirPlay 协议安全修复 [`4bd8a9b2`](https://github.com/netcookies/isulewTools/commit/4bd8a9b2)
- 修复 raop_server 中遗漏的 AirPlayPairing 引用 [`b9c312d9`](https://github.com/netcookies/isulewTools/commit/b9c312d9)

### Performance Improvements
- A/V 同步延迟队列复用 ByteArray，消除稳态分配 [`d0cec617`](https://github.com/netcookies/isulewTools/commit/d0cec617)
- 移除非必要的调试和诊断日志以提升性能 [`4efca4e8`](https://github.com/netcookies/isulewTools/commit/4efca4e8)
- 实现零拷贝内存优化 [`4224ea1f`](https://github.com/netcookies/isulewTools/commit/4224ea1f)

### Documentation
- 记录 AirPlay 镜像模式下 ANNOUNCE/SDP 不生效的关键行为 [`daf3ec0e`](https://github.com/netcookies/isulewTools/commit/daf3ec0e)
- 更新模块文档，反映架构重构和现代化改进 [`213d546c`](https://github.com/netcookies/isulewTools/commit/213d546c)
- 更新 CLAUDE.md 项目文档 [`894a39a3`](https://github.com/netcookies/isulewTools/commit/894a39a3)
- 补充缺失模块的 CLAUDE.md 文档 [`eedae277`](https://github.com/netcookies/isulewTools/commit/eedae277)
- 固化项目指南 (43条规范) [`126ac175`](https://github.com/netcookies/isulewTools/commit/126ac175)
- 更新 AIRPLAY_MODES.md 以反映 CarPlay 模式实现 [`5b550589`](https://github.com/netcookies/isulewTools/commit/5b550589)

### Chore
- 解决合并冲突，以本地版本为主 [`f336ae94`](https://github.com/netcookies/isulewTools/commit/f336ae94)
- IDE 自动清理包名整理后的冗余 import [`f2b893b6`](https://github.com/netcookies/isulewTools/commit/f2b893b6)
- 移除非 Android 渲染器与过期分析报告 [`daa922f1`](https://github.com/netcookies/isulewTools/commit/daa922f1)
- 允许追踪测试资源中的证书文件 [`73d50c9b`](https://github.com/netcookies/isulewTools/commit/73d50c9b)
- 调整音频 A/V 同步延迟默认值为 330ms [`8283add6`](https://github.com/netcookies/isulewTools/commit/8283add6)
- 移除实时路径中的频繁 DEBUG 日志 [`728fb562`](https://github.com/netcookies/isulewTools/commit/728fb562)
- 移除实时路径中的频繁 DEBUG 日志 [`5cc45557`](https://github.com/netcookies/isulewTools/commit/5cc45557)
- 移除音频实时线程中的诊断日志字段 [`34dc9521`](https://github.com/netcookies/isulewTools/commit/34dc9521)
- 清理修复过程中添加的调试日志 [`cf80da78`](https://github.com/netcookies/isulewTools/commit/cf80da78)
- 清理版本控制中的 IDE 配置和过期文档 [`c5b2a8d5`](https://github.com/netcookies/isulewTools/commit/c5b2a8d5)
- 清理过期 C++ 备份文件 [`877147c0`](https://github.com/netcookies/isulewTools/commit/877147c0)
- 更新 IDE 配置文件 [`558baccc`](https://github.com/netcookies/isulewTools/commit/558baccc)
- 清理工作流临时文件和过期文档 [`1518d5a1`](https://github.com/netcookies/isulewTools/commit/1518d5a1)
- 更新 issue 队列执行状态 [`9077d776`](https://github.com/netcookies/isulewTools/commit/9077d776)
- 更新 issue 队列执行状态 [`f31864df`](https://github.com/netcookies/isulewTools/commit/f31864df)
- 移除 Eclipse 项目配置文件 [`b55c0fdd`](https://github.com/netcookies/isulewTools/commit/b55c0fdd)

### Refactor
- Canvas Shader 歌词渲染重构，修复居中/扫光/末尾变暗问题 [`9b7604cb`](https://github.com/netcookies/isulewTools/commit/9b7604cb)
- 移除 AI 画质超分功能，减小包体积 [`9f8a0d97`](https://github.com/netcookies/isulewTools/commit/9f8a0d97)
- 配置管理重构并修复 nativeConcealFdk 缺失实现 [`5aa7cc94`](https://github.com/netcookies/isulewTools/commit/5aa7cc94)
- 修正 63 个 Kotlin 文件的包名声明并补齐跨包 import [`54e852ed`](https://github.com/netcookies/isulewTools/commit/54e852ed)
- 音频解码参数统一配置化，消除 AAC-ELD 魔法数字 [`9c60e869`](https://github.com/netcookies/isulewTools/commit/9c60e869)
- 合并 AI 渲染帧调度减少堆积 [`7f962fea`](https://github.com/netcookies/isulewTools/commit/7f962fea)
- 优化 AudioDecoderActor 缓冲访问复用 [`b78f8714`](https://github.com/netcookies/isulewTools/commit/b78f8714)
- 优化音视频命令队列到 O(1) [`5490eccf`](https://github.com/netcookies/isulewTools/commit/5490eccf)
- 统一 carplay-jni 配置键与属性解析 [`c3a58d81`](https://github.com/netcookies/isulewTools/commit/c3a58d81)
- JNI 句柄化兼容路由底座 [`78a7b5d9`](https://github.com/netcookies/isulewTools/commit/78a7b5d9)
- 清理非必要的调试日志 [`3435daf6`](https://github.com/netcookies/isulewTools/commit/3435daf6)
- 移除 AudioTrack 后端，统一使用 Oboe [`6a75bf2f`](https://github.com/netcookies/isulewTools/commit/6a75bf2f)
- 优化 Kotlin 空安全操作符使用 [`80e86d80`](https://github.com/netcookies/isulewTools/commit/80e86d80)
- 现代化同步机制，使用 ReentrantLock 替代 Object [`fe8efe22`](https://github.com/netcookies/isulewTools/commit/fe8efe22)
- Stage 3-5 架构重构与文件结构整理 [`3e7dacfa`](https://github.com/netcookies/isulewTools/commit/3e7dacfa)
- 重构音视频解码架构，移除 Facade 模式 [`d0d10987`](https://github.com/netcookies/isulewTools/commit/d0d10987)
- 使用全局持久化 ED25519 密钥对 [`a1b5d382`](https://github.com/netcookies/isulewTools/commit/a1b5d382)
- 实现动态端口配置，移除固定 7000 端口 [`31fd0206`](https://github.com/netcookies/isulewTools/commit/31fd0206)
- 为音频和视频流配置不同的 RTP 缓冲区大小 [`93940482`](https://github.com/netcookies/isulewTools/commit/93940482)
- 实现动态 mDNS 配置以支持 AirPlay 1 和 CarPlay 模式 [`d5d87476`](https://github.com/netcookies/isulewTools/commit/d5d87476)
- 重命名模式常量 AIRPLAY_MODE_CAST → CARPLAY_MODE [`ecc9ecd3`](https://github.com/netcookies/isulewTools/commit/ecc9ecd3)
- 删除 raop_server 和 carplay_jni 中的 AirPlay 2 代码 [`da71be0c`](https://github.com/netcookies/isulewTools/commit/da71be0c)
- 清理 crypto_utils 中的 AirPlay 2 函数 [`fab575fa`](https://github.com/netcookies/isulewTools/commit/fab575fa)
- 删除 AirPlay 2 配对组件和依赖 [`f4ebdc8a`](https://github.com/netcookies/isulewTools/commit/f4ebdc8a)
- 移除 mdnsd 守护进程启动逻辑，改为检测系统服务 [`9d24193b`](https://github.com/netcookies/isulewTools/commit/9d24193b)
- 移除 mdnsd 守护进程启动逻辑，改为检测系统服务 [`e6833cf5`](https://github.com/netcookies/isulewTools/commit/e6833cf5)

### Other
- Revert "fix(carplay-jni): TEARDOWN 使用非阻塞 signal_stop 立即响应 200 OK" [`a0e0f34d`](https://github.com/netcookies/isulewTools/commit/a0e0f34d)
- Revert "fix(carplay-jni): TEARDOWN 后延迟 4s 退出，等待 iOS 重新 ANNOUNCE" [`7bc38990`](https://github.com/netcookies/isulewTools/commit/7bc38990)
- diag(carplay-jni): 新增 ANNOUNCE 日志，追踪 TEARDOWN 后 iOS 是否重启视频流 [`b85ad706`](https://github.com/netcookies/isulewTools/commit/b85ad706)
- diag(carplay-jni): 添加竖横屏切换诊断日志 [`1fb76997`](https://github.com/netcookies/isulewTools/commit/1fb76997)
- Revert "chore(carplay-jni): 移除实时路径中的频繁 DEBUG 日志" [`f35fea15`](https://github.com/netcookies/isulewTools/commit/f35fea15)
- test-cycle: iteration 3 - conservative strategy (pass: 80% → 99.4%) [`9e26cab8`](https://github.com/netcookies/isulewTools/commit/9e26cab8)
- Merge pull request #11 from netcookies/queue-exec-QUE-20260212074341 [`0979cc8f`](https://github.com/netcookies/isulewTools/commit/0979cc8f)
- Merge queue-exec-QUE-20260212062421: 完成 CarPlay Bug 修复队列 [`b8f98709`](https://github.com/netcookies/isulewTools/commit/b8f98709)
- Merge queue-exec-QUE-20260211183600: 完成 CarPlay 性能优化队列 [`9244b34a`](https://github.com/netcookies/isulewTools/commit/9244b34a)
- Merge pull request #10 from netcookies/queue-exec-QUE-20260211183600 [`29802d07`](https://github.com/netcookies/isulewTools/commit/29802d07)
- Merge pull request #9 from netcookies/feature/carplay-jni-implementation [`82c8edd7`](https://github.com/netcookies/isulewTools/commit/82c8edd7)
- Update: gitignore [`75006657`](https://github.com/netcookies/isulewTools/commit/75006657)

## v2.1.1 (2026-02-05)

### Feature
- 添加完整16个原车色系方案 [`3619bf79`](https://github.com/netcookies/isulewTools/commit/3619bf79)
- 添加氛围灯音乐律动功能 [`2c7cf473`](https://github.com/netcookies/isulewTools/commit/2c7cf473)
- 聚合多版本 release notes，显示完整更新历史 [`30d7b11d`](https://github.com/netcookies/isulewTools/commit/30d7b11d)

### Bug Fixes
- 修正FFT magnitude阈值并替换为单色系渐变方案 [`df318b75`](https://github.com/netcookies/isulewTools/commit/df318b75)
- 移除不必要的安全调用警告 [`13c30d72`](https://github.com/netcookies/isulewTools/commit/13c30d72)

### Performance Improvements
- 完全重构氛围灯音乐律动算法，模仿原车系统实现 [`83de2091`](https://github.com/netcookies/isulewTools/commit/83de2091)
- 网易云音乐本身不提供逐字歌词源，所以改用 qq 歌词源来作为默认适配。 [`40beee36`](https://github.com/netcookies/isulewTools/commit/40beee36)

### Chore
- 更新车辆属性翻译 [`d47c4f5a`](https://github.com/netcookies/isulewTools/commit/d47c4f5a)

### Refactor
- 使用原车精确数据，仅保留音乐律动7种色系 [`afb14be1`](https://github.com/netcookies/isulewTools/commit/afb14be1)
- 使用OutlineSelectorDialog优化16色系选择UI [`38ecf8ed`](https://github.com/netcookies/isulewTools/commit/38ecf8ed)

## v2.1.0 (2026-01-31)

### Feature
- 实现汽水音乐逐字歌词源（基于 PC 端 API） [`896e5108`](https://github.com/netcookies/isulewTools/commit/896e5108)

### Bug Fixes
- 修复暂停时封面被覆盖为 null 的问题 & 清理调试日志 [`f637fbea`](https://github.com/netcookies/isulewTools/commit/f637fbea)
- 修复汽水音乐封面解析和 KRC 超时问题 [`83fc0f18`](https://github.com/netcookies/isulewTools/commit/83fc0f18)
- 修复汽水音乐 KRC 歌词解析失败问题 [`88a4de3d`](https://github.com/netcookies/isulewTools/commit/88a4de3d)
- 修复 .gitignore 忽略 media-core jniLibs 的问题 [`295a0b86`](https://github.com/netcookies/isulewTools/commit/295a0b86)
- 修复 AI release notes 生成时反引号导致的命令执行错误 [`77269e9c`](https://github.com/netcookies/isulewTools/commit/77269e9c)

### Performance Improvements
- 添加歌词请求去重机制，避免 2 秒内重复请求 [`54a1a5a9`](https://github.com/netcookies/isulewTools/commit/54a1a5a9)

### Other
- debug: 添加歌词源选择和执行的详细日志 [`94aef86b`](https://github.com/netcookies/isulewTools/commit/94aef86b)
- debug: 添加 AI release notes 生成内容的调试输出 [`6c88a33a`](https://github.com/netcookies/isulewTools/commit/6c88a33a)

## v2.0.9 (2026-01-30)

### Feature
- 集成 GitHub Models API 生成 AI release notes [`456ecd82`](https://github.com/netcookies/isulewTools/commit/456ecd82)
- 实现防抖白名单可配置页面 [`64ded813`](https://github.com/netcookies/isulewTools/commit/64ded813)
- 实现 QRC XML 格式逐字歌词解析 [`9bae4e80`](https://github.com/netcookies/isulewTools/commit/9bae4e80)
- 集成QQ音乐原生QRC歌词解密库 [`801a4f20`](https://github.com/netcookies/isulewTools/commit/801a4f20)
- 实现 QRC 自定义 DES 解密算法 [`8801b89c`](https://github.com/netcookies/isulewTools/commit/8801b89c)
- 集成QQ音乐新版API并修复测试 [`ebef57d4`](https://github.com/netcookies/isulewTools/commit/ebef57d4)
- 集成QQ音乐新版歌词API [`c3ad7a71`](https://github.com/netcookies/isulewTools/commit/c3ad7a71)
- 修复QQ音乐QRC格式获取（对照smart-lyric） [`557e6382`](https://github.com/netcookies/isulewTools/commit/557e6382)
- 启用LRCX权重算法 [`a61107fe`](https://github.com/netcookies/isulewTools/commit/a61107fe)
- 添加所有歌词源解密日志 [`6634e5e6`](https://github.com/netcookies/isulewTools/commit/6634e5e6)
- 实现多格式加密歌词解析器（LRCX/QRC/KRC/NRC） [`b5769139`](https://github.com/netcookies/isulewTools/commit/b5769139)
- 添加封面质量判断，Bridge封面低于120x120时自动下载API高清封面 [`09243092`](https://github.com/netcookies/isulewTools/commit/09243092)
- 优化LRCX歌词渲染性能和视觉效果 [`4488f579`](https://github.com/netcookies/isulewTools/commit/4488f579)
- 统一主屏和副屏MediaSession数据流架构 [`64e18529`](https://github.com/netcookies/isulewTools/commit/64e18529)
- 优化Timeline初始化和duration优先级策略 [`ebe197bc`](https://github.com/netcookies/isulewTools/commit/ebe197bc)
- 优化Bridge模式下Timeline同步机制和诊断日志 [`5c79f922`](https://github.com/netcookies/isulewTools/commit/5c79f922)
- 实现 LRCX 逐字歌词卡拉OK高亮效果 [`11f421e2`](https://github.com/netcookies/isulewTools/commit/11f421e2)
- 咪咕和 QQ 音乐支持封面和时长传递 [`39d1ee9c`](https://github.com/netcookies/isulewTools/commit/39d1ee9c)
- 媒体控制小组件支持 LRCX 逐字歌词高亮 [`a33f2995`](https://github.com/netcookies/isulewTools/commit/a33f2995)
- 扩展基类歌词同步方法支持封面和时长获取 [`bf3a7f7a`](https://github.com/netcookies/isulewTools/commit/bf3a7f7a)
- 添加歌词获取去重保护机制 [`47d53da4`](https://github.com/netcookies/isulewTools/commit/47d53da4)
- 酷我音乐添加封面和时长支持 [`15231dd4`](https://github.com/netcookies/isulewTools/commit/15231dd4)
- 新增多源歌词系统和 LyricPayload 数据结构 [`7fc5df78`](https://github.com/netcookies/isulewTools/commit/7fc5df78)
- 优化MediaSessionRouter数据投递和UI调试日志 [`51139877`](https://github.com/netcookies/isulewTools/commit/51139877)
- 完善日志转发AIDL接口，支持异常堆栈信息传递 [`98061d41`](https://github.com/netcookies/isulewTools/commit/98061d41)
- 添加日志转发回调状态诊断 [`ea9ea44f`](https://github.com/netcookies/isulewTools/commit/ea9ea44f)
- 安装副屏服务后自动授权通知监听器 [`a0773da8`](https://github.com/netcookies/isulewTools/commit/a0773da8)
- 创建共享AIDL模块并修复多用户安装 [`6d08d5bc`](https://github.com/netcookies/isulewTools/commit/6d08d5bc)
- 支持多用户安装并优化安装体验 [`49264c72`](https://github.com/netcookies/isulewTools/commit/49264c72)
- 副屏日志转发与版本集中管理 [`244a3cc6`](https://github.com/netcookies/isulewTools/commit/244a3cc6)
- 配置副屏APK release签名并优化构建流程 [`210b4d0a`](https://github.com/netcookies/isulewTools/commit/210b4d0a)
- 支持模拟器环境测试副屏桥接功能 [`542a1273`](https://github.com/netcookies/isulewTools/commit/542a1273)
- 实现副屏MediaSession桥接和自动安装机制 [`ad0ee6b0`](https://github.com/netcookies/isulewTools/commit/ad0ee6b0)
- 添加侧边栏展开状态持久化功能 [`b2120fa1`](https://github.com/netcookies/isulewTools/commit/b2120fa1)

### Bug Fixes
- 修复 QQ 音乐歌词空格丢失和 duration 单位不一致问题 [`7b714dc9`](https://github.com/netcookies/isulewTools/commit/7b714dc9)
- 修复网格布局不响应侧边栏状态变化的问题 [`8f5d796a`](https://github.com/netcookies/isulewTools/commit/8f5d796a)
- 修复小组件配置编辑器输入框的自动填充问题 [`75d569e5`](https://github.com/netcookies/isulewTools/commit/75d569e5)
- 修复 QRC 逐字歌词时间计算错误 [`d319cae3`](https://github.com/netcookies/isulewTools/commit/d319cae3)
- 修正QQ音乐新版API参数配置 [`398b524e`](https://github.com/netcookies/isulewTools/commit/398b524e)
- 修复Bridge模式下切歌时封面显示错误的时序问题 [`20c72152`](https://github.com/netcookies/isulewTools/commit/20c72152)
- 修复QQMusicApiClient接口编译错误 [`e86f24b2`](https://github.com/netcookies/isulewTools/commit/e86f24b2)
- 修复逐字歌词空格丢失和间隙高亮消失问题 [`9b60df32`](https://github.com/netcookies/isulewTools/commit/9b60df32)
- 修复LRCX歌词字符编码错误 [`c1b67c03`](https://github.com/netcookies/isulewTools/commit/c1b67c03)
- 完善加密工具类实现和测试 [`569e3d79`](https://github.com/netcookies/isulewTools/commit/569e3d79)
- 修复LRCX歌词自适应降级误判导致逐字效果失效 [`8f132f7e`](https://github.com/netcookies/isulewTools/commit/8f132f7e)
- 修复主屏播放时歌词下载两次的问题 [`e7fd8df7`](https://github.com/netcookies/isulewTools/commit/e7fd8df7)
- 修复MediaSession Bridge异步歌词回调覆盖播放状态的问题 [`3c6f15b1`](https://github.com/netcookies/isulewTools/commit/3c6f15b1)
- 修复Bridge周期性更新时duration未更新的问题 [`d4033312`](https://github.com/netcookies/isulewTools/commit/d4033312)
- 修复 Bridge 数据防抖导致播放状态不同步问题 [`b1d43031`](https://github.com/netcookies/isulewTools/commit/b1d43031)
- 修复 Bridge 模式切歌时播放状态错误的问题 [`c79c0061`](https://github.com/netcookies/isulewTools/commit/c79c0061)
- Bridge 模式下添加歌词 API 下载支持 [`da30c3a4`](https://github.com/netcookies/isulewTools/commit/da30c3a4)
- 修复切歌后歌词从中间开始显示的问题 [`79e165f0`](https://github.com/netcookies/isulewTools/commit/79e165f0)
- 修复媒体控制小组件显示原始 LRCX 标记的问题 [`774d87e5`](https://github.com/netcookies/isulewTools/commit/774d87e5)
- 修复 Bridge 数据路径切歌时歌词位置不一致问题 [`6bb0140f`](https://github.com/netcookies/isulewTools/commit/6bb0140f)
- 修复酷我音乐歌词获取和字符编码问题 [`5b627e91`](https://github.com/netcookies/isulewTools/commit/5b627e91)
- 修复 Bridge 数据源重复获取歌词问题 [`f277a12a`](https://github.com/netcookies/isulewTools/commit/f277a12a)
- 修复音乐数据源错误 [`42d204eb`](https://github.com/netcookies/isulewTools/commit/42d204eb)
- 修复MediaSession多实例和封面频繁推送问题 [`fbfc63be`](https://github.com/netcookies/isulewTools/commit/fbfc63be)
- 修复重启后歌词从固定位置开始的时序问题 [`d535f586`](https://github.com/netcookies/isulewTools/commit/d535f586)
- 修复歌词滚动和位置同步问题 [`35b5b2d2`](https://github.com/netcookies/isulewTools/commit/35b5b2d2)
- 修复副屏MediaSessionMonitor的并发广播问题和冗余日志 [`cdef791f`](https://github.com/netcookies/isulewTools/commit/cdef791f)
- 修复媒体控制小组件不显示内容的问题 [`5d2398b8`](https://github.com/netcookies/isulewTools/commit/5d2398b8)
- 修复BridgeMediaDataSource的context字段丢失 [`def0b006`](https://github.com/netcookies/isulewTools/commit/def0b006)
- 修复副屏MediaSession歌词传输并优化日志 [`4f33a93b`](https://github.com/netcookies/isulewTools/commit/4f33a93b)
- 修复所有AIDL接口实现以正确传递异常堆栈 [`ede63fa1`](https://github.com/netcookies/isulewTools/commit/ede63fa1)
- 统一副屏日志使用LogBus确保所有日志都能推送到主应用 [`f8773488`](https://github.com/netcookies/isulewTools/commit/f8773488)
- 移除副屏MediaSession监听器的时间防抖改用数据比较去重 [`c7fdbee1`](https://github.com/netcookies/isulewTools/commit/c7fdbee1)
- 支持多用户卸载副屏服务 [`38e69e04`](https://github.com/netcookies/isulewTools/commit/38e69e04)
- 完善多用户安装验证和状态显示 [`aad32b90`](https://github.com/netcookies/isulewTools/commit/aad32b90)
- 修复多用户安装验证逻辑 [`106ff29a`](https://github.com/netcookies/isulewTools/commit/106ff29a)
- 修复副屏MediaSession桥接功能的关键bug [`daedc99d`](https://github.com/netcookies/isulewTools/commit/daedc99d)
- 修复模拟器测试环境下的ContentProvider权限问题 [`6c378bde`](https://github.com/netcookies/isulewTools/commit/6c378bde)
- 优化侧边栏持久化功能 [`2881826c`](https://github.com/netcookies/isulewTools/commit/2881826c)

### Documentation
- 更新混淆配置注释，说明时间字段变更 [`0132337c`](https://github.com/netcookies/isulewTools/commit/0132337c)
- 添加副屏MediaSession实现总结文档 [`a4396bac`](https://github.com/netcookies/isulewTools/commit/a4396bac)

### Build
- optimize imports [`4340f7ea`](https://github.com/netcookies/isulewTools/commit/4340f7ea)

### Chore
- 清理遗漏的歌词解密日志 [`a394b091`](https://github.com/netcookies/isulewTools/commit/a394b091)
- 添加 .claude 目录到 .gitignore [`aba246d7`](https://github.com/netcookies/isulewTools/commit/aba246d7)
- 清理测试文件 [`645ac3b8`](https://github.com/netcookies/isulewTools/commit/645ac3b8)
- 清理日志 [`109825f0`](https://github.com/netcookies/isulewTools/commit/109825f0)
- delete unused agents [`422fecfb`](https://github.com/netcookies/isulewTools/commit/422fecfb)
- 将appcompat版本统一管理到libs.versions.toml [`7fde28e1`](https://github.com/netcookies/isulewTools/commit/7fde28e1)
- 完善LogBus日志接口并添加ProGuard混淆规则 [`1dab6595`](https://github.com/netcookies/isulewTools/commit/1dab6595)
- 删除已迁移到media-core模块的测试文件 [`b67a2970`](https://github.com/netcookies/isulewTools/commit/b67a2970)
- 更新ProGuard混淆规则覆盖2.0.9版本新增代码 [`5b222114`](https://github.com/netcookies/isulewTools/commit/5b222114)
- 更新音乐 API 兼容 LyricPayload 新字段 [`ffbeb056`](https://github.com/netcookies/isulewTools/commit/ffbeb056)
- 更新 ProGuard 规则以反映 bridge 包重构 [`ee88790a`](https://github.com/netcookies/isulewTools/commit/ee88790a)
- 清理MediaControlData中的getCurrentPosition调试日志 [`1262565d`](https://github.com/netcookies/isulewTools/commit/1262565d)
- 清理调试日志 [`ed0299d0`](https://github.com/netcookies/isulewTools/commit/ed0299d0)

### Refactor
- 从 libs.versions.toml 提取版本号 [`805f8efc`](https://github.com/netcookies/isulewTools/commit/805f8efc)
- 用分屏状态广播替换全屏广播控制小组件 [`22589c58`](https://github.com/netcookies/isulewTools/commit/22589c58)
- 优化媒体数据来源判断逻辑 [`046d0ef3`](https://github.com/netcookies/isulewTools/commit/046d0ef3)
- 优化媒体会话元数据显示和日志输出 [`642b56b6`](https://github.com/netcookies/isulewTools/commit/642b56b6)
- 清理混淆配置，删除旧包名和重复配置 [`9bedc1a6`](https://github.com/netcookies/isulewTools/commit/9bedc1a6)
- 统一时间单位为毫秒（Long），彻底解决精度问题 [`d7c5cdd3`](https://github.com/netcookies/isulewTools/commit/d7c5cdd3)
- 统一歌词解析架构，引入标准中间格式 [`930fdfe5`](https://github.com/netcookies/isulewTools/commit/930fdfe5)
- 提取独立media-core模块，实现主副屏媒体逻辑复用 [`c41e9cf7`](https://github.com/netcookies/isulewTools/commit/c41e9cf7)
- 统一数据源切换逻辑到 MediaSessionRouter [`4975f4bc`](https://github.com/netcookies/isulewTools/commit/4975f4bc)
- 提取公共方法消除 WebApiMusicDataSource 代码重复 [`a549d28f`](https://github.com/netcookies/isulewTools/commit/a549d28f)
- 优化封面和时长获取策略，实现 MediaSession 优先和歌词源兜底机制 [`3c1f545c`](https://github.com/netcookies/isulewTools/commit/3c1f545c)
- 优化 MediaSession 数据源架构，消除代码重复并修复异步状态管理问题 [`e72f7de1`](https://github.com/netcookies/isulewTools/commit/e72f7de1)
- 优化酷我音乐参数加密逻辑 [`243df124`](https://github.com/netcookies/isulewTools/commit/243df124)
- 优化 bridge 包结构并修复 LogClient 时序问题 [`c5b90aa6`](https://github.com/netcookies/isulewTools/commit/c5b90aa6)
- 抽象数据源共同逻辑并修复歌词时序竞争问题 [`1bcd90b5`](https://github.com/netcookies/isulewTools/commit/1bcd90b5)
- 重构媒体数据源架构，支持多应用配置驱动 [`846ea786`](https://github.com/netcookies/isulewTools/commit/846ea786)
- 重构Bridge架构为转发层，数据源按播放器特性分类 [`5c9359cf`](https://github.com/netcookies/isulewTools/commit/5c9359cf)
- 改进副屏APK构建流程 - 动态编译 [`2c545721`](https://github.com/netcookies/isulewTools/commit/2c545721)
- 架构优化和功能增强 [`f2aff58b`](https://github.com/netcookies/isulewTools/commit/f2aff58b)

### Other
- debug: 添加 LRCX 逐字高亮调试日志 [`e781b336`](https://github.com/netcookies/isulewTools/commit/e781b336)
- debug: 添加MediaSessionMonitor字段变化详细日志 [`97b17707`](https://github.com/netcookies/isulewTools/commit/97b17707)

## v2.0.8 (2026-01-09)

### Bug Fixes
- 将防抖逻辑从聚合层移到 AccessibilityEventDataSource [`51e23a02`](https://github.com/netcookies/isulewTools/commit/51e23a02)

### Refactor
- 前台监控架构统一重构 [`79a2dea1`](https://github.com/netcookies/isulewTools/commit/79a2dea1)
- TaskStackListenerServiceImpl 内部获取 ActivityTaskManager [`a227aec2`](https://github.com/netcookies/isulewTools/commit/a227aec2)
- 创建前台检测数据源架构（阶段1） [`0fad9ba5`](https://github.com/netcookies/isulewTools/commit/0fad9ba5)
- 实现 TaskStackListener 前台检测并清理旧代码 [`8aceab62`](https://github.com/netcookies/isulewTools/commit/8aceab62)

## v2.0.7 (2026-01-08)

### Feature
- 优化 ADB root 升级流程，添加重试和自动重启机制 [`5be5ebd1`](https://github.com/netcookies/isulewTools/commit/5be5ebd1)
- 前台应用检测准确度提升至 99% [`b100fa8f`](https://github.com/netcookies/isulewTools/commit/b100fa8f)

### Bug Fixes
- 修复 ADB root 服务命令协议格式错误 [`7dd8773e`](https://github.com/netcookies/isulewTools/commit/7dd8773e)
- 修复 ADB 认证状态检查误判问题 [`4f45a1ec`](https://github.com/netcookies/isulewTools/commit/4f45a1ec)
- 修复工作流成功时无法保存历史记录的问题 [`9c37b60c`](https://github.com/netcookies/isulewTools/commit/9c37b60c)
- 修复红绿灯数据源 UI 切换和 ADAS 悬浮窗初始化问题 [`54bd6ff0`](https://github.com/netcookies/isulewTools/commit/54bd6ff0)
- 直接发送 Float 类型，不转换为 Double [`c2d4db71`](https://github.com/netcookies/isulewTools/commit/c2d4db71)
- 修正 API 字段名，添加 speed 和 heading [`4b963c93`](https://github.com/netcookies/isulewTools/commit/4b963c93)
- 允许 localhost 的明文 HTTP 通信 [`d13e804a`](https://github.com/netcookies/isulewTools/commit/d13e804a)
- 更新 HTTP 端口为 9449 [`b1a0c06e`](https://github.com/netcookies/isulewTools/commit/b1a0c06e)
- 修复 import 语句，使用 HttpTrafficLightDataSource [`27104e2c`](https://github.com/netcookies/isulewTools/commit/27104e2c)

### Documentation
- 更新车辆属性 json 文件 [`757f3998`](https://github.com/netcookies/isulewTools/commit/757f3998)

### Build
- depends updated [`b6e00287`](https://github.com/netcookies/isulewTools/commit/b6e00287)

### Refactor
- killProcess 改用 ADB shell 避免特权服务自杀悖论 [`5f46c30d`](https://github.com/netcookies/isulewTools/commit/5f46c30d)
- 优化前台应用检测器架构和环境适配 [`8bb5267f`](https://github.com/netcookies/isulewTools/commit/8bb5267f)
- 更新 UI 适配新的 HTTP 红绿灯数据源 [`1ca7ccd3`](https://github.com/netcookies/isulewTools/commit/1ca7ccd3)
- 重构红绿灯数据源架构，切换到 HTTP REST API [`085e6bde`](https://github.com/netcookies/isulewTools/commit/085e6bde)

## v2.0.6 (2026-01-04)

### Feature
- 实现 CDN 配置系统和对话框优化 [`997aaea8`](https://github.com/netcookies/isulewTools/commit/997aaea8)
- 支持哪吒美式版本选择安装 [`66ae85ec`](https://github.com/netcookies/isulewTools/commit/66ae85ec)

### Bug Fixes
- CDN 系统初始化和 DashboardPage 集成 [`159206da`](https://github.com/netcookies/isulewTools/commit/159206da)

### Chore
- 添加应用更新系统混淆白名单 [`053491db`](https://github.com/netcookies/isulewTools/commit/053491db)

## v2.0.5 (2026-01-04)

### Feature
- 远程输入收到 POST 请求后显示 SnackBar 通知 [`0a9f04a8`](https://github.com/netcookies/isulewTools/commit/0a9f04a8)

## v2.0.4 (2026-01-04)

### Feature
- 添加 BoxJS 安装二维码和修复 IP 获取逻辑 [`e7258342`](https://github.com/netcookies/isulewTools/commit/e7258342)
- 支持远程输入 POST API 和模式切换 [`46806dc3`](https://github.com/netcookies/isulewTools/commit/46806dc3)
- 实现远程输入功能 [`af58434b`](https://github.com/netcookies/isulewTools/commit/af58434b)
- 支持 CompactInputFieldSetting 自定义输入框宽度 [`02da3e80`](https://github.com/netcookies/isulewTools/commit/02da3e80)

### Bug Fixes
- 修复广播接收器重复注册和全屏状态恢复问题 [`94bdd740`](https://github.com/netcookies/isulewTools/commit/94bdd740)

### Other

## v2.0.3 (2026-01-02)

### Feature
- 添加工作流取消功能和修复签到成功判断 [`0e05f44f`](https://github.com/netcookies/isulewTools/commit/0e05f44f)
- 实现 AlarmManager 精确定时调度 [`91095893`](https://github.com/netcookies/isulewTools/commit/91095893)
- 添加调度频率支持 [`2ad70c59`](https://github.com/netcookies/isulewTools/commit/2ad70c59)
- 在工作流配置页面显示调度描述文本 [`37348738`](https://github.com/netcookies/isulewTools/commit/37348738)
- 完善工作流配置和API集成 [`6cc11c86`](https://github.com/netcookies/isulewTools/commit/6cc11c86)
- 优化工作流执行体验 [`c8fa3697`](https://github.com/netcookies/isulewTools/commit/c8fa3697)
- 优化工作流配置页面 UI [`540e79ec`](https://github.com/netcookies/isulewTools/commit/540e79ec)
- 实现工作流 DSL 配置框架 [`b27b2280`](https://github.com/netcookies/isulewTools/commit/b27b2280)
- 锁定模式禁止删除 & 使用 AppSegmentedControl 替换全局开关 [`874619d9`](https://github.com/netcookies/isulewTools/commit/874619d9)
- 完成工作流管理页面 UX 改进 [`399a4108`](https://github.com/netcookies/isulewTools/commit/399a4108)
- WorkflowCard 改造为双层结构,添加底部信息栏 [`ef64de62`](https://github.com/netcookies/isulewTools/commit/ef64de62)
- 对齐 WorkflowGridContent 和 WidgetGridContent UI 设计 [`f9b6cdc8`](https://github.com/netcookies/isulewTools/commit/f9b6cdc8)
- 实现工作流配置页面和导航功能 [`99430cfa`](https://github.com/netcookies/isulewTools/commit/99430cfa)
- 实现工作流执行结果通知功能 [`6a19ebb6`](https://github.com/netcookies/isulewTools/commit/6a19ebb6)
- 实现工作流手动执行功能 [`64dac239`](https://github.com/netcookies/isulewTools/commit/64dac239)
- 完成 WorkManager 任务调度功能 [`ba8933aa`](https://github.com/netcookies/isulewTools/commit/ba8933aa)
- 在 MainApplication 中注册工作流 [`fc85c1fe`](https://github.com/netcookies/isulewTools/commit/fc85c1fe)
- 实现哪吒签到配置 UI 页面 [`ff50f2fa`](https://github.com/netcookies/isulewTools/commit/ff50f2fa)
- 实现哪吒签到 Worker 和工作流类 [`fece376f`](https://github.com/netcookies/isulewTools/commit/fece376f)
- 添加 Token 加密存储支持 [`64b12910`](https://github.com/netcookies/isulewTools/commit/64b12910)
- 实现工作流UI界面（阶段三） [`aec132f5`](https://github.com/netcookies/isulewTools/commit/aec132f5)
- 实现工作流框架核心（阶段五） [`2cd0e093`](https://github.com/netcookies/isulewTools/commit/2cd0e093)
- 媒体控制小组件添加默认播放软件配置 [`d167a6ea`](https://github.com/netcookies/isulewTools/commit/d167a6ea)
- 添加按需隐藏显示模式并统一命名 [`0cf3353b`](https://github.com/netcookies/isulewTools/commit/0cf3353b)
- 添加系统特权服务访问器并优化 AppServices [`8a61a4cb`](https://github.com/netcookies/isulewTools/commit/8a61a4cb)

### Bug Fixes
- 移除电池约束以提高定时准确性 [`91213db8`](https://github.com/netcookies/isulewTools/commit/91213db8)
- 修复定时调度时间和重试逻辑问题 [`5cd8decb`](https://github.com/netcookies/isulewTools/commit/5cd8decb)
- 修复工作流编辑保存无效的问题 [`a50d5c70`](https://github.com/netcookies/isulewTools/commit/a50d5c70)
- 锁定状态下删除按钮改为禁用而非隐藏 [`b3376d2a`](https://github.com/netcookies/isulewTools/commit/b3376d2a)
- 添加缺失的 ColumnScope 导入 [`818153ee`](https://github.com/netcookies/isulewTools/commit/818153ee)
- 注册数据库迁移 MIGRATION_13_14 [`836e3147`](https://github.com/netcookies/isulewTools/commit/836e3147)
- 使用双队列 FIFO 架构修复 Snackbar 自动消失问题 [`e6f5faba`](https://github.com/netcookies/isulewTools/commit/e6f5faba)
- 修复 ADAS 显示模式逻辑不一致问题 [`a4bd51f7`](https://github.com/netcookies/isulewTools/commit/a4bd51f7)
- 添加 HIDE_ON_PACKAGES 显示模式颜色 [`3ada4da3`](https://github.com/netcookies/isulewTools/commit/3ada4da3)
- 修复全屏应用切换卡顿和不完整的问题 [`20ea502d`](https://github.com/netcookies/isulewTools/commit/20ea502d)

### Performance Improvements
- 优化表达歧义 [`f1e97c13`](https://github.com/netcookies/isulewTools/commit/f1e97c13)
- 优化冗余代码 [`8cb3cc75`](https://github.com/netcookies/isulewTools/commit/8cb3cc75)

### Chore
- 添加 Retrofit 和 WorkManager 依赖 [`614f99de`](https://github.com/netcookies/isulewTools/commit/614f99de)

### Refactor
- 统一管理 AlarmManager 和 WorkManager [`a4e19263`](https://github.com/netcookies/isulewTools/commit/a4e19263)
- 统一管理工作流注册，参考小组件模式 [`81222228`](https://github.com/netcookies/isulewTools/commit/81222228)
- 重构 ADAS 浮窗为被动响应模式，修复多实例 bug [`320c1c79`](https://github.com/netcookies/isulewTools/commit/320c1c79)
- 优化 CoreOrchestrator 代码质量 [`d1521f25`](https://github.com/netcookies/isulewTools/commit/d1521f25)

## v2.0.2 (2025-12-25)

### Feature
- 静态注册美式的乾坤守护广播接收器 [`62a98ca4`](https://github.com/netcookies/isulewTools/commit/62a98ca4)
- 静态注册美式的乾坤守护广播接收器 [`7c97e0a1`](https://github.com/netcookies/isulewTools/commit/7c97e0a1)
- 开启乾坤守护时隐藏悬浮窗 [`41633ca9`](https://github.com/netcookies/isulewTools/commit/41633ca9)

### Bug Fixes
- compile warning [`5fa6458a`](https://github.com/netcookies/isulewTools/commit/5fa6458a)
- 尝试修复 ADAS 竞态导致多个浮窗问题 [`f81ea38b`](https://github.com/netcookies/isulewTools/commit/f81ea38b)
- 修复方控全局关闭无效的问题 [`b35fa971`](https://github.com/netcookies/isulewTools/commit/b35fa971)
- 修复方控全局关闭无效的问题 [`1b98c920`](https://github.com/netcookies/isulewTools/commit/1b98c920)

### Build
- update libs [`7fa44444`](https://github.com/netcookies/isulewTools/commit/7fa44444)

### Refactor
- 替换0x0日志服务为奔佬的 [`6a4778f6`](https://github.com/netcookies/isulewTools/commit/6a4778f6)

### Other
- remove: 移出无用的配置 [`20c5f005`](https://github.com/netcookies/isulewTools/commit/20c5f005)
- remove: 移出无用的配置 [`876dc7a2`](https://github.com/netcookies/isulewTools/commit/876dc7a2)
- remove: 移出无用的配置 [`a78aaa45`](https://github.com/netcookies/isulewTools/commit/a78aaa45)

## v2.0.1 (2025-12-23)

### Feature
- 统一内部坐标系为WGS84并修复红绿灯数据回调问题 [`d5e5fa0d`](https://github.com/netcookies/isulewTools/commit/d5e5fa0d)
- 颜色选择器添加哪吒主题预设颜色 [`d976829c`](https://github.com/netcookies/isulewTools/commit/d976829c)
- 媒体控制小组件简约布局添加标题控件大小配置 [`bcc49d4f`](https://github.com/netcookies/isulewTools/commit/bcc49d4f)

### Bug Fixes
- 修复外部控制(车载按键/通知栏/蓝牙)播放状态不更新UI [`902e5383`](https://github.com/netcookies/isulewTools/commit/902e5383)
- 修复WebAPI播放/暂停状态不更新UI的问题 [`b0f4bc26`](https://github.com/netcookies/isulewTools/commit/b0f4bc26)
- 修复FSM暂停→播放场景不自动切换歌词的问题 [`f2e1ae6a`](https://github.com/netcookies/isulewTools/commit/f2e1ae6a)
- 修复方控关闭失效问题 [`e559bb2f`](https://github.com/netcookies/isulewTools/commit/e559bb2f)

### Performance Improvements
- 移出没用变量 [`005e5b7e`](https://github.com/netcookies/isulewTools/commit/005e5b7e)
- 优化布局 [`7d01d1d2`](https://github.com/netcookies/isulewTools/commit/7d01d1d2)
- 主题管理页面的卡片添加一个标签占位符 [`1d5769ea`](https://github.com/netcookies/isulewTools/commit/1d5769ea)

### Build
- 更新gitignore [`ffe6a1f6`](https://github.com/netcookies/isulewTools/commit/ffe6a1f6)

### Refactor
- 实现完整FSM状态机模型并修复QQ音乐数据源切换问题 [`28294342`](https://github.com/netcookies/isulewTools/commit/28294342)
- 红绿灯数据系统迁移到AIDL架构并修复数据源问题 [`cd8bfcee`](https://github.com/netcookies/isulewTools/commit/cd8bfcee)
- 内置的 QQ 音乐车机版统一使用网络 API 下载歌词和封面 [`70f81ad4`](https://github.com/netcookies/isulewTools/commit/70f81ad4)

### Other

## v2.0.0 (2025-12-18)

### Feature
- 添加 Guardian 应用悬浮窗强制隐藏功能 [`f6e3d949`](https://github.com/netcookies/isulewTools/commit/f6e3d949)
- 新增高德地图路线规划和红绿灯解析功能 [`7b3c8c41`](https://github.com/netcookies/isulewTools/commit/7b3c8c41)
- 新增档位显示、迷你数值卡片、车辆灯光小组件 [`dd003607`](https://github.com/netcookies/isulewTools/commit/dd003607)
- 添加悬浮窗小组件锁定按钮，同时辅助编辑功能会同步状态到悬浮小组件的锁定状态 [`aafcc215`](https://github.com/netcookies/isulewTools/commit/aafcc215)

### Performance Improvements
- 优化档位显示、迷你数值卡片、车辆灯光小组件 [`404a800f`](https://github.com/netcookies/isulewTools/commit/404a800f)
- 优化 snackbar 表现 [`7a42e149`](https://github.com/netcookies/isulewTools/commit/7a42e149)
- 优化 QQ 音乐歌词的滚动逻辑，现在应该不会因为网络延迟而导致不同步了 [`fb7f05fe`](https://github.com/netcookies/isulewTools/commit/fb7f05fe)

### Refactor
- 更新 AirAutoSDK 移除后的配置文件 [`70ad1f93`](https://github.com/netcookies/isulewTools/commit/70ad1f93)
- 移除 AirAutoSDK 模块及紧密耦合的辅助类 [`d27a8877`](https://github.com/netcookies/isulewTools/commit/d27a8877)

## v1.9.9 (2025-12-17)

### Feature
- 添加媒体控制小组件简约模式 & 修复线程问题 [`a37d9962`](https://github.com/netcookies/isulewTools/commit/a37d9962)

### Bug Fixes
- 修复 jni 文件丢失的问题 [`019f13a7`](https://github.com/netcookies/isulewTools/commit/019f13a7)
- 修复农历日期错误 [`00c1e985`](https://github.com/netcookies/isulewTools/commit/00c1e985)
- 适配 QQ 音乐 2.9 [`d723c5a1`](https://github.com/netcookies/isulewTools/commit/d723c5a1)
- 修复歌词自动切换无效的问题 [`597e611d`](https://github.com/netcookies/isulewTools/commit/597e611d)
- 修复红绿灯数据源切换错误，现在用全局单例持有VHAL、红绿灯的数据源实例 [`d371d3e1`](https://github.com/netcookies/isulewTools/commit/d371d3e1)
- 修复红绿灯实时数据闪退 [`150ae979`](https://github.com/netcookies/isulewTools/commit/150ae979)

### Performance Improvements
- 优化兜底设计和日志输出 [`1c07b960`](https://github.com/netcookies/isulewTools/commit/1c07b960)
- 优化封面下载逻辑, 以及播放状态检测 [`3e96251b`](https://github.com/netcookies/isulewTools/commit/3e96251b)
- 优化混淆白名单 [`6161a2d5`](https://github.com/netcookies/isulewTools/commit/6161a2d5)
- 优化gitignore [`70bf4334`](https://github.com/netcookies/isulewTools/commit/70bf4334)
- 优化哪吒 L 模式下的默认宽度 [`a80871ca`](https://github.com/netcookies/isulewTools/commit/a80871ca)
- 同应用全屏切换时的防抖 [`66f304b6`](https://github.com/netcookies/isulewTools/commit/66f304b6)
- 主题管理页面的卡片列表高度统一 [`8151b80d`](https://github.com/netcookies/isulewTools/commit/8151b80d)
- 点击控制界面跳转到音乐软件 [`70b52c93`](https://github.com/netcookies/isulewTools/commit/70b52c93)
- 适配车机自带的网易云和 QQ 音乐 [`09f864f5`](https://github.com/netcookies/isulewTools/commit/09f864f5)
- 优化哪吒 L 媒体控制卡片大小 [`630ba4e8`](https://github.com/netcookies/isulewTools/commit/630ba4e8)
- 将美式移出内置按需显示白名单 [`32b76c62`](https://github.com/netcookies/isulewTools/commit/32b76c62)

### Build
- 清理日志 [`c8ac4596`](https://github.com/netcookies/isulewTools/commit/c8ac4596)

### Refactor
- 重构QQMusicDataSourceV29为WebApiMusicDataSource [`78a3705e`](https://github.com/netcookies/isulewTools/commit/78a3705e)
- 重构特权服务包结构 [`c174a475`](https://github.com/netcookies/isulewTools/commit/c174a475)
- QQ 音乐车机版 tmd，V2.7和V2.9有两套逻辑 [`e2a22243`](https://github.com/netcookies/isulewTools/commit/e2a22243)
- 适配车机原版 app重构歌词、媒体会话 [`05a6454c`](https://github.com/netcookies/isulewTools/commit/05a6454c)

## v1.9.8 (2025-12-14)

### Feature
- 实现歌词滚动显示 [`3a1e41b2`](https://github.com/netcookies/isulewTools/commit/3a1e41b2)
- 实现歌词获取功能 [`0ca672dc`](https://github.com/netcookies/isulewTools/commit/0ca672dc)
- 完善媒体控制卡片的权限问题 [`830dea83`](https://github.com/netcookies/isulewTools/commit/830dea83)
- 媒体服务 [`f06d47c9`](https://github.com/netcookies/isulewTools/commit/f06d47c9)
- 调用哪吒地图原厂的AirAutoSDK获取地图数据 [`5bcd47f7`](https://github.com/netcookies/isulewTools/commit/5bcd47f7)
- 初版红绿灯小组件 [`2c9fccf4`](https://github.com/netcookies/isulewTools/commit/2c9fccf4)
- 添加媒体控制卡片 [`a9a874f9`](https://github.com/netcookies/isulewTools/commit/a9a874f9)

### Bug Fixes
- 修复包名错误 [`dd29a678`](https://github.com/netcookies/isulewTools/commit/dd29a678)
- 修复多个自定义数据源公式竞态问题 [`e52d34de`](https://github.com/netcookies/isulewTools/commit/e52d34de)
- 小组件还原时如果有新参数不会新增到数据库的问题 [`09f91a6b`](https://github.com/netcookies/isulewTools/commit/09f91a6b)
- 指南针优化 [`2cbcdf8a`](https://github.com/netcookies/isulewTools/commit/2cbcdf8a)
- 轮胎小组件修复 [`7a08f590`](https://github.com/netcookies/isulewTools/commit/7a08f590)

### Performance Improvements
- 添加 MediaNotificationListenerService [`4acb9d59`](https://github.com/netcookies/isulewTools/commit/4acb9d59)
- 添加 STUB [`71085de0`](https://github.com/netcookies/isulewTools/commit/71085de0)
- 优化媒体控制卡片布局 [`297d85a4`](https://github.com/netcookies/isulewTools/commit/297d85a4)
- 数字时钟支持农历和公历日期的显示 [`766e7443`](https://github.com/netcookies/isulewTools/commit/766e7443)
- 统一内置小组件字体 [`10bdc837`](https://github.com/netcookies/isulewTools/commit/10bdc837)
- 添加从地库出来定位的恢复机制 [`8e09be02`](https://github.com/netcookies/isulewTools/commit/8e09be02)
- 添加从地库出来定位的恢复机制 [`89bcce9b`](https://github.com/netcookies/isulewTools/commit/89bcce9b)
- 添加从地库出来定位的恢复机制 [`63171809`](https://github.com/netcookies/isulewTools/commit/63171809)

### Test
- 每 15秒刷新一次路经，测试是否有红绿灯数据 [`6d2d9b1e`](https://github.com/netcookies/isulewTools/commit/6d2d9b1e)
- 实验性功能 - 红绿灯（还在科研中） [`d96458d2`](https://github.com/netcookies/isulewTools/commit/d96458d2)

### Build
- 清理实验性功能 [`66c5a487`](https://github.com/netcookies/isulewTools/commit/66c5a487)
- code clean up [`04a17118`](https://github.com/netcookies/isulewTools/commit/04a17118)

## v1.9.7 (2025-12-10)

### Feature
- 实现指南针小组件 [`8b33a44e`](https://github.com/netcookies/isulewTools/commit/8b33a44e)

### Bug Fixes
- 修复拖拽时的预览框同步问题 [`f2a9284f`](https://github.com/netcookies/isulewTools/commit/f2a9284f)
- 修复小组件缩放大小计算错误 [`283730fc`](https://github.com/netcookies/isulewTools/commit/283730fc)
- 修复仪表盘小组件缩放后的拖动问题 [`776a1197`](https://github.com/netcookies/isulewTools/commit/776a1197)

### Performance Improvements
- 指南针在北±22°或南±22° 显示度数必然 [`c0c2adf4`](https://github.com/netcookies/isulewTools/commit/c0c2adf4)

### Refactor
- 重构仪表盘拖动逻辑 [`8c4183a2`](https://github.com/netcookies/isulewTools/commit/8c4183a2)

## v1.9.6 (2025-12-09)

### Feature
- 侧边栏和设置-其他功能增加一个全屏切换按钮 [`f50b395f`](https://github.com/netcookies/isulewTools/commit/f50b395f)
- 侧边栏增加一个全屏切换按钮 [`11673820`](https://github.com/netcookies/isulewTools/commit/11673820)
- 全屏控制功能 [`8c20b590`](https://github.com/netcookies/isulewTools/commit/8c20b590)

### Bug Fixes
- 修复数据源选择器遗漏全局属性的问题并优化排序 [`c19463ff`](https://github.com/netcookies/isulewTools/commit/c19463ff)

### Other

## v1.9.5 (2025-12-09)

### Feature
- 实现座舱ID中文名称显示 [`5a88eb41`](https://github.com/netcookies/isulewTools/commit/5a88eb41)
- 添加车辆属性的有效与无效过滤开关 [`ff593156`](https://github.com/netcookies/isulewTools/commit/ff593156)

### Bug Fixes
- fix deprecate fun [`a7ef34ce`](https://github.com/netcookies/isulewTools/commit/a7ef34ce)
- 修复小组件升级或启动时不注入默认数据源 [`2a98aa5e`](https://github.com/netcookies/isulewTools/commit/2a98aa5e)
- 修复小组件升级或启动时不注入默认数据源 [`b26fc312`](https://github.com/netcookies/isulewTools/commit/b26fc312)

### Performance Improvements
- 优化基础设施 [`48e55191`](https://github.com/netcookies/isulewTools/commit/48e55191)

### Test
- 添加实验性功能 [`ae587da8`](https://github.com/netcookies/isulewTools/commit/ae587da8)

### Other

## v1.9.4 (2025-12-08)

### Feature
- 小组件系统支持数据源默认值自动注入 [`3b6e46f8`](https://github.com/netcookies/isulewTools/commit/3b6e46f8)

### Bug Fixes
- 修复电池小组件低电量时绘制错误 [`24c0d0d3`](https://github.com/netcookies/isulewTools/commit/24c0d0d3)

### Performance Improvements
- 小组件备份和载入代码优化 [`00a261d1`](https://github.com/netcookies/isulewTools/commit/00a261d1)
- 小组件和智驾悬浮窗的备份目录调整，目前的路径结构如下 // Download 目录结构： // neta_connect/ - JAR 插件手动加载目录 // neta_connect/widgets/ - 小组件配置备份 // neta_connect/adas/ - ADAS 悬浮窗配置备份 // neta_connect/custom_data_sources/- 自定义数据源备份 // neta_connect/themes/ - 主题包备份 [`dc97fcca`](https://github.com/netcookies/isulewTools/commit/dc97fcca)

## v1.9.3 (2025-12-07)

### Bug Fixes
- 修复历史数据造成的小组件数据显示重复的问题 [`57d0d86a`](https://github.com/netcookies/isulewTools/commit/57d0d86a)

## v1.9.2 (2025-12-06)

### Feature
- 仪表盘为空时提供默认导入功能 [`f728e3e0`](https://github.com/netcookies/isulewTools/commit/f728e3e0)
- 设置 - 高级设置 - 主题管理功能 [`b0774d7b`](https://github.com/netcookies/isulewTools/commit/b0774d7b)
- 添加常用图标 [`71e1f147`](https://github.com/netcookies/isulewTools/commit/71e1f147)
- 添加三个新的信息卡片 [`185b88bc`](https://github.com/netcookies/isulewTools/commit/185b88bc)
- 添加自定义数据源，支持引用车辆属性通过公式计算得出虚拟属性，比如: 车辆功率 = (电压 x 电流) / 1000 [`31d41505`](https://github.com/netcookies/isulewTools/commit/31d41505)
- 添加仪表盘状态栏。仪表盘作为首页。 [`b4c180b7`](https://github.com/netcookies/isulewTools/commit/b4c180b7)
- 添加三个网格图标小组件 [`ca7bd5e3`](https://github.com/netcookies/isulewTools/commit/ca7bd5e3)
- 仪表盘小组件重构 [`7d203271`](https://github.com/netcookies/isulewTools/commit/7d203271)
- 添加网格布局系统数据模型和修复选择器点击问题 [`ed1ac6e3`](https://github.com/netcookies/isulewTools/commit/ed1ac6e3)
- 重构 textfield 类的设置项 [`0fe442f5`](https://github.com/netcookies/isulewTools/commit/0fe442f5)
- 动态获取前景颜色，保证字体可读性 [`858e65a1`](https://github.com/netcookies/isulewTools/commit/858e65a1)
- 自定义SnackBar [`a08a1f57`](https://github.com/netcookies/isulewTools/commit/a08a1f57)
- 添加全局snackbar [`2c02914c`](https://github.com/netcookies/isulewTools/commit/2c02914c)
- 应用控制 tab 重构完毕 [`2d2940ee`](https://github.com/netcookies/isulewTools/commit/2d2940ee)
- 添加BottomBar [`c8211f98`](https://github.com/netcookies/isulewTools/commit/c8211f98)
- 拆分方控 tab [`e5f856ce`](https://github.com/netcookies/isulewTools/commit/e5f856ce)
- 更新主界面字体 [`5bfa40a4`](https://github.com/netcookies/isulewTools/commit/5bfa40a4)
- 新增两种分段式控制设置项的风格 [`1472e407`](https://github.com/netcookies/isulewTools/commit/1472e407)
- 新增通知栏基础设施 [`b1d157be`](https://github.com/netcookies/isulewTools/commit/b1d157be)
- 添加布局文件 [`63b5d806`](https://github.com/netcookies/isulewTools/commit/63b5d806)
- 统一设计常量 [`ce407799`](https://github.com/netcookies/isulewTools/commit/ce407799)
- 新增AppIconGroup.kt [`2f361089`](https://github.com/netcookies/isulewTools/commit/2f361089)
- 替换 Slider 为 AppSlider [`aa4d761f`](https://github.com/netcookies/isulewTools/commit/aa4d761f)
- 添加AppSlider [`d9a682ac`](https://github.com/netcookies/isulewTools/commit/d9a682ac)
- 导入小组件配置会自动建立订阅 [`aab37b92`](https://github.com/netcookies/isulewTools/commit/aab37b92)
- 优化高级设置页面 [`1125b65f`](https://github.com/netcookies/isulewTools/commit/1125b65f)
- 添加 IconCardButton [`e51d9c66`](https://github.com/netcookies/isulewTools/commit/e51d9c66)
- 添加高级设置 - 杀掉特权功能 [`e99364e6`](https://github.com/netcookies/isulewTools/commit/e99364e6)

### Bug Fixes
- 图标引用错误 [`57ae2a7b`](https://github.com/netcookies/isulewTools/commit/57ae2a7b)
- typo [`de807fd0`](https://github.com/netcookies/isulewTools/commit/de807fd0)
- 修复仪表盘小组件编辑功能 [`3dc64a6c`](https://github.com/netcookies/isulewTools/commit/3dc64a6c)
- 修复仪表盘缩放变形问题 [`6674759f`](https://github.com/netcookies/isulewTools/commit/6674759f)
- 修复仪表盘网格布局三个关键问题 [`63ed920e`](https://github.com/netcookies/isulewTools/commit/63ed920e)
- 修复小组件点击无法弹出 [`05c028e4`](https://github.com/netcookies/isulewTools/commit/05c028e4)
- 修复小组件禁用显示 [`ff5c9602`](https://github.com/netcookies/isulewTools/commit/ff5c9602)
- 修复时钟 bug [`2c99a241`](https://github.com/netcookies/isulewTools/commit/2c99a241)
- 去除屏幕上方白色多余背景 [`76dae097`](https://github.com/netcookies/isulewTools/commit/76dae097)
- adb端口缓存错误的问题 [`38fc25dd`](https://github.com/netcookies/isulewTools/commit/38fc25dd)
- 优化麦克风页面布局 [`d1af4b67`](https://github.com/netcookies/isulewTools/commit/d1af4b67)
- 修复麦克风重服务开关回弹问题 [`3f555af5`](https://github.com/netcookies/isulewTools/commit/3f555af5)
- 修复麦克风重启自动恢复功能 [`a30d890f`](https://github.com/netcookies/isulewTools/commit/a30d890f)
- 修复状态栏和内容区域的距离 [`c63a242f`](https://github.com/netcookies/isulewTools/commit/c63a242f)
- 修复亮屏权限恢复错误 [`fad5c347`](https://github.com/netcookies/isulewTools/commit/fad5c347)
- 修复休眠后 ADB 健康检测失效的问题 [`53fd07aa`](https://github.com/netcookies/isulewTools/commit/53fd07aa)
- 修复智驾悬浮窗加载保存的配置错误 [`f8fa3813`](https://github.com/netcookies/isulewTools/commit/f8fa3813)
- 数据类型错误 [`93afaed7`](https://github.com/netcookies/isulewTools/commit/93afaed7)

### Performance Improvements
- 优化小组件边距 [`e7d21ad5`](https://github.com/netcookies/isulewTools/commit/e7d21ad5)
- 虚拟 ID 10进制用正数 [`2d6a9f9a`](https://github.com/netcookies/isulewTools/commit/2d6a9f9a)
- 更新车辆属性翻译 [`93d52334`](https://github.com/netcookies/isulewTools/commit/93d52334)
- 优化图表小组件文字大小 [`0ceb89c1`](https://github.com/netcookies/isulewTools/commit/0ceb89c1)
- 减少仪表盘小组件边距 [`95d87daa`](https://github.com/netcookies/isulewTools/commit/95d87daa)
- 优化仪表盘状态栏可点击 [`2deb06bb`](https://github.com/netcookies/isulewTools/commit/2deb06bb)
- 小组件的备份和恢复（载入）管理更加精细 [`34f9d656`](https://github.com/netcookies/isulewTools/commit/34f9d656)
- 挂载区域和组件类型弹窗优化 [`95a8ca55`](https://github.com/netcookies/isulewTools/commit/95a8ca55)
- 更新状态标签字体颜色 [`7ad68bf6`](https://github.com/netcookies/isulewTools/commit/7ad68bf6)
- 刷新配置增加 snackbar 消息 [`e0a10055`](https://github.com/netcookies/isulewTools/commit/e0a10055)
- 更新 text + 弹窗组件的交互体验 [`b5ea4c47`](https://github.com/netcookies/isulewTools/commit/b5ea4c47)
- 适配小组件写入数据选择控件的保存、恢复序列化不正确问题 [`e7fb8051`](https://github.com/netcookies/isulewTools/commit/e7fb8051)
- 删除多余的小组件类型WidgetParamType.VEHICLE_PROPERTY [`b67d3338`](https://github.com/netcookies/isulewTools/commit/b67d3338)
- 更新插件描述 [`052f5538`](https://github.com/netcookies/isulewTools/commit/052f5538)
- 优化小组件的拖动排序功能 [`ae497423`](https://github.com/netcookies/isulewTools/commit/ae497423)
- 优化车辆属性卡片 [`ac76abb0`](https://github.com/netcookies/isulewTools/commit/ac76abb0)
- 优化车辆属性卡片 [`b145fdb1`](https://github.com/netcookies/isulewTools/commit/b145fdb1)
- 优化驾驶助手页面 [`71e4a6d1`](https://github.com/netcookies/isulewTools/commit/71e4a6d1)
- 增加属性分组字段 [`f5d926b2`](https://github.com/netcookies/isulewTools/commit/f5d926b2)
- 更新翻译信息 [`f1cb0402`](https://github.com/netcookies/isulewTools/commit/f1cb0402)
- 优化小组件布局 [`b6c4e76b`](https://github.com/netcookies/isulewTools/commit/b6c4e76b)
- 优化小组件的创建和编辑页面 [`27cb6a1e`](https://github.com/netcookies/isulewTools/commit/27cb6a1e)
- 调整 Fab 位置 [`a7c798ea`](https://github.com/netcookies/isulewTools/commit/a7c798ea)
- 优化文字描述 [`1c5af9ba`](https://github.com/netcookies/isulewTools/commit/1c5af9ba)
- 麦克风卡片迁移完毕 [`3dee0c84`](https://github.com/netcookies/isulewTools/commit/3dee0c84)
- 优化 BottomBar.kt [`d3d2d2e7`](https://github.com/netcookies/isulewTools/commit/d3d2d2e7)
- 通知从左向右 [`7812bfb2`](https://github.com/netcookies/isulewTools/commit/7812bfb2)
- 完成驾驶助手子 tab 的布局调整 [`a0d6f344`](https://github.com/netcookies/isulewTools/commit/a0d6f344)
- 常量替换 [`1da802d8`](https://github.com/netcookies/isulewTools/commit/1da802d8)
- 使用自定义 Layout 优化AppIconGroup [`a0013ceb`](https://github.com/netcookies/isulewTools/commit/a0013ceb)
- 关于页面重构 [`7fd16da3`](https://github.com/netcookies/isulewTools/commit/7fd16da3)
- 优化小组件编辑和创建页面 [`ae12ee0e`](https://github.com/netcookies/isulewTools/commit/ae12ee0e)
- 优化分段控制器 [`acfe4156`](https://github.com/netcookies/isulewTools/commit/acfe4156)
- removing warning stuff [`ccb672f7`](https://github.com/netcookies/isulewTools/commit/ccb672f7)
- 完善配色和滚动条 [`1f70b35f`](https://github.com/netcookies/isulewTools/commit/1f70b35f)
- 使用主题颜色 [`61db733b`](https://github.com/netcookies/isulewTools/commit/61db733b)
- 添加了保守的重连策略： - ADB 恢复健康后延迟 2 秒 - 检查应用是否完成初始化 (AppInitStatus.isReady) - 如果 Privilege 不健康，再延迟 3 秒 - 三重检查后触发 Privilege 重连 - 避免了初始化期间的竞态问题 [`ab117cb6`](https://github.com/netcookies/isulewTools/commit/ab117cb6)
- IconCardButton.kt 添加状态指示 [`8c34683a`](https://github.com/netcookies/isulewTools/commit/8c34683a)
- 每次升级杀掉特权进程 [`9d5cad7c`](https://github.com/netcookies/isulewTools/commit/9d5cad7c)
- 移出麦克风vm的自动注入，防止过早初始化 [`15bfbede`](https://github.com/netcookies/isulewTools/commit/15bfbede)
- 移出麦克风vm的自动注入，防止过早初始化 [`59d083c6`](https://github.com/netcookies/isulewTools/commit/59d083c6)
- 从实验室迁移麦克风功能到高级功能 [`4b6dda0b`](https://github.com/netcookies/isulewTools/commit/4b6dda0b)
- 完善实验性功能 UI 测试 [`daa7db1c`](https://github.com/netcookies/isulewTools/commit/daa7db1c)
- 添加麦克风回调机制（施工中...还不可用） [`ef15fbc5`](https://github.com/netcookies/isulewTools/commit/ef15fbc5)
- 尝试优化亮屏后恢复特权进程 [`31103bab`](https://github.com/netcookies/isulewTools/commit/31103bab)

### Test
- 测试麦克风 B 是否能单独获取设备信息 [`36561ca6`](https://github.com/netcookies/isulewTools/commit/36561ca6)

### Build
- 添加仪表盘代码到混淆白名单 [`0cf4e339`](https://github.com/netcookies/isulewTools/commit/0cf4e339)
- update gitignore [`b042e0f1`](https://github.com/netcookies/isulewTools/commit/b042e0f1)
- 添加 taskmaster 任务 [`02ce6e29`](https://github.com/netcookies/isulewTools/commit/02ce6e29)
- 添加 taskmaster 任务 [`37302a25`](https://github.com/netcookies/isulewTools/commit/37302a25)
- 添加 taskmaster 工具 [`0e28db9e`](https://github.com/netcookies/isulewTools/commit/0e28db9e)
- ViewModel代码整理 [`4c67db36`](https://github.com/netcookies/isulewTools/commit/4c67db36)
- update .gitignore [`8f36fe5c`](https://github.com/netcookies/isulewTools/commit/8f36fe5c)

### Refactor
- 重构提交反馈按钮，现在会自动在 ClickUp添加任务 [`e49ae272`](https://github.com/netcookies/isulewTools/commit/e49ae272)
- 重构数据源选择弹窗 [`3b3c5c48`](https://github.com/netcookies/isulewTools/commit/3b3c5c48)
- 重构写入车辆属性的弹窗 [`bfd04891`](https://github.com/netcookies/isulewTools/commit/bfd04891)
- 重构小组件配置的弹窗 [`9c6250be`](https://github.com/netcookies/isulewTools/commit/9c6250be)
- 重构小组件卡片 [`a41d5d47`](https://github.com/netcookies/isulewTools/commit/a41d5d47)
- 重构完成所有一级页面 [`66f7d207`](https://github.com/netcookies/isulewTools/commit/66f7d207)
- 重构设置页面 [`657766c1`](https://github.com/netcookies/isulewTools/commit/657766c1)
- 去除全局通知栏，用SnackBar替代，更优雅 [`cb69fb08`](https://github.com/netcookies/isulewTools/commit/cb69fb08)
- 重构小组件商店 [`ca6bba63`](https://github.com/netcookies/isulewTools/commit/ca6bba63)
- 重构小组件管理页面 [`f8753369`](https://github.com/netcookies/isulewTools/commit/f8753369)
- 悬浮窗页面重构完成 [`f4e4f441`](https://github.com/netcookies/isulewTools/commit/f4e4f441)
- 重构SliderSettings [`713289aa`](https://github.com/netcookies/isulewTools/commit/713289aa)
- 重构AppIconGroup [`5880f664`](https://github.com/netcookies/isulewTools/commit/5880f664)
- 将页面归集到 page 包 [`4c40889a`](https://github.com/netcookies/isulewTools/commit/4c40889a)
- 重构页面布局，采用三层架构 [`66d49cf5`](https://github.com/netcookies/isulewTools/commit/66d49cf5)
- 重构顶部状态栏 [`e7347cd8`](https://github.com/netcookies/isulewTools/commit/e7347cd8)
- 重构导航栏 [`66f801f7`](https://github.com/netcookies/isulewTools/commit/66f801f7)
- 应用IconGroup到所有关联页面 [`5a562494`](https://github.com/netcookies/isulewTools/commit/5a562494)
- 重构按钮 [`c24aa612`](https://github.com/netcookies/isulewTools/commit/c24aa612)
- 维护统一的padding [`ccc113cf`](https://github.com/netcookies/isulewTools/commit/ccc113cf)
- 重构StatusChip [`985a2bb6`](https://github.com/netcookies/isulewTools/commit/985a2bb6)
- renamed package name [`68552ac8`](https://github.com/netcookies/isulewTools/commit/68552ac8)
- 重命名IconCardButton [`f5b98c6e`](https://github.com/netcookies/isulewTools/commit/f5b98c6e)
- 重构分段控制器 [`ffb08a2a`](https://github.com/netcookies/isulewTools/commit/ffb08a2a)

## v1.9.1 (2025-11-13)

### Feature
- 添加背景文字 [`846202fe`](https://github.com/netcookies/isulewTools/commit/846202fe)
- 添加 AppSwitch UI 控件 [`02754eb6`](https://github.com/netcookies/isulewTools/commit/02754eb6)
- 添加小组件拖动排序逻辑 [`ab5aca26`](https://github.com/netcookies/isulewTools/commit/ab5aca26)

### Bug Fixes
- 修复背景大小没有应用缩放的问题 [`38e15d5b`](https://github.com/netcookies/isulewTools/commit/38e15d5b)
- 修复注册小组件版本号再数据库时硬编码为 1.0.0 的问题 [`c7969eb2`](https://github.com/netcookies/isulewTools/commit/c7969eb2)
- 用 ss 替换 lsof 查询监听进程 [`a8157966`](https://github.com/netcookies/isulewTools/commit/a8157966)

### Performance Improvements
- 应 pan 总要求，车辆属性显示中文名称、英文名称、10进制 ID [`b8d638f8`](https://github.com/netcookies/isulewTools/commit/b8d638f8)
- 小组件开关支持透明度 [`57d5b450`](https://github.com/netcookies/isulewTools/commit/57d5b450)
- 小组件开关支持缩放 [`069a688b`](https://github.com/netcookies/isulewTools/commit/069a688b)
- 所有的开关适配新的开关组件 [`4ee85e28`](https://github.com/netcookies/isulewTools/commit/4ee85e28)
- 所有的开关适配新的开关组件 [`2f9b2c95`](https://github.com/netcookies/isulewTools/commit/2f9b2c95)
- 优化日志页筛选功能 [`c8d3f2f8`](https://github.com/netcookies/isulewTools/commit/c8d3f2f8)
- 优化车辆状态 tab 布局 [`ae337ac3`](https://github.com/netcookies/isulewTools/commit/ae337ac3)
- 事件流筛选弹窗颜色优化 [`bd38010a`](https://github.com/netcookies/isulewTools/commit/bd38010a)
- 事件流筛选弹窗颜色优化 [`fe3652a8`](https://github.com/netcookies/isulewTools/commit/fe3652a8)
- 指针增加 0.6透明度，日期和周字体增大 40% [`855a7487`](https://github.com/netcookies/isulewTools/commit/855a7487)
- 弧形仪表盘小组件的默认最大值从 100 -> 220，需要调整的可以在最大值处设置 [`fdbd5c7b`](https://github.com/netcookies/isulewTools/commit/fdbd5c7b)

### Refactor
- 重构内置的开关小组件 [`7b86f712`](https://github.com/netcookies/isulewTools/commit/7b86f712)
- 重构事件流显示，提供筛选等控制功能 [`31e49a41`](https://github.com/netcookies/isulewTools/commit/31e49a41)

## v1.9.0 (2025-11-12)

### Bug Fixes
- 修复INT、FLOAT、STRING 类型小组件配置渲染丢失 [`06fcf181`](https://github.com/netcookies/isulewTools/commit/06fcf181)
- 修复小组件在后台无法随主题变化颜色 [`bfb81e4e`](https://github.com/netcookies/isulewTools/commit/bfb81e4e)

### Performance Improvements
- ci脚本环境变量优化 [`c303bb38`](https://github.com/netcookies/isulewTools/commit/c303bb38)
- ci脚本环境变量优化 [`293784c6`](https://github.com/netcookies/isulewTools/commit/293784c6)

### Test
- 测试雷石麦克风采用socket + iptables方式 [`964d5a9d`](https://github.com/netcookies/isulewTools/commit/964d5a9d)
- 测试雷石麦克风采用socket + iptables方式 [`d44a37d4`](https://github.com/netcookies/isulewTools/commit/d44a37d4)

## v1.8.9 (2025-11-12)

### Feature
- 小组件颜色可配置白天黑夜不同颜色 [`a635717a`](https://github.com/netcookies/isulewTools/commit/a635717a)
- 添加小组件禁用功能（总是显示｜按需显示｜禁止显示） [`ad643686`](https://github.com/netcookies/isulewTools/commit/ad643686)
- 添加本地加载功能，供开发者测试用 [`6310e1fc`](https://github.com/netcookies/isulewTools/commit/6310e1fc)
- 添加小组件最低版本要求检查 [`0bd45873`](https://github.com/netcookies/isulewTools/commit/0bd45873)
- 小组件api支持自定义字体加载 [`c15fda7e`](https://github.com/netcookies/isulewTools/commit/c15fda7e)
- 添加高级设置 - 管理权限功能，用于管理本 APP 权限 [`aa00cc32`](https://github.com/netcookies/isulewTools/commit/aa00cc32)
- 智驾悬浮窗和小组件统一采用json备份在Download/neta_connect目录下，卸载程序不会清空 [`4dea75e0`](https://github.com/netcookies/isulewTools/commit/4dea75e0)

### Bug Fixes
- 修改保存小组件会正确更新属性引用 [`89efe790`](https://github.com/netcookies/isulewTools/commit/89efe790)
- 修复属性订阅类型转换问题（胎压胎温无法显示） [`60552eaf`](https://github.com/netcookies/isulewTools/commit/60552eaf)
- ci脚本环境变量错误 [`c1d1c13d`](https://github.com/netcookies/isulewTools/commit/c1d1c13d)

### Performance Improvements
- 小组件的基础设置也参与滚动 [`a859f349`](https://github.com/netcookies/isulewTools/commit/a859f349)
- 优化import [`59ca9ad6`](https://github.com/netcookies/isulewTools/commit/59ca9ad6)
- 优化import [`8f5f6934`](https://github.com/netcookies/isulewTools/commit/8f5f6934)
- 下电太久后，尝试恢复特权服务 [`04daabdd`](https://github.com/netcookies/isulewTools/commit/04daabdd)

### Build
- update ci [`b29e5aab`](https://github.com/netcookies/isulewTools/commit/b29e5aab)
- update ci [`ca568605`](https://github.com/netcookies/isulewTools/commit/ca568605)

### Refactor
- 小组件架构优化，更简洁 [`7b6914da`](https://github.com/netcookies/isulewTools/commit/7b6914da)
- 迁移弧形仪表盘、电池、进度条、温度剂至小组件商店 [`a7a25392`](https://github.com/netcookies/isulewTools/commit/a7a25392)
- 重构智驾悬浮窗的配置保存与载入功能，卸载程序不会清空配置了。保存目录：Download/neta_connect [`d45481f9`](https://github.com/netcookies/isulewTools/commit/d45481f9)

### Other
- BUMP VERSION [`6cd4c299`](https://github.com/netcookies/isulewTools/commit/6cd4c299)

## v1.8.8 (2025-11-05)

### Feature
- 添加备份和载入小组件配置的功能 [`2a8ede38`](https://github.com/netcookies/isulewTools/commit/2a8ede38)
- 添加备份和载入小组件配置的功能 [`92363458`](https://github.com/netcookies/isulewTools/commit/92363458)
- 辅助编辑工具增加步进调整按钮，移动更加精细。辅助编辑器状态不再持久化 [`c76aa2fb`](https://github.com/netcookies/isulewTools/commit/c76aa2fb)

### Bug Fixes
- 辅助编辑工具不再强制显示通知栏 [`de9ebd77`](https://github.com/netcookies/isulewTools/commit/de9ebd77)
- 修复胎压换算问题 [`dabdfda9`](https://github.com/netcookies/isulewTools/commit/dabdfda9)

## v1.8.7 (2025-11-05)

### Feature
- 数据源支持注入，并提供各种类型的便捷函数简化插件编写 [`fcb319e0`](https://github.com/netcookies/isulewTools/commit/fcb319e0)

### Build
- 测试构建脚本 [`257458c9`](https://github.com/netcookies/isulewTools/commit/257458c9)
- 发布小组件 [`081d9ed5`](https://github.com/netcookies/isulewTools/commit/081d9ed5)

## v1.8.6 (2025-11-05)

### Feature
- 合并速度指示器、油表指示器为弧形仪表盘 [`51b38839`](https://github.com/netcookies/isulewTools/commit/51b38839)

### Bug Fixes
- 修复车辆属性 areaId 只有 0的问题 [`bf1a84ba`](https://github.com/netcookies/isulewTools/commit/bf1a84ba)

### Performance Improvements
- 完善弧形仪表盘等组件 [`1c679418`](https://github.com/netcookies/isulewTools/commit/1c679418)
- 完善刻度表现 [`ff296d0d`](https://github.com/netcookies/isulewTools/commit/ff296d0d)
- 电池默认颜色白色还是更好看 [`613c92cb`](https://github.com/netcookies/isulewTools/commit/613c92cb)

### Refactor
- 重构油量小组件 [`55ef7cc5`](https://github.com/netcookies/isulewTools/commit/55ef7cc5)
- 重构时间小组件 [`d5f3f159`](https://github.com/netcookies/isulewTools/commit/d5f3f159)

## v1.8.5 (2025-11-04)

### Feature
- 采用新的 color picker [`8b024231`](https://github.com/netcookies/isulewTools/commit/8b024231)

### Bug Fixes
- 修复颜色解析错误 [`6881916c`](https://github.com/netcookies/isulewTools/commit/6881916c)
- 添加mic jni 文件到.gitignore [`f078eafd`](https://github.com/netcookies/isulewTools/commit/f078eafd)

### Performance Improvements
- 优化电池配色 [`a0893814`](https://github.com/netcookies/isulewTools/commit/a0893814)
- 完善字体和图标大小 [`28bc3278`](https://github.com/netcookies/isulewTools/commit/28bc3278)

### Test
- 升级动态小组件示例 [`786ab90a`](https://github.com/netcookies/isulewTools/commit/786ab90a)
- 测试小组件升级版本，编译脚本自带需要的图标 [`77e9eeff`](https://github.com/netcookies/isulewTools/commit/77e9eeff)

### Build
- 减少包大小，优化Compose混淆规则，插件的 icons 由其 jar 包自己持有 [`49637daa`](https://github.com/netcookies/isulewTools/commit/49637daa)
- 减少包大小，优化Compose混淆规则，插件的 icons 由其 jar 包自己持有 [`07feb61f`](https://github.com/netcookies/isulewTools/commit/07feb61f)
- 移除ARM 和 X86的 JNI 支持，减少包的大小 [`95af655e`](https://github.com/netcookies/isulewTools/commit/95af655e)
- update depends [`7a569a36`](https://github.com/netcookies/isulewTools/commit/7a569a36)

### Refactor
- 所有小组件默认值统一管理，更好配置 [`82b62726`](https://github.com/netcookies/isulewTools/commit/82b62726)
- 重构电池小组件 [`cdd7f6c2`](https://github.com/netcookies/isulewTools/commit/cdd7f6c2)
- 重构按钮小组件 [`321d2ef0`](https://github.com/netcookies/isulewTools/commit/321d2ef0)

## v1.8.4 (2025-11-03)

### Feature
- 添加 vhal 属性的配置类型 [`1fcbba46`](https://github.com/netcookies/isulewTools/commit/1fcbba46)

### Bug Fixes
- 按钮可正常缩放 [`926daa4c`](https://github.com/netcookies/isulewTools/commit/926daa4c)
- 辅助编辑工具选择小组件切换时，高亮效果能正常切换 [`51b5e000`](https://github.com/netcookies/isulewTools/commit/51b5e000)
- 辅助编辑工具无法选中开关和按钮的问题 [`3e944927`](https://github.com/netcookies/isulewTools/commit/3e944927)
- 修复开关和按钮无法拖动的问题 [`2f90d42f`](https://github.com/netcookies/isulewTools/commit/2f90d42f)

### Performance Improvements
- 完善车辆属性 id 的选择 [`b46f3cb1`](https://github.com/netcookies/isulewTools/commit/b46f3cb1)
- 点击保存支持自动滚动到错误提示 [`6ec8b2b7`](https://github.com/netcookies/isulewTools/commit/6ec8b2b7)
- 确认INT\FLOAT可以正确转换 [`d7699231`](https://github.com/netcookies/isulewTools/commit/d7699231)
- 属性支持根据其他属性动态显示，如方形时才显示边角弧度 [`7841577b`](https://github.com/netcookies/isulewTools/commit/7841577b)
- 添加图标选择器 [`bf084650`](https://github.com/netcookies/isulewTools/commit/bf084650)
- 提供图标列表 [`a96e54dc`](https://github.com/netcookies/isulewTools/commit/a96e54dc)
- 优化button [`87ac5a90`](https://github.com/netcookies/isulewTools/commit/87ac5a90)
- 小组件创建和编辑空间布局调整 [`3e3e1b4a`](https://github.com/netcookies/isulewTools/commit/3e3e1b4a)
- 小组件创建和编辑节目标题更醒目 [`09db576d`](https://github.com/netcookies/isulewTools/commit/09db576d)
- 将getAlpha()和getScale()移至widget-api [`ffd22b26`](https://github.com/netcookies/isulewTools/commit/ffd22b26)
- 优化SegmentedButtonRow视觉表现 [`4e70065f`](https://github.com/netcookies/isulewTools/commit/4e70065f)

## v1.8.3 (2025-10-31)

### Bug Fixes
- 修复混淆导致的依赖缺失问题 [`e55bc36d`](https://github.com/netcookies/isulewTools/commit/e55bc36d)
- 预览编译错误 [`defe90ea`](https://github.com/netcookies/isulewTools/commit/defe90ea)

### Performance Improvements
- 换一种判断模拟器的方式 [`29aa9fc6`](https://github.com/netcookies/isulewTools/commit/29aa9fc6)
- 优化小组件架构，减少插件 jar 包大小 [`5c51914b`](https://github.com/netcookies/isulewTools/commit/5c51914b)

### Test
- 升级示例小程序的版本 [`41e1dbdb`](https://github.com/netcookies/isulewTools/commit/41e1dbdb)

### Build
- 优化编译配置 [`54db09e1`](https://github.com/netcookies/isulewTools/commit/54db09e1)

### Other
- plugin: 显示指定插件默认参数 [`ad58d720`](https://github.com/netcookies/isulewTools/commit/ad58d720)

## v1.8.2 (2025-10-28)

### Feature
- 示例插件添加预览示例 [`dfcc8e21`](https://github.com/netcookies/isulewTools/commit/dfcc8e21)

### Bug Fixes
- 修复ci脚本编译错误 [`caf1d83a`](https://github.com/netcookies/isulewTools/commit/caf1d83a)

### Performance Improvements
- 更新示例小组件版本 [`57032d13`](https://github.com/netcookies/isulewTools/commit/57032d13)
- 电池字体完善 [`3f51644f`](https://github.com/netcookies/isulewTools/commit/3f51644f)

### Other
- doc: 文档更新 [`bc153f25`](https://github.com/netcookies/isulewTools/commit/bc153f25)

## v1.8.1 (2025-10-27)

### Bug Fixes
- 修复应用商店插件无法安装问题 [`e7ae14a9`](https://github.com/netcookies/isulewTools/commit/e7ae14a9)
- 修复应用商店插件无法安装问题 [`baa708af`](https://github.com/netcookies/isulewTools/commit/baa708af)
- 小组件自动化发布脚本错误 [`980b8cc9`](https://github.com/netcookies/isulewTools/commit/980b8cc9)

### Build
- 更新混淆规则 [`aef910ce`](https://github.com/netcookies/isulewTools/commit/aef910ce)

## v1.8.0 (2025-10-27)

### Feature
- 完善小组件发布流程 [`cd3a036f`](https://github.com/netcookies/isulewTools/commit/cd3a036f)
- 透明度、缩放自动注入 [`b245231e`](https://github.com/netcookies/isulewTools/commit/b245231e)
- 添加高级功能 - 日志状态查看功能 [`d6deec53`](https://github.com/netcookies/isulewTools/commit/d6deec53)
- 新增辅助小工具 [`864ca90a`](https://github.com/netcookies/isulewTools/commit/864ca90a)
- 新增辅助小工具 [`bdb0d1ed`](https://github.com/netcookies/isulewTools/commit/bdb0d1ed)
- 完成小组件商店功能 [`f426b067`](https://github.com/netcookies/isulewTools/commit/f426b067)
- 小组件商店: 业务逻辑与测试UI完成 [`7301eec2`](https://github.com/netcookies/isulewTools/commit/7301eec2)
- 小组件商店: 业务逻辑与测试UI完成 [`eefd08fa`](https://github.com/netcookies/isulewTools/commit/eefd08fa)
- 音量只能 15 [`3952dc0d`](https://github.com/netcookies/isulewTools/commit/3952dc0d)
- 添加麦克风 jni 模块 [`e03ff0db`](https://github.com/netcookies/isulewTools/commit/e03ff0db)
- 日志支持暂停和扫码分享 [`ea9567e7`](https://github.com/netcookies/isulewTools/commit/ea9567e7)
- log统一调用入口 [`900d7919`](https://github.com/netcookies/isulewTools/commit/900d7919)
- 实现系统级悬浮窗（可覆盖状态栏） [`d168a7ab`](https://github.com/netcookies/isulewTools/commit/d168a7ab)
- 实现参考线逻辑 [`7548ff8f`](https://github.com/netcookies/isulewTools/commit/7548ff8f)
- 实现参考线逻辑 [`b69b287b`](https://github.com/netcookies/isulewTools/commit/b69b287b)
- 添加小组件按需显示功能 [`2786b43f`](https://github.com/netcookies/isulewTools/commit/2786b43f)
- 高级设置添加结束应用的按钮 [`56298768`](https://github.com/netcookies/isulewTools/commit/56298768)

### Bug Fixes
- 小组件不可以再被拖出仪表盘 [`a49d1f70`](https://github.com/netcookies/isulewTools/commit/a49d1f70)
- 修复按需显示的选择应用弹窗 [`0f57f0cd`](https://github.com/netcookies/isulewTools/commit/0f57f0cd)
- 仪表盘页面小组件消失 [`90914ecb`](https://github.com/netcookies/isulewTools/commit/90914ecb)
- 修复缺少的函数参数 [`9d26b591`](https://github.com/netcookies/isulewTools/commit/9d26b591)
- 修复缺少的函数参数 [`631080cf`](https://github.com/netcookies/isulewTools/commit/631080cf)
- viewModel用到时才初始化 [`627fcdc4`](https://github.com/netcookies/isulewTools/commit/627fcdc4)
- 档位调节 [`7bf34707`](https://github.com/netcookies/isulewTools/commit/7bf34707)
- 麦克风事件分发 [`7f76abc4`](https://github.com/netcookies/isulewTools/commit/7f76abc4)
- 对齐麦克风电量事件，移除没必要的轮询 [`42095676`](https://github.com/netcookies/isulewTools/commit/42095676)
- 麦克风状态检测 [`06aff95d`](https://github.com/netcookies/isulewTools/commit/06aff95d)
- 修复小组件闪烁问题 [`d319cf6a`](https://github.com/netcookies/isulewTools/commit/d319cf6a)
- add annotation [`2608b77b`](https://github.com/netcookies/isulewTools/commit/2608b77b)
- 移除掉服务端空实现的代码 [`1fc27900`](https://github.com/netcookies/isulewTools/commit/1fc27900)
- 修复麦克风 aidl 错误 [`6088f18b`](https://github.com/netcookies/isulewTools/commit/6088f18b)
- 复现雷石麦克风初始化逻辑 [`d88e529b`](https://github.com/netcookies/isulewTools/commit/d88e529b)
- 修复小组件初始化时序问题 [`a78f134e`](https://github.com/netcookies/isulewTools/commit/a78f134e)
- 修复按需显示的逻辑 [`de3c94d2`](https://github.com/netcookies/isulewTools/commit/de3c94d2)
- 修复按需显示包名弹窗列表 [`659d8a66`](https://github.com/netcookies/isulewTools/commit/659d8a66)
- 修复 root 进程日志回调问题 [`1d3e8504`](https://github.com/netcookies/isulewTools/commit/1d3e8504)

### Performance Improvements
- 优化onSecondary\onError颜色 [`6ec1f8d5`](https://github.com/netcookies/isulewTools/commit/6ec1f8d5)
- fix redundant package [`a48a243f`](https://github.com/netcookies/isulewTools/commit/a48a243f)
- TopStatusBar 优化 [`b9aec7f5`](https://github.com/netcookies/isulewTools/commit/b9aec7f5)
- 修复多余的 padding(视觉上) [`7a527b06`](https://github.com/netcookies/isulewTools/commit/7a527b06)
- 手动触发麦克风系统检查 [`737bd641`](https://github.com/netcookies/isulewTools/commit/737bd641)
- 优化特权日志初始化顺序 [`9d579a7e`](https://github.com/netcookies/isulewTools/commit/9d579a7e)
- 完善授权检测逻辑 [`8bf03f03`](https://github.com/netcookies/isulewTools/commit/8bf03f03)
- 调整黑夜主题的颜色，使文字更易可见 [`ca31f40a`](https://github.com/netcookies/isulewTools/commit/ca31f40a)
- 优化按钮布局逻辑 [`720a2597`](https://github.com/netcookies/isulewTools/commit/720a2597)
- 优化小组件管理页面 UI [`3fd85939`](https://github.com/netcookies/isulewTools/commit/3fd85939)

### Test
- 增加麦克风调试日志 [`c5bc7783`](https://github.com/netcookies/isulewTools/commit/c5bc7783)
- 初始化检测 [`c9f36cc2`](https://github.com/netcookies/isulewTools/commit/c9f36cc2)
- 尝试用 root 进程启动loopback [`33a9a4a3`](https://github.com/netcookies/isulewTools/commit/33a9a4a3)
- 注释掉 AudioTrack/Record 做测试 [`a2bc1865`](https://github.com/netcookies/isulewTools/commit/a2bc1865)
- 修复mic初始化错误 [`33d7b360`](https://github.com/netcookies/isulewTools/commit/33d7b360)
- 测试 UI 的优化 [`a5c95676`](https://github.com/netcookies/isulewTools/commit/a5c95676)
- 换一种方式绑定麦克风服务 [`80688f33`](https://github.com/netcookies/isulewTools/commit/80688f33)
- 测试混响配置 [`906a20c4`](https://github.com/netcookies/isulewTools/commit/906a20c4)
- 完善麦克风测试逻辑 [`ae8f653f`](https://github.com/netcookies/isulewTools/commit/ae8f653f)
- 完善麦克风测试逻辑 [`73cb271a`](https://github.com/netcookies/isulewTools/commit/73cb271a)
- 完善麦克风测试逻辑 [`89e6df29`](https://github.com/netcookies/isulewTools/commit/89e6df29)

### Build
- 完善小组件发布流程 [`c8a38a74`](https://github.com/netcookies/isulewTools/commit/c8a38a74)
- 移除掉无用的调试步骤 [`314463bc`](https://github.com/netcookies/isulewTools/commit/314463bc)
- 移除没用的 stub，清理无法使用的功能（系统层级悬浮窗口） [`2aa8b42e`](https://github.com/netcookies/isulewTools/commit/2aa8b42e)
- 添加 agent 配置 [`551340c6`](https://github.com/netcookies/isulewTools/commit/551340c6)
- 排除掉无用的文件 [`05ea81ce`](https://github.com/netcookies/isulewTools/commit/05ea81ce)

### Style
- 小组件页面优化 [`560f1157`](https://github.com/netcookies/isulewTools/commit/560f1157)
- 对齐上边距 [`ca8133ce`](https://github.com/netcookies/isulewTools/commit/ca8133ce)

### Refactor
- 重构弹窗 UI [`7872d0bf`](https://github.com/netcookies/isulewTools/commit/7872d0bf)

### Other
- Merge branch 'main' of https://github.com/netcookies/isulewTools [`e3740bff`](https://github.com/netcookies/isulewTools/commit/e3740bff)
- Merge pull request #8 from netcookies/widget-remote [`863d3974`](https://github.com/netcookies/isulewTools/commit/863d3974)
- branch init [`e21c899c`](https://github.com/netcookies/isulewTools/commit/e21c899c)

## v1.7.9 (2025-10-19)

### Feature
- 添加悬浮窗数据源模式切换按钮 [`2195ad57`](https://github.com/netcookies/isulewTools/commit/2195ad57)
- 添加无障碍模式按钮 [`668ed659`](https://github.com/netcookies/isulewTools/commit/668ed659)
- 添加麦克风功能测试 - 实验性功能 [`0bf84e75`](https://github.com/netcookies/isulewTools/commit/0bf84e75)
- 添加雷石麦克风 sdk [`8c45690a`](https://github.com/netcookies/isulewTools/commit/8c45690a)

### Bug Fixes
- 无障碍跳转问题 [`ffe361f8`](https://github.com/netcookies/isulewTools/commit/ffe361f8)

### Performance Improvements
- 麦克风调试添加冻结系统应用，避免闪退 [`316c3c42`](https://github.com/netcookies/isulewTools/commit/316c3c42)
- 翻译车辆属性 [`74da707c`](https://github.com/netcookies/isulewTools/commit/74da707c)

### Test
- 尝试通过冻结，接管麦克风服务 [`b288ecbf`](https://github.com/netcookies/isulewTools/commit/b288ecbf)
- 调整实验性功能：监测安装包 [`868c9970`](https://github.com/netcookies/isulewTools/commit/868c9970)

### Refactor
- 梳理特权服务包结构 [`fba108f5`](https://github.com/netcookies/isulewTools/commit/fba108f5)
- 梳理特权服务包结构 [`bcb0e21d`](https://github.com/netcookies/isulewTools/commit/bcb0e21d)

## v1.7.8 (2025-10-17)

### Feature
- 修复app_process闪退问题 [`a845717d`](https://github.com/netcookies/isulewTools/commit/a845717d)
- 实现通知机制提升重启后特权服务的获取速度 [`7805f875`](https://github.com/netcookies/isulewTools/commit/7805f875)
- 实现 JNI 服务端 [`ea871d29`](https://github.com/netcookies/isulewTools/commit/ea871d29)

### Bug Fixes
- 修复无障碍的自动恢复 [`4b42bc11`](https://github.com/netcookies/isulewTools/commit/4b42bc11)

### Other
- fix：确保不要误杀进程 [`1e651c59`](https://github.com/netcookies/isulewTools/commit/1e651c59)

## v1.7.7 (2025-10-17)

### Feature
- 实现 ContainProvider 传递特权服务 [`d36baccf`](https://github.com/netcookies/isulewTools/commit/d36baccf)

### Bug Fixes
- 缓存启动脚本用于手动触发重连 [`5554ef0f`](https://github.com/netcookies/isulewTools/commit/5554ef0f)
- 重试失败后重置isStarting [`c23dffe7`](https://github.com/netcookies/isulewTools/commit/c23dffe7)
- 修复悬浮窗数据源切换导致的不断重启订阅问题 [`b68dd746`](https://github.com/netcookies/isulewTools/commit/b68dd746)
- 修复悬浮窗数据源切换错误 [`559ad7e6`](https://github.com/netcookies/isulewTools/commit/559ad7e6)

### Performance Improvements
- 现在无障碍和特权服务不再阻碍用户进入主页面 [`efe8f4f3`](https://github.com/netcookies/isulewTools/commit/efe8f4f3)

## v1.7.6 (2025-10-16)

### Bug Fixes
- 修复无障碍服务健康监测 [`a1b32183`](https://github.com/netcookies/isulewTools/commit/a1b32183)
- 修复 adb stream 锁死问题 [`a005a975`](https://github.com/netcookies/isulewTools/commit/a005a975)
- 修复过过早调用日志实例的问题 [`070f6068`](https://github.com/netcookies/isulewTools/commit/070f6068)

### Performance Improvements
- 完善 adb 重连机制 [`77a1544d`](https://github.com/netcookies/isulewTools/commit/77a1544d)
- 优化启动检查 [`16235f47`](https://github.com/netcookies/isulewTools/commit/16235f47)
- 优化启动流程 [`5e3173bd`](https://github.com/netcookies/isulewTools/commit/5e3173bd)

### Refactor
- 重构 adb 模块，放弃import，太坑了 [`44df5b14`](https://github.com/netcookies/isulewTools/commit/44df5b14)

## v1.7.5 (2025-10-14)

### Feature
- 增加高级设置页签，将重启、安装卸载美式、实验性功能迁移到这个页 [`bbea527f`](https://github.com/netcookies/isulewTools/commit/bbea527f)
- 增加折叠侧边栏按钮 [`29d336c1`](https://github.com/netcookies/isulewTools/commit/29d336c1)

## v1.7.4 (2025-10-14)

### Feature
- 彻底移除shizuku [`36b86ce5`](https://github.com/netcookies/isulewTools/commit/36b86ce5)
- vhal 服务适配特权服务 [`45d53b72`](https://github.com/netcookies/isulewTools/commit/45d53b72)
- 特权服务静默安装 [`c943254e`](https://github.com/netcookies/isulewTools/commit/c943254e)
- 特权服务静默安装 [`b22b59c0`](https://github.com/netcookies/isulewTools/commit/b22b59c0)
- 特权服务静默安装 [`51d56bc5`](https://github.com/netcookies/isulewTools/commit/51d56bc5)
- 特权服务静默安装 [`73642ae9`](https://github.com/netcookies/isulewTools/commit/73642ae9)
- 特权服务静默安装 [`afa01bff`](https://github.com/netcookies/isulewTools/commit/afa01bff)
- 添加IBinder、IInterface包装方法 [`7be908f4`](https://github.com/netcookies/isulewTools/commit/7be908f4)
- 无障碍自动保活 [`60de2961`](https://github.com/netcookies/isulewTools/commit/60de2961)
- 自动提权迁移到特权服务 [`8b1c3944`](https://github.com/netcookies/isulewTools/commit/8b1c3944)
- 开始迁移安装和授权服务 [`8b94751c`](https://github.com/netcookies/isulewTools/commit/8b94751c)
- 开始迁移 shizuku 服务 [`6ca1d161`](https://github.com/netcookies/isulewTools/commit/6ca1d161)
- 采用 DER 生成密钥 [`dda59711`](https://github.com/netcookies/isulewTools/commit/dda59711)
- 采用 DER 生成密钥 [`dcd2a6f7`](https://github.com/netcookies/isulewTools/commit/dcd2a6f7)

### Bug Fixes
- 悬浮窗数据源切换错误 [`7422d0b3`](https://github.com/netcookies/isulewTools/commit/7422d0b3)
- 初始化错误 [`eebd1884`](https://github.com/netcookies/isulewTools/commit/eebd1884)
- 编译警告 [`755dd383`](https://github.com/netcookies/isulewTools/commit/755dd383)
- 优化订阅数据日志到事件流 [`5f7cfd02`](https://github.com/netcookies/isulewTools/commit/5f7cfd02)
- remoteLogger 单例初始化错误的问题 [`f1e76c3d`](https://github.com/netcookies/isulewTools/commit/f1e76c3d)
- 修复 vhal 初始化问题 [`a1daee76`](https://github.com/netcookies/isulewTools/commit/a1daee76)
- 日志过高的问题 [`3705312d`](https://github.com/netcookies/isulewTools/commit/3705312d)
- 悬浮窗补齐参数 [`f48639ab`](https://github.com/netcookies/isulewTools/commit/f48639ab)
- 修复安装权限问题 [`a4c73e65`](https://github.com/netcookies/isulewTools/commit/a4c73e65)
- manifest.xml [`589eac32`](https://github.com/netcookies/isulewTools/commit/589eac32)
- 修复静默安装方法 [`fd6ba8bc`](https://github.com/netcookies/isulewTools/commit/fd6ba8bc)
- 修复美式下载链接 [`30b094cc`](https://github.com/netcookies/isulewTools/commit/30b094cc)
- 修复特权服务重连问题 [`2a9673a6`](https://github.com/netcookies/isulewTools/commit/2a9673a6)
- 修复特权服务问题 [`b384106f`](https://github.com/netcookies/isulewTools/commit/b384106f)
- 修复安装问题 [`6e4972ec`](https://github.com/netcookies/isulewTools/commit/6e4972ec)
- 修复特权服务协程问题 [`4c10f12a`](https://github.com/netcookies/isulewTools/commit/4c10f12a)
- 修复返回值判断错误 [`99997060`](https://github.com/netcookies/isulewTools/commit/99997060)
- 优化特权服务 [`fef34707`](https://github.com/netcookies/isulewTools/commit/fef34707)

### Performance Improvements
- 添加是否adb auth 过，防止美式挂了 [`97a04097`](https://github.com/netcookies/isulewTools/commit/97a04097)
- 增加sampleRate的输入 [`3de2cf80`](https://github.com/netcookies/isulewTools/commit/3de2cf80)
- 优化vhal管理器 [`9e5fcf41`](https://github.com/netcookies/isulewTools/commit/9e5fcf41)
- cleanup code [`d56a02d4`](https://github.com/netcookies/isulewTools/commit/d56a02d4)
- 完善AnyBinderProxy [`edd540ce`](https://github.com/netcookies/isulewTools/commit/edd540ce)
- 调整包结构 [`ab96c8f1`](https://github.com/netcookies/isulewTools/commit/ab96c8f1)
- 归集一部分常量 [`28fa7981`](https://github.com/netcookies/isulewTools/commit/28fa7981)
- 防止adb多次启动 [`367417c8`](https://github.com/netcookies/isulewTools/commit/367417c8)
- 防止多次启动 [`62761c8a`](https://github.com/netcookies/isulewTools/commit/62761c8a)
- 防止多次启动 [`233dd31d`](https://github.com/netcookies/isulewTools/commit/233dd31d)
- 清理无用的媒体功能 [`48b60740`](https://github.com/netcookies/isulewTools/commit/48b60740)

### Test
- 添加日志断点 [`22edb8c1`](https://github.com/netcookies/isulewTools/commit/22edb8c1)
- 测试安装服务 [`c637e9d7`](https://github.com/netcookies/isulewTools/commit/c637e9d7)
- 日志迁移完毕，开始测试 [`de40c523`](https://github.com/netcookies/isulewTools/commit/de40c523)
- 日志迁移完毕，开始测试 [`35226351`](https://github.com/netcookies/isulewTools/commit/35226351)

### Build
- 优化gradle [`302d3011`](https://github.com/netcookies/isulewTools/commit/302d3011)
- 添加Stub [`9e47142c`](https://github.com/netcookies/isulewTools/commit/9e47142c)
- 添加Stub [`a476c1bf`](https://github.com/netcookies/isulewTools/commit/a476c1bf)
- 添加Stub [`45efc2d9`](https://github.com/netcookies/isulewTools/commit/45efc2d9)
- 适配特权服务 [`a1f81410`](https://github.com/netcookies/isulewTools/commit/a1f81410)
- 适配特权服务 [`cb9720df`](https://github.com/netcookies/isulewTools/commit/cb9720df)
- 适配特权服务 [`dfa4a78e`](https://github.com/netcookies/isulewTools/commit/dfa4a78e)
- 适配特权服务 [`57a9ce29`](https://github.com/netcookies/isulewTools/commit/57a9ce29)
- 适配特权服务 [`0748d99d`](https://github.com/netcookies/isulewTools/commit/0748d99d)
- 适配特权服务 [`4bea3b98`](https://github.com/netcookies/isulewTools/commit/4bea3b98)
- 适配特权服务 [`5183e82e`](https://github.com/netcookies/isulewTools/commit/5183e82e)
- 适配特权服务 [`cee71561`](https://github.com/netcookies/isulewTools/commit/cee71561)
- 适配特权服务 [`5f0e6ed0`](https://github.com/netcookies/isulewTools/commit/5f0e6ed0)
- 开始适配美式提供的超级特权服务 [`ee5746d3`](https://github.com/netcookies/isulewTools/commit/ee5746d3)
- 开始适配美式提供的超级特权服务 [`bd622291`](https://github.com/netcookies/isulewTools/commit/bd622291)

### Other
- Merge pull request #7 from netcookies/super-privileged [`a4b26d22`](https://github.com/netcookies/isulewTools/commit/a4b26d22)
- pear: 完善无障碍服务的健康机制 [`54f36079`](https://github.com/netcookies/isulewTools/commit/54f36079)
- pear: 完善无障碍服务的健康机制 [`9c8ce000`](https://github.com/netcookies/isulewTools/commit/9c8ce000)
- pear: 解耦各项日志子服务 [`92f25687`](https://github.com/netcookies/isulewTools/commit/92f25687)
- pear: 解耦各项特权子服务 [`42c9ee9e`](https://github.com/netcookies/isulewTools/commit/42c9ee9e)
- 防止疯狂点击重连 [`d8d328a1`](https://github.com/netcookies/isulewTools/commit/d8d328a1)
- pref: 优化初始化速度 [`2c8fc67b`](https://github.com/netcookies/isulewTools/commit/2c8fc67b)

## v1.7.3 (2025-10-04)

### Test
- AI 添加了几个小组件供大家测试 [`a7e5fd08`](https://github.com/netcookies/isulewTools/commit/a7e5fd08)
- AI 添加了几个小组件供大家测试 [`ee6ef349`](https://github.com/netcookies/isulewTools/commit/ee6ef349)

## v1.7.2 (2025-10-04)

### Bug Fixes
- 修复 NPE 错误导致的程序闪退 [`575594b6`](https://github.com/netcookies/isulewTools/commit/575594b6)

## v1.7.1 (2025-10-03)

### Feature
- 方控支持关闭 [`0de02409`](https://github.com/netcookies/isulewTools/commit/0de02409)
- 添加个按钮示例 [`2300fe1b`](https://github.com/netcookies/isulewTools/commit/2300fe1b)
- 添加胎压监测小组件 @原想s1160 [`623501a9`](https://github.com/netcookies/isulewTools/commit/623501a9)
- 添加胎压监测小组件 @原想s1160 [`261f009f`](https://github.com/netcookies/isulewTools/commit/261f009f)
- 每个订阅独立协程，确保某个订阅出问题不会影响到其他协程 [`0dc744b1`](https://github.com/netcookies/isulewTools/commit/0dc744b1)
- 解耦小组件配置的调用 [`1fb578af`](https://github.com/netcookies/isulewTools/commit/1fb578af)
- 从widget抽象出flow函数 [`0bdc5693`](https://github.com/netcookies/isulewTools/commit/0bdc5693)

### Bug Fixes
- flow 类型错误 [`5013a3be`](https://github.com/netcookies/isulewTools/commit/5013a3be)
- 添加调试日志 [`bed7399d`](https://github.com/netcookies/isulewTools/commit/bed7399d)

### Performance Improvements
- 优化订阅服务 [`044d0172`](https://github.com/netcookies/isulewTools/commit/044d0172)

### Build
- add lint [`4c77b5cd`](https://github.com/netcookies/isulewTools/commit/4c77b5cd)

## v1.7.0 (2025-10-02)

### Feature
- 数据库升级时会清空小组件！ BREAKING CHANGE: 数据库升级时会清空小组件！ [`0e47893e`](https://github.com/netcookies/isulewTools/commit/0e47893e)
- 智驾改用统一的车辆属性订阅 [`15e40276`](https://github.com/netcookies/isulewTools/commit/15e40276)

### Bug Fixes
- 修复智驾悬浮窗数据引用问题 [`9434878c`](https://github.com/netcookies/isulewTools/commit/9434878c)
- 修复保存后再拖动设置还原的问题 [`543ad546`](https://github.com/netcookies/isulewTools/commit/543ad546)

### Test
- mock 智驾数据 [`c4863728`](https://github.com/netcookies/isulewTools/commit/c4863728)

## v1.6.9 (2025-10-02)

### Feature
- 记忆仪表盘和悬浮窗选项 [`7d440139`](https://github.com/netcookies/isulewTools/commit/7d440139)
- 被引用不再实时计算，改用持久化实现 [`f93c2596`](https://github.com/netcookies/isulewTools/commit/f93c2596)
- 移除智驾悬浮窗里的电池 [`40fea212`](https://github.com/netcookies/isulewTools/commit/40fea212)

### Bug Fixes
- 添加线程锁，修复重复显示悬浮窗的 bug [`9a549ef0`](https://github.com/netcookies/isulewTools/commit/9a549ef0)
- 主动推送流，而不是悬浮窗被动获取 [`20e1a356`](https://github.com/netcookies/isulewTools/commit/20e1a356)

### Performance Improvements
- 去重持久化。且当去重切换时互联小组件引用关系。 [`1a40a9e5`](https://github.com/netcookies/isulewTools/commit/1a40a9e5)
- LogService 等待 Shizuku 上线。 [`5e01287d`](https://github.com/netcookies/isulewTools/commit/5e01287d)
- Shizuku就绪时立即重连Vhal [`43689593`](https://github.com/netcookies/isulewTools/commit/43689593)

## v1.6.8 (2025-10-01)

### Feature
- 添加SliderSetting.kt [`169ab75e`](https://github.com/netcookies/isulewTools/commit/169ab75e)
- 完成小组件悬浮窗后端功能 [`a7b05b86`](https://github.com/netcookies/isulewTools/commit/a7b05b86)
- 悬浮窗服务合并 [`3c8bdcc4`](https://github.com/netcookies/isulewTools/commit/3c8bdcc4)
- 添加 Mock config [`4d8b160b`](https://github.com/netcookies/isulewTools/commit/4d8b160b)
- 添加 Mock config [`7dfbe607`](https://github.com/netcookies/isulewTools/commit/7dfbe607)
- 添加 Mock 数据供测试 [`ed7802a2`](https://github.com/netcookies/isulewTools/commit/ed7802a2)

### Bug Fixes
- 修复拖动协程错误 [`a974bfd2`](https://github.com/netcookies/isulewTools/commit/a974bfd2)
- 移除没用的方法 [`dd3131a5`](https://github.com/netcookies/isulewTools/commit/dd3131a5)
- 悬浮窗开关逻辑错误 [`986e1fa5`](https://github.com/netcookies/isulewTools/commit/986e1fa5)
- 修复放大后圆角不圆的问题 [`6002d898`](https://github.com/netcookies/isulewTools/commit/6002d898)
- 修复小组件编辑后，引用消失 [`22e2621d`](https://github.com/netcookies/isulewTools/commit/22e2621d)
- 修复去重功能 [`07e8b2e4`](https://github.com/netcookies/isulewTools/commit/07e8b2e4)

### Performance Improvements
- 完善小组件悬浮窗逻辑 [`80d9c000`](https://github.com/netcookies/isulewTools/commit/80d9c000)
- 悬浮窗开关持久化 [`95660992`](https://github.com/netcookies/isulewTools/commit/95660992)
- 组件小浮窗拖动位置持久化 [`6c66cc88`](https://github.com/netcookies/isulewTools/commit/6c66cc88)
- 添加小组件悬浮窗开关持久化 [`7f11bfc3`](https://github.com/netcookies/isulewTools/commit/7f11bfc3)
- 优化智驾悬浮窗页面的 UI [`630fad59`](https://github.com/netcookies/isulewTools/commit/630fad59)
- 缩放和透明度，显示两位小数 [`e94f1107`](https://github.com/netcookies/isulewTools/commit/e94f1107)
- 创建小组件时赋予默认值 [`b8a4a5ec`](https://github.com/netcookies/isulewTools/commit/b8a4a5ec)
- 优化电池配置 [`e8a97178`](https://github.com/netcookies/isulewTools/commit/e8a97178)
- 增加透明度和缩放两种类型 [`769390b4`](https://github.com/netcookies/isulewTools/commit/769390b4)
- 完善修改逻辑 [`7a9c59e5`](https://github.com/netcookies/isulewTools/commit/7a9c59e5)
- 实现拖动 [`35f09e28`](https://github.com/netcookies/isulewTools/commit/35f09e28)
- 适配小组件悬浮窗的开启和隐藏命令 [`5c4f2ad7`](https://github.com/netcookies/isulewTools/commit/5c4f2ad7)
- 补齐mock数据 [`908053af`](https://github.com/netcookies/isulewTools/commit/908053af)
- 适配原智驾悬浮窗服务 [`3a3d7c74`](https://github.com/netcookies/isulewTools/commit/3a3d7c74)
- 添加应用加载屏 [`6bb06b2e`](https://github.com/netcookies/isulewTools/commit/6bb06b2e)
- 确保进入车辆属性页时属性已加载 [`747aef99`](https://github.com/netcookies/isulewTools/commit/747aef99)
- 调整服务和viewmodel的初始化顺序 [`09a30e6a`](https://github.com/netcookies/isulewTools/commit/09a30e6a)
- 添加电池预览 [`49b2c147`](https://github.com/netcookies/isulewTools/commit/49b2c147)
- 完善小组件页面 [`a1ad6f5f`](https://github.com/netcookies/isulewTools/commit/a1ad6f5f)
- 完善小组件页面 [`8fad4853`](https://github.com/netcookies/isulewTools/commit/8fad4853)
- 完善电池小组件 [`aefb484c`](https://github.com/netcookies/isulewTools/commit/aefb484c)

### Build
- 包位置调整 [`67c0a288`](https://github.com/netcookies/isulewTools/commit/67c0a288)

### Other

## v1.6.6 (2025-09-29)

### Feature
- 由AppService统一持有数据库实例 feat: 被小组件引用的属性禁止取消订阅 [`fbda6efe`](https://github.com/netcookies/isulewTools/commit/fbda6efe)
- 添加数据引用功能 [`096f7105`](https://github.com/netcookies/isulewTools/commit/096f7105)

### Bug Fixes
- 修复被引用无法点击 [`ab403d8c`](https://github.com/netcookies/isulewTools/commit/ab403d8c)
- 修复去重：开功能的无限循环 [`a1598dff`](https://github.com/netcookies/isulewTools/commit/a1598dff)
- 恢复误删除的代码 [`0b63e126`](https://github.com/netcookies/isulewTools/commit/0b63e126)
- 修复数据库升级错误 [`399db279`](https://github.com/netcookies/isulewTools/commit/399db279)

### Performance Improvements
- 移除重连按钮，需要重连的话点右上角状态栏图标。 [`f530a2d8`](https://github.com/netcookies/isulewTools/commit/f530a2d8)
- 移除重连按钮，需要重连的话点右上角状态栏图标。 [`ff752a2d`](https://github.com/netcookies/isulewTools/commit/ff752a2d)
- 移除去重模式，现在去重已经没有意义 [`85b975b0`](https://github.com/netcookies/isulewTools/commit/85b975b0)
- 小组件支持必填项 [`bbecad27`](https://github.com/netcookies/isulewTools/commit/bbecad27)
- 按钮样式优化 [`3db8e74b`](https://github.com/netcookies/isulewTools/commit/3db8e74b)
- 按钮布局优化 [`6b1cd5f3`](https://github.com/netcookies/isulewTools/commit/6b1cd5f3)
- 车辆属性列表高度优化 [`b5d7bbff`](https://github.com/netcookies/isulewTools/commit/b5d7bbff)
- 将和无障碍无关的服务从无障碍中解耦 [`751dd38e`](https://github.com/netcookies/isulewTools/commit/751dd38e)

### Build
- 移除Jetifier [`6fe27a4f`](https://github.com/netcookies/isulewTools/commit/6fe27a4f)

### Other

## v1.6.4 (2025-09-28)

### Feature
- 添加安装和卸载美式的按钮 [`ad4b506f`](https://github.com/netcookies/isulewTools/commit/ad4b506f)
- 实现属性数据也分页懒加载 fix: 修复置顶、过滤、订阅等无法显示 [`cfc55995`](https://github.com/netcookies/isulewTools/commit/cfc55995)
- 添加以表盘 perf: 合并数据库 build: 添加文档 [`3a6468cf`](https://github.com/netcookies/isulewTools/commit/3a6468cf)
- 小组件功能初版 [`3e0ffdfc`](https://github.com/netcookies/isulewTools/commit/3e0ffdfc)

### Bug Fixes
- cfc55995 feat: 实现属性数据也分页懒加载 fix: 修复置顶、过滤、订阅等无法显示 [`cfc55995`](https://github.com/netcookies/isulewTools/commit/cfc55995)
- 修复属性列表缓存加载问题 [`3d9d663b`](https://github.com/netcookies/isulewTools/commit/3d9d663b)
- 修复数据源绑定问题 [`31d495a8`](https://github.com/netcookies/isulewTools/commit/31d495a8)
- 修复数据源绑定问题 [`32d5fd3d`](https://github.com/netcookies/isulewTools/commit/32d5fd3d)
- 修复数据源绑定问题 [`337179e9`](https://github.com/netcookies/isulewTools/commit/337179e9)
- 0dc37d1f perf: 支持颜色选择器 fix: 属性数据源保存问题 [`0dc37d1f`](https://github.com/netcookies/isulewTools/commit/0dc37d1f)
- 数据库升级错误 [`3c4719fb`](https://github.com/netcookies/isulewTools/commit/3c4719fb)

### Performance Improvements
- 每页显示 25 条数据，重连按钮图标改成文字 [`0c4f6472`](https://github.com/netcookies/isulewTools/commit/0c4f6472)
- 优化电池小组件 [`ca20e2d2`](https://github.com/netcookies/isulewTools/commit/ca20e2d2)
- 优化弹窗 [`011f9055`](https://github.com/netcookies/isulewTools/commit/011f9055)
- 3a6468cf feat: 添加以表盘 perf: 合并数据库 build: 添加文档 [`3a6468cf`](https://github.com/netcookies/isulewTools/commit/3a6468cf)
- 更新gitignore [`5f87a53a`](https://github.com/netcookies/isulewTools/commit/5f87a53a)
- 支持颜色选择器 fix: 属性数据源保存问题 [`0dc37d1f`](https://github.com/netcookies/isulewTools/commit/0dc37d1f)
- 统一按钮样式 [`9ebfe3af`](https://github.com/netcookies/isulewTools/commit/9ebfe3af)
- 统一按钮样式 [`5a6d993c`](https://github.com/netcookies/isulewTools/commit/5a6d993c)
- 清理无用的context [`e19f35ed`](https://github.com/netcookies/isulewTools/commit/e19f35ed)
- 完善数据绑定逻辑 [`7ef832fd`](https://github.com/netcookies/isulewTools/commit/7ef832fd)
- 调整数据源 key [`33f4452a`](https://github.com/netcookies/isulewTools/commit/33f4452a)
- 完成主要功能框架 [`c5f50af7`](https://github.com/netcookies/isulewTools/commit/c5f50af7)
- 添加LCC 暂停时，手动加速的状态 [`36b5da94`](https://github.com/netcookies/isulewTools/commit/36b5da94)
- 补齐PropertySubscriptionService的参数 [`c0589e22`](https://github.com/netcookies/isulewTools/commit/c0589e22)
- 由AppServices全局持有PropertySubscriptionService [`c38d2725`](https://github.com/netcookies/isulewTools/commit/c38d2725)

### Build
- update gitignore [`e1217911`](https://github.com/netcookies/isulewTools/commit/e1217911)
- 3a6468cf feat: 添加以表盘 perf: 合并数据库 build: 添加文档 [`3a6468cf`](https://github.com/netcookies/isulewTools/commit/3a6468cf)
- idea stuff [`28e72a05`](https://github.com/netcookies/isulewTools/commit/28e72a05)

### Other
- debug: 增加临时调试日志 [`361620ce`](https://github.com/netcookies/isulewTools/commit/361620ce)
- Merge branch 'main' of https://github.com/netcookies/isulewTools [`cd1692e8`](https://github.com/netcookies/isulewTools/commit/cd1692e8)
- Merge pull request #6 [`64ef41c1`](https://github.com/netcookies/isulewTools/commit/64ef41c1)

## v1.6.3 (2025-09-24)

### Feature
- 增加取消全部订阅按钮 fix: 修复高度问题 [`2801a0af`](https://github.com/netcookies/isulewTools/commit/2801a0af)

### Bug Fixes
- 修复viewModel初始化问题 [`3f8f8bf3`](https://github.com/netcookies/isulewTools/commit/3f8f8bf3)
- 修复写入的结果值总是true [`a8ebabb3`](https://github.com/netcookies/isulewTools/commit/a8ebabb3)
- 2801a0af feat: 增加取消全部订阅按钮 fix: 修复高度问题 [`2801a0af`](https://github.com/netcookies/isulewTools/commit/2801a0af)

### Performance Improvements
- 回显居中显示 [`f3376ea6`](https://github.com/netcookies/isulewTools/commit/f3376ea6)
- 统一按钮样式 [`fcbb2f0e`](https://github.com/netcookies/isulewTools/commit/fcbb2f0e)
- 统一按钮样式 [`9abc0efa`](https://github.com/netcookies/isulewTools/commit/9abc0efa)
- 优化回显效果 [`014da0a5`](https://github.com/netcookies/isulewTools/commit/014da0a5)

## v1.6.2 (2025-09-24)

### Feature
- 提供统一的订阅池管理。为后续功能做准备 [`fcf1c448`](https://github.com/netcookies/isulewTools/commit/fcf1c448)
- 订阅持久化 [`be922718`](https://github.com/netcookies/isulewTools/commit/be922718)

### Bug Fixes
- 已订阅的属性置顶显示 [`827e5060`](https://github.com/netcookies/isulewTools/commit/827e5060)
- 修复事件流颜色 [`f6263016`](https://github.com/netcookies/isulewTools/commit/f6263016)

### Performance Improvements
- 优化交互逻辑 [`7304bc0e`](https://github.com/netcookies/isulewTools/commit/7304bc0e)
- 禁止状态 [`dd8ec3b2`](https://github.com/netcookies/isulewTools/commit/dd8ec3b2)
- 写入时显示示例 [`66bcf2cb`](https://github.com/netcookies/isulewTools/commit/66bcf2cb)
- UI颜色优化 [`61108433`](https://github.com/netcookies/isulewTools/commit/61108433)
- 统一速度的颜色 [`f35e46cd`](https://github.com/netcookies/isulewTools/commit/f35e46cd)
- 添加跟车、暂停的颜色 [`d432be24`](https://github.com/netcookies/isulewTools/commit/d432be24)

### Test
- 增加测试模式 [`5b35c492`](https://github.com/netcookies/isulewTools/commit/5b35c492)

### Build
- code clean up [`1cf88c02`](https://github.com/netcookies/isulewTools/commit/1cf88c02)

### Refactor
- 重构车辆状态 UI [`f7e69a07`](https://github.com/netcookies/isulewTools/commit/f7e69a07)

## v1.6.1 (2025-09-22)

### Bug Fixes
- 修复悬浮窗显示，如果有发现触发紫色背景的请报告到群里@我 [`026d6298`](https://github.com/netcookies/isulewTools/commit/026d6298)
- 移除枚举值，改用常量 [`dcc941f0`](https://github.com/netcookies/isulewTools/commit/dcc941f0)
- 移除枚举值，改用常量 [`66f84459`](https://github.com/netcookies/isulewTools/commit/66f84459)

### Performance Improvements
- 事件窗口优化 [`1b3906ec`](https://github.com/netcookies/isulewTools/commit/1b3906ec)

### Test
- 媒体卡片绑定测试 [`2e2edc40`](https://github.com/netcookies/isulewTools/commit/2e2edc40)

## v1.6.0 (2025-09-22)

### Bug Fixes
- 修补中文名缺失问题 [`527a2813`](https://github.com/netcookies/isulewTools/commit/527a2813)
- 修补参数问题 [`6749ea8e`](https://github.com/netcookies/isulewTools/commit/6749ea8e)

### Performance Improvements
- 事件倒序与自动滚动 [`0139dfc5`](https://github.com/netcookies/isulewTools/commit/0139dfc5)
- vhal 默认自动重连 [`8ab26095`](https://github.com/netcookies/isulewTools/commit/8ab26095)
- 事件分类 [`7703b09e`](https://github.com/netcookies/isulewTools/commit/7703b09e)
- 添加中文显示 [`348ea11c`](https://github.com/netcookies/isulewTools/commit/348ea11c)
- 时间格式添加毫秒 [`5b02abae`](https://github.com/netcookies/isulewTools/commit/5b02abae)
- 优化 acc 和 lcc枚举值 [`391be1cb`](https://github.com/netcookies/isulewTools/commit/391be1cb)
- 去除赞赏二维码 [`e447e63f`](https://github.com/netcookies/isulewTools/commit/e447e63f)

### Test
- adas state for test [`df3f711d`](https://github.com/netcookies/isulewTools/commit/df3f711d)

### Build
- code cleanup [`cef6b900`](https://github.com/netcookies/isulewTools/commit/cef6b900)
- 移除多余的图片和字符 [`677c60cd`](https://github.com/netcookies/isulewTools/commit/677c60cd)

## v1.5.9 (2025-09-21)

### Feature
- 日志过滤功能 [`e9c52866`](https://github.com/netcookies/isulewTools/commit/e9c52866)

### Bug Fixes
- 补齐两种悬浮窗状态 [`cb183708`](https://github.com/netcookies/isulewTools/commit/cb183708)

### Performance Improvements
- 日志倒序并自动滚动 [`dfb9624d`](https://github.com/netcookies/isulewTools/commit/dfb9624d)

## v1.5.8 (2025-09-21)

### Feature
- 添加多媒体卡片功能（ui 还没完成） [`3bb94ba7`](https://github.com/netcookies/isulewTools/commit/3bb94ba7)

### Bug Fixes
- 修复Vhal 悬浮窗 [`0a53655d`](https://github.com/netcookies/isulewTools/commit/0a53655d)
- vhal binder 断开提示 [`66f7ffc4`](https://github.com/netcookies/isulewTools/commit/66f7ffc4)

### Performance Improvements
- 反馈改成按钮，增大日志显示范围 [`4e3136c8`](https://github.com/netcookies/isulewTools/commit/4e3136c8)
- 日志样式美化 [`38b4eae1`](https://github.com/netcookies/isulewTools/commit/38b4eae1)
- 优化日志性能 [`ab811054`](https://github.com/netcookies/isulewTools/commit/ab811054)
- 添加accCode = 3 提速和accCode = 7 不可用两种状态 [`5d7c4421`](https://github.com/netcookies/isulewTools/commit/5d7c4421)

### Test
- 添加媒体卡片测试 [`880414b8`](https://github.com/netcookies/isulewTools/commit/880414b8)
- 实验性功能加入测试按钮 [`51d907b6`](https://github.com/netcookies/isulewTools/commit/51d907b6)

### Build
- 升级lib [`c83920e6`](https://github.com/netcookies/isulewTools/commit/c83920e6)

## v1.5.7 (2025-09-19)

### Feature
- 电量颜色参考 ios 风格 [`5b24846b`](https://github.com/netcookies/isulewTools/commit/5b24846b)
- 重构添加电池显示 [`ad71fbeb`](https://github.com/netcookies/isulewTools/commit/ad71fbeb)
- 事件流加上名字 [`f7e0aca5`](https://github.com/netcookies/isulewTools/commit/f7e0aca5)
- 实现动态切换悬浮窗数据源，vhal 有权限时会自动切到 VHAL，否则切回日志 [`e963a5da`](https://github.com/netcookies/isulewTools/commit/e963a5da)
- 加入电量图标 [`fbede017`](https://github.com/netcookies/isulewTools/commit/fbede017)
- 添加悬浮窗数据源 [`dd42362e`](https://github.com/netcookies/isulewTools/commit/dd42362e)
- 将PilotData抽象成接口 [`aa6f438b`](https://github.com/netcookies/isulewTools/commit/aa6f438b)
- 添加行的状态 Chip，方便后期调试 [`99d7af6d`](https://github.com/netcookies/isulewTools/commit/99d7af6d)

### Performance Improvements
- 适配行的悬浮窗数据源 [`88eb002c`](https://github.com/netcookies/isulewTools/commit/88eb002c)

### Build
- Cleanup Code [`791cfc6a`](https://github.com/netcookies/isulewTools/commit/791cfc6a)

## v1.5.6 (2025-09-17)

### Feature
- 车辆属性订阅可以用啦！🎉 [`8fbc81c1`](https://github.com/netcookies/isulewTools/commit/8fbc81c1)
- 切换 tab 时保存界面上的车辆状态值 [`66d20ac8`](https://github.com/netcookies/isulewTools/commit/66d20ac8)
- property 属性采用dump car_service 方式获取。效率提升 1000 倍！ [`83835895`](https://github.com/netcookies/isulewTools/commit/83835895)

### Bug Fixes
- 增强版的读取和写入 field [`d5d357d8`](https://github.com/netcookies/isulewTools/commit/d5d357d8)
- 讲property包添加入白名单 [`0e4f9d84`](https://github.com/netcookies/isulewTools/commit/0e4f9d84)
- 尝试修复订阅 flags = 0错误 [`99059d27`](https://github.com/netcookies/isulewTools/commit/99059d27)
- 测试模式改为静态检测 [`0378b1bc`](https://github.com/netcookies/isulewTools/commit/0378b1bc)
- 修改悬浮窗默认值为关闭 [`1fe6c6b1`](https://github.com/netcookies/isulewTools/commit/1fe6c6b1)
- 悬浮窗兜底策略修复 [`a058ad1e`](https://github.com/netcookies/isulewTools/commit/a058ad1e)
- 修复 getPropConfigs 签名错误 [`bce37238`](https://github.com/netcookies/isulewTools/commit/bce37238)
- 修复 getPropConfigs 签名错误 [`a0c9b921`](https://github.com/netcookies/isulewTools/commit/a0c9b921)
- 加入 changemode 调试信息 [`c873bf80`](https://github.com/netcookies/isulewTools/commit/c873bf80)
- 悬浮窗加一个兜底，免得掉下来 [`fcf3700b`](https://github.com/netcookies/isulewTools/commit/fcf3700b)

### Test
- 单元测试通过 [`e51b810c`](https://github.com/netcookies/isulewTools/commit/e51b810c)
- 采用模版驱动的方式实现匹配 [`56e0a632`](https://github.com/netcookies/isulewTools/commit/56e0a632)
- 更新测试用例 [`7f501ee5`](https://github.com/netcookies/isulewTools/commit/7f501ee5)
- 更新测试用例 [`0979c707`](https://github.com/netcookies/isulewTools/commit/0979c707)
- 添加单元测试方法 [`82a5b71d`](https://github.com/netcookies/isulewTools/commit/82a5b71d)
- 添加单元测试方法 [`84dff5c7`](https://github.com/netcookies/isulewTools/commit/84dff5c7)

### Build
- 适配CarStatusTab [`ba37f745`](https://github.com/netcookies/isulewTools/commit/ba37f745)
- 适配CarStatusTab [`91214de8`](https://github.com/netcookies/isulewTools/commit/91214de8)
- 适配CarStatusViewModel [`6b3d1d36`](https://github.com/netcookies/isulewTools/commit/6b3d1d36)
- code cleanup [`124bc219`](https://github.com/netcookies/isulewTools/commit/124bc219)

## v1.5.5 (2025-09-15)

### Feature
- Shizuku每次更新授权一次 [`129675c3`](https://github.com/netcookies/isulewTools/commit/129675c3)
- 无障碍判断该用 kotlin 的状态驱动 [`b1047a11`](https://github.com/netcookies/isulewTools/commit/b1047a11)
- 安装器注册成独立的安装器，其他程序可以调用。 [`3e9c6277`](https://github.com/netcookies/isulewTools/commit/3e9c6277)
- 去除外部存储权限依赖。 [`c5891720`](https://github.com/netcookies/isulewTools/commit/c5891720)

### Bug Fixes
- remove unused fun [`7722e480`](https://github.com/netcookies/isulewTools/commit/7722e480)

### Build
- code cleanup [`ace3da29`](https://github.com/netcookies/isulewTools/commit/ace3da29)

## v1.5.4 (2025-09-14)

### Bug Fixes
- 修复亮屏启动（测试通过） [`255e3794`](https://github.com/netcookies/isulewTools/commit/255e3794)
- 修复亮屏启动 [`5a12b122`](https://github.com/netcookies/isulewTools/commit/5a12b122)
- 修复亮屏启动 [`8dd96b20`](https://github.com/netcookies/isulewTools/commit/8dd96b20)

## v1.5.3 (2025-09-13)

### Feature
- 重构类型转换逻辑 [`b1561d4e`](https://github.com/netcookies/isulewTools/commit/b1561d4e)
- 处理mixed type [`1ee989c3`](https://github.com/netcookies/isulewTools/commit/1ee989c3)

### Bug Fixes
- 修正 UI 显示错误 [`27796d58`](https://github.com/netcookies/isulewTools/commit/27796d58)
- 修复包名错误 [`b0b95841`](https://github.com/netcookies/isulewTools/commit/b0b95841)

### Build
- code cleanup [`7781bd2b`](https://github.com/netcookies/isulewTools/commit/7781bd2b)
- 合并mixed type code [`337dbf92`](https://github.com/netcookies/isulewTools/commit/337dbf92)

### Other
- fix：对齐property type todo: mix type [`c6721ad5`](https://github.com/netcookies/isulewTools/commit/c6721ad5)
- fix：修复订阅 flag问题 [`c31d9a7c`](https://github.com/netcookies/isulewTools/commit/c31d9a7c)

## v1.5.2 (2025-09-13)

### Bug Fixes
- 对齐 aidl [`dedc3764`](https://github.com/netcookies/isulewTools/commit/dedc3764)
- 添加unuse tag [`f04e9c57`](https://github.com/netcookies/isulewTools/commit/f04e9c57)
- 添加缺失的常量 [`ed9614c2`](https://github.com/netcookies/isulewTools/commit/ed9614c2)
- 事件流窗口的背景色问题 [`7240db8c`](https://github.com/netcookies/isulewTools/commit/7240db8c)
- 黑夜模式字体颜色问题 [`d9ebf83a`](https://github.com/netcookies/isulewTools/commit/d9ebf83a)

## v1.5.1 (2025-09-12)

### Bug Fixes
- 更新车辆属性卡片布局 [`c098bd9d`](https://github.com/netcookies/isulewTools/commit/c098bd9d)
- 修复因传入错误 id 导致的崩溃。只在vhal 连接状态变化时更新状态值。回退到单 dex 模式 [`854a5a7f`](https://github.com/netcookies/isulewTools/commit/854a5a7f)

## v1.5.0 (2025-09-11)

### Bug Fixes
- 修复bridge连接问题 [`6513e1c8`](https://github.com/netcookies/isulewTools/commit/6513e1c8)
- 修复安装权限检测 [`789dc458`](https://github.com/netcookies/isulewTools/commit/789dc458)

## v1.4.9 (2025-09-11)

### Bug Fixes
- 增加读取和写入的覆盖类型 [`5ae68407`](https://github.com/netcookies/isulewTools/commit/5ae68407)

## v1.4.8 (2025-09-11)

### Bug Fixes
- 完善车辆状态测试 UI [`92a53a35`](https://github.com/netcookies/isulewTools/commit/92a53a35)

## v1.4.7 (2025-09-11)

### Feature
- 日志写入文件，方便 shizuku 进程调试 [`afe4e535`](https://github.com/netcookies/isulewTools/commit/afe4e535)

### Bug Fixes
- 修复反射方法错误 [`35818c5a`](https://github.com/netcookies/isulewTools/commit/35818c5a)
- 修复方控问题 [`0ec8d6b0`](https://github.com/netcookies/isulewTools/commit/0ec8d6b0)

## v1.4.6 (2025-09-11)

### Bug Fixes
- 测试界面修复 [`55d36f16`](https://github.com/netcookies/isulewTools/commit/55d36f16)
- 小 bug 修复 [`2a2eda9f`](https://github.com/netcookies/isulewTools/commit/2a2eda9f)

## v1.4.5 (2025-09-10)

### Bug Fixes
- 更新窗口错误时无法关闭的问题 [`98a167ab`](https://github.com/netcookies/isulewTools/commit/98a167ab)

## v1.4.4 (2025-09-10)

### Feature
- 添加property测试 [`7c44a99d`](https://github.com/netcookies/isulewTools/commit/7c44a99d)
- 动态代理注册回调无效，改用dexmaker [`aa597387`](https://github.com/netcookies/isulewTools/commit/aa597387)

### Bug Fixes
- 完善vhal模块 [`9302ec03`](https://github.com/netcookies/isulewTools/commit/9302ec03)
- 适配Shizuku UserService [`d3a63f9a`](https://github.com/netcookies/isulewTools/commit/d3a63f9a)
- shizuku 没权限时跳过部分初始化 [`82987a76`](https://github.com/netcookies/isulewTools/commit/82987a76)
- 修复拿铁美式跳转错误 [`491cf4a4`](https://github.com/netcookies/isulewTools/commit/491cf4a4)
- 修复启动速度慢 [`3ce3e0b3`](https://github.com/netcookies/isulewTools/commit/3ce3e0b3)
- 默认隐藏语音图标 [`7bd15d2b`](https://github.com/netcookies/isulewTools/commit/7bd15d2b)
- 无障碍判断加上对应子服务是否启动 [`dea4f040`](https://github.com/netcookies/isulewTools/commit/dea4f040)
- User Service 日志修复 [`dfd425fb`](https://github.com/netcookies/isulewTools/commit/dfd425fb)

### Build
- add property modules [`d9c33af1`](https://github.com/netcookies/isulewTools/commit/d9c33af1)
- add property modules [`d61d53b8`](https://github.com/netcookies/isulewTools/commit/d61d53b8)
- 统一viewmodel管理 [`726f87eb`](https://github.com/netcookies/isulewTools/commit/726f87eb)
- 提供统一的无障碍服务编排 [`de448bd8`](https://github.com/netcookies/isulewTools/commit/de448bd8)
- 修复调用错误 [`99de7dfe`](https://github.com/netcookies/isulewTools/commit/99de7dfe)
- 修复变异错误 [`8ab771c9`](https://github.com/netcookies/isulewTools/commit/8ab771c9)
- 添加dexmaker [`d209fec2`](https://github.com/netcookies/isulewTools/commit/d209fec2)
- 加入proxy的debug信息 [`b8ec8644`](https://github.com/netcookies/isulewTools/commit/b8ec8644)
- change agp version to stable [`e09ac0a9`](https://github.com/netcookies/isulewTools/commit/e09ac0a9)

### Chore
- remove build/manifest; keep aidl/kt/res; README retained per review (#4) [`63632357`](https://github.com/netcookies/isulewTools/commit/63632357)

### Other
- Merge branch 'main' of https://github.com/netcookies/isulewTools [`a0962b40`](https://github.com/netcookies/isulewTools/commit/a0962b40)
- Merge pull request #4 from netcookies/copilot/add-vhal-reflection-bridge [`411f05c3`](https://github.com/netcookies/isulewTools/commit/411f05c3)
- Complete VHAL reflection bridge implementation [`6da1dc38`](https://github.com/netcookies/isulewTools/commit/6da1dc38)
- Add AIDL interfaces and core reflection components [`a3334873`](https://github.com/netcookies/isulewTools/commit/a3334873)
- Initial commit for VHAL bridge planning [`25b28096`](https://github.com/netcookies/isulewTools/commit/25b28096)
- Initial plan [`3a22fbe3`](https://github.com/netcookies/isulewTools/commit/3a22fbe3)
- Merge pull request #3 from netcookies/copilot/add-vhal-bridge-reflection-service [`ba21b3ae`](https://github.com/netcookies/isulewTools/commit/ba21b3ae)
- Initial plan [`98088f4c`](https://github.com/netcookies/isulewTools/commit/98088f4c)
- code: cleanup [`353f6f8a`](https://github.com/netcookies/isulewTools/commit/353f6f8a)
- test：分组绑定messagetyps [`32e3ff1d`](https://github.com/netcookies/isulewTools/commit/32e3ff1d)

## v1.4.3 (2025-09-06)

### Documentation
- 添加调试信息，可以不更新 [`15c613a8`](https://github.com/netcookies/isulewTools/commit/15c613a8)

## v1.4.2 (2025-09-06)

### Bug Fixes
- 紧急修复下载地址错误 [`5a874d9c`](https://github.com/netcookies/isulewTools/commit/5a874d9c)
- 紧急修复下载地址错误 [`ef2f58ae`](https://github.com/netcookies/isulewTools/commit/ef2f58ae)

## v1.4.1 (2025-09-06)

### Feature
- 添加CarLanBridge [`edc6225c`](https://github.com/netcookies/isulewTools/commit/edc6225c)

### Bug Fixes
- 实现shizuku user service [`e6d4b6e5`](https://github.com/netcookies/isulewTools/commit/e6d4b6e5)
- NPE [`a6c16a7b`](https://github.com/netcookies/isulewTools/commit/a6c16a7b)
- 完善CarLanBridge [`4ba4537b`](https://github.com/netcookies/isulewTools/commit/4ba4537b)

### Test
- 完善测试vehicle proxy逻辑 [`820d9e9b`](https://github.com/netcookies/isulewTools/commit/820d9e9b)

### Build
- 升级版本，添加parcelize [`36231483`](https://github.com/netcookies/isulewTools/commit/36231483)

### Style
- cleanup code [`dffffa50`](https://github.com/netcookies/isulewTools/commit/dffffa50)

## v1.4.0 (2025-09-04)

### Feature
- 新增车辆状态页（测试中） cleanup: 去除旧的无用的代码 [`08859129`](https://github.com/netcookies/isulewTools/commit/08859129)
- CarLanManager reversion [`7aa74f71`](https://github.com/netcookies/isulewTools/commit/7aa74f71)
- CommonProxy reversion [`0b8a42a7`](https://github.com/netcookies/isulewTools/commit/0b8a42a7)
- 添加车辆状态页 [`74a34d58`](https://github.com/netcookies/isulewTools/commit/74a34d58)
- 添加车机属性测试页 [`aaaf0624`](https://github.com/netcookies/isulewTools/commit/aaaf0624)
- 添加vehicleProxy [`0a636961`](https://github.com/netcookies/isulewTools/commit/0a636961)
- carlan代码集成（root可调用） [`63e4ddbc`](https://github.com/netcookies/isulewTools/commit/63e4ddbc)
- 添加CarLanService [`0f77f685`](https://github.com/netcookies/isulewTools/commit/0f77f685)

### Bug Fixes
- 优化悬浮窗开关逻辑 [`76779a45`](https://github.com/netcookies/isulewTools/commit/76779a45)
- CarLanManager typo [`3ab0edd5`](https://github.com/netcookies/isulewTools/commit/3ab0edd5)
- 无障碍服务就不用进到页面去再开关了。直接不让用 :) [`44cfc19e`](https://github.com/netcookies/isulewTools/commit/44cfc19e)
- hidl server反射无法获取的问题 [`fedc7647`](https://github.com/netcookies/isulewTools/commit/fedc7647)
- 减小更新窗口 [`7bef0148`](https://github.com/netcookies/isulewTools/commit/7bef0148)
- 修复调用多种无障碍管理器 [`39341d60`](https://github.com/netcookies/isulewTools/commit/39341d60)
- cleanup code [`30f8c37f`](https://github.com/netcookies/isulewTools/commit/30f8c37f)
- 移除掉无用import [`783981b8`](https://github.com/netcookies/isulewTools/commit/783981b8)
- 修复 flow 调用 [`62b26886`](https://github.com/netcookies/isulewTools/commit/62b26886)
- 采用反射的方式获取车辆属性 [`8d7a4721`](https://github.com/netcookies/isulewTools/commit/8d7a4721)
- 遇到不支持属性直接关闭订阅 [`bc8ce32b`](https://github.com/netcookies/isulewTools/commit/bc8ce32b)

### Documentation
- 规范命名 [`b41403be`](https://github.com/netcookies/isulewTools/commit/b41403be)
- 完善代码 [`fe98dd1d`](https://github.com/netcookies/isulewTools/commit/fe98dd1d)

### Build
- 移除HwServiceManager [`ad1d8b2f`](https://github.com/netcookies/isulewTools/commit/ad1d8b2f)
- cleanup code [`6b6dedc0`](https://github.com/netcookies/isulewTools/commit/6b6dedc0)
- 添加CarLanManager [`b2611cac`](https://github.com/netcookies/isulewTools/commit/b2611cac)
- 添加CommonProxy [`e2b9809a`](https://github.com/netcookies/isulewTools/commit/e2b9809a)
- 添加stub service [`b74a6bcc`](https://github.com/netcookies/isulewTools/commit/b74a6bcc)
- 添加stub [`d61b7d9e`](https://github.com/netcookies/isulewTools/commit/d61b7d9e)

### Other

## v1.3.9 (2025-08-31)

### Feature
- 支持多区域属性 [`354836c4`](https://github.com/netcookies/isulewTools/commit/354836c4)

### Bug Fixes
- 处理混合属性类型 [`422a247f`](https://github.com/netcookies/isulewTools/commit/422a247f)
- 跳过混合属性类型 [`e02e38cd`](https://github.com/netcookies/isulewTools/commit/e02e38cd)
- 增强混合属性类型 [`816f13e2`](https://github.com/netcookies/isulewTools/commit/816f13e2)
- 增强代码调试 [`37b00315`](https://github.com/netcookies/isulewTools/commit/37b00315)
- 显示Shizuku是否 root [`42f7096a`](https://github.com/netcookies/isulewTools/commit/42f7096a)
- 每次切回主画面都提权改为只调用一次 [`961e48d9`](https://github.com/netcookies/isulewTools/commit/961e48d9)

### Test
- 更新新的测试方法 [`b06aca9a`](https://github.com/netcookies/isulewTools/commit/b06aca9a)
- 添加CarPropertyConfig.kt的dump [`eef05300`](https://github.com/netcookies/isulewTools/commit/eef05300)

### Build
- Code Cleanup [`b79a345f`](https://github.com/netcookies/isulewTools/commit/b79a345f)

## v1.3.8 (2025-08-30)

### Feature
- 增加常用车辆属性的便捷方法 [`a093ff2a`](https://github.com/netcookies/isulewTools/commit/a093ff2a)
- 单独实现car service [`bfe542f8`](https://github.com/netcookies/isulewTools/commit/bfe542f8)
- 兼容跳转多种无障碍管理器，完善提示信息 [`0d47fac5`](https://github.com/netcookies/isulewTools/commit/0d47fac5)

### Bug Fixes
- area config [`ced211cb`](https://github.com/netcookies/isulewTools/commit/ced211cb)
- 修复序列化问题 [`208ff529`](https://github.com/netcookies/isulewTools/commit/208ff529)
- 日志页面的字体太小 [`7ce3933e`](https://github.com/netcookies/isulewTools/commit/7ce3933e)
- 尝试修复byte 数组转换问题 [`de1f6550`](https://github.com/netcookies/isulewTools/commit/de1f6550)
- 尝试修复byte 数组转换问题 [`65571a74`](https://github.com/netcookies/isulewTools/commit/65571a74)

### Test
- 添加调用读取常用车辆属性的实验性功能 [`213e0b02`](https://github.com/netcookies/isulewTools/commit/213e0b02)

## v1.3.7 (2025-08-28)

### Test
- 更新电量测试 [`50707108`](https://github.com/netcookies/isulewTools/commit/50707108)

## v1.3.6 (2025-08-28)

### Feature
- 新增车辆属性清单 [`3911632b`](https://github.com/netcookies/isulewTools/commit/3911632b)

### Bug Fixes
- 更新权限 [`59d97cae`](https://github.com/netcookies/isulewTools/commit/59d97cae)
- 更新内容设置最小宽度 [`edf4155c`](https://github.com/netcookies/isulewTools/commit/edf4155c)
- 悬浮窗太小时缩在一起的问题 [`8614a136`](https://github.com/netcookies/isulewTools/commit/8614a136)

### Test
- 电量测试 [`43e71eb0`](https://github.com/netcookies/isulewTools/commit/43e71eb0)

## v1.3.5 (2025-08-27)

### Feature
- 增加更新日志的按钮 [`cbd72f95`](https://github.com/netcookies/isulewTools/commit/cbd72f95)
- 跨版本升级返回多版本日志 [`d9a0736c`](https://github.com/netcookies/isulewTools/commit/d9a0736c)
- 苹果圆角 [`4a73a4a7`](https://github.com/netcookies/isulewTools/commit/4a73a4a7)
- 悬浮窗支持加载和保存样式 [`226967f1`](https://github.com/netcookies/isulewTools/commit/226967f1)

### Bug Fixes
- 移除无用的import [`1ac3f725`](https://github.com/netcookies/isulewTools/commit/1ac3f725)

## v1.3.4 (2025-08-26)

### Bug Fixes
- 修复自动更新问题 [`1f2f209e`](https://github.com/netcookies/isulewTools/commit/1f2f209e)

## v1.3.3 (2025-08-26)

### Bug Fixes
- 修复空指针问题 [`dfd98347`](https://github.com/netcookies/isulewTools/commit/dfd98347)

### Test
- 测试蓝牙功能(shizuku) [`51960bc5`](https://github.com/netcookies/isulewTools/commit/51960bc5)

## v1.3.2 (2025-08-26)

### Feature
- 亮屏后后台检测一次更新 [`d9a7ccce`](https://github.com/netcookies/isulewTools/commit/d9a7ccce)
- 日志类重构 [`2a5768ba`](https://github.com/netcookies/isulewTools/commit/2a5768ba)
- 清理代码。完善后端服务逻辑。 [`e5b3f8fe`](https://github.com/netcookies/isulewTools/commit/e5b3f8fe)
- 完善Shizuku 权限和校验机制。添加Shizuku 日志、蓝牙AIDL 服务 [`2b112d91`](https://github.com/netcookies/isulewTools/commit/2b112d91)
- 添加User Service [`56c5806a`](https://github.com/netcookies/isulewTools/commit/56c5806a)
- 增加shizuku反射调用设置的BtService和WlanService [`3501387e`](https://github.com/netcookies/isulewTools/commit/3501387e)

### Bug Fixes
- 时距有时会为 0 [`e84ec087`](https://github.com/netcookies/isulewTools/commit/e84ec087)
- 清理冗余的日志，移至debug [`094bdaa2`](https://github.com/netcookies/isulewTools/commit/094bdaa2)
- 清理日志工具类的调用 [`186797a6`](https://github.com/netcookies/isulewTools/commit/186797a6)
- 没有异常信息时会多输出一个 Null [`0822ff6a`](https://github.com/netcookies/isulewTools/commit/0822ff6a)
- remove redundant qualifier name [`8f08884c`](https://github.com/netcookies/isulewTools/commit/8f08884c)
- Shizuku User Service [`d1c3b306`](https://github.com/netcookies/isulewTools/commit/d1c3b306)
- Shizuku User Service [`5a34fd33`](https://github.com/netcookies/isulewTools/commit/5a34fd33)
- 安装完成显示toast [`bfed3a6e`](https://github.com/netcookies/isulewTools/commit/bfed3a6e)
- 优化使用kotlin的协程替换thread [`36ea8dff`](https://github.com/netcookies/isulewTools/commit/36ea8dff)

### Build
- 合并代码 [`04317ca3`](https://github.com/netcookies/isulewTools/commit/04317ca3)

## v1.3.1 (2025-08-25)

### Bug Fixes
- 修复日志参数问题 [`8811661b`](https://github.com/netcookies/isulewTools/commit/8811661b)

### Build
- 整理代码 [`3a7b2d54`](https://github.com/netcookies/isulewTools/commit/3a7b2d54)

## v1.3.0 (2025-08-24)

### Bug Fixes
- 优化悬浮窗数据的匹配效率 [`fa535948`](https://github.com/netcookies/isulewTools/commit/fa535948)
- update toast display issue [`030ed18f`](https://github.com/netcookies/isulewTools/commit/030ed18f)
- update toast display issue [`69ddf6b3`](https://github.com/netcookies/isulewTools/commit/69ddf6b3)
- update toast display issue [`49c3f334`](https://github.com/netcookies/isulewTools/commit/49c3f334)

### Other
- Bump [`e56432da`](https://github.com/netcookies/isulewTools/commit/e56432da)
- Bump Gradle Version [`c7758c61`](https://github.com/netcookies/isulewTools/commit/c7758c61)

## v1.2.9 (2025-08-22)

### Feature
- 添加获取电量测试 [`eaa2bb4a`](https://github.com/netcookies/isulewTools/commit/eaa2bb4a)
- 添加反射调用car service工具类 [`c6a2b6c5`](https://github.com/netcookies/isulewTools/commit/c6a2b6c5)

### Bug Fixes
- 速度不会按需显示 [`e345b9f1`](https://github.com/netcookies/isulewTools/commit/e345b9f1)

## v1.2.8 (2025-08-22)

### Feature
- 增加悬浮窗字体透明度调整 [`3474a4d9`](https://github.com/netcookies/isulewTools/commit/3474a4d9)
- 适配shizuku [`760af8f6`](https://github.com/netcookies/isulewTools/commit/760af8f6)
- 添加实验性功能 [`ee01655b`](https://github.com/netcookies/isulewTools/commit/ee01655b)

### Bug Fixes
- 修复滚动问题 [`d53d9867`](https://github.com/netcookies/isulewTools/commit/d53d9867)
- 完善适配日志 [`f2c432dd`](https://github.com/netcookies/isulewTools/commit/f2c432dd)
- 修复圆角 [`7d80e7b9`](https://github.com/netcookies/isulewTools/commit/7d80e7b9)

## v1.2.7 (2025-08-20)

### Feature
- 添加shizuku工具类和 root 工具类 [`191b5df8`](https://github.com/netcookies/isulewTools/commit/191b5df8)
- 添加root调用方法 [`6b392d77`](https://github.com/netcookies/isulewTools/commit/6b392d77)

### Bug Fixes
- 按需显示为30也时隐藏 [`9d987716`](https://github.com/netcookies/isulewTools/commit/9d987716)
- 按需显示为 0 时隐藏 [`4f9e57b4`](https://github.com/netcookies/isulewTools/commit/4f9e57b4)
- 去除无用的Emoji [`7612a0bb`](https://github.com/netcookies/isulewTools/commit/7612a0bb)

## v1.2.6 (2025-08-20)

### Bug Fixes
- 删除无用变量 [`4398aa8f`](https://github.com/netcookies/isulewTools/commit/4398aa8f)
- 弹出窗口过大，导致按钮无法显示 [`f6924def`](https://github.com/netcookies/isulewTools/commit/f6924def)

### Other
- Bump sdk version [`b84dceab`](https://github.com/netcookies/isulewTools/commit/b84dceab)
- Bump sdk version [`eebd81fe`](https://github.com/netcookies/isulewTools/commit/eebd81fe)
- Bump gradle version [`b5548718`](https://github.com/netcookies/isulewTools/commit/b5548718)
- Merge pull request #2 [`cbcce14c`](https://github.com/netcookies/isulewTools/commit/cbcce14c)

## v1.2.5 (2025-08-18)

### Feature
- 增加方形悬浮窗 [`c6b6c5a3`](https://github.com/netcookies/isulewTools/commit/c6b6c5a3)
- 适配黑夜与白天的颜色 [`792fec75`](https://github.com/netcookies/isulewTools/commit/792fec75)
- 字体大小微调 [`882515f8`](https://github.com/netcookies/isulewTools/commit/882515f8)
- 字符资源化 [`eec7f6ea`](https://github.com/netcookies/isulewTools/commit/eec7f6ea)
- ui全部重构完成 TODO: 电池图标 适配 adb root [`d439e7d5`](https://github.com/netcookies/isulewTools/commit/d439e7d5)
- 完成列表业务逻辑绑定 [`4eea4cdb`](https://github.com/netcookies/isulewTools/commit/4eea4cdb)
- 完成列表业务逻辑绑定 [`4b89158f`](https://github.com/netcookies/isulewTools/commit/4b89158f)
- 完成关于页面 [`16d5a1ee`](https://github.com/netcookies/isulewTools/commit/16d5a1ee)
- 重构 ui [`77011ec6`](https://github.com/netcookies/isulewTools/commit/77011ec6)
- 重构 ui [`487bc2d8`](https://github.com/netcookies/isulewTools/commit/487bc2d8)

### Bug Fixes
- 完善悬浮窗按需显示的逻辑 [`55efc3b9`](https://github.com/netcookies/isulewTools/commit/55efc3b9)
- 增强颜色对比度 [`d0a33f62`](https://github.com/netcookies/isulewTools/commit/d0a33f62)
- 修复亮屏启动顺序上下逻辑 [`03d76999`](https://github.com/netcookies/isulewTools/commit/03d76999)

### Documentation
- 更新文字说明 [`3b84cf89`](https://github.com/netcookies/isulewTools/commit/3b84cf89)

### Build
- 颜色整理 [`690a04ec`](https://github.com/netcookies/isulewTools/commit/690a04ec)
- Color.White替换 [`90989a11`](https://github.com/netcookies/isulewTools/commit/90989a11)
- theme的颜色替换(fix) [`0650116a`](https://github.com/netcookies/isulewTools/commit/0650116a)
- theme的颜色替换 [`f5a1c9f2`](https://github.com/netcookies/isulewTools/commit/f5a1c9f2)
- theme的字体引用完成 [`816d4c2a`](https://github.com/netcookies/isulewTools/commit/816d4c2a)
- 整理theme [`31b753ab`](https://github.com/netcookies/isulewTools/commit/31b753ab)
- config cache enabled [`b17e4672`](https://github.com/netcookies/isulewTools/commit/b17e4672)
- 修复悬浮窗权限检测 TODO: About 页面联系人 [`15283dcb`](https://github.com/netcookies/isulewTools/commit/15283dcb)
- 修复完所有页面逻辑。修复 monitorLabelMap -> monitorList [`bc5b23fb`](https://github.com/netcookies/isulewTools/commit/bc5b23fb)
- 引导页修复完成 [`7da2bd08`](https://github.com/netcookies/isulewTools/commit/7da2bd08)
- 代码整理 [`655eef55`](https://github.com/netcookies/isulewTools/commit/655eef55)

## v1.2.3 (2025-08-14)

### Feature
- 修复安装器的权限问题 [`eb0d0aca`](https://github.com/netcookies/isulewTools/commit/eb0d0aca)
- 优化log [`d7faad06`](https://github.com/netcookies/isulewTools/commit/d7faad06)
- 实现日志上传功能 [`cc98290e`](https://github.com/netcookies/isulewTools/commit/cc98290e)
- 实现日志上传功能 [`09ecd087`](https://github.com/netcookies/isulewTools/commit/09ecd087)

### Bug Fixes
- 完善日志逻辑 [`dd60bc19`](https://github.com/netcookies/isulewTools/commit/dd60bc19)
- 完善日志上传功能 [`eecdb4ec`](https://github.com/netcookies/isulewTools/commit/eecdb4ec)
- 回退安装方式的实现 [`1cd34439`](https://github.com/netcookies/isulewTools/commit/1cd34439)

### Test
- 尝试指定系统installer [`88433dc9`](https://github.com/netcookies/isulewTools/commit/88433dc9)
- 测试构造的安装器 [`d8705e76`](https://github.com/netcookies/isulewTools/commit/d8705e76)

### Build
- 删除无用的测试代码 [`03fa8c91`](https://github.com/netcookies/isulewTools/commit/03fa8c91)
- 删除无用的测试代码 [`49b4c1ea`](https://github.com/netcookies/isulewTools/commit/49b4c1ea)
- format code [`4af7c1b4`](https://github.com/netcookies/isulewTools/commit/4af7c1b4)

## v1.2.2 (2025-08-12)

### Feature
- 新增白名单fun，统一维护 [`f870a525`](https://github.com/netcookies/isulewTools/commit/f870a525)
- 压缩代码，减小包的体积 [`d4c7fa24`](https://github.com/netcookies/isulewTools/commit/d4c7fa24)

### Bug Fixes
- 修复部分 emoji 显示错误 [`2f6ec0d9`](https://github.com/netcookies/isulewTools/commit/2f6ec0d9)

### Build
- 优化编译文件 [`4af1d528`](https://github.com/netcookies/isulewTools/commit/4af1d528)
- 优化编译文件 [`45dd7877`](https://github.com/netcookies/isulewTools/commit/45dd7877)

### Style
- 对齐 switch [`de87d54c`](https://github.com/netcookies/isulewTools/commit/de87d54c)

## v1.2.1 (2025-08-11)

### Bug Fixes
- 下载地址走github加速 [`01a0713f`](https://github.com/netcookies/isulewTools/commit/01a0713f)
- 添加“请立即接管车辆”classname进悬浮窗白名单 [`a187958f`](https://github.com/netcookies/isulewTools/commit/a187958f)
- 添加“请立即接管车辆”classname进悬浮窗白名单 [`ed264ac7`](https://github.com/netcookies/isulewTools/commit/ed264ac7)

## v1.2.0 (2025-08-11)

### Feature
- 兼容安卓 11 [`5930f7f7`](https://github.com/netcookies/isulewTools/commit/5930f7f7)
- 单例初始化放在onServiceConnected里 [`c0c4becf`](https://github.com/netcookies/isulewTools/commit/c0c4becf)
- 新增跟车距离 [`68437d90`](https://github.com/netcookies/isulewTools/commit/68437d90)
- 新增跟车距离 [`a5d6e284`](https://github.com/netcookies/isulewTools/commit/a5d6e284)
- all emoji! [`fc215097`](https://github.com/netcookies/isulewTools/commit/fc215097)
- 新增下载和安装失败的提醒 [`7a47e09d`](https://github.com/netcookies/isulewTools/commit/7a47e09d)
- 添加个不透明的状态栏背景 [`b79617a2`](https://github.com/netcookies/isulewTools/commit/b79617a2)

### Bug Fixes
- 背景美化，颜控！ [`24130a34`](https://github.com/netcookies/isulewTools/commit/24130a34)
- 添加手势软件和哪吒美式到悬浮窗白名单 [`cfad0cc4`](https://github.com/netcookies/isulewTools/commit/cfad0cc4)
- 修复方控开关无效 [`24f52332`](https://github.com/netcookies/isulewTools/commit/24f52332)
- 完善检查更新的点击逻辑 [`48f9a3d1`](https://github.com/netcookies/isulewTools/commit/48f9a3d1)
- 界面优化 [`db00e4c2`](https://github.com/netcookies/isulewTools/commit/db00e4c2)

### Documentation
- :robot: changelog file generated [`76543d6f`](https://github.com/netcookies/isulewTools/commit/76543d6f)

### Test
- 检查更新功能测试通过 [`50bec596`](https://github.com/netcookies/isulewTools/commit/50bec596)

### Refactor
- 重构安装的 fun [`118ebe3a`](https://github.com/netcookies/isulewTools/commit/118ebe3a)

### Other
- todo: 快速上手 [`fbef7e67`](https://github.com/netcookies/isulewTools/commit/fbef7e67)

## v1.1.9 (2025-08-11)

### Continuous Integration
- update release.yml [`b98752e0`](https://github.com/netcookies/isulewTools/commit/b98752e0)
- Update release.yml [`020cbcf5`](https://github.com/netcookies/isulewTools/commit/020cbcf5)

## v1.1.8 (2025-08-11)

### Feature
- 检查更新的弹窗 [`bdc9c1aa`](https://github.com/netcookies/isulewTools/commit/bdc9c1aa)

### Continuous Integration
- 更改CHANGELOG的action [`6c1f4511`](https://github.com/netcookies/isulewTools/commit/6c1f4511)

### Documentation
- 所有新增字符资源化 [`1436a2b0`](https://github.com/netcookies/isulewTools/commit/1436a2b0)
- 更新README [`5df48039`](https://github.com/netcookies/isulewTools/commit/5df48039)

### Build
- 新增依赖库解析Markdown/Release Notes [`aaa43ea0`](https://github.com/netcookies/isulewTools/commit/aaa43ea0)

## v1.1.7 (2025-08-10)

### Continuous Integration
- 完善公开仓 CHANGELOG 生成 [`32535dde`](https://github.com/netcookies/isulewTools/commit/32535dde)

### Other

## v1.1.6 (2025-08-10)

### Bug Fixes
- 修复模拟器判断 [`4b8b93f7`](https://github.com/netcookies/isulewTools/commit/4b8b93f7)
- 添加超时处理 [`160487cf`](https://github.com/netcookies/isulewTools/commit/160487cf)

### Continuous Integration
- Update release.yml [`4da17c66`](https://github.com/netcookies/isulewTools/commit/4da17c66)
- Update release.yml [`acf7d371`](https://github.com/netcookies/isulewTools/commit/acf7d371)

### Style
- 优化界面 [`a978b323`](https://github.com/netcookies/isulewTools/commit/a978b323)
- 优化界面 [`780d13d2`](https://github.com/netcookies/isulewTools/commit/780d13d2)

### Other
- Merge remote-tracking branch 'origin/main' [`07037363`](https://github.com/netcookies/isulewTools/commit/07037363)

## v1.1.5 (2025-08-10)

### Bug Fixes
- c1365e25 Bump Version fix: 完善更新逻辑 [`c1365e25`](https://github.com/netcookies/isulewTools/commit/c1365e25)
- ec0b6d84 Bump Version fix: permission missing [`ec0b6d84`](https://github.com/netcookies/isulewTools/commit/ec0b6d84)

### Chore
- update release action [`47eb5e17`](https://github.com/netcookies/isulewTools/commit/47eb5e17)

### Other

## v1.1.4 (2025-08-10)

### Feature
- 9843f696 Bump Version feat: 增加检查更新逻辑 fix: 一些小的修复 [`9843f696`](https://github.com/netcookies/isulewTools/commit/9843f696)

### Bug Fixes
- 9843f696 Bump Version feat: 增加检查更新逻辑 fix: 一些小的修复 [`9843f696`](https://github.com/netcookies/isulewTools/commit/9843f696)

### Chore
- update release action [`5c2776aa`](https://github.com/netcookies/isulewTools/commit/5c2776aa)

### Other

## v1.1.3 (2025-08-10)

### Feature
- add public repo for release [`07ece146`](https://github.com/netcookies/isulewTools/commit/07ece146)
- 增加about页面 [`4100a4d7`](https://github.com/netcookies/isulewTools/commit/4100a4d7)
- 增加车控开关 fix: 完善权限弹窗说明 fix: 修复蓝牙控制编译警告 [`e359850b`](https://github.com/netcookies/isulewTools/commit/e359850b)
- fix release yml [`088eb863`](https://github.com/netcookies/isulewTools/commit/088eb863)

### Bug Fixes
- 移除没用的变量。 [`04ebf70f`](https://github.com/netcookies/isulewTools/commit/04ebf70f)
- e359850b feat: 增加车控开关 fix: 完善权限弹窗说明 fix: 修复蓝牙控制编译警告 [`e359850b`](https://github.com/netcookies/isulewTools/commit/e359850b)

### Documentation
- update changelog for v1.1.3 [skip ci] [`8cb33b09`](https://github.com/netcookies/isulewTools/commit/8cb33b09)
- update changelog for main [skip ci] [`84007665`](https://github.com/netcookies/isulewTools/commit/84007665)

### Other
- update yml [`3dffadef`](https://github.com/netcookies/isulewTools/commit/3dffadef)
- Del CHANGELOG [`3e2ca095`](https://github.com/netcookies/isulewTools/commit/3e2ca095)
- Changelog [`1eee60c3`](https://github.com/netcookies/isulewTools/commit/1eee60c3)
- Merged [`e4a12cfe`](https://github.com/netcookies/isulewTools/commit/e4a12cfe)

## v1.1.2 (2025-08-09)

### Feature
- 尝试注册方控接收器 [`0f672cdd`](https://github.com/netcookies/isulewTools/commit/0f672cdd)
- added key mapper [`b10075e7`](https://github.com/netcookies/isulewTools/commit/b10075e7)

### Bug Fixes
- 5c0e7064 Bump Version fix: remove warning [`5c0e7064`](https://github.com/netcookies/isulewTools/commit/5c0e7064)
- remove unused import。 [`62d0858a`](https://github.com/netcookies/isulewTools/commit/62d0858a)

### Documentation
- update changelog for main [skip ci] [`123b7356`](https://github.com/netcookies/isulewTools/commit/123b7356)

### Other
- Merge branch 'main' of https://github.com/netcookies/isulewTools [`f13d8072`](https://github.com/netcookies/isulewTools/commit/f13d8072)
- Update bump-version-tag.yml [`901f68a7`](https://github.com/netcookies/isulewTools/commit/901f68a7)

## v1.1.1 (2025-08-08)

### Feature
- 新增一个透明的activity用于后台启动 app fix: 蓝牙关闭的触发开关修复。 [`dfd0d596`](https://github.com/netcookies/isulewTools/commit/dfd0d596)
- 新增一个透明的activity用于后台启动 app fix: 蓝牙关闭的触发开关修复。 [`942461ad`](https://github.com/netcookies/isulewTools/commit/942461ad)
- 清除无用的import [`574bd118`](https://github.com/netcookies/isulewTools/commit/574bd118)
- 优化无障碍服务结构 [`4847e7fe`](https://github.com/netcookies/isulewTools/commit/4847e7fe)

### Bug Fixes
- e4a2382f Bump Version fix: 移除一些WARNING。 [`e4a2382f`](https://github.com/netcookies/isulewTools/commit/e4a2382f)
- LaunchProxyActivity.kt不够透明的 bug🐶。 [`40847393`](https://github.com/netcookies/isulewTools/commit/40847393)
- dfd0d596 feat: 新增一个透明的activity用于后台启动 app fix: 蓝牙关闭的触发开关修复。 [`dfd0d596`](https://github.com/netcookies/isulewTools/commit/dfd0d596)
- 942461ad feat: 新增一个透明的activity用于后台启动 app fix: 蓝牙关闭的触发开关修复。 [`942461ad`](https://github.com/netcookies/isulewTools/commit/942461ad)

### Documentation
- update changelog for main [skip ci] [`0c488ad7`](https://github.com/netcookies/isulewTools/commit/0c488ad7)

### Other
- Merge remote-tracking branch 'refs/remotes/origin/main' Bump Version [`8d748b3d`](https://github.com/netcookies/isulewTools/commit/8d748b3d)

## v1.1.0 (2025-08-07)

### Bug Fixes
- 75903da5 Bump Version fix: 隐藏桌上角图标调整点位置 [`75903da5`](https://github.com/netcookies/isulewTools/commit/75903da5)
- 431f4b94 Bump Version fix: 隐藏桌上角图标调整点位置 [`431f4b94`](https://github.com/netcookies/isulewTools/commit/431f4b94)

### Documentation
- update changelog for main [skip ci] [`671d9c11`](https://github.com/netcookies/isulewTools/commit/671d9c11)

### Other
- Merge remote-tracking branch 'origin/main' [`cefe6e79`](https://github.com/netcookies/isulewTools/commit/cefe6e79)

## v1.0.9 (2025-08-07)

### Feature
- ae0b51a7 Bump Version feat: 新增 ACC/LCC 未打开时隐藏（透明度为 0） fix: 隐藏桌上角图标调整点位置 [`ae0b51a7`](https://github.com/netcookies/isulewTools/commit/ae0b51a7)
- f9828c2e Bump Version feat: 新增 ACC/LCC 未打开时隐藏（透明度为 0） fix: 隐藏桌上角图标调整点位置 [`f9828c2e`](https://github.com/netcookies/isulewTools/commit/f9828c2e)

### Bug Fixes
- ae0b51a7 Bump Version feat: 新增 ACC/LCC 未打开时隐藏（透明度为 0） fix: 隐藏桌上角图标调整点位置 [`ae0b51a7`](https://github.com/netcookies/isulewTools/commit/ae0b51a7)
- f9828c2e Bump Version feat: 新增 ACC/LCC 未打开时隐藏（透明度为 0） fix: 隐藏桌上角图标调整点位置 [`f9828c2e`](https://github.com/netcookies/isulewTools/commit/f9828c2e)

### Other
- Update bump-version-tag.yml [`80c573c8`](https://github.com/netcookies/isulewTools/commit/80c573c8)

## v1.0.8 (2025-08-07)

### Feature
- 新增一种悬浮窗颜色逻辑。Acc 可开，Lcc 不可开 [`90dae4fc`](https://github.com/netcookies/isulewTools/commit/90dae4fc)

### Bug Fixes
- 修复悬浮窗无法关闭的 bug。优化性能。 [`87f3bc8a`](https://github.com/netcookies/isulewTools/commit/87f3bc8a)
- 隐藏悬浮窗权限问题 [`ee08cd52`](https://github.com/netcookies/isulewTools/commit/ee08cd52)

### Other
- Merged [`2fd05e93`](https://github.com/netcookies/isulewTools/commit/2fd05e93)
- Bump sdk to 36 [`896ec896`](https://github.com/netcookies/isulewTools/commit/896ec896)

## v1.0.7 (2025-08-07)

### Feature
- add an action for creating a tag when commit has text “Bump Version” [`e48350c3`](https://github.com/netcookies/isulewTools/commit/e48350c3)

### Bug Fixes
- ForegroundAppMonitorService.kt [`cf0609b8`](https://github.com/netcookies/isulewTools/commit/cf0609b8)
- Update CarInfoOverlay.kt [`927ed997`](https://github.com/netcookies/isulewTools/commit/927ed997)
- Update FloatCarInfoWindow.kt [`b17fbf9e`](https://github.com/netcookies/isulewTools/commit/b17fbf9e)
- Update CarInfoOverlay.kt [`9f900594`](https://github.com/netcookies/isulewTools/commit/9f900594)
- Update LogcatCarInfoMonitor.kt [`d3f86dbd`](https://github.com/netcookies/isulewTools/commit/d3f86dbd)
- Update LogcatCollector.kt [`5ffea55e`](https://github.com/netcookies/isulewTools/commit/5ffea55e)
- release.yml [`79a04954`](https://github.com/netcookies/isulewTools/commit/79a04954)
- Update release.yml [`1b349b7c`](https://github.com/netcookies/isulewTools/commit/1b349b7c)
- release.yml [`2cbaf8c0`](https://github.com/netcookies/isulewTools/commit/2cbaf8c0)

### Documentation
- update changelog for main [skip ci] [`e24dd02b`](https://github.com/netcookies/isulewTools/commit/e24dd02b)
- update changelog for main [skip ci] [`301afd21`](https://github.com/netcookies/isulewTools/commit/301afd21)
- update changelog for main [skip ci] [`846919a3`](https://github.com/netcookies/isulewTools/commit/846919a3)
- update changelog for main [skip ci] [`7d383590`](https://github.com/netcookies/isulewTools/commit/7d383590)
- update changelog for main [skip ci] [`79e7bca8`](https://github.com/netcookies/isulewTools/commit/79e7bca8)
- update changelog for main [skip ci] [`f1b57954`](https://github.com/netcookies/isulewTools/commit/f1b57954)
- update changelog for main [`738c2684`](https://github.com/netcookies/isulewTools/commit/738c2684)

### Other
- Update release.yml [`25c690a7`](https://github.com/netcookies/isulewTools/commit/25c690a7)
- Merge pull request #1 from netcookies/copilot/fix-0f139135-3750-46bd-9428-c4c3a497d786 [`0b65d7ed`](https://github.com/netcookies/isulewTools/commit/0b65d7ed)
- Initial plan [`268c9948`](https://github.com/netcookies/isulewTools/commit/268c9948)
- Fix: Update FloatCarInfoWindow.kt [`00cd5565`](https://github.com/netcookies/isulewTools/commit/00cd5565)
- Delete CHANGELOG.md [`db7110d5`](https://github.com/netcookies/isulewTools/commit/db7110d5)
- Update release.yml [`a8199c67`](https://github.com/netcookies/isulewTools/commit/a8199c67)
- Delete CHANGELOG.md [`8bb28e0a`](https://github.com/netcookies/isulewTools/commit/8bb28e0a)
- Update release.yml [`997d1a43`](https://github.com/netcookies/isulewTools/commit/997d1a43)
- Update release.yml [`db3556a4`](https://github.com/netcookies/isulewTools/commit/db3556a4)
- Update release.yml [`1413fbc2`](https://github.com/netcookies/isulewTools/commit/1413fbc2)
- Update release.yml [`9cc6fddd`](https://github.com/netcookies/isulewTools/commit/9cc6fddd)
- Update release.yml [`b693ea70`](https://github.com/netcookies/isulewTools/commit/b693ea70)
- Update release.yml [`aff85ab5`](https://github.com/netcookies/isulewTools/commit/aff85ab5)
- Update release.yml [`8d8f4a60`](https://github.com/netcookies/isulewTools/commit/8d8f4a60)
- Update release.yml [`a0fcde47`](https://github.com/netcookies/isulewTools/commit/a0fcde47)
- Update release.yml [`ec45a894`](https://github.com/netcookies/isulewTools/commit/ec45a894)
- Update release.yml [`21d7823b`](https://github.com/netcookies/isulewTools/commit/21d7823b)
- Update release.yml [`349d75ef`](https://github.com/netcookies/isulewTools/commit/349d75ef)
- Update README.md [`74adccde`](https://github.com/netcookies/isulewTools/commit/74adccde)
- Update release.yml [`dd872f1d`](https://github.com/netcookies/isulewTools/commit/dd872f1d)
- Update README.md [`126a1ef1`](https://github.com/netcookies/isulewTools/commit/126a1ef1)
- Update release.yml [`f3b7fc2e`](https://github.com/netcookies/isulewTools/commit/f3b7fc2e)
- Update release.yml [`810f89a0`](https://github.com/netcookies/isulewTools/commit/810f89a0)
- Update release.yml [`4bab6106`](https://github.com/netcookies/isulewTools/commit/4bab6106)
- Update release.yml [`6743a2f0`](https://github.com/netcookies/isulewTools/commit/6743a2f0)
- Update release.yml [`4fe606d4`](https://github.com/netcookies/isulewTools/commit/4fe606d4)

## v1.0.6 (2025-08-06)

### Feature
- c83052b5 docs: 增加README feat: 增加Release流程 [`c83052b5`](https://github.com/netcookies/isulewTools/commit/c83052b5)
- 添加保存拖动后的位置。 feat: 监控前台包名，若为弹窗页签的包名，滑动下桌上角隐藏语音小图标 [`f4cf58c4`](https://github.com/netcookies/isulewTools/commit/f4cf58c4)

### Documentation
- 增加MIT LICENSE [`127a2649`](https://github.com/netcookies/isulewTools/commit/127a2649)
- 增加README feat: 增加Release流程 [`c83052b5`](https://github.com/netcookies/isulewTools/commit/c83052b5)

### Other
- Merge remote-tracking branch 'refs/remotes/origin/main' [`9db78bc4`](https://github.com/netcookies/isulewTools/commit/9db78bc4)
- Create README file with project guide [`15fdc273`](https://github.com/netcookies/isulewTools/commit/15fdc273)
- Update release.yml to integrate CHANGELOG.md generation and Release Notes functionality [`fdbf555a`](https://github.com/netcookies/isulewTools/commit/fdbf555a)
- Add GitHub Actions workflow for APK release [`6ebdc257`](https://github.com/netcookies/isulewTools/commit/6ebdc257)
- Update CarInfoOverlay.kt [`86d4ecdd`](https://github.com/netcookies/isulewTools/commit/86d4ecdd)
- Update ForegroundAppMonitorService.kt [`99d56841`](https://github.com/netcookies/isulewTools/commit/99d56841)
- Update ForegroundAppMonitorService.kt [`e0087812`](https://github.com/netcookies/isulewTools/commit/e0087812)
- 添加人脸认证的包名到悬浮窗。 [`2565c8f1`](https://github.com/netcookies/isulewTools/commit/2565c8f1)
- 添加人脸认证的包名到悬浮窗。 [`83cae6b9`](https://github.com/netcookies/isulewTools/commit/83cae6b9)
- 添加人脸认证的包名到悬浮窗。 [`a974a99c`](https://github.com/netcookies/isulewTools/commit/a974a99c)
- 添加人脸认证的包名到悬浮窗。 [`aaa87272`](https://github.com/netcookies/isulewTools/commit/aaa87272)
- 添加转弯的包名到悬浮窗。 [`a604495a`](https://github.com/netcookies/isulewTools/commit/a604495a)
- 悬浮窗可用。发布第一版 [`03a8dd00`](https://github.com/netcookies/isulewTools/commit/03a8dd00)
- 修自启动问题（启动线程移至服务） [`d5a95458`](https://github.com/netcookies/isulewTools/commit/d5a95458)
- 修自启动问题（启动线程移至服务） [`f21b614b`](https://github.com/netcookies/isulewTools/commit/f21b614b)
- 修自启动问题（启动线程移至服务） [`b0a34b77`](https://github.com/netcookies/isulewTools/commit/b0a34b77)
- bump version. [`2c231a91`](https://github.com/netcookies/isulewTools/commit/2c231a91)
- bump version. [`4fa3dc74`](https://github.com/netcookies/isulewTools/commit/4fa3dc74)
- 添加自启动的日志 [`878d99e4`](https://github.com/netcookies/isulewTools/commit/878d99e4)
- 修复一堆单例的问题。美化日志 [`b838d0c6`](https://github.com/netcookies/isulewTools/commit/b838d0c6)
- 修复布局默认值问题。 修复蓝牙页签跳居中问题。 [`51c0a8e8`](https://github.com/netcookies/isulewTools/commit/51c0a8e8)
- 用composeview 实现悬浮窗 ui [`cb22e9ad`](https://github.com/netcookies/isulewTools/commit/cb22e9ad)
- 重构悬浮窗 ui [`4a108af6`](https://github.com/netcookies/isulewTools/commit/4a108af6)
- 修复小bug，资源化。 [`e5a10342`](https://github.com/netcookies/isulewTools/commit/e5a10342)
- 生成新图标。 [`6b34d8ee`](https://github.com/netcookies/isulewTools/commit/6b34d8ee)
- 尝试修复开机的 wifibug [`351eead4`](https://github.com/netcookies/isulewTools/commit/351eead4)
- 尝试修复开机的 wifibug [`6d89a046`](https://github.com/netcookies/isulewTools/commit/6d89a046)
- 尝试修复开机的 wifibug [`902efe6b`](https://github.com/netcookies/isulewTools/commit/902efe6b)
- 删除无用的文件 [`c8c1f543`](https://github.com/netcookies/isulewTools/commit/c8c1f543)
- 删除无用的文件 [`6c7bad6e`](https://github.com/netcookies/isulewTools/commit/6c7bad6e)
- 完善悬浮窗逻辑 [`5795e9d5`](https://github.com/netcookies/isulewTools/commit/5795e9d5)
- 实现悬浮窗功能 [`b8f03c89`](https://github.com/netcookies/isulewTools/commit/b8f03c89)
- 代码优化。 [`76da5aa3`](https://github.com/netcookies/isulewTools/commit/76da5aa3)
- 彻底修复日志问题。 [`c6724028`](https://github.com/netcookies/isulewTools/commit/c6724028)
- 继续优化代码。 [`9ea202d8`](https://github.com/netcookies/isulewTools/commit/9ea202d8)
- 优化代码。修复日志重复打印问题 [`2c3172c0`](https://github.com/netcookies/isulewTools/commit/2c3172c0)
- 日志监听修复bug [`825d09a8`](https://github.com/netcookies/isulewTools/commit/825d09a8)
- 尝试修复日志 bug [`a99cbe68`](https://github.com/netcookies/isulewTools/commit/a99cbe68)
- 修复添加按钮消失的问题 [`5f05f507`](https://github.com/netcookies/isulewTools/commit/5f05f507)
- 优化图标显示和下拉框 [`2c8b7fed`](https://github.com/netcookies/isulewTools/commit/2c8b7fed)
- 修复悬浮开关配置被override。 [`15f10e73`](https://github.com/netcookies/isulewTools/commit/15f10e73)
- 代码优化完成 [`5af4c2b8`](https://github.com/netcookies/isulewTools/commit/5af4c2b8)
- 重构MainActivity [`27902264`](https://github.com/netcookies/isulewTools/commit/27902264)
- 重构MainActivity [`b2ba2996`](https://github.com/netcookies/isulewTools/commit/b2ba2996)
- 重构。还差MainActivity [`60d813f8`](https://github.com/netcookies/isulewTools/commit/60d813f8)
- 升级版本 [`6a39491d`](https://github.com/netcookies/isulewTools/commit/6a39491d)
- 增加悬浮窗开关。 [`7f9a0f5b`](https://github.com/netcookies/isulewTools/commit/7f9a0f5b)
- 提升健壮性。 [`39eef6d9`](https://github.com/netcookies/isulewTools/commit/39eef6d9)
- 添加亮屏自启动功能。 [`0c0dd5e7`](https://github.com/netcookies/isulewTools/commit/0c0dd5e7)
- 移除launcher ready事件。无效 [`fccd41fe`](https://github.com/netcookies/isulewTools/commit/fccd41fe)
- Bug fixs.更改日志输出路径。 [`0a88f899`](https://github.com/netcookies/isulewTools/commit/0a88f899)
- version 0.1.5基本可用，等待弹窗车机测试 [`fccee3cb`](https://github.com/netcookies/isulewTools/commit/fccee3cb)
- version 0.1.5基本可用，等待弹窗车机测试 [`b6ccd76d`](https://github.com/netcookies/isulewTools/commit/b6ccd76d)
- Adust TabRow margin bottom [`481453d7`](https://github.com/netcookies/isulewTools/commit/481453d7)
- 测试跳过usb功能。完善日志 [`ac9e2be2`](https://github.com/netcookies/isulewTools/commit/ac9e2be2)
- Remove release directory from Git and ignore it [`0c86f958`](https://github.com/netcookies/isulewTools/commit/0c86f958)
- Add usb handler tab [`738cd8f3`](https://github.com/netcookies/isulewTools/commit/738cd8f3)
- add tab [`05f0d6d8`](https://github.com/netcookies/isulewTools/commit/05f0d6d8)
- Add tabrow [`33897069`](https://github.com/netcookies/isulewTools/commit/33897069)
- Update [`645ca07e`](https://github.com/netcookies/isulewTools/commit/645ca07e)
- Update [`de128bb2`](https://github.com/netcookies/isulewTools/commit/de128bb2)
- Update [`ef71ee5a`](https://github.com/netcookies/isulewTools/commit/ef71ee5a)
- Init [`f96ea859`](https://github.com/netcookies/isulewTools/commit/f96ea859)

