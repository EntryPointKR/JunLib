package com.github.entrypointkr.junlib.command;

/**
 * Created by JunHyeong Lim on 2019-01-18
 */
public interface CommandSource {
    void sendMessage(Object... messages);

    boolean hasPermission(String permission);
}
