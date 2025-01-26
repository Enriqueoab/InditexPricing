package com.inditex.pricing.domain.validation;

import com.inditex.pricing.TestUtils;
import com.inditex.pricing.infrastructure.exception.DateTimeFormatException;
import com.inditex.pricing.infrastructure.validation.DataValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class DataValidatorTest extends TestUtils {

    /**
     * Method under test: {@link DataValidator#validateLocalDateTimeFormat(String)}
     */
    @Test
    void testValidateLocalDateTimeFormat_DateTimeFormatException() throws DateTimeFormatException {
        assertThrows(DateTimeFormatException.class, () -> DataValidator.validateLocalDateTimeFormat(WRONG_FORMAT_APPLICATION_DATE));
        assertTrue(DataValidator.validateLocalDateTimeFormat(APPLICATION_DATE));
    }

    @Test
    void testValidateLocalDateTimeFormat_Success() throws DateTimeFormatException {
        assertTrue(DataValidator.validateLocalDateTimeFormat(APPLICATION_DATE));
    }

}
