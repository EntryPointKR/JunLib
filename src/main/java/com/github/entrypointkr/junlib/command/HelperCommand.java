package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.util.Reader;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public class HelperCommand<T extends CommandSource> implements Command<T> {
    private final Command<T> command;
    private final CommandHelper helper;
    private String label = null;

    public HelperCommand(Command<T> command, CommandHelper helper) {
        this.command = command;
        this.helper = helper;
    }

    @Override
    public void execute(T receiver, Reader<String> args) {
        try {
            command.execute(receiver, args);
        } catch (CommandException ex) {
            String prev = Reader.getPreviousArguments(args);
            String prefix;
            if (StringUtils.isNotEmpty(label)) {
                prefix = prev.isEmpty() ? label : label + ' ' + prev;
            } else {
                prefix = prev;
            }
            helper.help(receiver, args, prefix, ex);
        }
    }

    @Override
    public List<String> tabComplete(T receiver, Reader<String> args) {
        return command.tabComplete(receiver, args);
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
