package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.util.Reader;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public class PrintCommand implements Command<CommandSource> {
    private final String message;

    public PrintCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(String label, CommandSource receiver, Reader<String> args) {
        System.out.println(message);
    }
}
