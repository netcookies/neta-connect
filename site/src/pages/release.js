import React from 'react';
import Link from '@docusaurus/Link';
import Layout from '@theme/Layout';
import styles from './release.module.css';
import releaseData from '../data/release-data.generated.json';

function formatBytes(bytes) {
  if (!bytes) {
    return '0 B';
  }

  const units = ['B', 'KB', 'MB', 'GB'];
  const index = Math.min(Math.floor(Math.log(bytes) / Math.log(1024)), units.length - 1);
  const value = bytes / (1024 ** index);
  return `${value.toFixed(index === 0 ? 0 : 2)} ${units[index]}`;
}

function AssetCard({asset, primary = false}) {
  return (
    <article className={`${styles.assetCard} ${primary ? styles.primaryAsset : ''}`}>
      <div className={styles.assetHeader}>
        <div>
          <span className={styles.assetLabel}>{asset.label}</span>
          <h2>{asset.filename}</h2>
        </div>
        <a
          className={styles.downloadButton}
          href={asset.url}
          target="_blank"
          rel="noopener noreferrer">
          立即下载
        </a>
      </div>
      <div className={styles.assetMeta}>
        <div>
          <span>文件大小</span>
          <strong>{formatBytes(asset.size)}</strong>
        </div>
        <div>
          <span>SHA-256</span>
          <code>{asset.sha256}</code>
        </div>
      </div>
    </article>
  );
}

export default function ReleasePage() {
  const [primaryAsset, ...partAssets] = releaseData.assets;

  return (
    <Layout
      title={`Release ${releaseData.version}`}
      description={`哪吒互联 ${releaseData.version} 下载页面`}>
      <main className={styles.page}>
        <section className={styles.hero}>
          <div className={styles.heroContent}>
            <span className={styles.heroEyebrow}>Release</span>
            <h1>{releaseData.version}</h1>
            <p>
              当前版本的完整 APK 和分片文件已经整理到站内页面，所有下载链接统一加上
              {' '}
              <code>{releaseData.proxyPrefix}</code>
              {' '}
              前缀。
            </p>
            <div className={styles.heroActions}>
              <a
                className={styles.primaryButton}
                href={primaryAsset.url}
                target="_blank"
                rel="noopener noreferrer">
                下载完整 APK
              </a>
              <a
                className={styles.secondaryButton}
                href={releaseData.githubReleaseUrl}
                target="_blank"
                rel="noopener noreferrer">
                打开 GitHub Release
              </a>
            </div>
          </div>
          <div className={styles.summaryCard}>
            <div>
              <span>当前文件</span>
              <strong>{releaseData.summary.filename}</strong>
            </div>
            <div>
              <span>完整包大小</span>
              <strong>{formatBytes(releaseData.summary.totalSize)}</strong>
            </div>
            <div>
              <span>下载文件数</span>
              <strong>{releaseData.assets.length}</strong>
            </div>
          </div>
        </section>

        <section className={styles.section}>
          <div className={styles.sectionHeader}>
            <h2>主下载</h2>
            <p>默认推荐下载完整 APK，代理前缀已内置，无需手工拼接。</p>
          </div>
          <AssetCard asset={primaryAsset} primary />
        </section>

        {partAssets.length > 0 && (
          <section className={styles.section}>
            <div className={styles.sectionHeader}>
              <h2>分片文件</h2>
              <p>网络环境不稳定时，可以分别下载分片文件，再本地合并成完整 APK。</p>
            </div>
            <div className={styles.assetList}>
              {partAssets.map((asset) => (
                <AssetCard key={asset.filename} asset={asset} />
              ))}
            </div>
            <div className={styles.mergeGuide}>
              <h3>合并示例</h3>
              <div className={styles.codeGroup}>
                <div>
                  <span>macOS / Linux</span>
                  <pre>
                    <code>{`cat ${partAssets.map((asset) => asset.filename).join(' ')} > ${releaseData.summary.filename}`}</code>
                  </pre>
                </div>
                <div>
                  <span>Windows</span>
                  <pre>
                    <code>{`copy /b ${partAssets.map((asset) => asset.filename).join('+')} ${releaseData.summary.filename}`}</code>
                  </pre>
                </div>
              </div>
            </div>
          </section>
        )}

        <section className={styles.section}>
          <div className={styles.sectionHeader}>
            <h2>后续操作</h2>
            <p>下载完成后，可以直接回到安装文档继续后续步骤。</p>
          </div>
          <div className={styles.linkRow}>
            <Link className={styles.docLink} to={releaseData.installGuidePath}>
              查看安装指南
            </Link>
            <Link className={styles.docLink} to={releaseData.changelogPath}>
              查看更新日志
            </Link>
          </div>
        </section>
      </main>
    </Layout>
  );
}
