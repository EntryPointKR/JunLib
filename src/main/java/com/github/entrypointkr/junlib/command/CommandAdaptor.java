package com.github.entrypointkr.junlib.command;

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
        throw new ArgumentException(this, failedArgument);
    }

    private Object parse(Reader<String> args, Argument<?> argument) {
        try {
            Object parsed = argument.parse(args);
            if (parsed == null) {
                fail(argument);
            }
            return parsed;
        } catch (ArgumentException ex) {
            ex.setSource(this);
            throw ex;
        }
    }

    @Override
    public void execute(T receiver, Reader<String> args) {
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
                Object parsed = parse(args, argument);
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
