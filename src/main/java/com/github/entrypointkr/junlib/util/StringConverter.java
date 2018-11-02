package com.github.entrypointkr.junlib.util;

import org.apache.commons.lang.math.NumberUtils;

/**
 * Created by JunHyeong on 2018-11-03
 */
public class StringConverter {
    private final String string;

    public StringConverter(String string) {
        this.string = string;
    }

    public Converter<Number> number() {
        return Converter.ofCommand(string, string -> {
            try {
                return NumberUtils.createNumber(string);
            } catch (Exception ex) {
                return null;
            }
        }, str -> str + " 는 숫자가 아닙니다.");
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return "StringConverter{" +
                "string='" + string + '\'' +
                '}';
    }
}
