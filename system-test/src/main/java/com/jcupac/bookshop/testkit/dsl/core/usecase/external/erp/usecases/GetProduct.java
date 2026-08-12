package com.jcupac.bookshop.testkit.dsl.core.usecase.external.erp.usecases;

import com.jcupac.bookshop.testkit.driver.port.external.erp.ErpDriver;
import com.jcupac.bookshop.testkit.driver.port.external.erp.dtos.GetProductRequest;
import com.jcupac.bookshop.testkit.driver.port.external.erp.dtos.GetProductResponse;
import com.jcupac.bookshop.testkit.dsl.core.usecase.external.erp.usecases.base.BaseErpUseCase;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseResult;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;
import com.jcupac.bookshop.testkit.driver.port.dtos.error.SystemError;

public class GetProduct extends BaseErpUseCase<GetProductResponse, GetProductVerification> {
    private String skuParamAlias;

    public GetProduct(ErpDriver driver, UseCaseContext context) {
        super(driver, context);
    }

    public GetProduct sku(String skuParamAlias) {
        this.skuParamAlias = skuParamAlias;
        return this;
    }

    @Override
    public UseCaseResult<GetProductResponse, GetProductVerification> execute() {
        var sku = context.getParamValue(skuParamAlias);

        var request = GetProductRequest.builder()
                .sku(sku)
                .build();

        var result = driver.getProduct(request);

        return new UseCaseResult<>(result.mapError(e -> SystemError.of(e.getMessage())), context, GetProductVerification::new);
    }
}
