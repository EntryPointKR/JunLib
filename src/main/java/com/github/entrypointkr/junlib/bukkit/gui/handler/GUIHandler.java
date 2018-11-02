package com.github.entrypointkr.junlib.bukkit.gui.handler;

import com.github.entrypointkr.junlib.bukkit.gui.GUI;
import org.bukkit.event.inventory.InventoryEvent;

/**
 * Created by JunHyeong on 2018-10-29
 */
public interface GUIHandler<T extends InventoryEvent> {
    void onEvent(GUI gui, T e);
}
