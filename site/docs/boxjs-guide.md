# BoxJS 自动抓取 Token 教程

本教程将指导你如何使用 BoxJS + 代理工具自动抓取哪吒汽车 Token 并同步到车机。

---

## 📖 什么是 BoxJS？

**BoxJS** 是一款运行在 Surge、Quantumult X、Loon、Shadowrocket、Stash 等代理工具中的脚本管理工具，提供可视化的配置界面。

### 主要功能

- 📦 管理和配置各种脚本
- 🎛️ 可视化配置参数
- 📊 查看脚本运行日志
- 🔄 自动同步数据

在哪吒互联项目中，BoxJS 用于**自动拦截哪吒汽车 App 的 API 请求，获取 Token 并自动同步到车机**，无需手动复制粘贴。

---

## 🚀 快速开始：一键安装

我们为你准备了**一键安装页面**，根据你使用的代理工具自动显示对应的安装步骤：

👉 [**点击前往一键安装页面**](/boxjs-install)

支持的代理工具：
- 🌊 **Surge** - iOS/macOS 全能代理工具
- ❌ **Quantumult X** - iOS 强大的网络工具
- 🎈 **Loon** - iOS 网络调试工具
- 📦 **Stash** - iOS/macOS 代理工具
- 🚀 **Shadowrocket** - iOS 轻量级代理工具

---

## 📚 详细教程

如果你想了解更详细的安装和配置过程，请继续阅读以下内容。

### 第一步：安装 BoxJS

根据你使用的代理工具选择对应的安装方法。

#### Surge 安装

**一键安装（推荐）：**
```
surge://install-module?url=https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.surge.sgmodule
```

**手动安装：**
1. 打开 Surge App
2. 进入 **首页 > 模块 > 安装新模块**
3. 粘贴模块地址并安装

---

#### Quantumult X 安装

**一键安装（推荐）：**
```
quantumult-x://add-resource?remote-resource=%7B%22rewrite_remote%22%3A%5B%22https%3A%2F%2Fraw.githubusercontent.com%2Fchavyleung%2Fscripts%2Fmaster%2Fbox%2Frewrite%2Fboxjs.rewrite.quanx.conf%22%5D%7D
```

**手动安装：**
1. 打开 Quantumult X App
2. 进入 **风车 > 重写 > 引用**
3. 点击右上角 "+" 添加引用
4. 粘贴地址：
   ```
   https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.quanx.conf
   ```

---

#### Loon 安装

**一键安装：**
```
loon://import?plugin=https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.loon.plugin
```

**手动安装：**
1. 打开 Loon App
2. 进入 **配置 > 插件**
3. 点击右上角 "+" 添加插件
4. 粘贴插件地址并保存

---

#### Stash 安装

**一键安装：**
```
stash://install-override?url=https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.stash.stoverride
```

**手动安装：**
1. 打开 Stash App
2. 进入 **覆写 > 安装覆写**
3. 粘贴覆写地址并安装

---

#### 访问 BoxJS

安装完成后，可以通过以下任意地址访问 BoxJS 管理界面：

