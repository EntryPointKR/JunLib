package com.github.entrypointkr.junlib.bukkit.gui.handler;

import com.github.entrypointkr.junlib.bukkit.gui.GUI;
import org.bukkit.event.inventory.InventoryEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JunHyeong on 2018-10-29
 */
public class CombinedHandler implements GUIHandler<InventoryEvent> {
    private final List<GUIHandler<InventoryEvent>> listeners = new ArrayList<>();

    @Override
    public void onEvent(GUI gui, InventoryEvent e) {
        for (GUIHandler<InventoryEvent> listener : listeners) {
            listener.onEvent(gui, e);
        }
    }

    @SafeVarargs
    public final CombinedHandler add(GUIHandler<InventoryEvent>... listeners) {
        this.listeners.addAll(Arrays.asList(listeners));
        return this;
    }
}
