package com.jcupac.bookshop.testkit.dsl.core.usecase;

import com.jcupac.bookshop.testkit.driver.port.BookShopDriver;
import com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.BrowseCoupons;
import com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.CancelOrder;
import com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.DeliverOrder;
import com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.GoToBookShop;
import com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.PlaceOrder;
import com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.PublishCoupon;
import com.jcupac.bookshop.testkit.dsl.core.usecase.usecases.ViewOrder;
import com.jcupac.bookshop.testkit.common.Closer;
import com.jcupac.bookshop.testkit.dsl.core.shared.UseCaseContext;

import java.io.Closeable;

public class BookShopDsl implements Closeable {
    private final BookShopDriver driver;
    private final UseCaseContext context;

    public BookShopDsl(BookShopDriver driver, UseCaseContext context) {
        this.driver = driver;
        this.context = context;
    }

    @Override
    public void close() {
        Closer.close(driver);
    }

    public GoToBookShop goToBookShop() {
        return new GoToBookShop(driver, context);
    }

    public PlaceOrder placeOrder() {
        return new PlaceOrder(driver, context);
    }

    public CancelOrder cancelOrder() {
        return new CancelOrder(driver, context);
    }

    public DeliverOrder deliverOrder() {
        return new DeliverOrder(driver, context);
    }

    public ViewOrder viewOrder() {
        return new ViewOrder(driver, context);
    }

    public PublishCoupon publishCoupon() {
        return new PublishCoupon(driver, context);
    }

    public BrowseCoupons browseCoupons() {
        return new BrowseCoupons(driver, context);
    }
}
