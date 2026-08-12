package com.jcupac.bookshop.systemtest.latest.smoke.external;

import com.jcupac.bookshop.systemtest.latest.base.BaseScenarioDslTest;
import org.junit.jupiter.api.Test;

class ClockSmokeTest extends BaseScenarioDslTest {
    @Test
    void shouldBeAbleToGoToClock() {
        scenario.assume().clock().shouldBeRunning();
    }
}


