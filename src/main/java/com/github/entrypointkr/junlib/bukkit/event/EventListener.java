package com.github.entrypointkr.junlib.bukkit.event;

import org.bukkit.event.Event;

/**
 * Created by JunHyeong on 2018-11-02
 */
public interface EventListener<E extends Event> {
    void onEvent(E event);
}
