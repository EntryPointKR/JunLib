package com.github.entrypointkr.junlib.command.exception;

import com.github.entrypointkr.junlib.command.Executable;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public final class CommandException extends RuntimeException {
    private Executable source;

    public CommandException(Executable source) {
        this.source = source;
    }

    public CommandException(String message, Executable source) {
        super(message);
        this.source = source;
    }

    public CommandException(String message) {
        this(message, null);
    }

    public CommandException(String message, Throwable cause, Executable source) {
        super(message, cause);
        this.source = source;
    }

    public CommandException(Throwable cause, Executable source) {
        super(cause);
        this.source = source;
    }

    public CommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Executable source) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.source = source;
    }

    public Executable getSource() {
        return source;
    }

    public void setSource(Executable source) {
        this.source = source;
    }
}
