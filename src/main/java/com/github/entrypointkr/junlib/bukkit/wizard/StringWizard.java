package com.github.entrypointkr.junlib.bukkit.wizard;

import com.github.entrypointkr.junlib.bukkit.util.BukkitStringConverter;
import com.github.entrypointkr.junlib.wizard.Wizard;

import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-02
 */
public interface StringWizard extends Wizard<String> {
    default void runMap(Consumer<BukkitStringConverter> consumer) {
        runMap(BukkitStringConverter::new, consumer, (ex, str) -> ex.printStackTrace());
    }
}
