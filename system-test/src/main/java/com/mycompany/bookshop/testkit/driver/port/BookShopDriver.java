package com.jcupac.bookshop.testkit.driver.port;

import com.jcupac.bookshop.testkit.driver.port.dtos.error.SystemError;
import com.jcupac.bookshop.testkit.driver.port.dtos.BrowseCouponsRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.BrowseCouponsResponse;
import com.jcupac.bookshop.testkit.driver.port.dtos.CancelOrderRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.CancelOrderResponse;
import com.jcupac.bookshop.testkit.driver.port.dtos.DeliverOrderRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.DeliverOrderResponse;
import com.jcupac.bookshop.testkit.driver.port.dtos.GoToBookShopRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.GoToBookShopResponse;
import com.jcupac.bookshop.testkit.driver.port.dtos.PlaceOrderRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.PlaceOrderResponse;
import com.jcupac.bookshop.testkit.driver.port.dtos.PublishCouponRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.PublishCouponResponse;
import com.jcupac.bookshop.testkit.driver.port.dtos.ViewOrderRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.ViewOrderResponse;
import com.jcupac.bookshop.testkit.common.Result;

public interface BookShopDriver extends AutoCloseable {
    Result<GoToBookShopResponse, SystemError> goToBookShop(GoToBookShopRequest request);

    Result<PlaceOrderResponse, SystemError> placeOrder(PlaceOrderRequest request);

    Result<CancelOrderResponse, SystemError> cancelOrder(CancelOrderRequest request);

    Result<DeliverOrderResponse, SystemError> deliverOrder(DeliverOrderRequest request);

    Result<ViewOrderResponse, SystemError> viewOrder(ViewOrderRequest request);

    Result<PublishCouponResponse, SystemError> publishCoupon(PublishCouponRequest request);

    Result<BrowseCouponsResponse, SystemError> browseCoupons(BrowseCouponsRequest request);
}
