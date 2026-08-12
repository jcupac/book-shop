import { randomUUID } from 'node:crypto';
import { chromium, type Browser, type BrowserContext, type Page } from 'playwright';
import { loadConfiguration, type TestConfig } from '../../../../config/configuration-loader.js';

export interface BookShopBrowser {
  browser: Browser;
  context: BrowserContext;
  page: Page;
}

export function getConfiguration(): TestConfig {
  return loadConfiguration();
}

export function getBookShopApiBaseUrl(config: TestConfig = getConfiguration()): string {
  return config.bookShop.backendApiUrl;
}

export function getBookShopUiBaseUrl(config: TestConfig = getConfiguration()): string {
  return config.bookShop.frontendUrl;
}

export function getErpBaseUrl(config: TestConfig = getConfiguration()): string {
  return config.externalSystems.erp.url;
}

export function getTaxBaseUrl(config: TestConfig = getConfiguration()): string {
  return config.externalSystems.tax.url;
}

export function createUniqueSku(baseSku: string): string {
  const suffix = randomUUID().substring(0, 8);
  return `${baseSku}-${suffix}`;
}

export async function setUpBookShopBrowser(): Promise<BookShopBrowser> {
  const browser = await chromium.launch({ headless: true });
  const context = await browser.newContext({ viewport: { width: 1920, height: 1080 } });
  const page = await context.newPage();
  return { browser, context, page };
}

export async function tearDownBookShopBrowser(bookShopBrowser: BookShopBrowser | null): Promise<void> {
  if (!bookShopBrowser) return;
  await bookShopBrowser.page.close().catch(() => {});
  await bookShopBrowser.context.close().catch(() => {});
  await bookShopBrowser.browser.close().catch(() => {});
}
