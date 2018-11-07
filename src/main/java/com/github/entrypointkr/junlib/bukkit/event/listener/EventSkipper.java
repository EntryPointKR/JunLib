package com.github.entrypointkr.junlib.bukkit.event.listener;

import org.bukkit.event.inventory.InventoryEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by JunHyeong on 2018-11-07
 */
public class EventSkipper implements GUINotifier {
    private final GUINotifier listener;
    private final Set<Class> skips = new HashSet<>();

    public EventSkipper(GUINotifier listener) {
        this.listener = listener;
    }

    public EventSkipper add(Class... skips) {
        this.skips.addAll(Arrays.asList(skips));
        return this;
    }

    public EventSkipper remove(Class... skips) {
        this.skips.removeAll(Arrays.asList(skips));
        return this;
    }

    @Override
    public void onEvent(InventoryEvent event) {
        if (!skips.remove(event.getClass())) {
            listener.onEvent(event);
        }
    }
}
