package com.inditex.pricing;

import com.inditex.pricing.web.request.PriceRequest;
import com.inditex.pricing.web.response.PriceResponse;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class TestUtils {

    protected static final LocalDateTime START_DATE = LocalDate.of(2020, 6, 15).atStartOfDay();

    protected static final LocalDateTime END_DATE = LocalDate.of(2020, 12, 31).atStartOfDay();

    protected static final String WRONG_FORMAT_APPLICATION_DATE = "2020/06/15";

    protected static final String APPLICATION_DATE = "2020-06-15T16:30:00";

    protected static final Double REAL_PRICE = 38.95;

    protected static final int REAL_PRODUCT_ID = 35455;

    protected static final int REAL_BRAND_ID = 1;

    protected static final int BRAND_ID = 1;

    protected static final int NOT_FOUND_BRAND_ID = 0;

    protected static final int PRODUCT_ID = 35463;

    protected static final Double PRICE = 37.5;

    protected PriceRequest realRequestNoBrandId;

    protected PriceRequest realRequestWrongDate;

    protected PriceRequest requestedTest1;

    protected PriceRequest requestedTest2;

    protected PriceRequest requestedTest3;

    protected PriceRequest requestedTest4;

    protected PriceRequest requestedTest5;

    protected PriceResponse priceResponse;

    protected PriceRequest realRequest;

    @BeforeEach
    public void init() {

        realRequestWrongDate = new PriceRequest(REAL_PRODUCT_ID, REAL_BRAND_ID, WRONG_FORMAT_APPLICATION_DATE);
        realRequestNoBrandId = new PriceRequest(REAL_PRODUCT_ID, NOT_FOUND_BRAND_ID, APPLICATION_DATE);
        realRequest = new PriceRequest(REAL_PRODUCT_ID, REAL_BRAND_ID, APPLICATION_DATE);

        // Requests with the values requested in the technical task for testing
        requestedTest1 = new PriceRequest(REAL_PRODUCT_ID, REAL_BRAND_ID, "2020-06-14T10:00:00");
        requestedTest2 = new PriceRequest(REAL_PRODUCT_ID, REAL_BRAND_ID, "2020-06-14T16:00:00");
        requestedTest3 = new PriceRequest(REAL_PRODUCT_ID, REAL_BRAND_ID, "2020-06-14T21:00:00");
        requestedTest4 = new PriceRequest(REAL_PRODUCT_ID, REAL_BRAND_ID, "2020-06-15T10:00:00");
        requestedTest5 = new PriceRequest(REAL_PRODUCT_ID, REAL_BRAND_ID, "2020-06-16T21:00:00");

        priceResponse = new PriceResponse(PRODUCT_ID, BRAND_ID, START_DATE, END_DATE, PRICE);

    }

}
