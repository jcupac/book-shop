import { test as base } from '@playwright/test';
import { chromium } from 'playwright';
import type { Browser } from 'playwright';
import { loadConfiguration } from '../../../../config/configuration-loader.js';
import { BookShopApiClient } from '../../../../src/testkit/driver/adapter/api/client/BookShopApiClient.js';
import { BookShopUiClient } from '../../../../src/testkit/driver/adapter/ui/client/BookShopUiClient.js';
import { ErpRealClient } from '../../../../src/testkit/driver/adapter/external/erp/client/ErpRealClient.js';

process.env.EXTERNAL_SYSTEM_MODE = process.env.EXTERNAL_SYSTEM_MODE ?? 'real';

const config = loadConfiguration();

// Client fixtures for API tests
export const apiTest = base.extend<{ bookShopApiClient: BookShopApiClient; erpClient: ErpRealClient }>({
    bookShopApiClient: async ({}, use) => {
        await use(new BookShopApiClient(config.bookShop.backendApiUrl));
    },
    erpClient: async ({}, use) => {
        await use(new ErpRealClient(config.externalSystems.erp.url));
    },
});

// Client fixtures for UI tests
export const uiTest = base.extend<{ bookShopUiClient: BookShopUiClient; _bookShopBrowser: Browser; erpClient: ErpRealClient }>({
    _bookShopBrowser: async ({}, use) => {
        const browser = await chromium.launch();
        await use(browser);
        await browser.close();
    },
    bookShopUiClient: async ({ _bookShopBrowser }, use) => {
        const client = new BookShopUiClient(config.bookShop.frontendUrl, _bookShopBrowser);
        await use(client);
        await client.close();
    },
    erpClient: async ({}, use) => {
        await use(new ErpRealClient(config.externalSystems.erp.url));
    },
});

export { expect } from '@playwright/test';
export { config };
