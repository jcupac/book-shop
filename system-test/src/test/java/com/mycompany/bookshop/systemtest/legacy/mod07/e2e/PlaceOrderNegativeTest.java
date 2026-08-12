package com.jcupac.bookshop.systemtest.legacy.mod07.e2e;

import com.jcupac.bookshop.testkit.channel.ChannelType;
import com.jcupac.bookshop.systemtest.legacy.mod07.e2e.base.BaseE2eTest;
import com.optivem.testing.Channel;
import org.junit.jupiter.api.TestTemplate;

import static com.jcupac.bookshop.systemtest.commons.constants.Defaults.*;

class PlaceOrderNegativeTest extends BaseE2eTest {
    @TestTemplate
    @Channel({ChannelType.UI, ChannelType.API})
    void shouldRejectOrderWithNonIntegerQuantity() {
        app.bookShop().placeOrder().sku(SKU).quantity("3.5").country(COUNTRY).execute()
                .shouldFail()
                .errorMessage("The request contains one or more validation errors")
                .fieldErrorMessage("quantity", "Quantity must be an integer");
    }
}
