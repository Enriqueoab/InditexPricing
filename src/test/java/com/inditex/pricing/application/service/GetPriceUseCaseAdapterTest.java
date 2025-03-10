package com.inditex.pricing.application.service;

import com.inditex.pricing.TestUtils;
import com.inditex.pricing.application.ports.out.GetPricePort;
import com.inditex.pricing.domain.constants.ExceptionMessage;
import com.inditex.pricing.infrastructure.exception.DateTimeFormatException;
import com.inditex.pricing.infrastructure.exception.PriceNotFoundException;
import com.inditex.pricing.infrastructure.web.request.PriceRequest;
import com.inditex.pricing.infrastructure.web.response.PriceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class GetPriceUseCaseAdapterTest extends TestUtils {
    @Mock
    private GetPricePort getPricePort;

    @InjectMocks
    private GetPriceUseCaseAdapter getPriceUseCaseAdapter;

    /**
     * Method under test:
     * {@link GetPriceUseCaseAdapter#getApplicablePrice(PriceRequest)}
     */
    @Test
    void testGetApplicablePrice() throws DateTimeFormatException, PriceNotFoundException {
        when(getPricePort.getApplicablePrice(Mockito.<PriceRequest>any())).thenReturn(price);

        PriceResponse actualApplicablePrice = getPriceUseCaseAdapter
                .getApplicablePrice(new PriceRequest(PRODUCT_ID, BRAND_ID, "9999-99-99T99:99:99"));

        verify(getPricePort).getApplicablePrice(isA(PriceRequest.class));
        assertNotNull(actualApplicablePrice.endDate());
        assertNotNull(actualApplicablePrice.startDate());
        assertEquals(price.getBrandId(), actualApplicablePrice.brandId());
        assertEquals(price.getProductId(), actualApplicablePrice.productId());
        assertEquals(price.getPrice(), actualApplicablePrice.price());
    }

    /**
     * Method under test:
     * {@link GetPriceUseCaseAdapter#getApplicablePrice(PriceRequest)}
     */
    @Test
    void testGetApplicablePrice_PriceNotFoundException() throws PriceNotFoundException {

        when(getPricePort.getApplicablePrice(Mockito.<PriceRequest>any()))
                .thenThrow(new PriceNotFoundException(ExceptionMessage.PRICE_NOT_FOUND));

        assertThrows(PriceNotFoundException.class,
                () -> getPriceUseCaseAdapter.getApplicablePrice(new PriceRequest(1, 1, "9999-99-99T99:99:99")));
        verify(getPricePort).getApplicablePrice(isA(PriceRequest.class));
    }

}
