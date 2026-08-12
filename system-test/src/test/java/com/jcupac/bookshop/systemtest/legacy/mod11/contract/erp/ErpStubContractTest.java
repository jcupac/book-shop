package com.jcupac.bookshop.systemtest.legacy.mod11.contract.erp;

import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;

class ErpStubContractTest extends BaseErpContractTest {
    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.STUB;
    }
}
