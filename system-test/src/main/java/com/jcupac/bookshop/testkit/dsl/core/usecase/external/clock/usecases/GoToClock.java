package com.jcupac.bookshop.testkit.dsl.core.usecase.external.clock.usecases;

import com.jcupac.bookshop.testkit.driver.port.external.clock.ClockDriver;
import com.jcupac.bookshop.testkit.dsl.core.usecase.external.clock.usecases.base.BaseClockUseCase;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseResult;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;
import com.jcupac.bookshop.testkit.dsl.core.shared.VoidVerification;
import com.jcupac.bookshop.testkit.driver.port.dtos.error.SystemError;

public class GoToClock extends BaseClockUseCase<Void, VoidVerification> {
    public GoToClock(ClockDriver clockDriver, UseCaseContext useCaseContext) {
        super(clockDriver, useCaseContext);
    }

    @Override
    public UseCaseResult<Void, VoidVerification> execute() {
        var result = driver.goToClock();
        return new UseCaseResult<>(result.mapError(e -> SystemError.of(e.getMessage())), context, VoidVerification::new);
    }
}
