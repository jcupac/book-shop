import type { BookShopDriver } from '../../../../driver/port/book-shop-driver.js';
import type { BrowseCouponsResponse } from '../../../../driver/port/dtos/BrowseCouponsResponse.js';
import { UseCaseResult } from '../../shared/use-case-result.js';
import type { UseCaseContext } from '../../shared/use-case-context.js';
import { BaseBookShopUseCase } from './base/BaseBookShopUseCase.js';
import { BrowseCouponsVerification } from './BrowseCouponsVerification.js';

export class BrowseCoupons extends BaseBookShopUseCase<BrowseCouponsResponse, BrowseCouponsVerification> {
  constructor(driver: BookShopDriver, context: UseCaseContext) {
    super(driver, context);
  }

  async execute(): Promise<UseCaseResult<BrowseCouponsResponse, BrowseCouponsVerification>> {
    const result = await this.driver.browseCoupons({});

    return new UseCaseResult(
      result,
      this.context,
      (response, ctx) => new BrowseCouponsVerification(response, ctx),
    );
  }
}
