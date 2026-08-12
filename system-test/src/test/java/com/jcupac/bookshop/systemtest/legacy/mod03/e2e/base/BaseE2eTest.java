package com.jcupac.bookshop.systemtest.legacy.mod03.e2e.base;

import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;
import com.jcupac.bookshop.systemtest.legacy.mod03.base.BaseRawTest;

import org.junit.jupiter.api.BeforeEach;

public abstract class BaseE2eTest extends BaseRawTest {
    @BeforeEach
    void setUp() {
        setBookShopClient();
        setUpExternalHttpClients();
    }

    protected abstract void setBookShopClient();

    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.REAL;
    }
}



