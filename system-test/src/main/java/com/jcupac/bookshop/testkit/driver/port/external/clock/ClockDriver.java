package com.jcupac.bookshop.testkit.driver.port.external.clock;

import com.jcupac.bookshop.testkit.driver.port.external.clock.dtos.GetTimeResponse;
import com.jcupac.bookshop.testkit.driver.port.external.clock.dtos.ReturnsTimeRequest;
import com.jcupac.bookshop.testkit.driver.port.external.clock.dtos.error.ClockErrorResponse;
import com.jcupac.bookshop.testkit.common.Result;

public interface ClockDriver extends AutoCloseable {
    Result<Void, ClockErrorResponse> goToClock();
    Result<GetTimeResponse, ClockErrorResponse> getTime();
    Result<Void, ClockErrorResponse> returnsTime(ReturnsTimeRequest request);
}
