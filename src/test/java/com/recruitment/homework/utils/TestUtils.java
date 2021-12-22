package com.recruitment.homework.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestUtils {

    public static BigDecimal bd(int number) {
        return BigDecimal.valueOf(number).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal bd(double number) {
        return BigDecimal.valueOf(number).setScale(2, RoundingMode.HALF_UP);
    }
}
