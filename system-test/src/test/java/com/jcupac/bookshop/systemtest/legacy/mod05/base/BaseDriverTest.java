package com.jcupac.bookshop.systemtest.legacy.mod05.base;

import com.jcupac.bookshop.systemtest.configuration.BaseConfigurableTest;
import com.jcupac.bookshop.systemtest.configuration.Configuration;
import com.jcupac.bookshop.testkit.driver.adapter.external.erp.ErpRealDriver;
import com.jcupac.bookshop.testkit.driver.adapter.external.tax.TaxRealDriver;
import com.jcupac.bookshop.testkit.driver.adapter.api.BookShopApiDriver;
import com.jcupac.bookshop.testkit.driver.port.BookShopDriver;
import com.jcupac.bookshop.testkit.driver.adapter.ui.BookShopUiDriver;
import com.jcupac.bookshop.systemtest.infrastructure.playwright.BrowserLifecycleExtension;
import com.jcupac.bookshop.testkit.common.Closer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseDriverTest extends BaseConfigurableTest {
    protected Configuration configuration;

    protected BookShopDriver bookShopDriver;
    protected ErpRealDriver erpDriver;
    protected TaxRealDriver taxDriver;

    @BeforeEach
    protected void setUpConfiguration() {
        configuration = loadConfiguration();
    }

    protected void setUpBookShopUiDriver() {
        bookShopDriver = new BookShopUiDriver(configuration.getBookShopUiBaseUrl(), BrowserLifecycleExtension.getBrowser());
    }

    protected void setUpBookShopApiDriver() {
        bookShopDriver = new BookShopApiDriver(configuration.getBookShopApiBaseUrl());
    }

    protected void setUpExternalDrivers() {
        erpDriver = new ErpRealDriver(configuration.getErpBaseUrl());
        taxDriver = new TaxRealDriver(configuration.getTaxBaseUrl());
    }

    @AfterEach
    void tearDown() {
        Closer.close(bookShopDriver);
        Closer.close(erpDriver);
        Closer.close(taxDriver);
    }
}
