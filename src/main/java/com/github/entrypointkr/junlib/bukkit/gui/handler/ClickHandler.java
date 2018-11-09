package com.github.entrypointkr.junlib.bukkit.gui.handler;

import com.github.entrypointkr.junlib.bukkit.gui.GUI;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JunHyeong on 2018-10-29
 */
public class ClickHandler implements GUIHandler<InventoryEvent> {
    private final Map<Integer, GUIHandler<InventoryClickEvent>> slotMap = new HashMap<>();
    private final GUIHandler<InventoryClickEvent> defaultHandler;

    public ClickHandler(GUIHandler<InventoryClickEvent> defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    public ClickHandler() {
        this(EmptyHandler.get());
    }

    @Override
    public void onEvent(GUI gui, InventoryEvent e) {
        if (e instanceof InventoryClickEvent) {
            InventoryClickEvent event = ((InventoryClickEvent) e);
            notify(event.getRawSlot(), gui, event);
        }
    }

    public void notify(Integer slot, GUI gui, InventoryClickEvent e) {
        slotMap.getOrDefault(slot, defaultHandler)
                .onEvent(gui, e);
    }

    public ClickHandler put(GUIHandler<InventoryClickEvent> handler, Integer... slots) {
        for (Integer slot : slots) {
            slotMap.put(slot, handler);
        }
        return this;
    }

    public ClickHandler put(int row, int column, GUIHandler<InventoryClickEvent> handler) {
        return put(handler, row * 9 + column);
    }

    public ClickHandler remove(int... slots) {
        for (int slot : slots) {
            slotMap.remove(slot);
        }
        return this;
    }

    public ClickHandler remove(int row, int column) {
        return remove(row * 9 + column);
    }
}
