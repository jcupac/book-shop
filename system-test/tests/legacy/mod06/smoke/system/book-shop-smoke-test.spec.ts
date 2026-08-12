import { test, expect, forChannels, ChannelType } from '../fixtures.js';

forChannels(ChannelType.UI, ChannelType.API)(() => {
    test('shouldBeAbleToGoToBookShop', async ({ bookShopDriver }) => {
        const result = await bookShopDriver.goToBookShop({});
        expect(result.success).toBe(true);
    });
});
