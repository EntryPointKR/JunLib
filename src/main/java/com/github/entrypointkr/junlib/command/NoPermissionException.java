package com.github.entrypointkr.junlib.command;

public class NoPermissionException extends CommandException {
    private final String permission;

    public NoPermissionException(Executable source, String permission) {
        super(source);
        this.permission = permission;
    }

    public NoPermissionException(String message, Executable source, String permission) {
        super(message, source);
        this.permission = permission;
    }

    public NoPermissionException(String message, String permission) {
        super(message);
        this.permission = permission;
    }

    public NoPermissionException(String message, Throwable cause, Executable source, String permission) {
        super(message, cause, source);
        this.permission = permission;
    }

    public NoPermissionException(Throwable cause, Executable source, String permission) {
        super(cause, source);
        this.permission = permission;
    }

    public NoPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Executable source, String permission) {
        super(message, cause, enableSuppression, writableStackTrace, source);
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
