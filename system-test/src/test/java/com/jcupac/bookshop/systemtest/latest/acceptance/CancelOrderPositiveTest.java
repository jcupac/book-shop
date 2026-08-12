package com.jcupac.bookshop.systemtest.latest.acceptance;

import com.jcupac.bookshop.systemtest.latest.acceptance.base.BaseAcceptanceTest;
import com.jcupac.bookshop.testkit.channel.ChannelType;
import com.jcupac.bookshop.testkit.domainvaluetypes.OrderStatus;
import com.optivem.testing.Channel;
import org.junit.jupiter.api.TestTemplate;

class CancelOrderPositiveTest extends BaseAcceptanceTest {
    @TestTemplate
    @Channel({ChannelType.UI, ChannelType.API})
    void shouldHaveCancelledStatusWhenCancelled() {
        scenario
                .given().order()
                .when().cancelOrder()
                .then().shouldSucceed()
                .and().order()
                    .hasStatus(OrderStatus.CANCELLED);
    }
}
