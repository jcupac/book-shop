package com.jcupac.bookshop.testkit.dsl.core.usecase;

import com.jcupac.bookshop.testkit.dsl.port.ExternalSystemMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Configuration {
    private final String bookShopUiBaseUrl;
    private final String bookShopApiBaseUrl;
    private final String erpBaseUrl;
    private final String clockBaseUrl;
    private final ExternalSystemMode externalSystemMode;
}
