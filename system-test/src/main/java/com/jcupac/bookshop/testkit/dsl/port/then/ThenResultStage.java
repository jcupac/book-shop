package com.jcupac.bookshop.testkit.dsl.port.then;

import com.jcupac.bookshop.testkit.dsl.port.then.steps.ThenFailure;
import com.jcupac.bookshop.testkit.dsl.port.then.steps.ThenSuccess;

public interface ThenResultStage extends ThenStage {
    ThenSuccess shouldSucceed();

    ThenFailure shouldFail();
}
