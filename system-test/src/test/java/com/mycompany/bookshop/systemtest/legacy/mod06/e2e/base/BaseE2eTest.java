package com.jcupac.bookshop.systemtest.legacy.mod06.e2e.base;

import com.jcupac.bookshop.systemtest.legacy.mod06.base.BaseChannelDriverTest;
import com.jcupac.bookshop.systemtest.configuration.ExternalSystemMode;

public abstract class BaseE2eTest extends BaseChannelDriverTest {
    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.REAL;
    }
}



