package com.jcupac.bookshop.systemtest.legacy.mod04.smoke.system;

import com.jcupac.bookshop.systemtest.legacy.mod04.base.BaseClientTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BookShopUiSmokeTest extends BaseClientTest {
    @BeforeEach
    void setUp() {
        setUpBookShopUiClient();
    }

    @Test
    void shouldBeAbleToGoToBookShop() {
        bookShopUiClient.openHomePage();
        assertTrue(bookShopUiClient.isPageLoaded());
    }
}


