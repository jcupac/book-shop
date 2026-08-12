package com.jcupac.bookshop.testkit.driver.adapter.external.tax;

import com.jcupac.bookshop.testkit.driver.adapter.external.tax.client.TaxStubClient;
import com.jcupac.bookshop.testkit.driver.adapter.external.tax.client.dtos.ExtGetCountryResponse;
import com.jcupac.bookshop.testkit.driver.port.external.tax.dtos.ReturnsTaxRateRequest;
import com.jcupac.bookshop.testkit.driver.port.external.tax.dtos.error.TaxErrorResponse;
import com.jcupac.bookshop.testkit.common.Converter;
import com.jcupac.bookshop.testkit.common.Result;

public class TaxStubDriver extends BaseTaxDriver<TaxStubClient> {
    public TaxStubDriver(String baseUrl) {
        super(new TaxStubClient(baseUrl));
    }

    @Override
    public void close() {
        client.removeStubs();
        super.close();
    }

    @Override
    public Result<Void, TaxErrorResponse> returnsTaxRate(ReturnsTaxRateRequest request) {
        var country = request.getCountry();
        var taxRate = Converter.toBigDecimal(request.getTaxRate());

        var response = ExtGetCountryResponse.builder()
                .id(country)
                .taxRate(taxRate)
                .countryName(country)
                .build();

        return client.configureGetCountry(country, response)
                .mapError(ext -> new TaxErrorResponse(ext.getMessage()));
    }
}
