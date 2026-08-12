package com.jcupac.bookshop.systemtest.legacy.mod04.e2e;

import com.jcupac.bookshop.systemtest.legacy.mod04.e2e.base.BaseE2eTest;
import com.jcupac.bookshop.testkit.driver.adapter.external.erp.client.dtos.ExtCreateProductRequest;
import com.jcupac.bookshop.testkit.driver.adapter.ui.client.pages.NewOrderPage;
import com.jcupac.bookshop.testkit.domainvaluetypes.OrderStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static com.jcupac.bookshop.testkit.common.ResultAssert.assertThatResult;
import static com.jcupac.bookshop.systemtest.commons.constants.Defaults.*;
import static org.assertj.core.api.Assertions.assertThat;

class PlaceOrderPositiveUiTest extends BaseE2eTest {
    @Override
    protected void setBookShopClient() {
        setUpBookShopUiClient();
    }

    @Test
    void shouldPlaceOrderForValidInput() {
        // GivenStage
        var sku = SKU + "-" + UUID.randomUUID().toString().substring(0, 8);
        var createProductRequest = ExtCreateProductRequest.builder()
                .id(sku)
                .title("Test Product")
                .description("Test Description")
                .category("Test Category")
                .brand("Test Brand")
                .price("20.00")
                .build();

        var createProductResult = erpClient.createProduct(createProductRequest);
        assertThatResult(createProductResult).isSuccess();

        // WhenStage
        var homePage = bookShopUiClient.openHomePage();
        var newOrderPage = homePage.clickNewOrder();
        newOrderPage.inputSku(sku);
        newOrderPage.inputQuantity("5");
        newOrderPage.inputCountry(COUNTRY);
        newOrderPage.clickPlaceOrder();

        var placeOrderResult = newOrderPage.getResult();
        assertThatResult(placeOrderResult).isSuccess();

        var orderNumber = NewOrderPage.getOrderNumber(placeOrderResult.getValue());
        assertThat(orderNumber).startsWith("ORD-");

        // ThenStage
        var orderHistoryPage = bookShopUiClient.openHomePage().clickOrderHistory();
        orderHistoryPage.inputOrderNumber(orderNumber);
        orderHistoryPage.clickSearch();
        assertThat(orderHistoryPage.isOrderListed(orderNumber)).isTrue();

        var orderDetailsPage = orderHistoryPage.clickViewOrderDetails(orderNumber);
        assertThat(orderDetailsPage.getOrderNumber()).isEqualTo(orderNumber);
        assertThat(orderDetailsPage.getSku()).isEqualTo(sku);
        assertThat(orderDetailsPage.getQuantity()).isEqualTo(5);
        assertThat(orderDetailsPage.getUnitPrice()).isEqualTo(new BigDecimal("20.00"));
        assertThat(orderDetailsPage.getTotalPrice()).isGreaterThan(BigDecimal.ZERO);
        assertThat(orderDetailsPage.getStatus()).isEqualTo(OrderStatus.PLACED);
    }
}
