package com.inditex.pricing.domain.validation;

import com.inditex.pricing.domain.constants.ExceptionMessage;
import com.inditex.pricing.domain.constants.Regex;
import com.inditex.pricing.domain.exception.DateTimeFormatException;
import java.util.regex.Pattern;

public class DataValidator {

    public static boolean validateLocalDateTimeFormat(String dateTime) throws DateTimeFormatException {
        var regex = Regex.ISO8601_DATE_FORMAT;

        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(dateTime);
        if (matcher.matches()) {
            return matcher.matches();
        }

        throw new DateTimeFormatException(ExceptionMessage.WRONG_DATE_FORMAT);
    }

}
