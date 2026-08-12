package com.jcupac.bookshop.testkit.dsl.core.usecase.usecases;

import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseResult;
import com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.base.BaseBookShopUseCase;
import com.jcupac.bookshop.testkit.driver.port.BookShopDriver;
import com.jcupac.bookshop.testkit.driver.port.dtos.BrowseCouponsRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.BrowseCouponsResponse;

public class BrowseCoupons extends BaseBookShopUseCase<BrowseCouponsResponse, BrowseCouponsVerification> {
    public BrowseCoupons(BookShopDriver driver, UseCaseContext context) {
        super(driver, context);
    }

    @Override
    public UseCaseResult<BrowseCouponsResponse, BrowseCouponsVerification> execute() {
        var request = BrowseCouponsRequest.builder().build();
        var result = driver.browseCoupons(request);
        return new UseCaseResult<>(result, context, BrowseCouponsVerification::new);
    }
}
