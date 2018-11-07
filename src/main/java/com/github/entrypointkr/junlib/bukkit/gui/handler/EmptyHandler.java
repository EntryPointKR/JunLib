package com.github.entrypointkr.junlib.bukkit.gui.handler;

import com.github.entrypointkr.junlib.bukkit.gui.GUI;
import org.bukkit.event.inventory.InventoryEvent;

/**
 * Created by JunHyeong on 2018-11-07
 */
public class EmptyHandler implements GUIHandler<InventoryEvent> {
    private static final GUIHandler<InventoryEvent> INSTANCE = new EmptyHandler();

    @SuppressWarnings("unchecked")
    public static <T extends InventoryEvent> GUIHandler<T> get() {
        return ((GUIHandler<T>) INSTANCE);
    }

    private EmptyHandler() {
    }

    @Override
    public void onEvent(GUI gui, InventoryEvent e) {
        // Empty
    }
}
