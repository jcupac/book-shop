import type { BookShopDriver } from '../../../../driver/port/book-shop-driver.js';
import type { GoToBookShopResponse } from '../../../../driver/port/dtos/GoToBookShopResponse.js';
import { UseCaseResult } from '../../shared/use-case-result.js';
import { VoidVerification } from '../../shared/void-verification.js';
import type { UseCaseContext } from '../../shared/use-case-context.js';
import { BaseBookShopUseCase } from './base/BaseBookShopUseCase.js';

export class GoToBookShop extends BaseBookShopUseCase<GoToBookShopResponse, VoidVerification> {
  constructor(driver: BookShopDriver, context: UseCaseContext) {
    super(driver, context);
  }

  async execute(): Promise<UseCaseResult<GoToBookShopResponse, VoidVerification>> {
    const result = await this.driver.goToBookShop({});

    return new UseCaseResult(
      result,
      this.context,
      (_, ctx) => new VoidVerification(undefined, ctx),
    );
  }
}
