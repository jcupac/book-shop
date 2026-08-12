import { test, forChannels, ChannelType } from '../fixtures.js';

forChannels(ChannelType.UI, ChannelType.API)(() => {
    test('shouldBeAbleToGoToBookShop', async ({ scenario }) => {
        await scenario.assume().bookShop().shouldBeRunning();
    });
});
