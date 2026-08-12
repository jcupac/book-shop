package com.jcupac.bookshop.systemtest.configuration;

import com.jcupac.bookshop.testkit.dsl.port.ChannelMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Configuration {
    private final String bookShopUiBaseUrl;
    private final String bookShopApiBaseUrl;
    private final String erpBaseUrl;
    private final String clockBaseUrl;
    private final String taxBaseUrl;
    private final ExternalSystemMode externalSystemMode;
    private final ChannelMode channelMode;
}
