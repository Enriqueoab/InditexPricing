package com.inditex.pricing.infrastructure.db.service;

import com.inditex.pricing.TestUtils;
import com.inditex.pricing.domain.constants.ExceptionMessage;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.infrastructure.db.repo.PriceRepository;
import com.inditex.pricing.web.request.PriceRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class GetPriceAdapterTest extends TestUtils {
    @InjectMocks
    private GetPriceAdapter getPriceAdapter;

    @Mock
    private PriceRepository priceRepository;

    /**
     * Method under test: {@link GetPriceAdapter#getApplicablePrice(PriceRequest)}
     */
    @Test
    void testGetApplicablePrice_Success() throws PriceNotFoundException {
        var expectedPrice = new Price(REAL_BRAND_ID, REAL_PRODUCT_ID, 1, LocalDateTime.parse("2020-06-15T16:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), true, 38.95, "EUR");

        when(getPriceAdapter.getPrice(realRequest, LocalDateTime.parse(APPLICATION_DATE))).thenReturn(expectedPrice);

        var result = getPriceAdapter.getApplicablePrice(realRequest);

        assertEquals(expectedPrice, result);
    }

    @Test
    void testGetApplicablePrice_PriceNotFoundException() {

        when(getPriceAdapter.getPrice(realRequest, LocalDateTime.parse(realRequest.applicationDate()))).thenReturn(null);

        var exception = assertThrows(PriceNotFoundException.class, () -> {
            getPriceAdapter.getApplicablePrice(realRequest);
        });

        assertEquals(
                ExceptionMessage.PRICE_NOT_FOUND + ", productId: " + realRequest.productId() + ", brandId: "
                        + realRequest.brandId() + " and applicationDate: " + LocalDateTime.parse(realRequest.applicationDate()),
                exception.getMessage()
        );
    }

    /**
     * Method under test:
     * {@link GetPriceAdapter#getPrice(PriceRequest, LocalDateTime)}
     */
    @Test
    void testGetPrice() {
        Price price = new Price();
        when(priceRepository.findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(anyInt(),
                anyInt(), Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any())).thenReturn(price);

        Price actualPrice = getPriceAdapter.getPrice(realRequest, LocalDateTime.parse(APPLICATION_DATE));

        verify(priceRepository).findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(eq(REAL_PRODUCT_ID),
                eq(REAL_BRAND_ID), isA(LocalDateTime.class), isA(LocalDateTime.class));
        assertSame(price, actualPrice);
    }
}
