package com.github.entrypointkr.junlib.command;

public class ArgumentParseException extends CommandException {
    private final Argument<?> argument;

    public ArgumentParseException(Executable source, Argument<?> argument) {
        super(source);
        this.argument = argument;
    }

    public ArgumentParseException(String message, Executable source, Argument<?> argument) {
        super(message, source);
        this.argument = argument;
    }

    public ArgumentParseException(String message, Argument<?> argument) {
        super(message);
        this.argument = argument;
    }

    public ArgumentParseException(String message, Throwable cause, Executable source, Argument<?> argument) {
        super(message, cause, source);
        this.argument = argument;
    }

    public ArgumentParseException(Throwable cause, Executable source, Argument<?> argument) {
        super(cause, source);
        this.argument = argument;
    }

    public ArgumentParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Executable source, Argument<?> argument) {
        super(message, cause, enableSuppression, writableStackTrace, source);
        this.argument = argument;
    }

    public Argument<?> getArgument() {
        return argument;
    }
}
