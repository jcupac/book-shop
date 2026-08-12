package com.jcupac.bookshop.systemtest.legacy.mod06.smoke.system;

import com.jcupac.bookshop.systemtest.legacy.mod06.base.BaseChannelDriverTest;
import com.jcupac.bookshop.testkit.channel.ChannelType;
import com.jcupac.bookshop.testkit.driver.port.dtos.GoToBookShopRequest;
import com.optivem.testing.Channel;
import org.junit.jupiter.api.TestTemplate;

import static com.jcupac.bookshop.testkit.common.ResultAssert.assertThatResult;

class BookShopSmokeTest extends BaseChannelDriverTest {
    @TestTemplate
    @Channel({ChannelType.UI, ChannelType.API})
    void shouldBeAbleToGoToBookShop() {
        var result = bookShopDriver.goToBookShop(GoToBookShopRequest.builder().build());
        assertThatResult(result).isSuccess();
    }
}

