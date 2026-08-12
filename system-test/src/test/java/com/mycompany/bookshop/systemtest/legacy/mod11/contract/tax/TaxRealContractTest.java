package com.jcupac.bookshop.systemtest.legacy.mod11.contract.tax;

import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;

class TaxRealContractTest extends BaseTaxContractTest {
    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.REAL;
    }
}
