package com.github.entrypointkr.junlib.bukkit.event.listener;

import com.github.entrypointkr.junlib.bukkit.gui.GUI;
import com.github.entrypointkr.junlib.bukkit.gui.handler.GUIHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by JunHyeong on 2018-11-07
 */
public class EventSkipper implements GUIHandler<InventoryEvent> {
    private final GUIHandler<InventoryEvent> handler;
    private final Set<Class> skips = new HashSet<>();

    public EventSkipper(GUIHandler<InventoryEvent> handler) {
        this.handler = handler;
    }

    public static EventSkipper of(GUIHandler<InventoryEvent> handler) {
        return new EventSkipper(handler);
    }

    public static EventSkipper ofParent(GUI parent, Player owner, GUIHandler<InventoryEvent> handler) {
        return new EventSkipper(new GUIParent(handler, parent, owner));
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
    public void onEvent(GUI gui, InventoryEvent e) {
        if (!skips.remove(e.getClass())) {
            handler.onEvent(gui, e);
        }
    }
}
