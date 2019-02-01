package com.github.entrypointkr.junlib.command;

public interface Detailed {
    Argument<?>[] arguments();

    String description();

    String permission();
}
