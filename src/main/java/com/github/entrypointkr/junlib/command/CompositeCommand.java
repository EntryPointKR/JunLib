package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.util.Reader;

import java.util.List;

public class CompositeCommand<T extends CommandSource> implements Command<T> {
    private final Executable<T> executor;
    private final TabCompletable<T> tabCompleter;

    public CompositeCommand(Executable<T> executor, TabCompletable<T> tabCompleter) {
        this.executor = executor;
        this.tabCompleter = tabCompleter;
    }

    @Override
    public void execute(String label, T receiver, Reader<String> args) {
        executor.execute(label, receiver, args);
    }

    @Override
    public List<String> tabComplete(T receiver, Reader<String> args) {
        return tabCompleter.tabComplete(receiver, args);
    }
}
