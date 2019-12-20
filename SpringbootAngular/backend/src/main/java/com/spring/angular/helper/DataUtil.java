package com.spring.angular.helper;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DataUtil {

    public static Long safeToLong(Object obj1, Long defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }
        if (obj1 instanceof BigDecimal) {
            return ((BigDecimal) obj1).longValue();
        }
        if (obj1 instanceof BigInteger) {
            return ((BigInteger) obj1).longValue();
        }

        try {
            return Long.parseLong(obj1.toString());
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static Long safeToLong(Object obj1) {
        return safeToLong(obj1, 0L);
    }
}
