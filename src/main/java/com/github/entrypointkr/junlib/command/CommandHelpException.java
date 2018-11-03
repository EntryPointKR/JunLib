package com.github.entrypointkr.junlib.command;

import org.bukkit.command.CommandException;

/**
 * Created by JunHyeong on 2018-11-03
 */
public class CommandHelpException extends CommandException {
    private final boolean fullHelpRequire;

    public CommandHelpException(boolean fullHelpRequire) {
        this.fullHelpRequire = fullHelpRequire;
    }

    public CommandHelpException() {
        this(false);
    }

    public CommandHelpException(String msg, boolean fullHelpRequire) {
        super(msg);
        this.fullHelpRequire = fullHelpRequire;
    }

    public CommandHelpException(String msg) {
        this(msg, false);
    }

    public CommandHelpException(String msg, Throwable cause, boolean fullHelpRequire) {
        super(msg, cause);
        this.fullHelpRequire = fullHelpRequire;
    }

    public boolean isFullHelpRequire() {
        return fullHelpRequire;
    }
}
