package com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.base;

import com.jcupac.bookshop.testkit.driver.port.BookShopDriver;
import com.jcupac.bookshop.testkit.dsl.core.shared.BaseUseCase;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;

public abstract class BaseBookShopUseCase<R, V> extends BaseUseCase<BookShopDriver, R, V> {
    protected BaseBookShopUseCase(BookShopDriver driver, UseCaseContext context) {
        super(driver, context);
    }
}



