package com.inditex.pricing.domain.validation;

import com.inditex.pricing.application.service.GetPriceUseCaseAdapter;
import com.inditex.pricing.domain.constants.ExceptionMessage;
import com.inditex.pricing.domain.constants.Regex;
import com.inditex.pricing.domain.exception.DateTimeFormatException;
import com.inditex.pricing.infrastructure.LoggerConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class DataValidator {

    private static final Logger logger = LogManager.getLogger(DataValidator.class);

    public static boolean validateLocalDateTimeFormat(String dateTime) throws DateTimeFormatException {
        logger.info("Validating date: {}", dateTime);
        var regex = Regex.ISO8601_DATE_FORMAT;

        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(dateTime);
        if (matcher.matches()) {
            return matcher.matches();
        }

        throw new DateTimeFormatException(ExceptionMessage.WRONG_DATE_FORMAT);
    }

}
