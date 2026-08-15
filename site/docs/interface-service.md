# 接口服务接入指南

哪吒互联将本机/局域网能力以「接口服务」形式开放，供第三方应用接入：

| 服务 | 传输层 | 鉴权 | 使用方 |
|------|--------|------|--------|
| 元数据 API（歌词/封面） | 本机 AF_UNIX Socket | 按应用 uid，无需令牌 | 车机上的第三方应用 |
| 语音网关（CBridge） | 局域网 HTTP / WebSocket | 访问令牌 | 手机 / 外部语音运行时 |
| 远程输入 | 局域网 HTTP / WebSocket | 访问令牌 | 手机网页端 |

访问令牌在应用内获取：**设置 → 其他 → 接口服务**（可复制 / 重新生成）。

---

## 一、访问令牌

### 传递方式（三选一）

```
Authorization: Bearer <令牌>
X-Api-Token: <令牌>
?token=<令牌>            # URL 查询参数
```

> 令牌被「重新生成」后立即失效，请同步更新接入方配置。

---

## 二、元数据 API（AF_UNIX Socket）

面向**车机本机**的第三方应用（同为 Android 应用），通过 AF_UNIX 抽象命名 socket
访问，无需令牌——服务端通过 `LocalSocket.getPeerCredentials()` 读取对端真实 uid
做身份识别与限流。

- **抽象 socket 名**：`com.neta.isulewtools.localserver.metadata`
- **协议**：HTTP/1.1（`GET` 请求，`Connection: close` 响应）

### 端点

| 端点 | 说明 |
|------|------|
| `GET /v1/health` | 健康检查 |
| `GET /v1/lyric?title=歌名&artist=歌手` | 歌词（LRC 结构化 JSON） |
| `GET /v1/cover?title=歌名&artist=歌手` | 封面 URL |

### 响应示例

```json
{"status":"ok","protocolVersion":1}

{"requestId":3,"title":"归途有风","artist":"海来阿木","lines":[
  {"timeMs":0,"text":"归途有风"},
  {"timeMs":1200,"text":"词：学松"}
]}
```

### Android 客户端示例（Kotlin）

```kotlin
val socket = LocalSocket()
socket.connect(LocalSocketAddress(
    "com.neta.isulewtools.localserver.metadata",
    LocalSocketAddress.Namespace.ABSTRACT
))
socket.outputStream.use {
    it.write("GET /v1/lyric?title=%E5%BD%92%E9%80%94%E6%9C%89%E9%A3%8E HTTP/1.1\r\n\r\n".toByteArray())
    it.flush()
}
val body = socket.inputStream.readBytes().decodeToString()
socket.close()
```

> 服务端每次处理完请求即关闭连接，客户端读到 EOF 即为响应结束。
> 歌词/封面查询需要联网，未命中时会回退多个歌词源。

---

## 三、语音网关（CBridge，局域网）

面向**手机 / 外部语音运行时**，通过局域网 HTTP / WebSocket 访问，需携带访问令牌。

- **地址来源**：系统属性 `ro_cbridge_addr`（本机语音运行时使用，形如
  `ws://127.0.0.1:<端口>?token=<令牌>`；手机端将 IP 换成设备局域网地址即可）
- **鉴权**：三通道任一

```bash
# WebSocket（推荐）：token 直接附在 URL
ws://<设备IP>:<端口>?token=<令牌>

# HTTP（健康检查/握手）
curl -H 'Authorization: Bearer <令牌>' 'http://<设备IP>:<端口>/'
curl 'http://<设备IP>:<端口>/?token=<令牌>'
```

---

## 四、远程输入（局域网）

面向手机网页端。通过应用内「远程输入」生成的二维码或页面访问，二维码内容已附带
`?token=`，正常扫码即可使用，无需手动填写令牌。

---

## 五、安全说明

- 元数据 API 按 **uid** 鉴权：仅同设备应用可访问，局域网不可达。
- LAN 端点（语音网关/远程输入）暴露于局域网，**必须携带令牌**；请勿使用默认/弱令牌。
- 令牌重新生成后旧令牌立即失效。
- AF_UNIX 抽象 socket 跨应用访问依赖 SELinux 策略：permissive 环境下可用；
  若车机启用 enforcing，请确认 `untrusted_app` 域对抽象 socket 的放行规则后再接入。
