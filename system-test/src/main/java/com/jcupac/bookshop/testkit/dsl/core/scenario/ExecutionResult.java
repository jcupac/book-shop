package com.jcupac.bookshop.testkit.dsl.core.scenario;

import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseResult;
import com.jcupac.bookshop.testkit.dsl.core.shared.ResponseVerification;
import lombok.Getter;

@Getter
public class ExecutionResult<R, V extends ResponseVerification<R>> {
    private final UseCaseResult<R, V> result;
    private final ExecutionResultContext context;

    ExecutionResult(UseCaseResult<R, V> result, String orderNumber, String couponCode) {
        if (result == null) {
            throw new IllegalArgumentException("Result cannot be null");
        }
        this.result = result;
        this.context = new ExecutionResultContext(orderNumber, couponCode);
    }
}
