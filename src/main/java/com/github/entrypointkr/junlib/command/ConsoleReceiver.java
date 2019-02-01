package com.github.entrypointkr.junlib.command;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public class ConsoleReceiver implements CommandSource {
    private final boolean accessible;

    public ConsoleReceiver(boolean accessible) {
        this.accessible = accessible;
    }

    public ConsoleReceiver() {
        this(true);
    }

    @Override
    public void sendMessage(Object... messages) {
        for (Object message : messages) {
            System.out.println(message);
        }
    }

    @Override
    public boolean hasPermission(String permission) {
        return accessible;
    }
}
