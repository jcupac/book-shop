package com.jcupac.bookshop.testkit.dsl.core.usecase.external.erp.usecases;

import com.jcupac.bookshop.testkit.driver.port.external.erp.ErpDriver;
import com.jcupac.bookshop.testkit.dsl.core.usecase.external.erp.usecases.base.BaseErpUseCase;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseResult;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;
import com.jcupac.bookshop.testkit.dsl.core.shared.VoidVerification;
import com.jcupac.bookshop.testkit.driver.port.dtos.error.SystemError;

public class GoToErp extends BaseErpUseCase<Void, VoidVerification> {
    public GoToErp(ErpDriver driver, UseCaseContext context) {
        super(driver, context);
    }

    @Override
    public UseCaseResult<Void, VoidVerification> execute() {
        var result = driver.goToErp();
        return new UseCaseResult<>(result.mapError(e -> SystemError.of(e.getMessage())), context, VoidVerification::new);
    }
}
