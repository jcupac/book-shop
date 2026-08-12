package com.jcupac.bookshop.systemtest.configuration;

import com.jcupac.bookshop.testkit.dsl.core.usecase.UseCaseDsl;
import com.jcupac.bookshop.testkit.driver.port.external.clock.ClockDriver;
import com.jcupac.bookshop.testkit.driver.port.external.erp.ErpDriver;
import com.jcupac.bookshop.testkit.driver.port.external.tax.TaxDriver;
import com.jcupac.bookshop.testkit.channel.ChannelType;
import com.jcupac.bookshop.testkit.dsl.port.ChannelMode;
import com.jcupac.bookshop.testkit.driver.port.BookShopDriver;
import com.jcupac.bookshop.testkit.driver.adapter.external.clock.ClockRealDriver;
import com.jcupac.bookshop.testkit.driver.adapter.external.clock.ClockStubDriver;
import com.jcupac.bookshop.testkit.driver.adapter.external.erp.ErpRealDriver;
import com.jcupac.bookshop.testkit.driver.adapter.external.erp.ErpStubDriver;
import com.jcupac.bookshop.testkit.driver.adapter.external.tax.TaxRealDriver;
import com.jcupac.bookshop.testkit.driver.adapter.external.tax.TaxStubDriver;
import com.jcupac.bookshop.testkit.driver.adapter.api.BookShopApiDriver;
import com.jcupac.bookshop.testkit.driver.adapter.ui.BookShopUiDriver;
import com.jcupac.bookshop.systemtest.infrastructure.playwright.BrowserLifecycleExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BrowserLifecycleExtension.class)
public abstract class BaseConfigurableTest {
    protected Environment getFixedEnvironment() {
        return null;
    }

    protected ExternalSystemMode getFixedExternalSystemMode() {
        return null;
    }

    protected ChannelMode getFixedChannelMode() {
        return null;
    }

    protected Configuration loadConfiguration() {
        var environment = PropertyLoader.getEnvironment(getFixedEnvironment());
        var externalSystemMode = PropertyLoader.getExternalSystemMode(getFixedExternalSystemMode());
        var channelMode = PropertyLoader.getChannelMode(getFixedChannelMode());

        return ConfigurationLoader.load(environment, externalSystemMode, channelMode);
    }

    protected UseCaseDsl createUseCaseDsl(Configuration configuration) {
        var externalSystemMode = com.jcupac.bookshop.testkit.dsl.port.ExternalSystemMode.valueOf(
                configuration.getExternalSystemMode().name());

        return new UseCaseDsl(
                externalSystemMode,
                configuration.getChannelMode(),
                channel -> createBookShopDriverForChannel(configuration, channel),
                () -> createErpDriver(configuration),
                () -> createClockDriver(configuration),
                () -> createTaxDriver(configuration)
        );
    }

    private BookShopDriver createBookShopDriverForChannel(Configuration configuration, String channel) {
        if (ChannelType.UI.equals(channel)) {
            return new BookShopUiDriver(configuration.getBookShopUiBaseUrl(), BrowserLifecycleExtension.getBrowser());
        } else if (ChannelType.API.equals(channel)) {
            return new BookShopApiDriver(configuration.getBookShopApiBaseUrl());
        } else {
            throw new IllegalStateException("Unknown channel: " + channel);
        }
    }

    private ErpDriver createErpDriver(Configuration configuration) {
        return switch (configuration.getExternalSystemMode()) {
            case REAL -> new ErpRealDriver(configuration.getErpBaseUrl());
            case STUB -> new ErpStubDriver(configuration.getErpBaseUrl());
        };
    }

    private ClockDriver createClockDriver(Configuration configuration) {
        return switch (configuration.getExternalSystemMode()) {
            case REAL -> new ClockRealDriver();
            case STUB -> new ClockStubDriver(configuration.getClockBaseUrl());
        };
    }

    private TaxDriver createTaxDriver(Configuration configuration) {
        return switch (configuration.getExternalSystemMode()) {
            case REAL -> new TaxRealDriver(configuration.getTaxBaseUrl());
            case STUB -> new TaxStubDriver(configuration.getTaxBaseUrl());
        };
    }
}
