package com.jcupac.bookshop.systemtest.legacy.mod06.base;

import com.jcupac.bookshop.systemtest.configuration.BaseConfigurableTest;
import com.jcupac.bookshop.systemtest.configuration.Configuration;
import com.jcupac.bookshop.testkit.driver.adapter.external.erp.ErpRealDriver;
import com.jcupac.bookshop.testkit.driver.adapter.external.tax.TaxRealDriver;
import com.jcupac.bookshop.testkit.channel.ChannelType;
import com.jcupac.bookshop.testkit.driver.adapter.api.BookShopApiDriver;
import com.jcupac.bookshop.testkit.driver.port.BookShopDriver;
import com.jcupac.bookshop.testkit.driver.adapter.ui.BookShopUiDriver;
import com.jcupac.bookshop.systemtest.infrastructure.playwright.BrowserLifecycleExtension;
import com.jcupac.bookshop.testkit.common.Closer;
import com.optivem.testing.contexts.ChannelContext;
import com.optivem.testing.extensions.ChannelExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ChannelExtension.class)
public abstract class BaseChannelDriverTest extends BaseConfigurableTest {
    protected BookShopDriver bookShopDriver;
    protected ErpRealDriver erpDriver;
    protected TaxRealDriver taxDriver;

    @BeforeEach
    void setUp() {
        var configuration = loadConfiguration();

        bookShopDriver = createChannelBookShopDriver(configuration);
        erpDriver = new ErpRealDriver(configuration.getErpBaseUrl());
        taxDriver = new TaxRealDriver(configuration.getTaxBaseUrl());
    }

    @AfterEach
    void tearDown() {
        Closer.close(bookShopDriver);
        Closer.close(erpDriver);
        Closer.close(taxDriver);
    }

    private BookShopDriver createChannelBookShopDriver(Configuration configuration) {
        var channel = ChannelContext.get();

        if(channel == null) {
            return null;
        }

        if (ChannelType.UI.equals(channel)) {
            return new BookShopUiDriver(configuration.getBookShopUiBaseUrl(), BrowserLifecycleExtension.getBrowser());
        } else if (ChannelType.API.equals(channel)) {
            return new BookShopApiDriver(configuration.getBookShopApiBaseUrl());
        } else {
            throw new IllegalStateException("Unknown channel: " + channel);
        }
    }
}
