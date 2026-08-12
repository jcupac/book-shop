package com.jcupac.bookshop.testkit.dsl.port.given.steps.base;

import com.jcupac.bookshop.testkit.dsl.port.given.GivenStage;
import com.jcupac.bookshop.testkit.dsl.port.then.ThenStage;
import com.jcupac.bookshop.testkit.dsl.port.when.WhenStage;

public interface GivenStep {
    GivenStage and();

    WhenStage when();

    ThenStage then();
}


