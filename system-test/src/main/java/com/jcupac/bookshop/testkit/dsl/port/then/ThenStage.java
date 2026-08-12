package com.jcupac.bookshop.testkit.dsl.port.then;

import com.jcupac.bookshop.testkit.dsl.port.then.steps.ThenClock;
import com.jcupac.bookshop.testkit.dsl.port.then.steps.ThenCountry;
import com.jcupac.bookshop.testkit.dsl.port.then.steps.ThenProduct;

public interface ThenStage {
    ThenClock clock();

    ThenProduct product(String skuAlias);

    ThenCountry country(String countryAlias);
}
