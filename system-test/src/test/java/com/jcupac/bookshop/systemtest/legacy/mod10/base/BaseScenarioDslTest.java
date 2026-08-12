package com.jcupac.bookshop.systemtest.legacy.mod10.base;

import com.jcupac.bookshop.systemtest.configuration.BaseConfigurableTest;
import com.jcupac.bookshop.testkit.dsl.core.usecase.UseCaseDsl;
import com.jcupac.bookshop.testkit.dsl.core.ScenarioDslImpl;
import com.jcupac.bookshop.testkit.dsl.port.ScenarioDsl;
import com.jcupac.bookshop.testkit.common.Closer;
import com.jcupac.bookshop.systemtest.infrastructure.playwright.BrowserLifecycleExtension;
import com.optivem.testing.extensions.ChannelExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({ChannelExtension.class, BrowserLifecycleExtension.class})
public abstract class BaseScenarioDslTest extends BaseConfigurableTest {
    private UseCaseDsl app;
    protected ScenarioDsl scenario;

    @BeforeEach
    void setUp() {
        var configuration = loadConfiguration();
        app = createUseCaseDsl(configuration);
        scenario = new ScenarioDslImpl(app);
    }

    @AfterEach
    void tearDown() {
        Closer.close(app);
    }
}

