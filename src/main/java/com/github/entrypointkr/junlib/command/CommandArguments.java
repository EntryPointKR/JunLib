package com.github.entrypointkr.junlib.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandArguments {
    private final Map<String, Object> arguments = new HashMap<>();

    public CommandArguments put(Object value, String... keys) {
        for (String key : keys) {
            arguments.put(key, value);
        }
        return this;
    }

    public <T> T get(String key, Class<T> type) {
        return getOptional(key).map(type::cast).get();
    }

    public Optional<Object> getOptional(String key) {
        return Optional.ofNullable(arguments.get(key));
    }
}
