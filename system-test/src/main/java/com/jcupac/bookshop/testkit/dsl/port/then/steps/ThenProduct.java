package com.jcupac.bookshop.testkit.dsl.port.then.steps;

import com.jcupac.bookshop.testkit.dsl.port.then.steps.base.ThenStep;

public interface ThenProduct extends ThenStep<ThenProduct> {
    ThenProduct hasSku(String sku);

    ThenProduct hasPrice(double price);

    ThenProduct hasPrice(String price);
}
