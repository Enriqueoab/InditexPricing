package com.inditex.pricing.web.controller;

import com.inditex.pricing.TestUtils;
import com.inditex.pricing.domain.constants.ExceptionMessage;
import com.inditex.pricing.infrastructure.db.repo.PriceRepository;
import com.inditex.pricing.infrastructure.exception.DateTimeFormatException;
import com.inditex.pricing.infrastructure.exception.PriceNotFoundException;
import com.inditex.pricing.infrastructure.web.controller.PricingController;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PricingControllerIntegrationTest extends TestUtils {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PriceRepository priceRepo;

    @Autowired
    private PricingController pricingController;

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     * Integration test:
     * <p>
     * Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void testGetApplicablePrice_Success_TestRequired_Test1() throws Exception {

        mockMvc.perform(get("/v1/prices")
                        .param("productId", String.valueOf(requestedTest1.productId()))
                        .param("brandId", String.valueOf(requestedTest1.brandId()))
                        .param("applicationDate", String.valueOf(requestedTest1.applicationDate()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value("35455"))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(35.5));
    }


    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     * Integration test:
     * <p>
     * Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void testGetApplicablePrice_Success_TestRequired_Test2() throws Exception {

        mockMvc.perform(get("/v1/prices")
                        .param("productId", String.valueOf(requestedTest2.productId()))
                        .param("brandId", String.valueOf(requestedTest2.brandId()))
                        .param("applicationDate", String.valueOf(requestedTest2.applicationDate()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value("35455"))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(25.45));

    }

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     * Integration test:
     * <p>
     * Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void testGetApplicablePrice_Success_TestRequired_Test3() throws Exception {

        mockMvc.perform(get("/v1/prices")
                        .param("productId", String.valueOf(requestedTest3.productId()))
                        .param("brandId", String.valueOf(requestedTest3.brandId()))
                        .param("applicationDate", String.valueOf(requestedTest3.applicationDate()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value("35455"))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(35.5));
    }

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     * Integration test:
     * <p>
     * Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void testGetApplicablePrice_Success_TestRequired_Test4() throws Exception {

        mockMvc.perform(get("/v1/prices")
                        .param("productId", String.valueOf(requestedTest4.productId()))
                        .param("brandId", String.valueOf(requestedTest4.brandId()))
                        .param("applicationDate", String.valueOf(requestedTest4.applicationDate()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value("35455"))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(30.5));
    }

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     * Integration test:
     * <p>
     * Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void testGetApplicablePrice_Success_TestRequired_Test5() throws Exception {

        mockMvc.perform(get("/v1/prices")
                        .param("productId", String.valueOf(requestedTest5.productId()))
                        .param("brandId", String.valueOf(requestedTest5.brandId()))
                        .param("applicationDate", String.valueOf(requestedTest5.applicationDate()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value("35455"))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(38.95));
    }

    /**
     * Method under test: {@link PricingController#getPrice(int, int, String)}
     * General Integration test:
     */
    @Test
    void testGetApplicablePrice_Success() throws Exception {

        mockMvc.perform(get("/v1/prices")
                        .param("productId", String.valueOf(requestedTest2.productId()))
                        .param("brandId", String.valueOf(requestedTest2.brandId()))
                        .param("applicationDate", String.valueOf(requestedTest2.applicationDate()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value("35455"))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    void testGetApplicablePrice_PriceNotFoundException() {

        var priceNotFound = assertThrows(
                PriceNotFoundException.class,
                () -> pricingController.getPrice(realRequestNoBrandId.productId(), realRequestNoBrandId.brandId(),
                        realRequestNoBrandId.applicationDate())
        );

        assertEquals(
                ExceptionMessage.PRICE_NOT_FOUND + ", productId: " + realRequestNoBrandId.productId() + ", brandId: "
                        + realRequestNoBrandId.brandId() + " and applicationDate: " + realRequestNoBrandId.applicationDate(),
                priceNotFound.getMessage()
        );
    }

    @Test
    void testGetApplicablePrice_DateTimeFormatException() {

        var dateFormatException = assertThrows(
                DateTimeFormatException.class,
                () -> pricingController.getPrice(realRequestWrongDate.productId(), realRequestWrongDate.brandId(),
                        realRequestWrongDate.applicationDate())
        );
        assertEquals(ExceptionMessage.WRONG_DATE_FORMAT, dateFormatException.getMessage());
    }

}
