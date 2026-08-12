package com.jcupac.bookshop.systemtest.legacy.mod05.smoke.system;

import com.jcupac.bookshop.systemtest.legacy.mod05.base.BaseDriverTest;
import com.jcupac.bookshop.testkit.driver.port.dtos.GoToBookShopRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.jcupac.bookshop.testkit.common.ResultAssert.assertThatResult;

public abstract class BookShopBaseSmokeTest extends BaseDriverTest {
    @BeforeEach
    void setUp() {
        setBookShopDriver();
    }

    protected abstract void setBookShopDriver();

    @Test
    void shouldBeAbleToGoToBookShop() {
        var result = bookShopDriver.goToBookShop(GoToBookShopRequest.builder().build());
        assertThatResult(result).isSuccess();
    }
}

