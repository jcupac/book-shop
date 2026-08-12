import { test as base } from '@playwright/test';
import { chromium } from 'playwright';
import type { Browser, BrowserContext, Page } from 'playwright';
import { loadConfiguration } from '../../../../config/configuration-loader.js';
import { BookShopApiClient } from '../../../../src/testkit/driver/adapter/api/client/BookShopApiClient.js';
import { ErpRealClient } from '../../../../src/testkit/driver/adapter/external/erp/client/ErpRealClient.js';
import { TaxRealClient } from '../../../../src/testkit/driver/adapter/external/tax/client/TaxRealClient.js';

process.env.EXTERNAL_SYSTEM_MODE = process.env.EXTERNAL_SYSTEM_MODE || 'real';

const config = loadConfiguration();

export const apiTest = base.extend<{ bookShopApiClient: BookShopApiClient; erpClient: ErpRealClient; taxClient: TaxRealClient }>({
    bookShopApiClient: async ({}, use) => {
        await use(new BookShopApiClient(config.bookShop.backendApiUrl));
    },
    erpClient: async ({}, use) => {
        await use(new ErpRealClient(config.externalSystems.erp.url));
    },
    taxClient: async ({}, use) => {
        await use(new TaxRealClient(config.externalSystems.tax.url));
    },
});

export const uiTest = base.extend<{ bookShopPage: Page; bookShopUiUrl: string; _bookShopBrowser: Browser; _bookShopContext: BrowserContext }>({
    bookShopUiUrl: async ({}, use) => {
        await use(config.bookShop.frontendUrl);
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
