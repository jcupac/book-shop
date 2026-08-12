package com.jcupac.bookshop.testkit.dsl.port;

import com.jcupac.bookshop.testkit.dsl.port.assume.AssumeStage;
import com.jcupac.bookshop.testkit.dsl.port.given.GivenStage;
import com.jcupac.bookshop.testkit.dsl.port.when.WhenStage;

public interface ScenarioDsl {
    AssumeStage assume();

    GivenStage given();

    WhenStage when();
}