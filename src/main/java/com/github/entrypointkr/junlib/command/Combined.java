package com.github.entrypointkr.junlib.command;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public interface Combined {
    void forEach(StringBuilder argumentsBuilder, BiConsumer<Command<?>, List<String>> consumer);

    default void forEach(BiConsumer<Command<?>, List<String>> consumer) {
        forEach(new StringBuilder(), consumer);
    }

    int size();
}
