package com.jcupac.bookshop.systemtest.latest.acceptance;

import com.jcupac.bookshop.systemtest.latest.acceptance.base.BaseAcceptanceTest;
import com.jcupac.bookshop.testkit.channel.ChannelType;
import com.optivem.testing.Channel;
import org.junit.jupiter.api.TestTemplate;

class ViewOrderPositiveTest extends BaseAcceptanceTest {
    @TestTemplate
    @Channel({ChannelType.UI, ChannelType.API})
    void shouldBeAbleToViewOrder() {
        scenario
                .given().order()
                .when().viewOrder()
                .then().shouldSucceed();
    }
}
