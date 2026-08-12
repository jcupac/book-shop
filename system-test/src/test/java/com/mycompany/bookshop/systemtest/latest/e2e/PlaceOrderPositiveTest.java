package com.jcupac.bookshop.systemtest.latest.e2e;

import com.jcupac.bookshop.testkit.channel.ChannelType;
import com.jcupac.bookshop.systemtest.latest.e2e.base.BaseE2eTest;
import com.optivem.testing.Channel;
import org.junit.jupiter.api.TestTemplate;

class PlaceOrderPositiveTest extends BaseE2eTest {
    @TestTemplate
    @Channel({ChannelType.UI, ChannelType.API})
    void shouldPlaceOrder() {
        scenario
                .when().placeOrder()
                .then().shouldSucceed();
    }
}



