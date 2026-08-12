package com.jcupac.bookshop.systemtest.legacy.mod08.e2e;

import com.jcupac.bookshop.testkit.channel.ChannelType;
import com.jcupac.bookshop.systemtest.legacy.mod08.e2e.base.BaseE2eTest;
import com.optivem.testing.Channel;
import org.junit.jupiter.api.TestTemplate;

class PlaceOrderNegativeTest extends BaseE2eTest {
    @TestTemplate
    @Channel({ChannelType.UI, ChannelType.API})
    void shouldRejectOrderWithNonIntegerQuantity() {
        scenario
                .when().placeOrder().withQuantity("3.5")
                .then().shouldFail()
                .errorMessage("The request contains one or more validation errors")
                .fieldErrorMessage("quantity", "Quantity must be an integer");
    }
}
