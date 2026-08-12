package com.jcupac.bookshop.testkit.driver.adapter.api.client.controllers;

import com.jcupac.bookshop.testkit.driver.adapter.api.client.dtos.errors.ProblemDetailResponse;
import com.jcupac.bookshop.testkit.driver.adapter.shared.client.http.JsonHttpClient;
import com.jcupac.bookshop.testkit.driver.port.dtos.BrowseCouponsResponse;
import com.jcupac.bookshop.testkit.driver.port.dtos.PublishCouponRequest;
import com.jcupac.bookshop.testkit.common.Result;

public class CouponController {
    private static final String ENDPOINT = "/api/coupons";

    private final JsonHttpClient<ProblemDetailResponse> httpClient;

    public CouponController(JsonHttpClient<ProblemDetailResponse> httpClient) {
        this.httpClient = httpClient;
    }

    public Result<Void, ProblemDetailResponse> publishCoupon(PublishCouponRequest request) {
        return httpClient.post(ENDPOINT, request);
    }

    public Result<BrowseCouponsResponse, ProblemDetailResponse> browseCoupons() {
        return httpClient.get(ENDPOINT, BrowseCouponsResponse.class);
    }
}
