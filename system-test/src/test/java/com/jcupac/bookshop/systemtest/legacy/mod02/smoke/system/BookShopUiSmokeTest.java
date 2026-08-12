package com.jcupac.bookshop.systemtest.legacy.mod02.smoke.system;

import com.jcupac.bookshop.systemtest.legacy.mod02.base.BaseRawTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookShopUiSmokeTest extends BaseRawTest {
    @BeforeEach
    void setUp() {
        setUpBookShopBrowser();
    }

    @Test
    void shouldBeAbleToGoToBookShop() {
        var response = bookShopUiPage.navigate(getBookShopUiBaseUrl());

        assertThat(response.status()).isEqualTo(200);

        var contentType = response.headers().get("content-type");
        assertThat(contentType).isNotNull().contains("text/html");

        var pageContent = bookShopUiPage.content();
        assertThat(pageContent).contains("<html").contains("</html>");
    }
}


