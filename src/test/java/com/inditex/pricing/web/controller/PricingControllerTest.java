package com.inditex.pricing.web.controller;

import com.inditex.pricing.TestUtils;
import com.inditex.pricing.application.ports.in.GetPriceUseCase;
import com.inditex.pricing.domain.constants.ExceptionMessage;
import com.inditex.pricing.domain.exception.DateTimeFormatException;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.web.request.PriceRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class PricingControllerTest extends TestUtils {

    @Mock
    private GetPriceUseCase getPriceUseCase;

    @InjectMocks
    private PricingController pricingController;

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     */
    @Test
    void testGetPrice() throws DateTimeFormatException, PriceNotFoundException {

        when(getPriceUseCase.getApplicablePrice(Mockito.<PriceRequest>any())).thenReturn(priceResponse);

        var actualPrice = pricingController.getPrice(PRODUCT_ID, BRAND_ID, APPLICATION_DATE);

        verify(getPriceUseCase).getApplicablePrice(isA(PriceRequest.class));
        assertSame(priceResponse, actualPrice);
    }

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     */
    @Test
    void testGetPrice_NotFoundException() throws DateTimeFormatException, PriceNotFoundException {

        when(getPriceUseCase.getApplicablePrice(Mockito.<PriceRequest>any()))
                .thenThrow(new PriceNotFoundException(ExceptionMessage.PRICE_NOT_FOUND));

        assertThrows(PriceNotFoundException.class,
                () -> pricingController.getPrice(PRODUCT_ID, NOT_FOUND_BRAND_ID, APPLICATION_DATE));
        verify(getPriceUseCase).getApplicablePrice(isA(PriceRequest.class));
    }

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     */
    @Test
    void testGetPrice_DateTimeFormatException() throws DateTimeFormatException, PriceNotFoundException {

        when(getPriceUseCase.getApplicablePrice(Mockito.<PriceRequest>any()))
                .thenThrow(new DateTimeFormatException(ExceptionMessage.WRONG_DATE_FORMAT));

        assertThrows(DateTimeFormatException.class,
                () -> pricingController.getPrice(PRODUCT_ID, BRAND_ID, WRONG_FORMAT_APPLICATION_DATE));
        verify(getPriceUseCase).getApplicablePrice(isA(PriceRequest.class));
    }

}
