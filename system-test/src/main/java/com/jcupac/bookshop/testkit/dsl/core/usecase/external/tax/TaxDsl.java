package com.jcupac.bookshop.testkit.dsl.core.usecase.external.tax;

import com.jcupac.bookshop.testkit.common.Closer;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;
import com.jcupac.bookshop.testkit.dsl.core.usecase.external.tax.usecases.GetTaxRate;
import com.jcupac.bookshop.testkit.dsl.core.usecase.external.tax.usecases.GoToTax;
import com.jcupac.bookshop.testkit.dsl.core.usecase.external.tax.usecases.ReturnsTaxRate;
import com.jcupac.bookshop.testkit.driver.port.external.tax.TaxDriver;

public class TaxDsl implements AutoCloseable {
    private final TaxDriver driver;
    private final UseCaseContext context;

    public TaxDsl(TaxDriver driver, UseCaseContext context) {
        this.driver = driver;
        this.context = context;
    }

    @Override
    public void close() {
        Closer.close(driver);
    }

    public GoToTax goToTax() {
        return new GoToTax(driver, context);
    }

    public ReturnsTaxRate returnsTaxRate() {
        return new ReturnsTaxRate(driver, context);
    }

    public GetTaxRate getTaxRate() {
        return new GetTaxRate(driver, context);
    }
}
