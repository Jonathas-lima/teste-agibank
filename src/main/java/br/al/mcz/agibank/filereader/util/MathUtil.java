package br.al.mcz.agibank.filereader.util;

import java.math.BigDecimal;

public class MathUtil {

    public static BigDecimal multiplicar(Integer a, BigDecimal b) {
        return BigDecimal.valueOf(a).multiply(b);
    }
}
