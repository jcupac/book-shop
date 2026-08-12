package com.jcupac.bookshop.testkit.dsl.core.scenario.when.steps;

import static com.jcupac.bookshop.testkit.dsl.core.scenario.ScenarioDefaults.DEFAULT_ORDER_NUMBER;

import com.jcupac.bookshop.testkit.dsl.core.ScenarioDslImpl;
import com.jcupac.bookshop.testkit.dsl.core.usecase.UseCaseDsl;
import com.jcupac.bookshop.testkit.dsl.core.scenario.ExecutionResult;
import com.jcupac.bookshop.testkit.dsl.core.scenario.ExecutionResultBuilder;
import com.jcupac.bookshop.testkit.driver.port.dtos.ViewOrderResponse;
import com.jcupac.bookshop.testkit.dsl.port.ChannelMode;
import com.jcupac.bookshop.testkit.dsl.port.when.steps.WhenViewOrder;
import com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.ViewOrderVerification;

public class WhenViewOrderImpl extends BaseWhenStep<ViewOrderResponse, ViewOrderVerification> implements WhenViewOrder {
    private String orderNumber;

    public WhenViewOrderImpl(UseCaseDsl app, ScenarioDslImpl scenario) {
        super(app, scenario);
        withOrderNumber(DEFAULT_ORDER_NUMBER);
    }

    public WhenViewOrderImpl withOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    @Override
    protected ExecutionResult<ViewOrderResponse, ViewOrderVerification> execute(UseCaseDsl app) {
        var result = app.bookShop(ChannelMode.DYNAMIC).viewOrder()
                .orderNumber(orderNumber)
                .execute();

        return new ExecutionResultBuilder<>(result)
                .orderNumber(orderNumber)
                .build();
    }
}
