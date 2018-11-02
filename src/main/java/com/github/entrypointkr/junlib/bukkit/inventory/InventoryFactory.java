package com.github.entrypointkr.junlib.bukkit.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Created by JunHyeong on 2018-10-28
 */
public interface InventoryFactory {
    Inventory create(InventoryHolder holder);
}
