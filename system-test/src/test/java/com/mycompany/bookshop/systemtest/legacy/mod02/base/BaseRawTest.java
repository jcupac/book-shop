package com.jcupac.bookshop.systemtest.legacy.mod02.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.microsoft.playwright.*;
import com.jcupac.bookshop.systemtest.configuration.BaseConfigurableTest;
import com.jcupac.bookshop.systemtest.configuration.Configuration;
import com.jcupac.bookshop.testkit.common.Closer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.net.http.HttpClient;
import java.util.UUID;

public abstract class BaseRawTest extends BaseConfigurableTest {
    protected Configuration configuration;

    protected Playwright bookShopUiPlaywright;
    protected Browser bookShopUiBrowser;
    protected BrowserContext bookShopUiBrowserContext;
    protected Page bookShopUiPage;

    protected HttpClient bookShopApiHttpClient;
    protected HttpClient erpHttpClient;
    protected HttpClient taxHttpClient;

    protected ObjectMapper httpObjectMapper;

    @BeforeEach
    protected void setUpConfiguration() {
        configuration = loadConfiguration();
    }

    protected void setUpBookShopBrowser() {
        bookShopUiPlaywright = Playwright.create();

        var launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(true);

        bookShopUiBrowser = bookShopUiPlaywright.chromium().launch(launchOptions);

        var contextOptions = new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setStorageStatePath(null);

        bookShopUiBrowserContext = bookShopUiBrowser.newContext(contextOptions);
        bookShopUiPage = bookShopUiBrowserContext.newPage();
    }

    protected void setUpBookShopHttpClient() {
        bookShopApiHttpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        if (httpObjectMapper == null) {
            httpObjectMapper = createObjectMapper();
        }
    }

    protected void setUpExternalHttpClients() {
        erpHttpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        taxHttpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        httpObjectMapper = createObjectMapper();
    }

    protected String getBookShopApiBaseUrl() {
        return configuration.getBookShopApiBaseUrl();
    }

    protected String getBookShopUiBaseUrl() {
        return configuration.getBookShopUiBaseUrl();
    }

    protected String getErpBaseUrl() {
        return configuration.getErpBaseUrl();
    }

    protected String getTaxBaseUrl() {
        return configuration.getTaxBaseUrl();
    }

    private ObjectMapper createObjectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    protected String createUniqueSku(String baseSku) {
        var suffix = UUID.randomUUID().toString().substring(0, 8);
        return baseSku + "-" + suffix;
    }

    @AfterEach
    void tearDown() {
        Closer.close(bookShopUiPage);
        Closer.close(bookShopUiBrowserContext);
        Closer.close(bookShopUiBrowser);
        Closer.close(bookShopUiPlaywright);
        Closer.close(erpHttpClient);
        Closer.close(taxHttpClient);
        Closer.close(bookShopApiHttpClient);
    }
}
