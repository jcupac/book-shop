package com.jcupac.bookshop.testkit.dsl.core.usecase.external.tax.usecases;

import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseResult;
import com.jcupac.bookshop.testkit.dsl.core.shared.VoidVerification;
import com.jcupac.bookshop.testkit.driver.port.dtos.error.SystemError;
import com.jcupac.bookshop.testkit.dsl.core.usecase.external.tax.usecases.base.BaseTaxUseCase;
import com.jcupac.bookshop.testkit.driver.port.external.tax.TaxDriver;

public class GoToTax extends BaseTaxUseCase<Void, VoidVerification> {
    public GoToTax(TaxDriver driver, UseCaseContext context) {
        super(driver, context);
    }

    @Override
    public UseCaseResult<Void, VoidVerification> execute() {
        var result = driver.goToTax();
        return new UseCaseResult<>(result.mapError(e -> SystemError.of(e.getMessage())), context, VoidVerification::new);
    }
}
