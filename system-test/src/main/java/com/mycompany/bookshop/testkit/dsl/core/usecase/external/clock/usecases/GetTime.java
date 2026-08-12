package com.jcupac.bookshop.testkit.dsl.core.usecase.external.clock.usecases;

import com.jcupac.bookshop.testkit.driver.port.external.clock.ClockDriver;
import com.jcupac.bookshop.testkit.driver.port.external.clock.dtos.GetTimeResponse;
import com.jcupac.bookshop.testkit.dsl.core.usecase.external.clock.usecases.base.BaseClockUseCase;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseResult;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;
import com.jcupac.bookshop.testkit.driver.port.dtos.error.SystemError;

public class GetTime extends BaseClockUseCase<GetTimeResponse, GetTimeVerification> {
    public GetTime(ClockDriver driver, UseCaseContext context) {
        super(driver, context);
    }

    @Override
    public UseCaseResult<GetTimeResponse, GetTimeVerification> execute() {
        var result = driver.getTime();
        return new UseCaseResult<>(result.mapError(e -> SystemError.of(e.getMessage())), context, GetTimeVerification::new);
    }
}
