package com.jcupac.bookshop.testkit.dsl.core.scenario.when.steps;

import static com.jcupac.bookshop.testkit.dsl.core.scenario.ScenarioDefaults.*;

import com.jcupac.bookshop.testkit.common.Converter;
import com.jcupac.bookshop.testkit.dsl.core.ScenarioDslImpl;
import com.jcupac.bookshop.testkit.dsl.core.usecase.UseCaseDsl;
import com.jcupac.bookshop.testkit.dsl.core.scenario.ExecutionResult;
import com.jcupac.bookshop.testkit.dsl.core.scenario.ExecutionResultBuilder;
import com.jcupac.bookshop.testkit.driver.port.dtos.PlaceOrderResponse;
import com.jcupac.bookshop.testkit.dsl.port.ChannelMode;
import com.jcupac.bookshop.testkit.dsl.port.when.steps.WhenPlaceOrder;
import com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.PlaceOrderVerification;

public class WhenPlaceOrderImpl extends BaseWhenStep<PlaceOrderResponse, PlaceOrderVerification> implements WhenPlaceOrder {
    private String orderNumber;
    private String sku;
    private String quantity;
    private String country;
    private String couponCode;

    public WhenPlaceOrderImpl(UseCaseDsl app, ScenarioDslImpl scenario) {
        super(app, scenario);
        withOrderNumber(DEFAULT_ORDER_NUMBER);
        withSku(DEFAULT_SKU);
        withQuantity(DEFAULT_QUANTITY);
        withCountry(DEFAULT_COUNTRY);
        withCouponCode(EMPTY);
    }

    public WhenPlaceOrderImpl withOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public WhenPlaceOrderImpl withSku(String sku) {
        this.sku = sku;
        return this;
    }

    public WhenPlaceOrderImpl withQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public WhenPlaceOrderImpl withQuantity(int quantity) {
        return withQuantity(Converter.fromInteger(quantity));
    }

    public WhenPlaceOrderImpl withCountry(String country) {
        this.country = country;
        return this;
    }

    public WhenPlaceOrderImpl withCouponCode(String couponCode) {
        this.couponCode = couponCode;
        return this;
    }

    public WhenPlaceOrderImpl withCouponCode() {
        return withCouponCode(DEFAULT_COUPON_CODE);
    }

    @Override
    protected ExecutionResult<PlaceOrderResponse, PlaceOrderVerification> execute(UseCaseDsl app) {
        var result = app.bookShop(ChannelMode.DYNAMIC).placeOrder()
                .orderNumber(orderNumber)
                .sku(sku)
                .quantity(quantity)
                .country(country)
                .couponCode(couponCode)
                .execute();

        return new ExecutionResultBuilder<>(result)
                .orderNumber(orderNumber)
                .couponCode(couponCode)
                .build();
    }
}
