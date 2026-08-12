import { expect, type TestType } from '@playwright/test';

// Playwright's TestType is invariant in its fixture shape, so we accept any test
// type and rely on the runtime destructuring of `bookShopDriver` from fixtures.
// eslint-disable-next-line @typescript-eslint/no-explicit-any
export function runBookShopBaseSmokeTest(test: TestType<any, any>): void {
  test('shouldBeAbleToGoToBookShop', async ({ bookShopDriver }) => {
    const result = await bookShopDriver.goToBookShop({});
    expect(result.success).toBe(true);
  });
}
