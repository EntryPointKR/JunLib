package com.github.entrypointkr.junlib.bukkit.gui;

import com.github.entrypointkr.junlib.bukkit.event.EventListener;
import com.github.entrypointkr.junlib.bukkit.event.Events;
import com.github.entrypointkr.junlib.bukkit.gui.handler.GUIHandler;
import com.github.entrypointkr.junlib.bukkit.inventory.InventoryFactory;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by JunHyeong on 2018-10-28
 */
public final class GUI implements GUIHandler {
    private static final GUIHandler EMPTY_LISTENER = (gui, event) -> {
    };
    private final InventoryFactory factory;
    private final GUIHandler<InventoryEvent> handler;

    public GUI(InventoryFactory factory, GUIHandler<InventoryEvent> handler) {
        this.factory = factory;
        this.handler = handler;
    }

    public GUI(GUIComponent component) {
        this(component, component);
    }

    @SuppressWarnings("unchecked")
    public static <T extends InventoryEvent> GUIHandler<T> emptyListener() {
        return ((GUIHandler<T>) EMPTY_LISTENER);
    }

    public static boolean isSimilar(Inventory a, Inventory b) {
        return a == b || a != null && b != null && a.getSize() == b.getSize()
                && a.getType() == b.getType()
                && b.getTitle().equals(b.getTitle());
    }

    public void open(HumanEntity human) {
        Inventory created = factory.create(human);
        Inventory opened = human.getOpenInventory().getTopInventory();
        if (isSimilar(created, opened)) {
            opened.setContents(created.getContents());
        } else {
            human.openInventory(created);
            Events.registerListener(EventPriority.MONITOR, new Notifier());
        }
    }

    @Override
    public void onEvent(GUI gui, InventoryEvent e) {
        handler.onEvent(gui, e);
    }

    class Notifier implements EventListener<Event> {
        @Override
        public void onEvent(Event event) {
            if (event instanceof InventoryEvent) {
                GUI.this.onEvent(GUI.this, ((InventoryEvent) event));
                if (event instanceof InventoryCloseEvent) {
                    Events.removeListener(this);
                }
            }
        }
    }
}
