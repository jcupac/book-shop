import { test, expect } from '@playwright/test';
import { getBookShopApiBaseUrl } from '../../base/BaseRawTest.js';

test('shouldBeAbleToGoToBookShop', async () => {
    const response = await fetch(`${getBookShopApiBaseUrl()}/health`);
    expect(response.status).toBe(200);
});
