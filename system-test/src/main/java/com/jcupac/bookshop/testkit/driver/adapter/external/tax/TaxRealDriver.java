package com.jcupac.bookshop.testkit.driver.adapter.external.tax;

import com.jcupac.bookshop.testkit.driver.adapter.external.tax.client.TaxRealClient;
import com.jcupac.bookshop.testkit.driver.port.external.tax.dtos.ReturnsTaxRateRequest;
import com.jcupac.bookshop.testkit.driver.port.external.tax.dtos.error.TaxErrorResponse;
import com.jcupac.bookshop.testkit.common.Result;

public class TaxRealDriver extends BaseTaxDriver<TaxRealClient> {
    public TaxRealDriver(String baseUrl) {
        super(new TaxRealClient(baseUrl));
    }

    @Override
    public Result<Void, TaxErrorResponse> returnsTaxRate(ReturnsTaxRateRequest request) {
        return Result.success();
    }
}
