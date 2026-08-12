import { uiTest as test, expect } from '../fixtures.js';

test('shouldBeAbleToGoToBookShop', async ({ bookShopPage, bookShopUiUrl }) => {
    const response = await bookShopPage.goto(bookShopUiUrl);
    expect(response?.status()).toBe(200);
});
