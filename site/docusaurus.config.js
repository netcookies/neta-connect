// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const config = {
  title: '哪吒互联',
  tagline: '哪吒互联工具 - 适用于哪吒汽车的手车互联增强工具',
  favicon: 'img/logo.png',

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
          sidebarPath: require.resolve('./sidebars.js'),
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],
};

module.exports = config;
