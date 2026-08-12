package com.jcupac.bookshop.systemtest.legacy.mod10.acceptance;

import com.jcupac.bookshop.systemtest.legacy.mod10.acceptance.base.BaseAcceptanceTest;
import com.jcupac.bookshop.testkit.channel.ChannelType;
import com.jcupac.bookshop.testkit.domainvaluetypes.OrderStatus;
import com.optivem.testing.Channel;

import org.junit.jupiter.api.TestTemplate;

class PlaceOrderPositiveTest extends BaseAcceptanceTest {
    @TestTemplate
    @Channel({ChannelType.UI, ChannelType.API})
    void orderNumberShouldStartWithORD() {
        scenario
                .when().placeOrder()
                .then().shouldSucceed()
                .and().order()
                    .hasOrderNumberPrefix("ORD-");
    }

    @TestTemplate
    @Channel({ChannelType.UI, ChannelType.API})
    void orderStatusShouldBePlacedAfterPlacingOrder() {
        scenario
                .when().placeOrder()
                .then().shouldSucceed()
                .and().order()
                    .hasStatus(OrderStatus.PLACED);
    }
}
