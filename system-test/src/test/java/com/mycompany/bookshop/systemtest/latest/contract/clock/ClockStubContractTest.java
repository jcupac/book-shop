package com.jcupac.bookshop.systemtest.latest.contract.clock;

import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;

class ClockStubContractTest extends BaseClockContractTest {
    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.STUB;
    }
}
