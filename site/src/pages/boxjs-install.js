import React, { useState, useEffect } from 'react';
import Layout from '@theme/Layout';
import styles from './boxjs-install.module.css';

const PROXY_TOOLS = [
  {
    id: 'surge',
    name: 'Surge',
    icon: '🌊',
    boxjsUrl: 'surge://install-module?url=https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.surge.sgmodule',
    netaUrl: 'surge://install-module?url=https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.sgmodule',
    description: 'iOS/macOS 全能代理工具',
    manualBoxjs: 'https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.surge.sgmodule',
    manualNeta: 'https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.sgmodule',
  },
  {
    id: 'quantumultx',
    name: 'Quantumult X',
    icon: '❌',
    boxjsUrl: 'quantumult-x://add-resource?remote-resource=%7B%22rewrite_remote%22%3A%5B%22https%3A%2F%2Fgh-proxy.com%2Fhttps%3A%2F%2Fraw.githubusercontent.com%2Fchavyleung%2Fscripts%2Fmaster%2Fbox%2Frewrite%2Fboxjs.rewrite.quanx.conf%22%5D%7D',
    netaUrl: 'quantumult-x://add-resource?remote-resource=%7B%22rewrite_remote%22%3A%5B%22https%3A%2F%2Fgh-proxy.com%2Fhttps%3A%2F%2Fraw.githubusercontent.com%2Fnetcookies%2Fneta-connect%2Fmain%2Fscripts%2FnetaConnect.conf%22%5D%7D',
    description: 'iOS 强大的网络工具',
    manualBoxjs: 'https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.quanx.conf',
    manualNeta: 'https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.conf',
  },
  {
    id: 'loon',
    name: 'Loon',
    icon: '🎈',
    boxjsUrl: 'loon://import?plugin=https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.loon.plugin',
    netaUrl: 'loon://import?plugin=https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.plugin',
    description: 'iOS 网络调试工具',
    manualBoxjs: 'https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.loon.plugin',
    manualNeta: 'https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.plugin',
  },
  {
    id: 'stash',
    name: 'Stash',
    icon: '📦',
    boxjsUrl: 'stash://install-override?url=https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.stash.stoverride',
    netaUrl: 'stash://install-override?url=https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.stoverride',
    description: 'iOS/macOS 代理工具',
    manualBoxjs: 'https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.stash.stoverride',
    manualNeta: 'https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.stoverride',
  },
  {
    id: 'shadowrocket',
    name: 'Shadowrocket',
    icon: '🚀',
    boxjsUrl: 'shadowrocket://install?module=https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.surge.sgmodule',
    netaUrl: 'shadowrocket://install?module=https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.sgmodule',
    description: 'iOS 轻量级代理工具',
    manualBoxjs: 'https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.surge.sgmodule',
    manualNeta: 'https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaConnect.sgmodule',
  },
];

function CopyButton({ text, label = '复制链接' }) {
  const [copied, setCopied] = useState(false);

  const handleCopy = () => {
    navigator.clipboard.writeText(text);
    setCopied(true);
    setTimeout(() => setCopied(false), 2000);
  };

  return (
    <button className={styles.copyButton} onClick={handleCopy}>
      {copied ? '✅ 已复制' : `📋 ${label}`}
    </button>
  );
}

function InstallCard({ tool }) {
  return (
    <div className={styles.installCard}>
      <div className={styles.cardHeader}>
        <span className={styles.icon}>{tool.icon}</span>
        <div>
          <h3>{tool.name}</h3>
          <p className={styles.description}>{tool.description}</p>
        </div>
      </div>

      <div className={styles.installSection}>
        <h4>📦 步骤 1: 安装 BoxJS</h4>
        <div className={styles.buttonGroup}>
          <a href={tool.boxjsUrl} className={styles.installButton}>
            🚀 一键安装 BoxJS
          </a>
          <CopyButton text={tool.manualBoxjs} label="复制模块地址" />
        </div>
      </div>

      <div className={styles.installSection}>
        <h4>🚗 步骤 2: 安装哪吒互联脚本</h4>
        <div className={styles.buttonGroup}>
          <a href={tool.netaUrl} className={styles.installButton}>
            🚀 一键安装脚本
          </a>
          <CopyButton text={tool.manualNeta} label="复制脚本地址" />
        </div>
      </div>

      <div className={styles.installSection}>
        <h4>🔐 步骤 3: 启用 MITM</h4>
        <ol className={styles.steps}>
          <li>在 {tool.name} 中开启 <strong>MITM</strong> 功能</li>
          <li>安装并信任 CA 证书</li>
          <li>添加主机名: <code>appapi-pki.chehezhi.cn:18443</code></li>
        </ol>
      </div>

      <div className={styles.installSection}>
        <h4>✅ 步骤 4: 访问 BoxJS</h4>
        <div className={styles.buttonGroup}>
          <a href="http://boxjs.com" target="_blank" rel="noopener noreferrer" className={styles.visitButton}>
            🌐 打开 BoxJS
          </a>
        </div>
        <p className={styles.hint}>在 BoxJS 中导入订阅地址：</p>
        <div className={styles.codeBlock}>
          <code>https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/boxjs.json</code>
          <CopyButton text="https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/boxjs.json" label="复制" />
        </div>
      </div>
    </div>
  );
}

