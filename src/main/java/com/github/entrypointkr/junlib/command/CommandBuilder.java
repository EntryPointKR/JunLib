package com.github.entrypointkr.junlib.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CommandBuilder<T extends CommandSource> {
    private Supplier<String> description;
    private String permission;
    private CommandExecutor<T> executable;
    private Argument<?>[] arguments;
    private CommandHelper helper;
    private final Map<String, Command<T>> commandMap = new LinkedHashMap<>();

    public static <T extends CommandSource> CommandBuilder<T> of() {
        return new CommandBuilder<>();
    }

    public static CommandBuilder<BukkitSource> ofBukkit() {
        return of();
    }

    public CommandBuilder<T> desc(Supplier<String> desc) {
        this.description = desc;
        return this;
    }

    public CommandBuilder<T> desc(String description) {
        return desc(() -> description);
    }

    public CommandBuilder<T> perm(String permission) {
        this.permission = permission;
        return this;
    }

    public CommandBuilder<T> args(Argument<?>... arguments) {
        this.arguments = arguments;
        return this;
    }

    public CommandBuilder<T> executor(CommandExecutor<T> executor) {
        this.executable = executor;
        return this;
    }

    public CommandBuilder<T> child(Command<T> command, String... aliases) {
        for (String alias : aliases) {
            commandMap.put(alias, command);
        }
        return this;
    }

    public CommandBuilder<T> helper(CommandHelper helper) {
        this.helper = helper;
        return this;
    }

    public CommandBuilder<T> helper() {
        return helper(new DefaultCommandHelper());
    }

    public Command<T> build() {
        Command<T> command;
        if (commandMap.isEmpty()) {
            command = new DetailedCommand<>(description, permission, new CommandAdaptor<>(arguments, executable), arguments);
        } else {
            MapCommand<T> mapCommand = MapCommand.of(new LinkedHashMap<>(commandMap));
            if (executable != null) {
                mapCommand.defaultExecutor((label, receiver, args) -> {
                    CommandArguments arguments = new CommandArguments();
                    int prev = args.getPosition();
                    int index = 0;
                    while (args.remain() > 0) {
                        arguments.put(index++, args.read());
                    }
                    args.setPosition(prev);
                    executable.execute(receiver, arguments);
                });
            }
            command = mapCommand;
        }
        return helper != null ? new HelperCommand<>(command, helper) : command;
    }
}
