package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.util.Reader;

import java.util.List;

public class DetailedCommand<T extends CommandSource> implements Command<T>, Detailed {
    private final String description;
    private final String permission;
    private final Command<T> command;
    private final Argument<?>[] arguments;

    public DetailedCommand(String description, String permission, Command<T> command, Argument<?>[] arguments) {
        this.description = description;
        this.permission = permission;
        this.command = command;
        this.arguments = arguments;
    }

    @Override
    public void execute(T receiver, Reader<String> args) {
        if (permission != null && !receiver.hasPermission(permission)) {
            throw new NoPermissionException(this, permission);
        }
        command.execute(receiver, args);
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
        return description;
    }

    @Override
    public String permission() {
        return permission;
    }
}
