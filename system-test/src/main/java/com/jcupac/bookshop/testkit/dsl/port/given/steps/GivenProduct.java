package com.jcupac.bookshop.testkit.dsl.port.given.steps;

import com.jcupac.bookshop.testkit.dsl.port.given.steps.base.GivenStep;

public interface GivenProduct extends GivenStep {
    GivenProduct withSku(String sku);

    GivenProduct withUnitPrice(String unitPrice);

    GivenProduct withUnitPrice(double unitPrice);
}
