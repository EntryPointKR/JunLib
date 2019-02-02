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

    public <T> Optional<T> get(String key, Class<T> type) {
        return Optional.ofNullable(arguments.get(key))
                .filter(type::isInstance)
                .map(type::cast);
    }

    public <T> T getOrNull(String key, Class<T> type) {
        return get(key, type).orElse(null);
    }
}
