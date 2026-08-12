package com.jcupac.bookshop.testkit.driver.port.external.erp;

import com.jcupac.bookshop.testkit.driver.port.external.erp.dtos.GetProductRequest;
import com.jcupac.bookshop.testkit.driver.port.external.erp.dtos.GetProductResponse;
import com.jcupac.bookshop.testkit.driver.port.external.erp.dtos.ReturnsProductRequest;
import com.jcupac.bookshop.testkit.driver.port.external.erp.dtos.ReturnsPromotionRequest;
import com.jcupac.bookshop.testkit.driver.port.external.erp.dtos.error.ErpErrorResponse;
import com.jcupac.bookshop.testkit.common.Result;

public interface ErpDriver extends AutoCloseable {
    Result<Void, ErpErrorResponse> goToErp();

    Result<GetProductResponse, ErpErrorResponse> getProduct(GetProductRequest request);

    Result<Void, ErpErrorResponse> returnsProduct(ReturnsProductRequest request);

    Result<Void, ErpErrorResponse> returnsPromotion(ReturnsPromotionRequest request);
}
