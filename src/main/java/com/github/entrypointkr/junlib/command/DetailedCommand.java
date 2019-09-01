package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.exception.NoPermissionException;
import com.github.entrypointkr.junlib.command.util.Reader;

import java.util.List;
import java.util.function.Supplier;

public class DetailedCommand<T extends CommandSource> implements Command<T>, Detailed {
    private final Supplier<String> description;
    private final String permission;
    private final Command<T> command;
    private final Argument<?>[] arguments;

    public DetailedCommand(Supplier<String> description, String permission, Command<T> command, Argument<?>[] arguments) {
        this.description = description;
        this.permission = permission;
        this.command = command;
        this.arguments = arguments;
    }

    @Override
    public void execute(String label, T receiver, Reader<String> args) {
        if (permission != null && !receiver.hasPermission(permission)) {
            throw new NoPermissionException(permission);
        }
        command.execute(label, receiver, args);
    }

    @Override
    public List<String> tabComplete(T receiver, Reader<String> args) {
        return command.tabComplete(receiver, args);
    }

    @Override
    public Argument<?>[] arguments() {
        return arguments;
    }

    @Override
    public String description() {
        return description != null ? description.get() : null;
    }

    @Override
    public String permission() {
        return permission;
    }
}
