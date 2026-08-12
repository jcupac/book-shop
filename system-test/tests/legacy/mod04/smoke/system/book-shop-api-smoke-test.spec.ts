import { apiTest as test, expect } from '../fixtures.js';

test('shouldBeAbleToGoToBookShop', async ({ bookShopApiClient }) => {
    const result = await bookShopApiClient.health().checkHealth();
    expect(result.success).toBe(true);
});
