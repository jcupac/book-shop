import { test as base } from '@playwright/test';
import { chromium } from 'playwright';
import type { Browser, BrowserContext, Page } from 'playwright';
import { loadConfiguration, type TestConfig } from '../../../../config/configuration-loader.js';

process.env.EXTERNAL_SYSTEM_MODE = process.env.EXTERNAL_SYSTEM_MODE || 'real';

const config = loadConfiguration();

// Raw HTTP fixtures for API tests
export const apiTest = base.extend<{ config: TestConfig }>({
    config: async ({}, use) => {
        await use(config);
    },
});

// Raw Playwright fixtures for UI tests
export const uiTest = base.extend<{ config: TestConfig; bookShopPage: Page; _bookShopBrowser: Browser; _bookShopContext: BrowserContext }>({
    config: async ({}, use) => {
        await use(config);
    },
    _bookShopBrowser: async ({}, use) => {
        const browser = await chromium.launch();
        await use(browser);
        await browser.close();
    },
    _bookShopContext: async ({ _bookShopBrowser }, use) => {
        const context = await _bookShopBrowser.newContext({ viewport: { width: 1920, height: 1080 } });
        await use(context);
        await context.close();
    },
    bookShopPage: async ({ _bookShopContext }, use) => {
        const page = await _bookShopContext.newPage();
        await use(page);
        await page.close();
    },
});

export { expect } from '@playwright/test';
export { config };
