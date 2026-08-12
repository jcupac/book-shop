import { test, expect } from '@playwright/test';
import { getBookShopUiBaseUrl, setUpBookShopBrowser, tearDownBookShopBrowser, type BookShopBrowser } from '../../base/BaseRawTest.js';

test('shouldBeAbleToGoToBookShop', async () => {
    let bookShopBrowser: BookShopBrowser | null = null;
    try {
        bookShopBrowser = await setUpBookShopBrowser();
        const response = await bookShopBrowser.page.goto(getBookShopUiBaseUrl());

        expect(response?.status()).toBe(200);

        const contentType = response?.headers()['content-type'];
        expect(contentType).toBeDefined();
        expect(contentType).toContain('text/html');

        const pageContent = await bookShopBrowser.page.content();
        expect(pageContent).toContain('<html');
        expect(pageContent).toContain('</html>');
    } finally {
        await tearDownBookShopBrowser(bookShopBrowser);
    }
});
