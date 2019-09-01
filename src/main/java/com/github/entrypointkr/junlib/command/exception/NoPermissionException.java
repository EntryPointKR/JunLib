package com.github.entrypointkr.junlib.command.exception;

import com.github.entrypointkr.junlib.command.InternalException;

@InternalException
public class NoPermissionException extends RuntimeException {
    private final String permission;

    public NoPermissionException(String permission) {
        this.permission = permission;
    }

    public NoPermissionException(String message, String permission) {
        super(message);
        this.permission = permission;
    }

    public NoPermissionException(String message, Throwable cause, String permission) {
        super(message, cause);
        this.permission = permission;
    }

    public NoPermissionException(Throwable cause, String permission) {
        super(cause);
        this.permission = permission;
    }

    public NoPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String permission) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
