import { Result } from '../../common/result.js';
import { AsyncCloseable } from './async-closeable.js';
import { GoToBookShopRequest } from './dtos/GoToBookShopRequest.js';
import { GoToBookShopResponse } from './dtos/GoToBookShopResponse.js';
import { PlaceOrderRequest } from './dtos/PlaceOrderRequest.js';
import { PlaceOrderResponse } from './dtos/PlaceOrderResponse.js';
import { CancelOrderRequest } from './dtos/CancelOrderRequest.js';
import { CancelOrderResponse } from './dtos/CancelOrderResponse.js';
import { DeliverOrderRequest } from './dtos/DeliverOrderRequest.js';
import { DeliverOrderResponse } from './dtos/DeliverOrderResponse.js';
import { ViewOrderRequest } from './dtos/ViewOrderRequest.js';
import { ViewOrderResponse } from './dtos/ViewOrderResponse.js';
import { SystemError } from './dtos/errors/SystemError.js';
import { PublishCouponRequest } from './dtos/PublishCouponRequest.js';
import { PublishCouponResponse } from './dtos/PublishCouponResponse.js';
import { BrowseCouponsRequest } from './dtos/BrowseCouponsRequest.js';
import { BrowseCouponsResponse } from './dtos/BrowseCouponsResponse.js';

export interface BookShopDriver extends AsyncCloseable {
  goToBookShop(request: GoToBookShopRequest): Promise<Result<GoToBookShopResponse, SystemError>>;
  placeOrder(request: PlaceOrderRequest): Promise<Result<PlaceOrderResponse, SystemError>>;
  cancelOrder(request: CancelOrderRequest): Promise<Result<CancelOrderResponse, SystemError>>;
  deliverOrder(request: DeliverOrderRequest): Promise<Result<DeliverOrderResponse, SystemError>>;
  viewOrder(request: ViewOrderRequest): Promise<Result<ViewOrderResponse, SystemError>>;
  publishCoupon(request: PublishCouponRequest): Promise<Result<PublishCouponResponse, SystemError>>;
  browseCoupons(request: BrowseCouponsRequest): Promise<Result<BrowseCouponsResponse, SystemError>>;
}
