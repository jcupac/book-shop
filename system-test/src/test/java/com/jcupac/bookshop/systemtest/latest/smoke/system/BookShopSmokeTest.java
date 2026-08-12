package com.jcupac.bookshop.systemtest.latest.smoke.system;

import com.jcupac.bookshop.systemtest.latest.base.BaseScenarioDslTest;
import com.jcupac.bookshop.testkit.channel.ChannelType;
import com.optivem.testing.Channel;
import org.junit.jupiter.api.TestTemplate;

class BookShopSmokeTest extends BaseScenarioDslTest {
    @TestTemplate
    @Channel({ChannelType.UI, ChannelType.API})
    void shouldBeAbleToGoToBookShop() {
        scenario.assume().bookShop().shouldBeRunning();
    }
}

