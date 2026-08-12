package com.jcupac.bookshop.testkit.dsl.core.usecase.external.erp.usecases.base;

import com.jcupac.bookshop.testkit.driver.port.external.erp.ErpDriver;
import com.jcupac.bookshop.testkit.dsl.core.shared.BaseUseCase;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;

public abstract class BaseErpUseCase<R, V> extends BaseUseCase<ErpDriver, R, V> {
    protected BaseErpUseCase(ErpDriver driver, UseCaseContext context) {
        super(driver, context);
    }
}
