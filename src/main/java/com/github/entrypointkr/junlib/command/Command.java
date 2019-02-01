package com.github.entrypointkr.junlib.command;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public interface Command<T extends CommandSource>
        extends Executable<T>, TabCompletable<T> {
}
