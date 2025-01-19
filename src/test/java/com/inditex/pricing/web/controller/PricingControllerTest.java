package com.inditex.pricing.web.controller;

import com.inditex.pricing.application.ports.in.GetPriceUseCase;
import com.inditex.pricing.domain.exception.DateTimeFormatException;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.web.request.PriceRequest;
import com.inditex.pricing.web.response.PriceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class PricingControllerTest {

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     */
    @Test
    void testGetPrice() throws DateTimeFormatException, PriceNotFoundException {

        var getPriceUseCase = mock(GetPriceUseCase.class);
        var startDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        var priceResponse = new PriceResponse(1, 1, startDate, LocalDate.of(1970, 1, 1).atStartOfDay(), 10.0d);

        when(getPriceUseCase.getApplicablePrice(Mockito.<PriceRequest>any())).thenReturn(priceResponse);

        var actualPrice = (new PricingController(getPriceUseCase)).getPrice(1, 1, "2020-03-01");

        verify(getPriceUseCase).getApplicablePrice(isA(PriceRequest.class));
        assertSame(priceResponse, actualPrice);
    }

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     */
    @Test
    void testGetPrice_NotFoundException() throws DateTimeFormatException, PriceNotFoundException {

        var getPriceUseCase = mock(GetPriceUseCase.class);
        when(getPriceUseCase.getApplicablePrice(Mockito.<PriceRequest>any()))
                .thenThrow(new PriceNotFoundException("An error occurred"));

        assertThrows(PriceNotFoundException.class,
                () -> (new PricingController(getPriceUseCase)).getPrice(1, 1, "2020-03-01"));
        verify(getPriceUseCase).getApplicablePrice(isA(PriceRequest.class));
    }

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     */
    @Test
    void testGetPrice_DateTimeFormatException() throws DateTimeFormatException, PriceNotFoundException {

        var getPriceUseCase = mock(GetPriceUseCase.class);
        when(getPriceUseCase.getApplicablePrice(Mockito.<PriceRequest>any()))
                .thenThrow(new DateTimeFormatException("Wrong date format"));

        assertThrows(DateTimeFormatException.class,
                () -> (new PricingController(getPriceUseCase)).getPrice(35455, 1, "2020/03/01"));
        verify(getPriceUseCase).getApplicablePrice(isA(PriceRequest.class));
    }

}
