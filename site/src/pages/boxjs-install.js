import React, { useState } from 'react';
import Layout from '@theme/Layout';
import styles from './boxjs-install.module.css';

const PROXY_TOOLS = [
  {
    id: 'surge',
    name: 'Surge',
    icon: '🌊',
    boxjsUrl: 'surge://install-module?url=https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.surge.sgmodule',
    netaUrl: 'surge://install-module?url=https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaVehicle.sgmodule',
    description: 'iOS/macOS 全能代理工具',
    manualBoxjs: 'https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.surge.sgmodule',
    manualNeta: 'https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaVehicle.sgmodule',
  },
  {
    id: 'quantumultx',
    name: 'Quantumult X',
    icon: '❌',
    boxjsUrl: 'quantumult-x://add-resource?remote-resource=%7B%22rewrite_remote%22%3A%5B%22https%3A%2F%2Fgh-proxy.com%2Fhttps%3A%2F%2Fraw.githubusercontent.com%2Fchavyleung%2Fscripts%2Fmaster%2Fbox%2Frewrite%2Fboxjs.rewrite.quanx.conf%22%5D%7D',
    netaUrl: 'quantumult-x://add-resource?remote-resource=%7B%22rewrite_remote%22%3A%5B%22https%3A%2F%2Fgh-proxy.com%2Fhttps%3A%2F%2Fraw.githubusercontent.com%2Fnetcookies%2Fneta-connect%2Fmain%2Fscripts%2FnetaVehicle.conf%22%5D%7D',
    description: 'iOS 强大的网络工具',
    manualBoxjs: 'https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.quanx.conf',
    manualNeta: 'https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaVehicle.conf',
  },
  {
    id: 'loon',
    name: 'Loon',
    icon: '🎈',
    boxjsUrl: 'loon://import?plugin=https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.loon.plugin',
    netaUrl: 'loon://import?plugin=https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaVehicle.plugin',
    description: 'iOS 网络调试工具',
    manualBoxjs: 'https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.loon.plugin',
    manualNeta: 'https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaVehicle.plugin',
  },
  {
    id: 'stash',
    name: 'Stash',
    icon: '📦',
    boxjsUrl: 'stash://install-override?url=https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.stash.stoverride',
    netaUrl: 'stash://install-override?url=https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaVehicle.stoverride',
    description: 'iOS/macOS 代理工具',
    manualBoxjs: 'https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.stash.stoverride',
    manualNeta: 'https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaVehicle.stoverride',
  },
  {
    id: 'shadowrocket',
    name: 'Shadowrocket',
    icon: '🚀',
    boxjsUrl: 'shadowrocket://install?module=https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.surge.sgmodule',
    netaUrl: 'shadowrocket://install?module=https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaVehicle.sgmodule',
    description: 'iOS 轻量级代理工具',
    manualBoxjs: 'https://gh-proxy.com/https://raw.githubusercontent.com/chavyleung/scripts/master/box/rewrite/boxjs.rewrite.surge.sgmodule',
    manualNeta: 'https://gh-proxy.com/https://raw.githubusercontent.com/netcookies/neta-connect/main/scripts/netaVehicle.sgmodule',
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
  const currentTool = PROXY_TOOLS.find(t => t.id === selectedTool);

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
