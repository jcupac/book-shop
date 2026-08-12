package com.jcupac.bookshop.systemtest.latest.contract.tax;

import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;

public class TaxRealContractTest extends BaseTaxContractTest {
    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.REAL;
    }
}
