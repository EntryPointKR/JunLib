package com.github.entrypointkr.junlib.command;

public class ArgumentException extends CommandException {
    private final Argument<?> argument;

    public ArgumentException(Executable source, Argument<?> argument) {
        super(source);
        this.argument = argument;
    }

    public ArgumentException(String message, Executable source, Argument<?> argument) {
        super(message, source);
        this.argument = argument;
    }

    public ArgumentException(String message, Argument<?> argument) {
        super(message);
        this.argument = argument;
    }

    public ArgumentException(String message, Throwable cause, Executable source, Argument<?> argument) {
        super(message, cause, source);
        this.argument = argument;
    }

    public ArgumentException(Throwable cause, Executable source, Argument<?> argument) {
        super(cause, source);
        this.argument = argument;
    }

    public ArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Executable source, Argument<?> argument) {
        super(message, cause, enableSuppression, writableStackTrace, source);
        this.argument = argument;
    }

    public Argument<?> getArgument() {
        return argument;
    }
}
