package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.util.Reader;

import java.util.Collections;
import java.util.List;

public class CommandAdaptor<T extends CommandSource> implements Command<T> {
    private final Argument<?>[] arguments;
    private final CommandExecutor<T> executor;

    public CommandAdaptor(Argument<?>[] arguments, CommandExecutor<T> executor) {
        this.arguments = arguments;
        this.executor = executor;
    }

    @Override
    public void execute(T receiver, Reader<String> args) {
        CommandArguments argumentMap = new CommandArguments();
        if (arguments != null) {
            for (Argument<?> argument : arguments) {
                if (args.remain() <= 0) {
                    break;
                }
                Object parsed = argument.parse(args);
                if (parsed == null) {
                    throw new ArgumentParseException(this, argument);
                }
                argumentMap.put(parsed, argument.getName());
            }
        }
        executor.execute(receiver, argumentMap);
    }

    @Override
    public List<String> tabComplete(T receiver, Reader<String> args) {
        return Collections.emptyList(); // TODO
    }
}
