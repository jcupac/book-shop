package com.jcupac.bookshop.testkit.dsl.port.given;

import com.jcupac.bookshop.testkit.dsl.port.given.steps.GivenClock;
import com.jcupac.bookshop.testkit.dsl.port.given.steps.GivenCoupon;
import com.jcupac.bookshop.testkit.dsl.port.given.steps.GivenCountry;
import com.jcupac.bookshop.testkit.dsl.port.given.steps.GivenOrder;
import com.jcupac.bookshop.testkit.dsl.port.given.steps.GivenProduct;
import com.jcupac.bookshop.testkit.dsl.port.given.steps.GivenPromotion;
import com.jcupac.bookshop.testkit.dsl.port.then.ThenStage;
import com.jcupac.bookshop.testkit.dsl.port.when.WhenStage;

public interface GivenStage {
    GivenClock clock();

    GivenProduct product();

    GivenPromotion promotion();

    GivenOrder order();

    GivenCountry country();

    GivenCoupon coupon();

    WhenStage when();

    ThenStage then();
}
