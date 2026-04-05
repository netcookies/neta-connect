# neta-connect models

公共语音模型资源仓库。

## 当前约定

- `models[].modelType == "LLM"` 使用 GGUF 目录包发布，供应用内置 `llama.cpp` 直接加载。
- LLM 目录包至少包含 1 个 `.gguf` 文件；`runtimeEntryPath` 为空时，客户端会把整个版本目录当作模型目录处理。
- `runtimes.runners` 继续用于 Sherpa TTS / ASR 运行时发布。
- `runtimes.mlc4js` 已保留为空列表，仅用于兼容旧 schema；新版本不再发布 MLC4J 运行时。

## 发布建议

- LLM 模型优先使用上游官方 GGUF 仓库。
- `index.json` 中的 `files[].sha256` 应填写上游文件内容哈希。
- 如需新增 LLM 模型，推荐使用单个主 `.gguf` 文件，避免再引入 MLC 目录结构。
