package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.util.Reader;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public interface Executable<T extends CommandSource> {
    void execute(String label, T receiver, Reader<String> args);
}
