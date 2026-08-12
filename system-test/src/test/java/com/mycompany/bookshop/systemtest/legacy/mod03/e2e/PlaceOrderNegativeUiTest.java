package com.jcupac.bookshop.systemtest.legacy.mod03.e2e;

import com.jcupac.bookshop.systemtest.legacy.mod03.e2e.base.BaseE2eTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.jcupac.bookshop.systemtest.commons.constants.Defaults.*;
import static org.assertj.core.api.Assertions.assertThat;

class PlaceOrderNegativeUiTest extends BaseE2eTest {
    @Override
    protected void setBookShopClient() {
        setUpBookShopBrowser();
    }

    @Test
    void shouldRejectOrderWithNonIntegerQuantity() {
        bookShopUiPage.navigate(getBookShopUiBaseUrl());
        bookShopUiPage.locator("a[href='/new-order']").click();

        bookShopUiPage.locator("[aria-label=\"SKU\"]").fill(SKU + "-" + UUID.randomUUID().toString().substring(0, 8));
        bookShopUiPage.locator("[aria-label=\"Quantity\"]").fill("invalid-quantity");
        bookShopUiPage.locator("[aria-label=\"Place Order\"]").click();

        var errorAlert = bookShopUiPage.locator("[role='alert'][data-notification-id]");
        errorAlert.waitFor();
        assertThat(errorAlert.isVisible()).isTrue();
        var errorText = errorAlert.textContent();
        assertThat(errorText)
                .contains("The request contains one or more validation errors")
                .contains("quantity")
                .contains("Quantity must be an integer");
    }
}
