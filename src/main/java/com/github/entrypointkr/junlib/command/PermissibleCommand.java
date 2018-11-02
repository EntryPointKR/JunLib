package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.util.ArrayReader;

/**
 * Created by JunHyeong on 2018-10-26
 */
public interface PermissibleCommand<T, U extends ArrayReader<String>> extends DetailedCommand<T, U> {
    default boolean checkPermission(T sender) {
        return true;
    }

    void doExecute(T sender, U args);

    @Override
    default void execute(T sender, U args) {
        if (checkPermission(sender)) {
            doExecute(sender, args);
        }
    }
}
