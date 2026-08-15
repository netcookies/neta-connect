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

function formatDate(isoText) {
  return isoText ? isoText.slice(0, 10) : '--';
}

function AssetCard({release, primary = false}) {
  const {asset, summary, version} = release;

  return (
    <article className={`${styles.assetCard} ${primary ? styles.primaryAsset : ''}`}>
      <div className={styles.assetHeader}>
        <div>
          <span className={styles.assetLabel}>主文件下载</span>
          <h2>{version}</h2>
          <p className={styles.assetFilename}>{summary.filename}</p>
        </div>
        <a
          className={styles.downloadButton}
          href={asset.url}
          target="_blank"
          rel="noopener noreferrer">
          下载 APK
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

function VersionRow({release, latest = false}) {
  return (
    <article className={`${styles.versionRow} ${latest ? styles.latestVersionRow : ''}`}>
      <div className={styles.versionInfo}>
        <div className={styles.versionHeading}>
          <div className={styles.versionTags}>
            {latest && <span className={styles.assetLabel}>当前推荐</span>}
            {release.version.includes('beta') && <span className={styles.versionTag}>Beta</span>}
          </div>
          <h3>{release.version}</h3>
        </div>
        <p className={styles.versionFilename}>{release.summary.filename}</p>
        <dl className={styles.versionMeta}>
          <div>
            <dt>文件大小</dt>
            <dd>{formatBytes(release.summary.totalSize)}</dd>
          </div>
          <div>
            <dt>元数据更新</dt>
            <dd>{formatDate(release.generatedAt)}</dd>
          </div>
        </dl>
      </div>
      <a
        className={styles.downloadButton}
        href={release.asset.url}
        target="_blank"
        rel="noopener noreferrer">
        下载 APK
      </a>
    </article>
  );
}

export default function ReleasePage() {
  const currentRelease = releaseData.releases.find((release) => release.version === releaseData.version)
    || releaseData.releases[0];

  return (
    <Layout
      title={`Release ${releaseData.version}`}
      description={`哪吒互联 ${releaseData.version} 下载页面`}>
      <main className={styles.page}>
        <section className={styles.hero}>
          <div className={styles.heroContent}>
            <span className={styles.heroEyebrow}>Release</span>
            <h1>{currentRelease.version}</h1>
            <p>
              发布页现在只保留主 APK 下载，所有历史版本统一按列表展示在下方。
              所有下载链接统一加上
              {' '}
              <code>{releaseData.proxyPrefix}</code>
              {' '}
              前缀。
            </p>
            <div className={styles.heroActions}>
              <a
                className={styles.primaryButton}
                href={currentRelease.asset.url}
                target="_blank"
                rel="noopener noreferrer">
                下载当前 APK
              </a>
              <a
                className={styles.secondaryButton}
                href={currentRelease.githubReleaseUrl}
                target="_blank"
                rel="noopener noreferrer">
                打开 GitHub Release
              </a>
            </div>
          </div>
          <div className={styles.summaryCard}>
            <div>
              <span>当前文件</span>
              <strong>{currentRelease.summary.filename}</strong>
            </div>
            <div>
              <span>完整包大小</span>
              <strong>{formatBytes(currentRelease.summary.totalSize)}</strong>
            </div>
            <div>
              <span>可选版本数</span>
              <strong>{releaseData.releases.length}</strong>
            </div>
          </div>
        </section>

        <section className={styles.section}>
          <div className={styles.sectionHeader}>
            <h2>当前版本</h2>
            <p>默认推荐下载最新版本的主 APK，页面不再展示分片文件。</p>
          </div>
          <AssetCard release={currentRelease} primary />
        </section>

        <section className={styles.section}>
          <div className={styles.sectionHeader}>
            <h2>全部版本</h2>
            <p>按版本从新到旧排列，适合直接浏览和下载指定 APK。</p>
          </div>
          <div className={styles.versionList}>
            {releaseData.releases.map((release) => (
              <VersionRow
                key={release.version}
                release={release}
                latest={release.version === currentRelease.version}
              />
            ))}
          </div>
        </section>

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
