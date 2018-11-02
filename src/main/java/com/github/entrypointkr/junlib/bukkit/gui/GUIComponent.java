package com.github.entrypointkr.junlib.bukkit.gui;

import com.github.entrypointkr.junlib.bukkit.gui.handler.GUIHandler;
import com.github.entrypointkr.junlib.bukkit.inventory.InventoryFactory;
import org.bukkit.event.inventory.InventoryEvent;

/**
 * Created by JunHyeong on 2018-10-31
 */
public interface GUIComponent extends InventoryFactory, GUIHandler<InventoryEvent> {
}
