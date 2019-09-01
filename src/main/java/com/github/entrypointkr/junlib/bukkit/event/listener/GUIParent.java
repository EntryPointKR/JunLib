package com.github.entrypointkr.junlib.bukkit.event.listener;

import com.github.entrypointkr.junlib.bukkit.gui.GUI;
import com.github.entrypointkr.junlib.bukkit.gui.handler.GUIHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;

/**
 * Created by JunHyeong on 2018-11-07
 */
public class GUIParent implements GUIHandler<InventoryEvent> {
    private final GUIHandler<InventoryEvent> notifier;
    private final GUI parent;
    private final Player owner;

    public GUIParent(GUIHandler<InventoryEvent> notifier, GUI parent, Player owner) {
        this.notifier = notifier;
        this.parent = parent;
        this.owner = owner;
    }

    @Override
    public void onEvent(GUI gui, InventoryEvent e) {
        notifier.onEvent(gui, e);
        if (e instanceof InventoryCloseEvent) {
            parent.open(owner);
        }
    }
}
