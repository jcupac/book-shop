package com.jcupac.bookshop.testkit.dsl.port.given.steps;

import com.jcupac.bookshop.testkit.dsl.port.given.steps.base.GivenStep;

public interface GivenClock extends GivenStep {
    GivenClock withTime();
    GivenClock withTime(String time);
    GivenClock withWeekday();
    GivenClock withWeekend();
}

