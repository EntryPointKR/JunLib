package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.exception.NoPermissionException;

/**
 * Created by JunHyeong Lim on 2019-01-18
 */
public interface CommandSource {
    void sendMessage(Object... messages);

    boolean hasPermission(String permission);

    boolean isOp();

    default void checkPermission(String permission) {
        if (!hasPermission(permission)) {
            throw new NoPermissionException(permission);
        }
    }
}
