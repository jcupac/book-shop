package com.jcupac.bookshop.testkit.dsl.port.when;

import com.jcupac.bookshop.testkit.dsl.port.when.steps.WhenBrowseCoupons;
import com.jcupac.bookshop.testkit.dsl.port.when.steps.WhenCancelOrder;
import com.jcupac.bookshop.testkit.dsl.port.when.steps.WhenPlaceOrder;
import com.jcupac.bookshop.testkit.dsl.port.when.steps.WhenPublishCoupon;
import com.jcupac.bookshop.testkit.dsl.port.when.steps.WhenViewOrder;

public interface WhenStage {
    WhenPlaceOrder placeOrder();

    WhenCancelOrder cancelOrder();

    WhenViewOrder viewOrder();

    WhenPublishCoupon publishCoupon();

    WhenBrowseCoupons browseCoupons();
}
