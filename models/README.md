# neta-connect llm-models

公共大模型资源仓库，只保留本地大模型目录包。

## 当前约定

- `models[].modelType == "LLM"` 使用 GGUF 目录包发布，供应用内置 `llama.cpp` 直接加载。
- 当前 LLM 清单收敛为 4 个端侧模型：
  - `Qwen3.5 0.8B · UD Q4_K_XL`
  - `Llama 3.2 1B Instruct · UD Q4_K_XL`
  - `Gemma 3 1B IT · UD Q4_K_XL`
  - `Qwen3 0.6B · UD Q5_K_XL`
- LLM 目录包至少包含 1 个 `.gguf` 文件；`runtimeEntryPath` 为空时，客户端会把整个版本目录当作模型目录处理。
- 每个 LLM 版本目录都应附带 `config.json`，用于下发模型默认采样参数。
- 当前 `config.json` 约定字段为：`version`、`top_k`、`max_tokens`、`temperature`、`top_p`。
- 如需保留历史模型条目，请将 `status` 标为 `HIDDEN`，避免客户端继续在模型商店中展示不可用资源。

## 发布建议

- LLM 模型优先使用上游官方或主流维护者提供的 GGUF 仓库。
- Hugging Face 资源统一优先使用 `hf-mirror.com` 下载链接。
- `index.json` 中的 `files[].sha256` 应填写上游文件内容哈希。
- 如需新增 LLM 模型，推荐使用单个主 `.gguf` 文件，并补充同目录 `config.json`，避免再引入 MLC 目录结构。
