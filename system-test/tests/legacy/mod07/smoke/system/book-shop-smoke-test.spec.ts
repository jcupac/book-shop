import { test, forChannels, ChannelType } from '../fixtures.js';

forChannels(ChannelType.UI, ChannelType.API)(() => {
    test('shouldBeAbleToGoToBookShop', async ({ app }) => {
        (await app.bookShop().goToBookShop().execute()).shouldSucceed();
    });
});
