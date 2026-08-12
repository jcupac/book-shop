package com.jcupac.bookshop.systemtest.legacy.mod11.contract.erp;

import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;

class ErpRealContractTest extends BaseErpContractTest {
    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.REAL;
    }
}



