package com.github.entrypointkr.junlib.bukkit.wizard;

import com.github.entrypointkr.junlib.bukkit.util.StringConverter;

import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-02
 */
public interface StringWizard extends Wizard<String> {
    default void runMap(Consumer<StringConverter> consumer) {
        runMap(StringConverter::new, consumer, (ex, str) -> ex.printStackTrace());
    }
}
