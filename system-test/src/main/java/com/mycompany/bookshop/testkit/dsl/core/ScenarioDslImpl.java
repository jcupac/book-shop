package com.jcupac.bookshop.testkit.dsl.core;

import com.jcupac.bookshop.testkit.dsl.core.usecase.UseCaseDsl;
import com.jcupac.bookshop.testkit.dsl.core.scenario.assume.AssumeImpl;
import com.jcupac.bookshop.testkit.dsl.port.ScenarioDsl;
import com.jcupac.bookshop.testkit.dsl.core.scenario.given.GivenImpl;
import com.jcupac.bookshop.testkit.dsl.core.scenario.when.WhenImpl;

public class ScenarioDslImpl implements ScenarioDsl {
    private final UseCaseDsl app;
    private boolean executed = false;

    public ScenarioDslImpl(UseCaseDsl app) {
        this.app = app;
    }

    public AssumeImpl assume() {
        return new AssumeImpl(app);
    }

    public GivenImpl given() {
        ensureNotExecuted();
        return new GivenImpl(app, this);
    }

    public WhenImpl when() {
        ensureNotExecuted();
        return new WhenImpl(app, this);
    }

    public void markAsExecuted() {
        this.executed = true;
    }

    private void ensureNotExecuted() {
        if (executed) {
            throw new IllegalStateException("Scenario has already been executed. " +
                    "Each test method should contain only ONE scenario execution (Given-When-Then). " +
                    "Split multiple scenarios into separate test methods.");
        }
    }
}

