import React from 'react';
import clsx from 'clsx';
import Link from '@docusaurus/Link';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import Layout from '@theme/Layout';
import styles from './index.module.css';
import releaseData from '../data/release-summary.generated.json';

function HomepageHeader() {
  const {siteConfig} = useDocusaurusContext();
  return (
    <header className={clsx('hero hero--primary', styles.heroBanner)}>
      <div className="container">
        <h1 className="hero__title">{siteConfig.title}</h1>
        <p className="hero__subtitle">{siteConfig.tagline}</p>
        <div className={styles.versionBadge}>
          <Link className={styles.badge} to={releaseData.releasePath}>
            📦 Latest: {releaseData.version}
          </Link>
        </div>
        <div className={styles.buttons}>
          <Link
            className="button button--secondary button--lg"
            to="/docs/intro">
            开始使用 🚀
          </Link>
        </div>
      </div>
    </header>
  );
}

function FeatureList() {
  return [
    {
      title: '🎯 智能仪表盘',
      description: (
        <>
          自定义网格布局，丰富的小组件库，拖拽式可视化编辑。
          所有配置自动保存，打造专属车机仪表盘。
        </>
      ),
    },
    {
      title: '🔌 动态插件系统',
      description: (
        <>
          支持JAR插件动态加载，开放的Widget API。
          无需重新编译，即插即用的插件生态。
        </>
      ),
    },
    {
      title: '🔧 系统级增强',
      description: (
        <>
          智能蓝牙控制、按键映射、语音助手管理。
          免Root操作，所有功能通过无障碍服务实现。
        </>
      ),
    },
    {
      title: '📊 实时数据监控',
      description: (
        <>
          悬浮窗显示车辆数据，VHAL属性实时监控。
          支持自定义数据源和公式计算。
        </>
      ),
    },
    {
      title: '⚡ 高性能架构',
      description: (
        <>
          Kotlin + Jetpack Compose 现代化技术栈。
          MVVM架构，Coroutines协程支持，流畅体验。
        </>
      ),
    },
    {
      title: '🎨 现代化UI',
      description: (
        <>
          Material Design 3 设计语言，深色/浅色主题切换。
          流畅动画，精致交互，赏心悦目。
        </>
      ),
    },
  ];
}

function Feature({title, description}) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center padding-horiz--md">
        <h3>{title}</h3>
        <p>{description}</p>
      </div>
    </div>
  );
}

function HomepageFeatures() {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList().map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
        <div className={styles.moreInfo}>
          <Link
            className="button button--primary button--lg"
            to="/docs/intro">
            查看完整文档 📚
          </Link>
        </div>
      </div>
    </section>
  );
}

export default function Home() {
  const {siteConfig} = useDocusaurusContext();
  return (
    <Layout
      title={`${siteConfig.title} - 首页`}
      description="哪吒互联工具 - 专为哪吒汽车设计的车机增强工具集">
      <HomepageHeader />
      <main>
        <HomepageFeatures />
      </main>
    </Layout>
  );
}
