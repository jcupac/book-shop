package com.jcupac.bookshop.testkit.dsl.core.shared;

import com.jcupac.bookshop.testkit.driver.port.dtos.error.SystemError;
import com.jcupac.bookshop.testkit.common.Result;

import java.util.function.BiFunction;

import static com.jcupac.bookshop.testkit.common.ResultAssert.assertThatResult;

public class UseCaseResult<R, V> {
    private final Result<R, SystemError> result;
    private final UseCaseContext context;
    private final BiFunction<R, UseCaseContext, V> successVerificationFactory;

    public UseCaseResult(
            Result<R, SystemError> result,
            UseCaseContext context,
            BiFunction<R, UseCaseContext, V> successVerificationFactory) {
        this.result = result;
        this.context = context;
        this.successVerificationFactory = successVerificationFactory;
    }

    public V shouldSucceed() {
        assertThatResult(result).isSuccess();
        return successVerificationFactory.apply(result.getValue(), context);
    }

    public ErrorVerification shouldFail() {
        assertThatResult(result).isFailure();
        return new ErrorVerification(result.getError(), context);
    }
}
