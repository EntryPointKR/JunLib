package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.exception.CommandException;
import com.github.entrypointkr.junlib.command.util.Reader;

import java.util.List;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public class HelperCommand<T extends CommandSource> implements Command<T> {
    private final Command<T> command;
    private final CommandHelper helper;

    public HelperCommand(Command<T> command, CommandHelper helper) {
        this.command = command;
        this.helper = helper;
    }

    @Override
    public void execute(String label, T receiver, Reader<String> args) {
        try {
            command.execute(label, receiver, args);
        } catch (CommandException ex) {
            String prev = args.getPreviousArguments();
            String prefix = prev.isEmpty() ? label : label + ' ' + prev;
            helper.help(receiver, args, prefix, ex);
        }
    }

    @Override
    public List<String> tabComplete(T receiver, Reader<String> args) {
        return command.tabComplete(receiver, args);
    }
}
