package com.jcupac.bookshop.testkit.dsl.core.usecase.usecases;

import com.jcupac.bookshop.testkit.driver.port.BookShopDriver;
import com.jcupac.bookshop.testkit.driver.port.dtos.GoToBookShopRequest;
import com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.base.BaseBookShopUseCase;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseResult;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;
import com.jcupac.bookshop.testkit.dsl.core.shared.VoidVerification;

public class GoToBookShop extends BaseBookShopUseCase<Void, VoidVerification> {
    public GoToBookShop(BookShopDriver driver, UseCaseContext context) {
        super(driver, context);
    }

    @Override
    public UseCaseResult<Void, VoidVerification> execute() {
        var request = GoToBookShopRequest.builder().build();
        var result = driver.goToBookShop(request).mapVoid();
        return new UseCaseResult<>(result, context, VoidVerification::new);
    }
}



