package com.github.entrypointkr.junlib.command.exception;

import com.github.entrypointkr.junlib.command.Argument;
import com.github.entrypointkr.junlib.command.InternalException;

@InternalException
public class ArgumentException extends RuntimeException {
    private final Argument<?> argument;

    public ArgumentException(Argument<?> argument) {
        this.argument = argument;
    }

    public ArgumentException(String message, Argument<?> argument) {
        super(message);
        this.argument = argument;
    }

    public ArgumentException(String message, Throwable cause, Argument<?> argument) {
        super(message, cause);
        this.argument = argument;
    }

    public ArgumentException(Throwable cause, Argument<?> argument) {
        super(cause);
        this.argument = argument;
    }

    public ArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Argument<?> argument) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.argument = argument;
    }

    public Argument<?> getArgument() {
        return argument;
    }
}