export default function BoxJSInstall() {
  const [selectedTool, setSelectedTool] = useState('surge');
  const [isAndroid, setIsAndroid] = useState(false);
  const currentTool = PROXY_TOOLS.find(t => t.id === selectedTool);

  useEffect(() => {
    // 检测是否为安卓设备
    const userAgent = navigator.userAgent || navigator.vendor || window.opera;
    const androidDetected = /android/i.test(userAgent);
    setIsAndroid(androidDetected);
  }, []);

  // 如果是安卓设备，显示安卓专用页面
  if (isAndroid) {
    return (
      <Layout
        title="安卓用户指南"
        description="安卓用户请下载修改版哪吒汽车 App">
        <div className={styles.container}>
          <header className={styles.header}>
            <h1>🤖 安卓用户指南</h1>
            <p>安卓用户请使用修改版哪吒汽车 App</p>
          </header>

          <div className={styles.installCard}>
            <div className={styles.installSection}>
              <h3>📱 安卓用户专属方案</h3>
              <p className={styles.description}>
                由于安卓系统的特殊性，我们为您准备了修改版的哪吒汽车 App。
              </p>
              <p className={styles.description}>
                <strong>修改版 App 内置了抓包工具</strong>，无需额外配置代理和证书，即可直接使用。
              </p>
            </div>

            <div className={styles.installSection}>
              <h4>📥 下载修改版 App</h4>
              <div className={styles.buttonGroup}>
                <a
                  href="https://www.123865.com/s/8yIYvd-DDrB?pwd=9339#"
                  target="_blank"
                  rel="noopener noreferrer"
                  className={styles.installButton}>
                  🚀 立即下载
                </a>
                <CopyButton text="https://www.123865.com/s/8yIYvd-DDrB?pwd=9339#" label="复制下载链接" />
              </div>
              <p className={styles.hint}>
                提取码：<code>9339</code>
              </p>
            </div>

            <div className={styles.installSection}>
              <h4>⚠️ 安装说明</h4>
              <ol className={styles.steps}>
                <li>下载修改版 APK 文件</li>
                <li>如已安装官方版，请先卸载</li>
                <li>允许安装来自未知来源的应用</li>
                <li>安装并打开修改版 App</li>
                <li>
                  <strong>⚠️ 开启悬浮窗权限（重要！）</strong>
                  <ul style={{marginTop: '8px', marginLeft: '20px', listStyleType: 'circle'}}>
                    <li>进入系统设置 → 应用管理 → 哪吒汽车</li>
                    <li>找到并开启「悬浮窗权限」或「显示在其他应用上层」</li>
                    <li>不同品牌手机设置路径可能略有差异（华为/小米/OPPO/vivo等）</li>
                    <li><strong>未开启此权限将无法使用抓包功能</strong></li>
                  </ul>
                </li>
                <li>按照 App 内提示完成配置</li>
              </ol>
            </div>
          </div>

          <div className={styles.helpSection}>
            <h3>📚 需要帮助？</h3>
            <div className={styles.helpLinks}>
              <a href="/docs/boxjs-guide">查看完整教程</a>
              <a href="/docs/faq">常见问题</a>
              <a href="https://github.com/netcookies/neta-connect/issues" target="_blank" rel="noopener noreferrer">
                提交问题
              </a>
            </div>
          </div>
        </div>
      </Layout>
    );
  }

  return (
    <Layout
      title="BoxJS 一键安装"
      description="选择你的代理工具，一键安装 BoxJS 和哪吒互联脚本">
      <div className={styles.container}>
        <header className={styles.header}>
          <h1>📦 BoxJS 一键安装</h1>
          <p>选择你使用的代理工具，按照步骤完成安装</p>
        </header>

        <div className={styles.toolSelector}>
          {PROXY_TOOLS.map(tool => (
            <button
              key={tool.id}
              className={`${styles.toolButton} ${selectedTool === tool.id ? styles.active : ''}`}
              onClick={() => setSelectedTool(tool.id)}>
              <span className={styles.toolIcon}>{tool.icon}</span>
              <span>{tool.name}</span>
            </button>
          ))}
        </div>

        <InstallCard tool={currentTool} />

        <div className={styles.helpSection}>
          <h3>📚 需要帮助？</h3>
          <div className={styles.helpLinks}>
            <a href="/docs/boxjs-guide">查看完整教程</a>
            <a href="/docs/faq">常见问题</a>
            <a href="https://github.com/netcookies/neta-connect/issues" target="_blank" rel="noopener noreferrer">
              提交问题
            </a>
          </div>
        </div>
      </div>
    </Layout>
  );
}
