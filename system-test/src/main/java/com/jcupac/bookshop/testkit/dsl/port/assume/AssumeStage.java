package com.jcupac.bookshop.testkit.dsl.port.assume;

import com.jcupac.bookshop.testkit.dsl.port.assume.steps.AssumeRunning;

public interface AssumeStage {
    AssumeRunning bookShop();

    AssumeRunning erp();

    AssumeRunning tax();

    AssumeRunning clock();
}
