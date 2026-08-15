# 搜索功能配置说明

本文档站点使用 Algolia DocSearch 提供搜索功能。

## 申请 Algolia DocSearch

1. 访问 [Algolia DocSearch 申请页面](https://docsearch.algolia.com/apply/)
2. 填写申请表单：
   - **网站 URL**: `https://neta.nznd.org`
   - **仓库 URL**: `https://github.com/netcookies/neta-connect`
   - **邮箱**: 你的联系邮箱
   - 确认你是网站所有者
   - 确认网站是公开可访问的
   - 确认网站是技术文档

3. 提交后等待 Algolia 团队审核（通常 1-2 周）

## 配置步骤

审核通过后，你会收到邮件，包含：
- `appId`: 应用 ID
- `apiKey`: 搜索 API Key
- `indexName`: 索引名称

在 `site/docusaurus.config.js` 中更新配置：

```javascript
algolia: {
  appId: 'YOUR_APP_ID',        // 替换为你的 App ID
  apiKey: 'YOUR_SEARCH_API_KEY', // 替换为你的 API Key
  indexName: 'neta-connect',    // 替换为你的索引名称
  contextualSearch: true,
  searchParameters: {},
  searchPagePath: 'search',
},
```

## 测试

配置完成后：
1. 本地运行 `npm run build && npm run serve`
2. 访问站点，导航栏右侧应出现搜索框
3. 输入关键词测试搜索功能

## 替代方案

如果不想使用 Algolia，可以使用本地搜索插件：

```bash
npm install --save @easyops-cn/docusaurus-search-local
```

然后在 `docusaurus.config.js` 中配置：

```javascript
themes: [
  [
    require.resolve("@easyops-cn/docusaurus-search-local"),
    {
      hashed: true,
      language: ["zh", "en"],
      highlightSearchTermsOnTargetPage: true,
    },
  ],
],
```

## 参考资料

- [Algolia DocSearch 文档](https://docsearch.algolia.com/)
- [Docusaurus 搜索文档](https://docusaurus.io/docs/search)
