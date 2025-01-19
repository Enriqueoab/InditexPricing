package com.inditex.pricing.web.controller;

import com.inditex.pricing.TestUtils;
import com.inditex.pricing.application.service.GetPriceUseCaseAdapter;
import com.inditex.pricing.domain.constants.ExceptionMessage;
import com.inditex.pricing.domain.exception.DateTimeFormatException;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.infrastructure.db.repo.PriceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PricingControllerIntegrationTest extends TestUtils {

    @Mock
    private GetPriceUseCaseAdapter pricingService;

    @Autowired
    private PriceRepository priceRepo;

    @Autowired
    private PricingController pricingController;

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     * Integration test:
     *
     * Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */

    @Test
    void testGetApplicablePrice_Success_TestRequired_Test1() throws PriceNotFoundException, DateTimeFormatException {

        var price = pricingController.getPrice(requestedTest1.productId(), requestedTest1.brandId(), requestedTest1.applicationDate());
        var applyDate = LocalDateTime.parse(requestedTest1.applicationDate());

        var dbPrice = priceRepo.findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc
                (requestedTest1.productId(), requestedTest1.brandId(), applyDate, applyDate);

        assertNotNull(dbPrice);
        assertNotNull(price);
        assertEquals(35.5, dbPrice.getPrice());
        assertEquals(price.price(), dbPrice.getPrice());
        assertEquals(price.brandId(), dbPrice.getBrandId());
    }


    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     * Integration test:
     *
     * Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void testGetApplicablePrice_Success_TestRequired_Test2() throws PriceNotFoundException, DateTimeFormatException {

        var price = pricingController.getPrice(requestedTest2.productId(), requestedTest2.brandId(), requestedTest2.applicationDate());
        var applyDate = LocalDateTime.parse(requestedTest2.applicationDate());

        var dbPrice = priceRepo.findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc
                (requestedTest2.productId(), requestedTest2.brandId(), applyDate, applyDate);

        assertNotNull(dbPrice);
        assertNotNull(price);
        assertEquals(25.45, dbPrice.getPrice());
        assertEquals(price.price(), dbPrice.getPrice());
        assertEquals(price.brandId(), dbPrice.getBrandId());
    }

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     * Integration test:
     *
     * Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void testGetApplicablePrice_Success_TestRequired_Test3() throws PriceNotFoundException, DateTimeFormatException {

        var price = pricingController.getPrice(requestedTest3.productId(), requestedTest3.brandId(), requestedTest3.applicationDate());
        var applyDate = LocalDateTime.parse(requestedTest3.applicationDate());

        var dbPrice = priceRepo.findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc
                (requestedTest3.productId(), requestedTest3.brandId(), applyDate, applyDate);

        assertNotNull(dbPrice);
        assertNotNull(price);
        assertEquals(35.5, dbPrice.getPrice());
        assertEquals(price.price(), dbPrice.getPrice());
        assertEquals(price.brandId(), dbPrice.getBrandId());
    }

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     * Integration test:
     *
     * Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void testGetApplicablePrice_Success_TestRequired_Test4() throws PriceNotFoundException, DateTimeFormatException {

        var price = pricingController.getPrice(requestedTest4.productId(), requestedTest4.brandId(), requestedTest4.applicationDate());
        var applyDate = LocalDateTime.parse(requestedTest4.applicationDate());

        var dbPrice = priceRepo.findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc
                (requestedTest4.productId(), requestedTest4.brandId(), applyDate, applyDate);

        assertNotNull(dbPrice);
        assertNotNull(price);
        assertEquals(30.5, dbPrice.getPrice());
        assertEquals(price.price(), dbPrice.getPrice());
        assertEquals(price.brandId(), dbPrice.getBrandId());
    }

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     * Integration test:
     *
     * Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void testGetApplicablePrice_Success_TestRequired_Test5() throws PriceNotFoundException, DateTimeFormatException {

        var price = pricingController.getPrice(requestedTest5.productId(), requestedTest5.brandId(), requestedTest5.applicationDate());
        var applyDate = LocalDateTime.parse(requestedTest5.applicationDate());

        var dbPrice = priceRepo.findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc
                (requestedTest5.productId(), requestedTest5.brandId(), applyDate, applyDate);

        assertNotNull(dbPrice);
        assertNotNull(price);
        assertEquals(38.95, dbPrice.getPrice());
        assertEquals(price.price(), dbPrice.getPrice());
        assertEquals(price.brandId(), dbPrice.getBrandId());
    }

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     * General Integration test:
     *
     */
    @Test
    void testGetApplicablePrice_Success() throws PriceNotFoundException, DateTimeFormatException {

        var price = pricingController.getPrice(realRequest.productId(), realRequest.brandId(), realRequest.applicationDate());
        var applyDate = LocalDateTime.parse(realRequest.applicationDate());

        var dbPrice = priceRepo.findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc
                (realRequest.productId(), realRequest.brandId(), applyDate, applyDate);

        assertNotNull(dbPrice);
        assertNotNull(price);
        assertEquals(REAL_PRICE, dbPrice.getPrice());
        assertEquals(REAL_PRODUCT_ID, price.productId());
        assertEquals(price.price(), dbPrice.getPrice());
        assertEquals(price.brandId(), dbPrice.getBrandId());
    }

    @Test
    void testGetApplicablePrice_PriceNotFoundException() throws PriceNotFoundException, DateTimeFormatException {

        var applyDate = LocalDateTime.parse(realRequestNoBrandId.applicationDate());
        var priceNotFound = assertThrows(
                PriceNotFoundException.class,
                () -> pricingController.getPrice(realRequestNoBrandId.productId(), realRequestNoBrandId.brandId(),
                        realRequestNoBrandId.applicationDate())
        );

        assertEquals(
                ExceptionMessage.PRICE_NOT_FOUND + ", productId: " + realRequestNoBrandId.productId() + ", brandId: "
                        + realRequestNoBrandId.brandId() + " and applicationDate: " + applyDate,
                priceNotFound.getMessage()
        );
    }

    @Test
    void testGetApplicablePrice_DateTimeFormatException() throws PriceNotFoundException, DateTimeFormatException {

        var dateFormatException = assertThrows(
                DateTimeFormatException.class,
                () -> pricingController.getPrice(realRequestWrongDate.productId(), realRequestWrongDate.brandId(),
                        realRequestWrongDate.applicationDate())
        );
        assertEquals(ExceptionMessage.WRONG_DATE_FORMAT, dateFormatException.getMessage());
    }

}
