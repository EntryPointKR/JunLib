package com.github.entrypointkr.junlib.bukkit.gui;

import com.github.entrypointkr.junlib.JunLibrary;
import com.github.entrypointkr.junlib.bukkit.event.EventListener;
import com.github.entrypointkr.junlib.bukkit.event.Events;
import com.github.entrypointkr.junlib.bukkit.event.listener.GUIModal;
import com.github.entrypointkr.junlib.bukkit.event.listener.GUINotifier;
import com.github.entrypointkr.junlib.bukkit.gui.handler.GUIHandler;
import com.github.entrypointkr.junlib.bukkit.inventory.InventoryFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by JunHyeong on 2018-10-28
 */
public final class GUI implements GUINotifier {
    private final HumanEntity owner;
    private final InventoryFactory factory;
    private final GUIHandler<InventoryEvent> handler;

    public GUI(HumanEntity owner, InventoryFactory factory, GUIHandler<InventoryEvent> handler) {
        this.owner = owner;
        this.factory = factory;
        this.handler = handler;
    }

    public GUI(HumanEntity owner, GUIComponent component) {
        this(owner, component, component);
    }

    public static boolean isSimilar(Inventory a, Inventory b) {
        return a == b || a != null && b != null && a.getSize() == b.getSize()
                && a.getType() == b.getType()
                && b.getTitle().equals(b.getTitle());
    }

    public void open(GUINotifier eventNotifier) {
        Bukkit.getScheduler().runTask(JunLibrary.getPlugin(), () -> {
            Inventory created = factory.create(owner);
            owner.openInventory(created);
            Events.registerListener(EventPriority.MONITOR, new BootstrapNotifier(eventNotifier));
        });
    }

    public void open() {
        open(this);
    }

    public void openModal(GUI parent, GUINotifier notifier) {
        open(new GUIModal(this, parent, notifier));
    }

    public void openModal(GUI parent) {
        openModal(parent, parent);
    }

    @Override
    public void onEvent(InventoryEvent event) {
        if (event != null) {
            handler.onEvent(this, event);
        }
    }

    class BootstrapNotifier implements EventListener<Event> {
        private final EventListener<InventoryEvent> notifier;

        BootstrapNotifier(EventListener<InventoryEvent> notifier) {
            this.notifier = notifier;
        }

        @Override
        public void onEvent(Event event) {
            if (event instanceof InventoryEvent) {
                InventoryEvent e = ((InventoryEvent) event);
                if (owner.equals(e.getView().getPlayer())) {
                    notifier.onEvent(e);
                    if (e instanceof InventoryCloseEvent) {
                        Events.removeListener(this);
                    }
                }
            }
        }
    }
}
