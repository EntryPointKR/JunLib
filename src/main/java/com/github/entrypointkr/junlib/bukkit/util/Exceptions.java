package com.github.entrypointkr.junlib.bukkit.util;

import com.github.entrypointkr.junlib.general.ThrowsRunnable;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by JunHyeong on 2018-11-03
 */
public class Exceptions {
    private Exceptions() {
    }

    public static void run(Consumer<Exception> consumer, ThrowsRunnable... runnables) {
        for (ThrowsRunnable runnable : runnables) {
            try {
                runnable.run();
            } catch (Exception ex) {
                consumer.accept(ex);
            }
        }
    }

    public static void run(ThrowsRunnable... runnables) {
        run(ex -> {
        }, runnables);
    }

    public static void runOrLog(Logger logger, ThrowsRunnable... runnables) {
        run(ex -> logger.log(Level.WARNING, ex, () -> "Exception was thrown"), runnables);
    }

    public static void runOrLog(Plugin plugin, ThrowsRunnable... runnables) {
        runOrLog(plugin.getLogger(), runnables);
    }
}
