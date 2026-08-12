package com.jcupac.bookshop.systemtest.legacy.mod11.e2e.base;

import com.jcupac.bookshop.systemtest.legacy.mod11.base.BaseScenarioDslTest;
import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;

public abstract class BaseE2eTest extends BaseScenarioDslTest {
    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.REAL;
    }
}




