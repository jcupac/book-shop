package com.jcupac.bookshop.systemtest.legacy.mod09.smoke.system;

import com.jcupac.bookshop.systemtest.legacy.mod09.base.BaseScenarioDslTest;
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

