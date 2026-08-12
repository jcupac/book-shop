package com.jcupac.bookshop.testkit.dsl.port.when.steps;

import com.jcupac.bookshop.testkit.dsl.port.when.steps.base.WhenStep;

public interface WhenViewOrder extends WhenStep {
    WhenViewOrder withOrderNumber(String orderNumber);
}

