package com.jcupac.bookshop.testkit.driver.adapter.api.client.controllers;

import com.jcupac.bookshop.testkit.driver.adapter.api.client.dtos.errors.ProblemDetailResponse;
import com.jcupac.bookshop.testkit.driver.adapter.shared.client.http.JsonHttpClient;
import com.jcupac.bookshop.testkit.common.Result;

public class HealthController {
    private static final String ENDPOINT = "/health";

    private final JsonHttpClient<ProblemDetailResponse> httpClient;

    public HealthController(JsonHttpClient<ProblemDetailResponse> httpClient) {
        this.httpClient = httpClient;
    }

    public Result<Void, ProblemDetailResponse> checkHealth() {
        return httpClient.get(ENDPOINT);
    }
}



