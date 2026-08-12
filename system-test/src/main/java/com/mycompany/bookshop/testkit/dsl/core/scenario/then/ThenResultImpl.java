package com.jcupac.bookshop.testkit.dsl.core.scenario.then;

import com.jcupac.bookshop.testkit.dsl.core.shared.ResponseVerification;
import com.jcupac.bookshop.testkit.dsl.core.usecase.UseCaseDsl;
import com.jcupac.bookshop.testkit.dsl.core.scenario.ExecutionResult;
import com.jcupac.bookshop.testkit.dsl.core.scenario.then.steps.ThenFailureImpl;
import com.jcupac.bookshop.testkit.dsl.core.scenario.then.steps.ThenSuccessImpl;
import com.jcupac.bookshop.testkit.dsl.port.then.ThenResultStage;

public class ThenResultImpl<R, V extends ResponseVerification<R>> extends ThenImpl implements ThenResultStage {
    private final ExecutionResult<R, V> executionResult;

    public ThenResultImpl(UseCaseDsl app, ExecutionResult<R, V> executionResult) {
        super(app);
        this.executionResult = executionResult;
    }

    @Override
    public ThenSuccessImpl<R, V> shouldSucceed() {
        var successVerification = executionResult.getResult().shouldSucceed();
        return new ThenSuccessImpl<>(app, executionResult.getContext(), successVerification);
    }

    @Override
    public ThenFailureImpl<R, V> shouldFail() {
        return new ThenFailureImpl<>(app, executionResult.getContext(), executionResult.getResult());
    }
}


