package com.inditex.pricing.infrastructure.db.service;

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

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class GetPriceAdapterTest {
    @InjectMocks
    private GetPriceAdapter getPriceAdapter;

    @Mock
    private PriceRepository priceRepository;

    /**
     * Method under test: {@link GetPriceAdapter#getApplicablePrice(PriceRequest)}
     */
    @Test
    void testGetApplicablePrice_Success() throws PriceNotFoundException {
        var request = new PriceRequest(1, 35455, "2023-06-14T10:00:00");
        var expectedPrice = new Price(35455, 1, 1, LocalDateTime.parse("2023-06-14T10:00:00"),
                LocalDateTime.parse("2023-06-14T23:59:59"), true, Double.valueOf(35.50), "");

        when(getPriceAdapter.getPrice(request, LocalDateTime.parse("2023-06-14T10:00:00"))).thenReturn(expectedPrice);

        var result = getPriceAdapter.getApplicablePrice(request);

        assertEquals(expectedPrice, result);
    }

    @Test
    void testGetApplicablePrice_PriceNotFoundException() {

        var request = new PriceRequest(1, 35455, "2020-06-15T16:30:00");

        when(getPriceAdapter.getPrice(request, LocalDateTime.parse(request.applicationDate()))).thenReturn(null);

        var exception = assertThrows(PriceNotFoundException.class, () -> {
            getPriceAdapter.getApplicablePrice(request);
        });

        assertEquals(
                ExceptionMessage.PRICE_NOT_FOUND + ", productId: " + request.productId() + ", brandId: "
                        + request.brandId() + " and applicationDate: " + LocalDateTime.parse(request.applicationDate()),
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
        PriceRequest request = new PriceRequest(1, 1, "2020-03-01");

        Price actualPrice = getPriceAdapter.getPrice(request, LocalDate.of(1970, 1, 1).atStartOfDay());

        verify(priceRepository).findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(eq(1),
                eq(1), isA(LocalDateTime.class), isA(LocalDateTime.class));
        assertSame(price, actualPrice);
    }
}
