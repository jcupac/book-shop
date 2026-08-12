package com.jcupac.bookshop.testkit.driver.adapter.ui;

import com.microsoft.playwright.Browser;
import com.jcupac.bookshop.testkit.driver.adapter.ui.client.BookShopUiClient;
import com.jcupac.bookshop.testkit.driver.adapter.ui.client.pages.CouponManagementPage;
import com.jcupac.bookshop.testkit.driver.adapter.ui.client.pages.HomePage;
import com.jcupac.bookshop.testkit.driver.adapter.ui.client.pages.NewOrderPage;
import com.jcupac.bookshop.testkit.driver.adapter.ui.client.pages.OrderDetailsPage;
import com.jcupac.bookshop.testkit.driver.adapter.ui.client.pages.OrderHistoryPage;
import com.jcupac.bookshop.testkit.driver.port.dtos.BrowseCouponsRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.BrowseCouponsResponse;
import com.jcupac.bookshop.testkit.driver.port.dtos.CancelOrderRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.CancelOrderResponse;
import com.jcupac.bookshop.testkit.driver.port.dtos.DeliverOrderRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.DeliverOrderResponse;
import com.jcupac.bookshop.testkit.driver.port.dtos.GoToBookShopRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.GoToBookShopResponse;
import com.jcupac.bookshop.testkit.driver.port.dtos.PlaceOrderRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.PlaceOrderResponse;
import com.jcupac.bookshop.testkit.driver.port.dtos.PublishCouponRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.PublishCouponResponse;
import com.jcupac.bookshop.testkit.driver.port.dtos.ViewOrderRequest;
import com.jcupac.bookshop.testkit.driver.port.dtos.ViewOrderResponse;
import com.jcupac.bookshop.testkit.driver.port.BookShopDriver;
import com.jcupac.bookshop.testkit.driver.port.dtos.error.SystemError;
import com.jcupac.bookshop.testkit.common.Result;

import static com.jcupac.bookshop.testkit.dsl.core.usecase.commons.SystemResults.failure;
import static com.jcupac.bookshop.testkit.dsl.core.usecase.commons.SystemResults.success;

public class BookShopUiDriver implements BookShopDriver {
    private final BookShopUiClient client;

    private Page currentPage = Page.NONE;
    private HomePage homePage;
    private NewOrderPage newOrderPage;
    private OrderHistoryPage orderHistoryPage;
    private OrderDetailsPage orderDetailsPage;
    private CouponManagementPage couponManagementPage;

    public BookShopUiDriver(String baseUrl, Browser browser) {
        this.client = new BookShopUiClient(baseUrl, browser);
    }

    @Override
    public void close() {
        client.close();
    }

    @Override
    public Result<GoToBookShopResponse, SystemError> goToBookShop(GoToBookShopRequest request) {
        homePage = client.openHomePage();

        if (!client.isStatusOk() || !client.isPageLoaded()) {
            return failure("Failed to load home page");
        }

        currentPage = Page.HOME;
        return success(GoToBookShopResponse.builder().build());
    }

    @Override
    public Result<PlaceOrderResponse, SystemError> placeOrder(PlaceOrderRequest request) {
        var sku = request.getSku();
        var quantity = request.getQuantity();
        var country = request.getCountry();
        var couponCode = request.getCouponCode();

        ensureOnNewOrderPage();
        newOrderPage.inputSku(sku);
        newOrderPage.inputQuantity(quantity);
        newOrderPage.inputCountry(country);
        if (couponCode != null && !couponCode.isBlank()) {
            newOrderPage.inputCouponCode(couponCode);
        }
        newOrderPage.clickPlaceOrder();

        var result = newOrderPage.getResult();

        if (result.isFailure()) {
            return failure(result.getError());
        }

        var orderNumberValue = NewOrderPage.getOrderNumber(result.getValue());

        var response = PlaceOrderResponse.builder().orderNumber(orderNumberValue).build();
        return Result.success(response);
    }

    @Override
    public Result<CancelOrderResponse, SystemError> cancelOrder(CancelOrderRequest request) {
        var viewResult = viewOrder(ViewOrderRequest.builder().orderNumber(request.getOrderNumber()).build());

        if (viewResult.isFailure()) {
            return failure(viewResult.getError());
        }

        orderDetailsPage.clickCancelOrder();

        var result = orderDetailsPage.getResult();

        if (result.isFailure()) {
            return failure(result.getError());
        }

        return success(CancelOrderResponse.builder().build());
    }

    @Override
    public Result<DeliverOrderResponse, SystemError> deliverOrder(DeliverOrderRequest request) {
        var viewResult = viewOrder(ViewOrderRequest.builder().orderNumber(request.getOrderNumber()).build());

        if (viewResult.isFailure()) {
            return failure(viewResult.getError());
        }

        orderDetailsPage.clickDeliverOrder();

        var result = orderDetailsPage.getResult();

        if (result.isFailure()) {
            return failure(result.getError());
        }

        return success(DeliverOrderResponse.builder().build());
    }

