package com.jcupac.bookshop.testkit.dsl.core.usecase.external.tax.usecases.base;

import com.jcupac.bookshop.testkit.dsl.core.shared.BaseUseCase;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;
import com.jcupac.bookshop.testkit.driver.port.external.tax.TaxDriver;

public abstract class BaseTaxUseCase<R, V> extends BaseUseCase<TaxDriver, R, V> {
    protected BaseTaxUseCase(TaxDriver driver, UseCaseContext context) {
        super(driver, context);
    }
}
