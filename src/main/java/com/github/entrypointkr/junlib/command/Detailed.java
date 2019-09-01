package com.github.entrypointkr.junlib.command;

public interface Detailed {
    Argument<?>[] arguments();

    default Argument<?>[] dynamicArguments(CommandSource source) {
        return arguments();
    }

    String description();

    String permission();
}