    @Override
    public Result<ViewOrderResponse, SystemError> viewOrder(ViewOrderRequest request) {
        var orderNumber = request.getOrderNumber();
        var result = ensureOnOrderDetailsPage(orderNumber);
        if (result.isFailure()) {
            return failure(result.getError());
        }

        var isSuccess = orderDetailsPage.isLoadedSuccessfully();

        if (!isSuccess) {
            return Result.failure(result.getError());
        }

        var displayOrderNumber = orderDetailsPage.getOrderNumber();
        var orderTimestamp = orderDetailsPage.getOrderTimestamp();
        var sku = orderDetailsPage.getSku();
        var quantity = orderDetailsPage.getQuantity();
        var country = orderDetailsPage.getCountry();
        var unitPrice = orderDetailsPage.getUnitPrice();
        var basePrice = orderDetailsPage.getBasePrice();
        var discountRate = orderDetailsPage.getDiscountRate();
        var discountAmount = orderDetailsPage.getDiscountAmount();
        var subtotalPrice = orderDetailsPage.getSubtotalPrice();
        var taxRate = orderDetailsPage.getTaxRate();
        var taxAmount = orderDetailsPage.getTaxAmount();
        var totalPrice = orderDetailsPage.getTotalPrice();
        var status = orderDetailsPage.getStatus();
        var appliedCoupon = orderDetailsPage.getAppliedCoupon();

        var response = ViewOrderResponse.builder()
                .orderNumber(displayOrderNumber)
                .orderTimestamp(orderTimestamp)
                .sku(sku)
                .quantity(quantity)
                .country(country)
                .unitPrice(unitPrice)
                .basePrice(basePrice)
                .discountRate(discountRate)
                .discountAmount(discountAmount)
                .subtotalPrice(subtotalPrice)
                .taxRate(taxRate)
                .taxAmount(taxAmount)
                .totalPrice(totalPrice)
                .status(status)
                .appliedCouponCode(appliedCoupon)
                .build();

        return success(response);
    }

    @Override
    public Result<PublishCouponResponse, SystemError> publishCoupon(PublishCouponRequest request) {
        ensureOnCouponManagementPage();

        couponManagementPage.inputCouponCode(request.getCode());
        couponManagementPage.inputDiscountRate(request.getDiscountRate());
        couponManagementPage.inputValidFrom(request.getValidFrom());
        couponManagementPage.inputValidTo(request.getValidTo());
        couponManagementPage.inputUsageLimit(request.getUsageLimit());
        couponManagementPage.clickPublishCoupon();

        return couponManagementPage.getResult()
                .map(value -> PublishCouponResponse.builder().build());
    }

    @Override
    public Result<BrowseCouponsResponse, SystemError> browseCoupons(BrowseCouponsRequest request) {
        navigateToCouponManagementPage();

        var coupons = couponManagementPage.readCoupons();

        var response = BrowseCouponsResponse.builder()
                .coupons(coupons)
                .build();

        return success(response);
    }

    // --- page navigation ---

    private HomePage getHomePage() {
        if (homePage == null || currentPage != Page.HOME) {
            homePage = client.openHomePage();
            currentPage = Page.HOME;
        }
        return homePage;
    }

    private void ensureOnNewOrderPage() {
        if (currentPage != Page.NEW_ORDER) {
            newOrderPage = getHomePage().clickNewOrder();
            currentPage = Page.NEW_ORDER;
        }
    }

    private void ensureOnCouponManagementPage() {
        if (currentPage != Page.COUPON_MANAGEMENT) {
            navigateToCouponManagementPage();
        }
    }

    private void navigateToCouponManagementPage() {
        couponManagementPage = getHomePage().clickCouponManagement();
        currentPage = Page.COUPON_MANAGEMENT;
    }

    private void ensureOnOrderHistoryPage() {
        if (currentPage != Page.ORDER_HISTORY) {
            orderHistoryPage = getHomePage().clickOrderHistory();
            currentPage = Page.ORDER_HISTORY;
        }
    }

    private Result<Void, SystemError> ensureOnOrderDetailsPage(String orderNumber) {
        ensureOnOrderHistoryPage();
        orderHistoryPage.inputOrderNumber(orderNumber);
        orderHistoryPage.clickSearch();

        var isOrderListed = orderHistoryPage.isOrderListed(orderNumber);
        if (!isOrderListed) {
            return failure("Order " + orderNumber + " does not exist.");
        }

        orderDetailsPage = orderHistoryPage.clickViewOrderDetails(orderNumber);
        currentPage = Page.ORDER_DETAILS;

        return success();
    }

    private enum Page {
        NONE,
        HOME,
        NEW_ORDER,
        ORDER_HISTORY,
        ORDER_DETAILS,
        COUPON_MANAGEMENT
    }
}
