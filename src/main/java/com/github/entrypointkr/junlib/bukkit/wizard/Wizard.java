package com.github.entrypointkr.junlib.bukkit.wizard;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by JunHyeong on 2018-11-02
 */
public interface Wizard<T> {
    void run(Consumer<T> consumer);

    default <U> void runMap(Function<T, U> mapper, Consumer<U> consumer, BiConsumer<Exception, T> failure) {
        run(data -> {
            try {
                consumer.accept(mapper.apply(data));
            } catch (Exception ex) {
                failure.accept(ex, data);
            }
        });
    }
}
