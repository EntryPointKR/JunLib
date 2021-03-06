package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.exception.ArgumentException;
import com.github.entrypointkr.junlib.command.exception.CommandException;
import com.github.entrypointkr.junlib.command.util.Reader;

import java.util.Collections;
import java.util.List;

public class CommandAdaptor<T extends CommandSource> implements Command<T> {
    private final Argument<?>[] arguments; // TODO: Composite interface?
    private final CommandExecutor<T> executor;

    public CommandAdaptor(Argument<?>[] arguments, CommandExecutor<T> executor) {
        this.arguments = arguments;
        this.executor = executor;
    }

    private void fail(Argument<?> failedArgument) {
        throw new ArgumentException(failedArgument);
    }

    private Object parse(Reader<String> args, Argument<?> argument) {
        try {
            Object parsed = argument.parse(args);
            if (parsed == null) {
                fail(argument);
            }
            return parsed;
        } catch (ArgumentException ex) {
            throw new CommandException(ex, this);
        }
    }

    @Override
    public void execute(String label, T receiver, Reader<String> args) {
        CommandArguments argumentMap = new CommandArguments();
        if (arguments != null) {
            for (Argument<?> argument : arguments) {
                if (args.isEmpty()) {
                    if (argument.isRequire()) {
                        fail(argument);
                    } else {
                        break;
                    }
                }
                int pos = args.getPosition();
                Object parsed = parse(args, argument);
                if (parsed != null) {
                    argumentMap.put(parsed, argument.getName());
                } else {
                    args.setPosition(pos);
                }
            }
        }
        executor.execute(receiver, argumentMap);
    }

    @Override
    public List<String> tabComplete(T receiver, Reader<String> args) {
        return Collections.emptyList(); // TODO
    }
}
