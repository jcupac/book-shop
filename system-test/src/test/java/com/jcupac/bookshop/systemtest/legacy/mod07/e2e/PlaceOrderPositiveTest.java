package com.jcupac.bookshop.systemtest.legacy.mod07.e2e;

import com.jcupac.bookshop.testkit.channel.ChannelType;
import com.jcupac.bookshop.testkit.domainvaluetypes.OrderStatus;
import com.jcupac.bookshop.systemtest.legacy.mod07.e2e.base.BaseE2eTest;
import com.optivem.testing.Channel;
import org.junit.jupiter.api.TestTemplate;

import static com.jcupac.bookshop.systemtest.commons.constants.Defaults.*;

class PlaceOrderPositiveTest extends BaseE2eTest {
    @TestTemplate
    @Channel({ChannelType.UI, ChannelType.API})
    void shouldPlaceOrderForValidInput() {
        app.erp().returnsProduct().sku(SKU).unitPrice(20.00).execute()
                .shouldSucceed();

        app.bookShop().placeOrder().orderNumber(ORDER_NUMBER).sku(SKU).quantity(5).country(COUNTRY).execute()
                .shouldSucceed()
                .orderNumber(ORDER_NUMBER)
                .orderNumberStartsWith("ORD-");

        app.bookShop().viewOrder().orderNumber(ORDER_NUMBER).execute()
                .shouldSucceed()
                .orderNumber(ORDER_NUMBER)
                .sku(SKU)
                .quantity(5)
                .unitPrice(20.00)
                .status(OrderStatus.PLACED)
                .totalPriceGreaterThanZero();
    }
}
