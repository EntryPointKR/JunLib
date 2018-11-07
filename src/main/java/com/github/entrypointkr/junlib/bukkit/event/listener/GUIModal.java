package com.github.entrypointkr.junlib.bukkit.event.listener;

import com.github.entrypointkr.junlib.bukkit.gui.GUI;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;

/**
 * Created by JunHyeong on 2018-11-07
 */
public class GUIModal implements GUINotifier {
    private final GUINotifier notifier;
    private final GUI parent;
    private final GUINotifier parentNotifier;

    public GUIModal(GUINotifier notifier, GUI parent, GUINotifier parentNotifier) {
        this.notifier = notifier;
        this.parent = parent;
        this.parentNotifier = parentNotifier;
    }

    public GUIModal(GUINotifier notifier, GUI parent) {
        this(notifier, parent, parent);
    }

    @Override
    public void onEvent(InventoryEvent event) {
        notifier.onEvent(event);
        if (event instanceof InventoryCloseEvent) {
            parent.open(parentNotifier);
        }
    }
}
