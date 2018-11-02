package com.github.entrypointkr.junlib.bukkit.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Created by JunHyeong on 2018-10-29
 */
public class InventoryWrapper {
    private final Inventory inventory;

    public InventoryWrapper(Inventory inventory) {
        this.inventory = inventory;
    }

    public InventoryWrapper(InventoryHolder inventoryHolder) {
        this(inventoryHolder.getInventory());
    }
}
