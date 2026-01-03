// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const config = {
  title: '哪吒互联',
  tagline: '哪吒互联工具 - 适用于哪吒汽车的手车互联增强工具',
  favicon: 'img/logo.webp',

  url: 'https://neta.nznd.org', // 你的 GitHub Pages 域名
  baseUrl: '/',           // 仓库名

  organizationName: 'netcookies', // GitHub 用户名
  projectName: 'neta-connect',    // 仓库名
  deploymentBranch: 'gh-pages',

  i18n: {
    defaultLocale: 'zh',
    locales: ['zh', 'en'],
  },

  presets: [
    [
      'classic',
      ({
        docs: {
          sidebarPath: false, // 禁用左侧sidebar，使用topnav导航
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],

  themeConfig: {
    // 搜索配置 - Algolia DocSearch
    algolia: {
      appId: '11K4P1TV3H',
      apiKey: '6cfcbf536592b7756dad022b0b73846a',
      indexName: 'Neta-connect docs',
      contextualSearch: true,
      searchParameters: {},
      searchPagePath: 'search',
      // askAi: 'YOUR_ALGOLIA_ASSISTANT_ID', // 可选：AI 助手功能
    },
    navbar: {
      title: '哪吒互联',
      logo: {
        alt: '哪吒互联 Logo',
        src: 'img/logo.webp',
      },
      items: [
        {
          to: '/docs/intro',
          label: '介绍',
          position: 'left',
        },
        {
          to: '/docs/features',
          label: '功能特性',
          position: 'left',
        },
        {
          to: '/docs/install',
          label: '安装指南',
          position: 'left',
        },
        {
          to: '/docs/usage',
          label: '使用教程',
          position: 'left',
        },
        {
          to: '/boxjs-install',
          label: 'BoxJS 安装',
          position: 'left',
          className: 'header-boxjs-link',
        },
        {
          to: '/docs/widget-store',
          label: '小组件商店',
          position: 'left',
        },
        {
          to: '/docs/plugin-development',
          label: '插件开发',
          position: 'left',
        },
        {
          to: '/docs/faq',
          label: 'FAQ',
          position: 'left',
        },
        {
          to: '/docs/changelog',
          label: '更新日志',
          position: 'left',
        },
        {
          href: 'https://github.com/netcookies/neta-connect/releases',
          label: '{{VERSION}}',
          position: 'right',
          className: 'header-github-release',
        },
        {
          href: 'https://github.com/netcookies/neta-connect',
          position: 'right',
          className: 'header-github-link',
          'aria-label': 'GitHub repository',
        },
      ],
    },
    docs: {
      sidebar: {
        hideable: false,
        autoCollapseCategories: false,
      },
    },
  },
};

module.exports = config;
