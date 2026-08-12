package com.jcupac.bookshop.testkit.dsl.port.when.steps;

import com.jcupac.bookshop.testkit.dsl.port.when.steps.base.WhenStep;

public interface WhenCancelOrder extends WhenStep {
    WhenCancelOrder withOrderNumber(String orderNumber);
}
