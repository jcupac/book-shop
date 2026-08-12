package com.jcupac.bookshop.testkit.dsl.core.usecase.external.tax.usecases;

import com.jcupac.bookshop.testkit.common.Converter;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseResult;
import com.jcupac.bookshop.testkit.dsl.core.shared.VoidVerification;
import com.jcupac.bookshop.testkit.driver.port.dtos.error.SystemError;
import com.jcupac.bookshop.testkit.dsl.core.usecase.external.tax.usecases.base.BaseTaxUseCase;
import com.jcupac.bookshop.testkit.driver.port.external.tax.TaxDriver;
import com.jcupac.bookshop.testkit.driver.port.external.tax.dtos.ReturnsTaxRateRequest;

public class ReturnsTaxRate extends BaseTaxUseCase<Void, VoidVerification> {
    private String countryAlias;
    private String taxRate;

    public ReturnsTaxRate(TaxDriver driver, UseCaseContext context) {
        super(driver, context);
    }

    public ReturnsTaxRate country(String countryAlias) {
        this.countryAlias = countryAlias;
        return this;
    }

    public ReturnsTaxRate taxRate(String taxRate) {
        this.taxRate = taxRate;
        return this;
    }

    public ReturnsTaxRate taxRate(double taxRate) {
        return taxRate(Converter.fromDouble(taxRate));
    }

    @Override
    public UseCaseResult<Void, VoidVerification> execute() {
        var country = context.getParamValueOrLiteral(countryAlias);

        var request = ReturnsTaxRateRequest.builder()
                .country(country)
                .taxRate(taxRate)
                .build();

        var result = driver.returnsTaxRate(request);

        return new UseCaseResult<>(result.mapError(e -> SystemError.of(e.getMessage())), context, VoidVerification::new);
    }
}
