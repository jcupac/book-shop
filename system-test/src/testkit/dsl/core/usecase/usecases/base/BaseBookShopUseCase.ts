import type { BookShopDriver } from '../../../../../driver/port/book-shop-driver.js';
import { BaseUseCase } from '../../../shared/base-use-case.js';

export abstract class BaseBookShopUseCase<TResponse, TVerification> extends BaseUseCase<
  BookShopDriver,
  TResponse,
  TVerification
> {}
