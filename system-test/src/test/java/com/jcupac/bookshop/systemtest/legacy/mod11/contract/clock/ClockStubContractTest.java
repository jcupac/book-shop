package com.jcupac.bookshop.systemtest.legacy.mod11.contract.clock;

import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;

class ClockStubContractTest extends BaseClockContractTest {
    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.STUB;
    }
}
