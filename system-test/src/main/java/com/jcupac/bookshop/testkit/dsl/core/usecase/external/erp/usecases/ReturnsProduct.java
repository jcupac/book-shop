package com.jcupac.bookshop.testkit.dsl.core.usecase.external.erp.usecases;

import com.jcupac.bookshop.testkit.driver.port.external.erp.ErpDriver;
import com.jcupac.bookshop.testkit.driver.port.external.erp.dtos.ReturnsProductRequest;
import com.jcupac.bookshop.testkit.dsl.core.usecase.external.erp.usecases.base.BaseErpUseCase;
import com.jcupac.bookshop.testkit.common.Converter;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseResult;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;
import com.jcupac.bookshop.testkit.dsl.core.shared.VoidVerification;
import com.jcupac.bookshop.testkit.driver.port.dtos.error.SystemError;

public class ReturnsProduct extends BaseErpUseCase<Void, VoidVerification> {
    private String skuParamAlias;
    private String unitPrice;

    public ReturnsProduct(ErpDriver driver, UseCaseContext context) {
        super(driver, context);
    }

    public ReturnsProduct sku(String skuParamAlias) {
        this.skuParamAlias = skuParamAlias;
        return this;
    }

    public ReturnsProduct unitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public ReturnsProduct unitPrice(double unitPrice) {
        return unitPrice(Converter.fromDouble(unitPrice));
    }

    @Override
    public UseCaseResult<Void, VoidVerification> execute() {
        var sku = context.getParamValue(skuParamAlias);

        var request = ReturnsProductRequest.builder()
                .sku(sku)
                .price(unitPrice)
                .build();

        var result = driver.returnsProduct(request);

        return new UseCaseResult<>(result.mapError(e -> SystemError.of(e.getMessage())), context, VoidVerification::new);
    }
}
