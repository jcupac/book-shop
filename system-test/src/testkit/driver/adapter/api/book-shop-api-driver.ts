import type { Result } from '../../../common/result.js';
import { success, failure } from '../../../common/result.js';
import type { GoToBookShopRequest } from '../../port/dtos/GoToBookShopRequest.js';
import type { GoToBookShopResponse } from '../../port/dtos/GoToBookShopResponse.js';
import type { PlaceOrderRequest } from '../../port/dtos/PlaceOrderRequest.js';
import type { PlaceOrderResponse } from '../../port/dtos/PlaceOrderResponse.js';
import type { CancelOrderRequest } from '../../port/dtos/CancelOrderRequest.js';
import type { CancelOrderResponse } from '../../port/dtos/CancelOrderResponse.js';
import type { DeliverOrderRequest } from '../../port/dtos/DeliverOrderRequest.js';
import type { DeliverOrderResponse } from '../../port/dtos/DeliverOrderResponse.js';
import type { ViewOrderRequest } from '../../port/dtos/ViewOrderRequest.js';
import type { ViewOrderResponse } from '../../port/dtos/ViewOrderResponse.js';
import type { SystemError } from '../../port/dtos/errors/SystemError.js';
import type { PublishCouponRequest } from '../../port/dtos/PublishCouponRequest.js';
import type { PublishCouponResponse } from '../../port/dtos/PublishCouponResponse.js';
import type { BrowseCouponsRequest } from '../../port/dtos/BrowseCouponsRequest.js';
import type { BrowseCouponsResponse } from '../../port/dtos/BrowseCouponsResponse.js';
import type { BookShopDriver } from '../../port/book-shop-driver.js';
import { BookShopApiClient } from './client/BookShopApiClient.js';

export class BookShopApiDriver implements BookShopDriver {
  private readonly client: BookShopApiClient;

  constructor(baseUrl: string) {
    this.client = new BookShopApiClient(baseUrl);
  }

  async goToBookShop(_request: GoToBookShopRequest): Promise<Result<GoToBookShopResponse, SystemError>> {
    const result = await this.client.health().checkHealth();
    if (result.success) return success({});
    return failure(result.error);
  }

  async placeOrder(request: PlaceOrderRequest): Promise<Result<PlaceOrderResponse, SystemError>> {
    return this.client.orders().placeOrder(request);
  }

  async cancelOrder(request: CancelOrderRequest): Promise<Result<CancelOrderResponse, SystemError>> {
    const result = await this.client.orders().cancelOrder(request.orderNumber);
    if (result.success) return success({});
    return failure(result.error);
  }

  async deliverOrder(request: DeliverOrderRequest): Promise<Result<DeliverOrderResponse, SystemError>> {
    const result = await this.client.orders().deliverOrder(request.orderNumber);
    if (result.success) return success({});
    return failure(result.error);
  }

  async viewOrder(request: ViewOrderRequest): Promise<Result<ViewOrderResponse, SystemError>> {
    return this.client.orders().viewOrder(request.orderNumber);
  }

  async publishCoupon(request: PublishCouponRequest): Promise<Result<PublishCouponResponse, SystemError>> {
    const result = await this.client.coupons().publishCoupon(request);
    if (result.success) return success({});
    return failure(result.error);
  }

  async browseCoupons(_request: BrowseCouponsRequest): Promise<Result<BrowseCouponsResponse, SystemError>> {
    return this.client.coupons().browseCoupons();
  }

  async close(): Promise<void> {
    // No resources to release: the API client uses fetch per-call.
  }
}
