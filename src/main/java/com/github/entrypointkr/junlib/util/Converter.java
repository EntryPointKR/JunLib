package com.github.entrypointkr.junlib.util;

import org.bukkit.command.CommandException;

import java.util.Optional;
import java.util.function.Function;

/**
 * Created by JunHyeong on 2018-10-26
 */
public class Converter<T> {
    private final Function<String, T> converter;
    private final Function<String, RuntimeException> exceptionFactory;
    private final Function<String, String> defaultMessageFunc;
    private String string;

    private Converter(String string, Function<String, T> converter, Function<String, RuntimeException> exceptionFactory, Function<String, String> defaultMessageFunc) {
        this.string = string;
        this.converter = converter;
        this.exceptionFactory = exceptionFactory;
        this.defaultMessageFunc = defaultMessageFunc;
    }

    public static <T> Converter<T> ofCommand(String string, Function<String, T> converter, Function<String, String> defaultMessageFunc) {
        return new Converter<>(string, converter, CommandException::new, defaultMessageFunc);
    }

    public static <T> Converter<T> of(String string, Function<String, T> converter, Function<String, RuntimeException> exceptionFactory, Function<String, String> defaultMessageFunc) {
        return new Converter<>(string, converter, exceptionFactory, defaultMessageFunc);
    }

    public Optional<T> getOptional() {
        return string != null
                ? Optional.ofNullable(converter.apply(string))
                : Optional.empty();
    }

    public T get(Function<String, String> messageFunc) {
        return getOptional().orElseThrow(() -> exceptionFactory.apply(messageFunc.apply(string)));
    }

    public T get() {
        return get(defaultMessageFunc);
    }

    public <U> Optional<U> map(Function<T, Converter<U>> mapper) {
        return getOptional().map(mapper).flatMap(Converter::getOptional);
    }
}
