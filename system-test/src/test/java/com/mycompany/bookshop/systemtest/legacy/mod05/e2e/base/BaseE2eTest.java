package com.jcupac.bookshop.systemtest.legacy.mod05.e2e.base;

import com.jcupac.bookshop.systemtest.legacy.mod05.base.BaseDriverTest;
import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseE2eTest extends BaseDriverTest {
    @BeforeEach
    void setUpDrivers() {
        setBookShopDriver();
        setUpExternalDrivers();
    }

    protected abstract void setBookShopDriver();

    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.REAL;
    }
}