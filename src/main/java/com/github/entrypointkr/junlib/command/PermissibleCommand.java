package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.util.ArrayReader;

/**
 * Created by JunHyeong on 2018-10-26
 */
public abstract class PermissibleCommand<T, U extends ArrayReader<String>> implements DetailedCommand<T, U> {
    public boolean checkPermission(T sender) {
        return true; // Hook
    }

    protected abstract void doExecute(T sender, U args);

    protected void onPermissionDenied(T sender, U args) {
        // Hook
    }

    @Override
    public final void execute(T sender, U args) {
        if (checkPermission(sender)) {
            doExecute(sender, args);
        } else {
            onPermissionDenied(sender, args);
        }
    }
}
