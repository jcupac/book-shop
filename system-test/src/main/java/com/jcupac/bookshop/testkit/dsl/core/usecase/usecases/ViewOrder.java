package com.jcupac.bookshop.testkit.dsl.core.usecase.usecases;

import com.jcupac.bookshop.testkit.driver.port.dtos.ViewOrderRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.ViewOrderResponse;
import com.jcupac.bookshop.testkit.driver.port.BookShopDriver;
import com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.base.BaseBookShopUseCase;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseResult;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;

public class ViewOrder extends BaseBookShopUseCase<ViewOrderResponse, ViewOrderVerification> {
    private String orderNumberResultAlias;

    public ViewOrder(BookShopDriver driver, UseCaseContext context) {
        super(driver, context);
    }

    public ViewOrder orderNumber(String orderNumberResultAlias) {
        this.orderNumberResultAlias = orderNumberResultAlias;
        return this;
    }

    @Override
    public UseCaseResult<ViewOrderResponse, ViewOrderVerification> execute() {
        var orderNumber = context.getResultValue(orderNumberResultAlias);

        var request = ViewOrderRequest.builder().orderNumber(orderNumber).build();
        var result = driver.viewOrder(request);

        return new UseCaseResult<>(result, context, ViewOrderVerification::new);
    }
}



