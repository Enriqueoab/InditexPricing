package com.inditex.pricing.web.controller;

import com.inditex.pricing.application.service.GetPriceUseCaseAdapter;
import com.inditex.pricing.domain.constants.ExceptionMessage;
import com.inditex.pricing.domain.exception.DateTimeFormatException;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.infrastructure.db.repo.PriceRepository;
import com.inditex.pricing.web.request.PriceRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PricingControllerIntegrationTest {

    @Mock
    private GetPriceUseCaseAdapter pricingService;

    @Autowired
    private PriceRepository priceRepo;

    @Autowired
    private PricingController pricingController;

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     * Integration test
     */
    @Test
    void testGetApplicablePrice_Success() throws PriceNotFoundException, DateTimeFormatException {

        var request = new PriceRequest(35455, 1, "2020-06-15T16:30:00");

        var price = pricingController.getPrice(request.productId(), request.brandId(), request.applicationDate());
        System.err.println(price);
        var applyDate = LocalDateTime.parse(request.applicationDate());

        var dbPrice = priceRepo.findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc
                (request.productId(), request.brandId(), applyDate, applyDate);

        assertNotNull(dbPrice);
        assertNotNull(price);
        assertEquals(Double.valueOf(38.95), dbPrice.getPrice());
        assertEquals(35455, price.productId());
        assertEquals(price.price(), dbPrice.getPrice());
        assertEquals(price.brandId(), dbPrice.getBrandId());
    }

    @Test
    void testGetApplicablePrice_PriceNotFoundException() throws PriceNotFoundException, DateTimeFormatException {
        var request = new PriceRequest(35455, 2, "2020-06-15T16:30:00");

        var applyDate = LocalDateTime.parse(request.applicationDate());

        var priceNotFound = assertThrows(
                PriceNotFoundException.class,
                () -> pricingController.getPrice(request.productId(), request.brandId(), request.applicationDate())
        );

        assertEquals(
                ExceptionMessage.PRICE_NOT_FOUND+", productId: " + request.productId() +", brandId: "
                        + request.brandId() +" and applicationDate: "+ applyDate,

                priceNotFound.getMessage()
        );
    }

    @Test
    void testGetApplicablePrice_DateTimeFormatException() throws PriceNotFoundException, DateTimeFormatException {
        var request = new PriceRequest(35455, 1, "2020-06-15");


        var dateFormatException = assertThrows(
                DateTimeFormatException.class,
                () -> pricingController.getPrice(request.productId(), request.brandId(), request.applicationDate())
        );
        assertEquals(ExceptionMessage.WRONG_DATE_FORMAT, dateFormatException.getMessage());
    }

}
