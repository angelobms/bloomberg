package com.bloomberg.mathexpression.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {

    public static BigDecimal toBigDecimal(Double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
    }
}
