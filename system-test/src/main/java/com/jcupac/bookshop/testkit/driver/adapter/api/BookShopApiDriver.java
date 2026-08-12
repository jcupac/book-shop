package com.jcupac.bookshop.testkit.driver.adapter.api;

import com.jcupac.bookshop.testkit.driver.adapter.api.client.BookShopApiClient;
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
import com.jcupac.bookshop.testkit.driver.port.BookShopDriver;
import com.jcupac.bookshop.testkit.common.Closer;
import com.jcupac.bookshop.testkit.common.Result;

public class BookShopApiDriver implements BookShopDriver {
    private final BookShopApiClient apiClient;

    public BookShopApiDriver(String baseUrl) {
        this.apiClient = new BookShopApiClient(baseUrl);
    }

    @Override
    public void close() {
        Closer.close(apiClient);
    }

    @Override
    public Result<GoToBookShopResponse, SystemError> goToBookShop(GoToBookShopRequest request) {
        return apiClient.health().checkHealth()
                .mapError(SystemErrorMapper::from)
                .map(value -> GoToBookShopResponse.builder().build());
    }

    @Override
    public Result<PlaceOrderResponse, SystemError> placeOrder(PlaceOrderRequest request) {
        return apiClient.orders().placeOrder(request).mapError(SystemErrorMapper::from);
    }

    @Override
    public Result<CancelOrderResponse, SystemError> cancelOrder(CancelOrderRequest request) {
        return apiClient.orders().cancelOrder(request.getOrderNumber())
                .mapError(SystemErrorMapper::from)
                .map(value -> CancelOrderResponse.builder().build());
    }

    @Override
    public Result<DeliverOrderResponse, SystemError> deliverOrder(DeliverOrderRequest request) {
        return apiClient.orders().deliverOrder(request.getOrderNumber())
                .mapError(SystemErrorMapper::from)
                .map(value -> DeliverOrderResponse.builder().build());
    }

    @Override
    public Result<ViewOrderResponse, SystemError> viewOrder(ViewOrderRequest request) {
        return apiClient.orders().viewOrder(request.getOrderNumber()).mapError(SystemErrorMapper::from);
    }

    @Override
    public Result<PublishCouponResponse, SystemError> publishCoupon(PublishCouponRequest request) {
        return apiClient.coupons().publishCoupon(request)
                .mapError(SystemErrorMapper::from)
                .map(value -> PublishCouponResponse.builder().build());
    }

    @Override
    public Result<BrowseCouponsResponse, SystemError> browseCoupons(BrowseCouponsRequest request) {
        return apiClient.coupons().browseCoupons().mapError(SystemErrorMapper::from);
    }
}
