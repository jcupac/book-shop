package com.jcupac.bookshop.testkit.dsl.core.scenario.when.steps;

import com.jcupac.bookshop.testkit.dsl.core.ScenarioDslImpl;
import com.jcupac.bookshop.testkit.dsl.core.shared.ResponseVerification;
import com.jcupac.bookshop.testkit.dsl.core.usecase.UseCaseDsl;
import com.jcupac.bookshop.testkit.dsl.core.scenario.ExecutionResult;
import com.jcupac.bookshop.testkit.dsl.core.scenario.then.ThenResultImpl;

public abstract class BaseWhenStep<R, V extends ResponseVerification<R>> {
    private final UseCaseDsl app;
    private final ScenarioDslImpl scenario;

    protected BaseWhenStep(UseCaseDsl app, ScenarioDslImpl scenario) {
        this.app = app;
        this.scenario = scenario;
    }
    public ThenResultImpl<R, V> then() {
        scenario.markAsExecuted();
        var result = execute(app);
        return new ThenResultImpl<>(app, result);
    }

    protected abstract ExecutionResult<R, V> execute(UseCaseDsl app);
}



