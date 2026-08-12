import { test as base } from '@playwright/test';
import { ChannelContext, bindChannels, bindTestEach } from '@optivem/optivem-testing';
import { chromium } from 'playwright';
import type { Browser } from 'playwright';
import { loadConfiguration } from '../../../../config/configuration-loader.js';
import { BookShopApiDriver } from '../../../../src/testkit/driver/adapter/api/book-shop-api-driver.js';
import { BookShopUiDriver } from '../../../../src/testkit/driver/adapter/ui/book-shop-ui-driver.js';
import { ErpRealDriver } from '../../../../src/testkit/driver/adapter/external/erp/erp-real-driver.js';
import { TaxRealDriver } from '../../../../src/testkit/driver/adapter/external/tax/tax-real-driver.js';
import { ClockRealDriver } from '../../../../src/testkit/driver/adapter/external/clock/clock-real-driver.js';
import { AppContext, UseCaseDsl } from '../../../../src/testkit/dsl/scenario-dsl.js';
import { ChannelType } from '../../../../src/testkit/channel/channel-type.js';

const config = loadConfiguration();

const _test = base.extend<{ app: UseCaseDsl; _bookShopBrowser: Browser }>({
    _bookShopBrowser: async ({}, use) => {
        const browser = await chromium.launch();
        await use(browser);
        await browser.close();
    },
    app: async ({ _bookShopBrowser }, use) => {
        const channel = ChannelContext.get() || ChannelType.API;
        const appContext = new AppContext({
            channelMode: 'dynamic',
            channel,
            bookShopDriverFactory: (ch) => {
                if (ch === ChannelType.UI) {
                    return new BookShopUiDriver(config.bookShop.frontendUrl, _bookShopBrowser);
                }
                return new BookShopApiDriver(config.bookShop.backendApiUrl);
            },
            erpDriver: new ErpRealDriver(config.externalSystems.erp.url),
            clockDriver: new ClockRealDriver(),
            taxDriver: new TaxRealDriver(config.externalSystems.tax.url),
        });
        const app = new UseCaseDsl(appContext);
        await use(app);
        await app.close();
    },
});

const test = Object.assign(_test, { each: bindTestEach(_test) });
const { forChannels } = bindChannels(test);
export { test, forChannels };
export { ChannelType } from '../../../../src/testkit/channel/channel-type.js';
export { expect } from '@playwright/test';
