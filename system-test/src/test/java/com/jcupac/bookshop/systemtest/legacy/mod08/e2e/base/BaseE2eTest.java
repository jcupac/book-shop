package com.jcupac.bookshop.systemtest.legacy.mod08.e2e.base;

import com.jcupac.bookshop.systemtest.legacy.mod08.base.BaseScenarioDslTest;
import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;

public abstract class BaseE2eTest extends BaseScenarioDslTest {
    
    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.REAL;
    }
}




