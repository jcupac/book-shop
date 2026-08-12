package com.jcupac.bookshop.systemtest.legacy.mod05.e2e;

import com.jcupac.bookshop.testkit.driver.port.dtos.PlaceOrderRequest;
import com.jcupac.bookshop.systemtest.legacy.mod05.e2e.base.BaseE2eTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.jcupac.bookshop.testkit.common.ResultAssert.assertThatResult;
import static com.jcupac.bookshop.systemtest.commons.constants.Defaults.COUNTRY;
import static com.jcupac.bookshop.systemtest.commons.constants.Defaults.SKU;
import static org.assertj.core.api.Assertions.assertThat;

abstract class PlaceOrderNegativeBaseTest extends BaseE2eTest {
    @Test
    void shouldRejectOrderWithNonIntegerQuantity() {
        var request = PlaceOrderRequest.builder()
                .sku(SKU + "-" + UUID.randomUUID().toString().substring(0, 8))
                .quantity("3.5")
                .country(COUNTRY)
                .build();

        var result = bookShopDriver.placeOrder(request);

        assertThatResult(result).isFailure();
        var error = result.getError();
        assertThat(error.getMessage()).isEqualTo("The request contains one or more validation errors");
        assertThat(error.getFields()).anySatisfy(field -> {
            assertThat(field.getField()).isEqualTo("quantity");
            assertThat(field.getMessage()).isEqualTo("Quantity must be an integer");
        });
    }
}