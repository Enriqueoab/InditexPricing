package com.inditex.pricing.domain.validation;

import com.inditex.pricing.domain.exception.DateTimeFormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class DataValidatorTest {

    /**
     * Method under test: {@link DataValidator#validateLocalDateTimeFormat(String)}
     */
    @Test
    void testValidateLocalDateTimeFormat_DateTimeFormatException() throws DateTimeFormatException {
        assertThrows(DateTimeFormatException.class, () -> DataValidator.validateLocalDateTimeFormat("2020-03-01"));
        assertTrue(DataValidator.validateLocalDateTimeFormat("9999-99-99T99:99:99"));
    }

    @Test
    void testValidateLocalDateTimeFormat_Success() throws DateTimeFormatException {
        assertTrue(DataValidator.validateLocalDateTimeFormat("2020-06-15T16:30:00"));
    }

}
