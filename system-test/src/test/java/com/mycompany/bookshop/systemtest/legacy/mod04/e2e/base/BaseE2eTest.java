package com.jcupac.bookshop.systemtest.legacy.mod04.e2e.base;

import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;
import com.jcupac.bookshop.systemtest.legacy.mod04.base.BaseClientTest;

import org.junit.jupiter.api.BeforeEach;

public abstract class BaseE2eTest extends BaseClientTest {
    @BeforeEach
    void setUp() {
        setBookShopClient();
        setUpExternalClients();
    }

    protected abstract void setBookShopClient();

    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.REAL;
    }
}



