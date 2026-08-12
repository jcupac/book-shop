package com.jcupac.bookshop.systemtest.legacy.mod07.smoke.system;

import com.jcupac.bookshop.systemtest.legacy.mod07.base.BaseUseCaseDslTest;
import com.jcupac.bookshop.testkit.channel.ChannelType;
import com.optivem.testing.Channel;
import org.junit.jupiter.api.TestTemplate;

class BookShopSmokeTest extends BaseUseCaseDslTest {
    @TestTemplate
    @Channel({ChannelType.UI, ChannelType.API})
    void shouldBeAbleToGoToBookShop() {
        app.bookShop().goToBookShop()
                .execute()
                .shouldSucceed();
    }
}

