package com.jcupac.bookshop.testkit.driver.adapter.api.client;

import com.jcupac.bookshop.testkit.driver.adapter.api.client.controllers.CouponController;
import com.jcupac.bookshop.testkit.driver.adapter.api.client.controllers.HealthController;
import com.jcupac.bookshop.testkit.driver.adapter.api.client.controllers.OrderController;
import com.jcupac.bookshop.testkit.driver.adapter.api.client.dtos.errors.ProblemDetailResponse;
import com.jcupac.bookshop.testkit.driver.adapter.shared.client.http.JsonHttpClient;
import com.jcupac.bookshop.testkit.common.Closer;

public class BookShopApiClient implements AutoCloseable {
    private final JsonHttpClient<ProblemDetailResponse> httpClient;
    private final HealthController healthController;
    private final OrderController orderController;
    private final CouponController couponController;

    public BookShopApiClient(String baseUrl) {
        this.httpClient = new JsonHttpClient<>(baseUrl, ProblemDetailResponse.class);
        this.healthController = new HealthController(httpClient);
        this.orderController = new OrderController(httpClient);
        this.couponController = new CouponController(httpClient);
    }

    public HealthController health() {
        return healthController;
    }

    public OrderController orders() {
        return orderController;
    }

    public CouponController coupons() {
        return couponController;
    }

    @Override
    public void close() {
        Closer.close(httpClient);
    }
}
