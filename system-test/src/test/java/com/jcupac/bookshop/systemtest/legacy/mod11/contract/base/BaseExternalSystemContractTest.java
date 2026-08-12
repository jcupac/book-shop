package com.jcupac.bookshop.systemtest.legacy.mod11.contract.base;

import com.jcupac.bookshop.systemtest.legacy.mod11.base.BaseScenarioDslTest;
import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;
import com.jcupac.bookshop.testkit.dsl.core.ScenarioDslImpl;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseExternalSystemContractTest extends BaseScenarioDslTest {
    protected ScenarioDslImpl scenario;

    @BeforeEach
    void setUpScenario() {
        scenario = (ScenarioDslImpl) super.scenario;
    }

    @Override
    protected abstract ExternalSystemMode getFixedExternalSystemMode();
}

