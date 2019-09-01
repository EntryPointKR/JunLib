package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.exception.CommandException;
import com.github.entrypointkr.junlib.command.util.Reader;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public interface CommandHelper {
    void help(CommandSource receiver, Reader<String> args, String prefix, CommandException ex);
}
