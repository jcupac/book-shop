package com.jcupac.bookshop.testkit.dsl.port.given.steps;

import com.jcupac.bookshop.testkit.dsl.port.given.steps.base.GivenStep;

public interface GivenPromotion extends GivenStep {
    GivenPromotion withActive(boolean promotionActive);
    GivenPromotion withDiscount(double discount);
    GivenPromotion withDiscount(String discount);
}