- 🌐 [http://boxjs.com](http://boxjs.com) （推荐）
- 🌐 [http://boxjs.net](http://boxjs.net)

---

### 第二步：安装哪吒互联脚本

安装脚本后，代理工具会自动拦截哪吒汽车 App 的 API 请求并提取 Token。

#### Surge / Shadowrocket

**一键安装：**
```
surge://install-module?url=https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.sgmodule
```

**手动安装：**
1. 进入 **模块** 页面
2. 点击右上角 "+" 添加模块
3. 粘贴地址：
   ```
   https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.sgmodule
   ```

---

#### Quantumult X

**手动安装：**
1. 进入 **风车 > 重写 > 引用**
2. 点击右上角 "+" 添加引用
3. 粘贴地址：
   ```
   https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.conf
   ```

---

#### Loon

**手动安装：**
1. 进入 **配置 > 插件**
2. 点击右上角 "+" 添加插件
3. 粘贴地址：
   ```
   https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.plugin
   ```

---

#### Stash

**一键安装：**
```
stash://install-override?url=https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.stoverride
```

---

### 第三步：配置 MITM

**重要**：必须启用 MITM（中间人攻击）并信任证书，否则无法拦截 HTTPS 请求。

#### 安装和信任证书

1. 在代理工具中进入 **MITM** 设置
2. 开启 **MITM 开关**
3. 点击 **生成新的 CA 证书**（如果没有）
4. 点击 **安装证书**
5. 前往系统设置信任证书：

**iOS 设备：**
1. 设置 > 通用 > VPN与设备管理
2. 找到刚安装的证书并信任
3. 设置 > 通用 > 关于本机 > 证书信任设置
4. 开启对应证书的完全信任

**macOS 设备：**
1. 打开"钥匙串访问"应用
2. 找到代理工具的证书
3. 双击证书 > 信任 > 设置为"始终信任"

#### 添加主机名

确保 MITM 主机名列表中包含：
```
appapi-pki.chehezhi.cn:18443
```

---

### 第四步：导入哪吒互联订阅

通过 BoxJS 订阅，可以更方便地管理脚本配置。

#### 操作步骤

1. **打开 BoxJS 管理界面**
   - 在浏览器中访问 [http://boxjs.com](http://boxjs.com)

2. **进入订阅管理**
   - 点击底部导航栏的 **"订阅"** 图标

3. **添加订阅**
   - 点击右上角的 **"+"** 按钮
   - 粘贴订阅地址：
     ```
     https://raw.githubusercontent.com/netcookies/neta-connect/main/boxjs.json
     ```
   - 点击 **"保存"**

4. **确认订阅成功**
   - 回到 **"应用"** 页面
   - 应该能看到 **"哪吒汽车"** 应用

---

## 🎯 第五步：配对车机并抓取 Token

完成以上配置后，即可开始抓取 Token 并自动同步到车机。

### 1. 配对车机

首次使用需要先配对车机，让 BoxJS 知道将 Token 发送到哪里。

**车机端操作：**
1. 启动 **哪吒互联 App**（车机）
2. 进入 **"工作流"** 选项卡
3. 创建或编辑 **"任意哪吒签到工作流"**
4. 点击 **"token输入框边上的二维码图标"**

**手机端操作：**
1. **开启代理**
   - 确保代理工具（Surge/Quantumult X 等）已开启
   - 确保 MITM 功能已启用
2. 打开微信或其他二维码扫码 **"车机二维码"** 

**第一次扫码：**
- BoxJS 会自动保存车机 IP 地址

---

### 2. 抓取 Token

配对完成后，每次哪吒汽车 App 请求 API 时会自动抓取 Token。

#### 操作步骤

1. **开启代理**
   - 确保代理工具（Surge/Quantumult X 等）已开启
   - 确保 MITM 功能已启用

2. **打开哪吒汽车 App**
   - 打开官方哪吒汽车 App
   - 进入车辆详情页面
   - App 会自动请求车辆数据

3. **自动抓取和同步**
   - 脚本会自动拦截 API 请求
   - 提取 `Authorization` header 中的 Token
   - 与之前保存的 Token 比对
   - 如果 Token 发生变化：
     - 自动保存新 Token 到 BoxJS
     - 自动发送 Token 到车机（如果已配对）
     - 显示通知 **"Token 已更新并同步到车机"**

---

### 3. 查看日志

在 BoxJS 中查看脚本运行日志：

1. 打开 [http://boxjs.com](http://boxjs.com)
2. 进入 **"哪吒汽车"** 应用
3. 点击 **"日志"** 标签
4. 查看 Token 更新记录

---

## 🔍 工作原理

```
┌──────────────┐      ①请求API       ┌──────────────┐
│  哪吒汽车App  │ ──────────────────> │  哪吒服务器   │
└──────────────┘                     └──────────────┘
      ↓ ②拦截请求
┌──────────────┐
│  代理工具脚本  │
│ (netaConnect) │
└──────────────┘
      ↓ ③提取Token
┌──────────────┐
│    BoxJS     │ ← 保存Token
└──────────────┘
      ↓ ④自动同步
┌──────────────┐
│ 哪吒互联车机  │ ← 接收Token
└──────────────┘
```

### 工作流程

1. **拦截请求**: 代理工具拦截哪吒汽车 App 的 HTTPS 请求
2. **提取 Token**: 脚本从请求头的 `Authorization` 字段提取 Token
3. **保存到 BoxJS**: 将 Token 保存到 BoxJS 的持久化存储中
4. **同步到车机**: 通过 HTTP POST 请求发送 Token 到车机（端口 9339）
5. **车机应用**: 车机接收 Token 后自动更新，无需手动输入

---

## ⚙️ 配置说明

在 BoxJS 的 **"哪吒汽车"** 应用中，有两个配置项：

| 配置项 | 说明 | 获取方式 |
|--------|------|----------|
| **车机 IP 地址** | 车机在局域网的 IP 地址 | ✅ 自动（扫码配对） |
| **当前 Token** | 哪吒汽车 API 的访问令牌 | ✅ 自动（API 拦截） |

> **提示**: 这两个配置项都是自动获取，无需手动填写。

---

## 🆘 常见问题

### Q1: 脚本没有触发？

**可能原因：**
- ❌ MITM 未开启或证书未信任
- ❌ 代理工具未开启
- ❌ 脚本配置错误

**解决方法：**
1. 检查 MITM 是否开启
2. 检查证书是否已信任
3. 在代理工具的日志中查看是否有拦截记录
4. 重新安装模块或手动配置脚本

---

### Q2: Token 抓取成功但未同步到车机？

**可能原因：**
- ❌ 车机未配对或 IP 地址错误
- ❌ 车机未开启或不在同一局域网
- ❌ 车机防火墙阻止了端口 9339

**解决方法：**
1. 重新扫码配对车机
2. 确保手机和车机在同一 Wi-Fi 网络
3. 在车机上检查 "Token 同步设置" 是否开启
4. 查看 BoxJS 日志中的错误信息

---

### Q3: 如何手动查看当前 Token？

在 BoxJS 中查看：
1. 打开 [http://boxjs.com](http://boxjs.com)
2. 进入 **"哪吒汽车"** 应用
3. 查看 **"当前 Token"** 配置项

---

### Q4: Token 多久会过期？

哪吒汽车的 Token 有效期通常为 **30 天**。脚本会在 Token 更新时自动同步，无需担心过期问题。

---

### Q5: 能否在多个车机上使用？

可以。但每次只能配对一个车机。如果需要切换车机：
1. 在新车机上生成配对二维码
2. 在 BoxJS 中重新扫码配对
3. Token 会自动同步到新车机

---

### Q6: 为什么需要 MITM 证书？

MITM（中间人攻击）是代理工具拦截 HTTPS 请求的必要技术。通过安装和信任证书，代理工具可以：
- 解密 HTTPS 流量
- 读取请求头中的 Token
- 将 Token 传递给脚本处理

**安全说明**：证书仅在你的设备上使用，不会泄露给第三方。

---

## 📝 使用技巧

### 自动化程度最高的使用方式

1. **一次配置，长期使用**
   - 完成初次配置后，无需再次操作
   - Token 会在过期前自动更新

2. **无感知同步**
   - 只要打开哪吒汽车 App 查看车辆数据
   - Token 就会自动同步到车机

3. **多设备支持**
   - 同一个 BoxJS 账号可以在多个设备上使用
   - Token 会自动同步到所有设备

---

## 📚 相关链接

- **一键安装页面**: [点击前往](/boxjs-install)
- **BoxJS 官方文档**: [https://docs.boxjs.app](https://docs.boxjs.app)
- **BoxJS GitHub**: [https://github.com/chavyleung/scripts](https://github.com/chavyleung/scripts)
- **哪吒互联项目**: [https://github.com/netcookies/neta-connect](https://github.com/netcookies/neta-connect)
- **脚本源码**: [netaConnect.js](https://github.com/netcookies/neta-connect/blob/main/scripts/netaConnect.js)

---

## 🎯 总结

通过 BoxJS + 代理工具，你可以：

✅ **自动抓取** 哪吒汽车 App 的 Token
✅ **自动同步** Token 到车机，无需手动输入
✅ **持久化保存** Token，防止丢失
✅ **可视化管理** 配置和日志

享受便捷的车机互联体验吧！🚗
