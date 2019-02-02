package com.github.entrypointkr.junlib.command;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public interface Combined {
    void forEach(BiConsumer<Command<?>, List<String>> consumer);

    default void deepForEach(StringBuilder prefix, BiConsumer<String, Command<?>> consumer) {
        forEach((cmd, args) -> {
            if (args.isEmpty()) {
                return;
            }
            String argument = args.get(0);
            int prevSize = prefix.length();
            if (prefix.length() > 0) {
                prefix.append(' ');
            }
            prefix.append(argument);
            if (cmd instanceof MapCommand) {
                ((MapCommand<?>) cmd).deepForEach(prefix, consumer);
            } else {
                consumer.accept(prefix.toString(), cmd);
            }
            prefix.setLength(prevSize);
        });
    }

    int size();
}
