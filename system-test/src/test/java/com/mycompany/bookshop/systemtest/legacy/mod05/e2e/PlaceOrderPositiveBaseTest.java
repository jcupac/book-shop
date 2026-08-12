package com.jcupac.bookshop.systemtest.legacy.mod05.e2e;

import com.jcupac.bookshop.testkit.driver.port.external.erp.dtos.ReturnsProductRequest;
import com.jcupac.bookshop.testkit.domainvaluetypes.OrderStatus;
import com.jcupac.bookshop.testkit.driver.port.dtos.PlaceOrderRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.ViewOrderRequest;
import com.jcupac.bookshop.systemtest.legacy.mod05.e2e.base.BaseE2eTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static com.jcupac.bookshop.testkit.common.ResultAssert.assertThatResult;
import static com.jcupac.bookshop.systemtest.commons.constants.Defaults.COUNTRY;
import static com.jcupac.bookshop.systemtest.commons.constants.Defaults.SKU;
import static org.assertj.core.api.Assertions.assertThat;

abstract class PlaceOrderPositiveBaseTest extends BaseE2eTest {
    @Test
    void shouldPlaceOrderForValidInput() {
        // GivenStage
        var sku = SKU + "-" + UUID.randomUUID().toString().substring(0, 8);
        var returnsProductRequest = ReturnsProductRequest.builder()
                .sku(sku)
                .price("20.00")
                .build();

        var returnsProductResult = erpDriver.returnsProduct(returnsProductRequest);
        assertThatResult(returnsProductResult).isSuccess();

        // WhenStage
        var placeOrderRequest = PlaceOrderRequest.builder()
                .sku(sku)
                .quantity("5")
                .country(COUNTRY)
                .build();

        var placeOrderResult = bookShopDriver.placeOrder(placeOrderRequest);
        assertThatResult(placeOrderResult).isSuccess();

        var orderNumber = placeOrderResult.getValue().getOrderNumber();
        assertThat(orderNumber).startsWith("ORD-");

        // ThenStage
        var viewOrderResult = bookShopDriver.viewOrder(ViewOrderRequest.builder().orderNumber(orderNumber).build());
        assertThatResult(viewOrderResult).isSuccess();

        var order = viewOrderResult.getValue();
        assertThat(order.getOrderNumber()).isEqualTo(orderNumber);
        assertThat(order.getSku()).isEqualTo(sku);
        assertThat(order.getQuantity()).isEqualTo(5);
        assertThat(order.getUnitPrice()).isEqualTo(new BigDecimal("20.00"));
        assertThat(order.getTotalPrice()).isGreaterThan(BigDecimal.ZERO);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.PLACED);
    }
}
