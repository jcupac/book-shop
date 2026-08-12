package com.jcupac.bookshop.testkit.dsl.core.scenario.given.steps;

import com.jcupac.bookshop.testkit.common.Converter;
import com.jcupac.bookshop.testkit.dsl.core.scenario.given.GivenImpl;
import com.jcupac.bookshop.testkit.dsl.core.usecase.UseCaseDsl;
import com.jcupac.bookshop.testkit.dsl.port.given.steps.GivenCountry;

import static com.jcupac.bookshop.testkit.dsl.core.scenario.ScenarioDefaults.*;

public class GivenCountryImpl extends BaseGivenStep implements GivenCountry {
    private String country;
    private String taxRate;

    public GivenCountryImpl(GivenImpl given) {
        super(given);
        withCode(DEFAULT_COUNTRY);
        withTaxRate(DEFAULT_TAX_RATE);
    }

    @Override
    public GivenCountryImpl withCode(String country) {
        this.country = country;
        return this;
    }

    @Override
    public GivenCountryImpl withTaxRate(double taxRate) {
        return withTaxRate(Converter.fromDouble(taxRate));
    }

    @Override
    public GivenCountryImpl withTaxRate(String taxRate) {
        this.taxRate = taxRate;
        return this;
    }

    @Override
    public void execute(UseCaseDsl app) {
        app.tax().returnsTaxRate()
                .country(country)
                .taxRate(taxRate)
                .execute()
                .shouldSucceed();
    }
}
