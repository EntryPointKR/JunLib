package com.github.entrypointkr.junlib.util;

public class Cooldown {
    private long last = 0;

    public boolean action(long coolMillis) {
        if (coolMillis <= 0) {
            return true;
        }
        long now = System.currentTimeMillis();
        long diff = now - last;
        if (diff >= coolMillis) {
            last = now;
            return true;
        }
        return false;
    }

    public long getLast() {
        return last;
    }
}
