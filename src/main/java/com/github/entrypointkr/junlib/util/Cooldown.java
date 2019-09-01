package com.github.entrypointkr.junlib.util;

public class Cooldown {
    private long coolMillis;
    private long next = 0;

    public Cooldown(long coolMillis) {
        this.coolMillis = coolMillis;
    }

    public boolean action() {
        if (coolMillis <= 0) {
            return true;
        }
        if (next <= System.currentTimeMillis()) {
            next = System.currentTimeMillis() + coolMillis;
            return true;
        }
        return false;
    }

    public long getCoolMillis() {
        return coolMillis;
    }

    public void setCoolMillis(long coolMillis) {
        this.coolMillis = coolMillis;
    }
}
