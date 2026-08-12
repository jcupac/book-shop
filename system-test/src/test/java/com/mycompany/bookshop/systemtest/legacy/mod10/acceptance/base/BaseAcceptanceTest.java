package com.jcupac.bookshop.systemtest.legacy.mod10.acceptance.base;

import com.jcupac.bookshop.systemtest.legacy.mod10.base.BaseScenarioDslTest;
import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;

public abstract class BaseAcceptanceTest extends BaseScenarioDslTest {
    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.STUB;
    }
}



