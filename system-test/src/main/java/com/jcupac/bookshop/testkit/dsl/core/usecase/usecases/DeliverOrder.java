package com.jcupac.bookshop.testkit.dsl.core.usecase.usecases;

import com.jcupac.bookshop.testkit.driver.port.BookShopDriver;
import com.jcupac.bookshop.testkit.driver.port.dtos.DeliverOrderRequest;
import com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.base.BaseBookShopUseCase;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseResult;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;
import com.jcupac.bookshop.testkit.dsl.core.shared.VoidVerification;

public class DeliverOrder extends BaseBookShopUseCase<Void, VoidVerification> {
    private String orderNumberResultAlias;

    public DeliverOrder(BookShopDriver driver, UseCaseContext context) {
        super(driver, context);
    }

    public DeliverOrder orderNumber(String orderNumberResultAlias) {
        this.orderNumberResultAlias = orderNumberResultAlias;
        return this;
    }

    @Override
    public UseCaseResult<Void, VoidVerification> execute() {
        var orderNumber = context.getResultValue(orderNumberResultAlias);
        var request = DeliverOrderRequest.builder().orderNumber(orderNumber).build();
        var result = driver.deliverOrder(request).mapVoid();
        return new UseCaseResult<>(result, context, VoidVerification::new);
    }
}
