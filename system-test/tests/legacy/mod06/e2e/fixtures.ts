import { test as base } from '@playwright/test';
import { ChannelContext, bindChannels, bindTestEach } from '@optivem/optivem-testing';
import { chromium } from 'playwright';
import type { Browser } from 'playwright';
import { loadConfiguration } from '../../../../config/configuration-loader.js';
import type { BookShopDriver } from '../../../../src/testkit/driver/port/book-shop-driver.js';
import type { ErpDriver } from '../../../../src/testkit/driver/port/external/erp/erp-driver.js';
import type { TaxDriver } from '../../../../src/testkit/driver/port/external/tax/tax-driver.js';
import { BookShopApiDriver } from '../../../../src/testkit/driver/adapter/api/book-shop-api-driver.js';
import { BookShopUiDriver } from '../../../../src/testkit/driver/adapter/ui/book-shop-ui-driver.js';
import { ErpRealDriver } from '../../../../src/testkit/driver/adapter/external/erp/erp-real-driver.js';
import { TaxRealDriver } from '../../../../src/testkit/driver/adapter/external/tax/tax-real-driver.js';
import { ChannelType } from '../../../../src/testkit/channel/channel-type.js';

process.env.EXTERNAL_SYSTEM_MODE = process.env.EXTERNAL_SYSTEM_MODE || 'real';

const config = loadConfiguration();

// Channel-aware driver fixture: bookShopDriver switches between API/UI based on ChannelContext
const _test = base.extend<{ bookShopDriver: BookShopDriver; erpDriver: ErpDriver; taxDriver: TaxDriver; _bookShopBrowser: Browser }>({
    _bookShopBrowser: async ({}, use) => {
        const browser = await chromium.launch();
        await use(browser);
        await browser.close();
    },
    bookShopDriver: async ({ _bookShopBrowser }, use) => {
        const channel = ChannelContext.get() || ChannelType.API;
        let driver: BookShopDriver;
        if (channel === ChannelType.UI) {
            driver = new BookShopUiDriver(config.bookShop.frontendUrl, _bookShopBrowser);
        } else {
            driver = new BookShopApiDriver(config.bookShop.backendApiUrl);
        }
        await use(driver);
        await driver.close();
    },
    erpDriver: async ({}, use) => {
        const driver = new ErpRealDriver(config.externalSystems.erp.url);
        await use(driver);
        await driver.close();
    },
    taxDriver: async ({}, use) => {
        const driver = new TaxRealDriver(config.externalSystems.tax.url);
        await use(driver);
        await driver.close();
    },
});

const test = Object.assign(_test, { each: bindTestEach(_test) });
const { forChannels } = bindChannels(test);
export { test, forChannels };
export { ChannelType } from '../../../../src/testkit/channel/channel-type.js';
export { expect } from '@playwright/test';
