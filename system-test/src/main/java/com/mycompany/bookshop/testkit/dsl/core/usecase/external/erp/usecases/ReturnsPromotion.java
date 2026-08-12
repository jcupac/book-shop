package com.jcupac.bookshop.testkit.dsl.core.usecase.external.erp.usecases;

import com.jcupac.bookshop.testkit.driver.port.external.erp.ErpDriver;
import com.jcupac.bookshop.testkit.driver.port.external.erp.dtos.ReturnsPromotionRequest;
import com.jcupac.bookshop.testkit.dsl.core.usecase.external.erp.usecases.base.BaseErpUseCase;
import com.jcupac.bookshop.testkit.common.Converter;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseResult;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;
import com.jcupac.bookshop.testkit.dsl.core.shared.VoidVerification;
import com.jcupac.bookshop.testkit.driver.port.dtos.error.SystemError;

public class ReturnsPromotion extends BaseErpUseCase<Void, VoidVerification> {
    private boolean promotionActive;
    private String discount;

    public ReturnsPromotion(ErpDriver driver, UseCaseContext context) {
        super(driver, context);
    }

    public ReturnsPromotion withActive(boolean promotionActive) {
        this.promotionActive = promotionActive;
        return this;
    }

    public ReturnsPromotion withDiscount(String discount) {
        this.discount = discount;
        return this;
    }

    public ReturnsPromotion withDiscount(double discount) {
        return withDiscount(Converter.fromDouble(discount));
    }

    @Override
    public UseCaseResult<Void, VoidVerification> execute() {
        var request = ReturnsPromotionRequest.builder()
                .promotionActive(promotionActive)
                .discount(discount)
                .build();

        var result = driver.returnsPromotion(request);

        return new UseCaseResult<>(result.mapError(e -> SystemError.of(e.getMessage())), context, VoidVerification::new);
    }
}
