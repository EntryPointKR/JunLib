package com.github.entrypointkr.junlib.bukkit.gui;

import com.github.entrypointkr.junlib.JunLibrary;
import com.github.entrypointkr.junlib.bukkit.event.EventListener;
import com.github.entrypointkr.junlib.bukkit.event.EventManager;
import com.github.entrypointkr.junlib.bukkit.gui.handler.GUIHandler;
import com.github.entrypointkr.junlib.bukkit.inventory.InventoryFactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

/**
 * Created by JunHyeong on 2018-10-28
 */
public final class GUI {
    private final InventoryFactory factory;
    private final GUIHandler<InventoryEvent> handler;
    private final EventManager manager;
    private final Plugin plugin;

    private GUI(InventoryFactory factory, GUIHandler<InventoryEvent> handler, EventManager manager, Plugin plugin) {
        this.factory = factory;
        this.handler = handler;
        this.manager = manager;
        this.plugin = plugin;
    }

    public static GUI of(InventoryFactory factory, GUIHandler<InventoryEvent> handler, EventManager manager, Plugin plugin) {
        return new GUI(factory, handler, manager, plugin);
    }

    public static GUI of(InventoryFactory factory, GUIHandler<InventoryEvent> handler, Plugin plugin) {
        return of(factory, handler, JunLibrary.get().getEventManager(), plugin);
    }

    public static GUI of(InventoryFactory factory, GUIHandler<InventoryEvent> handler) {
        return of(factory, handler, JunLibrary.get());
    }

    public static GUI of(GUIComponent component, Plugin plugin) {
        return of(component, component, plugin);
    }

    public static GUI of(GUIComponent component) {
        return of(component, JunLibrary.get());
    }

    public void open(Player owner) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            Inventory created = factory.create(owner);
            owner.openInventory(created);
            manager.registerListener(EventPriority.MONITOR, new Notifier(owner, handler));
        });
    }

    class Notifier implements EventListener<Event> {
        private final Player owner;
        private final GUIHandler<InventoryEvent> handler;

        Notifier(Player owner, GUIHandler<InventoryEvent> handler) {
            this.owner = owner;
            this.handler = handler;
        }

        @Override
        public void onEvent(Event event) {
            if (event instanceof InventoryEvent) {
                InventoryEvent e = ((InventoryEvent) event);
                if (owner.equals(e.getView().getPlayer())) {
                    handler.onEvent(GUI.this, e);
                    if (e instanceof InventoryCloseEvent) {
                        manager.removeListener(this);
                    }
                }
            }
        }
    }
}
