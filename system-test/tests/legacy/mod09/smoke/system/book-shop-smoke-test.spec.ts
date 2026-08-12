import { test, forChannels, ChannelType } from '../../base/BaseScenarioDslTest.js';

forChannels(ChannelType.UI, ChannelType.API)(() => {
    test('shouldBeAbleToGoToBookShop', async ({ scenario }) => {
        await scenario.assume().bookShop().shouldBeRunning();
    });
});
