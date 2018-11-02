package com.github.entrypointkr.junlib.bukkit.gui.handler;

import com.github.entrypointkr.junlib.bukkit.gui.GUI;
import org.bukkit.event.Cancellable;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiPredicate;

/**
 * Created by JunHyeong on 2018-10-29
 */
public class CancelHandler implements GUIHandler<InventoryEvent> {
    public static final CancelHandler ALL = new CancelHandler((gui, e) -> true);
    public static final CancelHandler TOP = new CancelHandler((gui, e) -> {
        if (e instanceof InventoryClickEvent) {
            InventoryClickEvent event = ((InventoryClickEvent) e);
            Inventory topInv = event.getView().getTopInventory();
            if (event.getAction() == InventoryAction.COLLECT_TO_CURSOR) {
                ItemStack cursor = event.getCursor();
                for (int i = 0; i < topInv.getSize(); i++) {
                    ItemStack item = topInv.getItem(i);
                    if (cursor.isSimilar(item)) {
                        return true;
                    }
                }
            } else {
                return event.getRawSlot() < topInv.getSize();
            }
        } else if (e instanceof InventoryDragEvent) {
            InventoryDragEvent event = ((InventoryDragEvent) e);
            for (Integer rawSlot : event.getRawSlots()) {
                if (rawSlot < event.getView().getTopInventory().getSize()) {
                    return true;
                }
            }
        }
        return false;
    });
    private final BiPredicate<GUI, InventoryEvent> predicate;

    public CancelHandler(BiPredicate<GUI, InventoryEvent> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void onEvent(GUI gui, InventoryEvent e) {
        if (e instanceof Cancellable) {
            Cancellable cancellable = ((Cancellable) e);
            if (predicate.test(gui, e)) {
                cancellable.setCancelled(true);
            }
        }
    }
}
