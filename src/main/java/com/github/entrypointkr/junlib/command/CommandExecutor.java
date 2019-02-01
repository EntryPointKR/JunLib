package com.github.entrypointkr.junlib.command;

public interface CommandExecutor<T extends CommandSource> {
    void execute(T sender, CommandArguments args);
}
