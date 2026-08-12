package com.jcupac.bookshop.testkit.dsl.core.scenario.then.steps;

import com.jcupac.bookshop.testkit.dsl.core.usecase.UseCaseDsl;
import com.jcupac.bookshop.testkit.dsl.core.usecase.external.erp.usecases.GetProductVerification;
import com.jcupac.bookshop.testkit.dsl.core.scenario.ExecutionResultContext;
import com.jcupac.bookshop.testkit.dsl.core.shared.VoidVerification;
import com.jcupac.bookshop.testkit.dsl.port.then.steps.ThenProduct;

public class ThenProductImpl extends BaseThenStep<Void, VoidVerification> implements ThenProduct {
    private final GetProductVerification verification;

    public ThenProductImpl(UseCaseDsl app, ExecutionResultContext executionResult, GetProductVerification verification) {
        super(app, executionResult, null);
        this.verification = verification;
    }

    @Override
    public ThenProductImpl hasSku(String sku) {
        verification.sku(sku);
        return this;
    }

    @Override
    public ThenProductImpl hasPrice(double price) {
        verification.price(price);
        return this;
    }

    @Override
    public ThenProductImpl hasPrice(String price) {
        verification.price(price);
        return this;
    }

    @Override
    public ThenProductImpl and() {
        return this;
    }
}
