package com.jcupac.bookshop.testkit.dsl.port.given.steps;

import com.jcupac.bookshop.testkit.dsl.port.given.steps.base.GivenStep;
import com.jcupac.bookshop.testkit.domainvaluetypes.OrderStatus;

public interface GivenOrder extends GivenStep {
    GivenOrder withOrderNumber(String orderNumber);

    GivenOrder withSku(String sku);

    GivenOrder withQuantity(String quantity);

    GivenOrder withQuantity(int quantity);

    GivenOrder withCountry(String country);

    GivenOrder withCouponCode(String couponCode);

    GivenOrder withStatus(String status);

    GivenOrder withStatus(OrderStatus status);
}
