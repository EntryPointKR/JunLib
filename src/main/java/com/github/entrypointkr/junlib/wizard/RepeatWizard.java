package com.github.entrypointkr.junlib.wizard;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-02
 */
public class RepeatWizard<T> implements Wizard<T> {
    private final Wizard<T> wizard;
    private final int loopCount;

    public RepeatWizard(Wizard<T> wizard, int loopCount) {
        this.wizard = wizard;
        this.loopCount = loopCount;
    }

    @Override
    public void run(Consumer<T> consumer) {
        AtomicInteger counter = new AtomicInteger();
        wizard.run(new Consumer<T>() {
            @Override
            public void accept(T data) {
                consumer.accept(data);
                int count = counter.incrementAndGet();
                if (loopCount > count) {
                    wizard.run(this);
                }
            }
        });
    }
}
