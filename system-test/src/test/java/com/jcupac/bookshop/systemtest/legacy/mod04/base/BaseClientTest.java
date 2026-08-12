package com.jcupac.bookshop.systemtest.legacy.mod04.base;

import com.jcupac.bookshop.systemtest.configuration.BaseConfigurableTest;
import com.jcupac.bookshop.systemtest.configuration.Configuration;
import com.jcupac.bookshop.testkit.driver.adapter.external.erp.client.ErpRealClient;
import com.jcupac.bookshop.testkit.driver.adapter.external.tax.client.TaxRealClient;
import com.jcupac.bookshop.testkit.driver.adapter.api.client.BookShopApiClient;
import com.jcupac.bookshop.testkit.driver.adapter.ui.client.BookShopUiClient;
import com.jcupac.bookshop.systemtest.infrastructure.playwright.BrowserLifecycleExtension;
import com.jcupac.bookshop.testkit.common.Closer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseClientTest extends BaseConfigurableTest {
    protected Configuration configuration;

    protected BookShopUiClient bookShopUiClient;
    protected BookShopApiClient bookShopApiClient;
    protected ErpRealClient erpClient;
    protected TaxRealClient taxClient;

    @BeforeEach
    protected void setUpConfiguration() {
        configuration = loadConfiguration();
    }

    protected void setUpBookShopUiClient() {
        bookShopUiClient = new BookShopUiClient(configuration.getBookShopUiBaseUrl(), BrowserLifecycleExtension.getBrowser());
    }

    protected void setUpBookShopApiClient() {
        bookShopApiClient = new BookShopApiClient(configuration.getBookShopApiBaseUrl());
    }

    protected void setUpExternalClients() {
        erpClient = new ErpRealClient(configuration.getErpBaseUrl());
        taxClient = new TaxRealClient(configuration.getTaxBaseUrl());
    }

    @AfterEach
    void tearDown() {
        Closer.close(bookShopUiClient);
        Closer.close(bookShopApiClient);
        Closer.close(erpClient);
        Closer.close(taxClient);
    }

}
