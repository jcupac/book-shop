package com.jcupac.bookshop.systemtest.legacy.mod04.smoke.system;

import com.jcupac.bookshop.systemtest.legacy.mod04.base.BaseClientTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.jcupac.bookshop.testkit.common.ResultAssert.assertThatResult;

class BookShopApiSmokeTest extends BaseClientTest {
    @BeforeEach
    void setUp() {
        setUpBookShopApiClient();
    }

    @Test
    void shouldBeAbleToGoToBookShop() {
        var result = bookShopApiClient.health().checkHealth();
        assertThatResult(result).isSuccess();
    }
}


