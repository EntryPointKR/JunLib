package com.github.entrypointkr.junlib.bukkit.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunHyeong on 2018-10-29
 */
public class InventoryWrapper {
    private final Inventory inventory;

    private InventoryWrapper(Inventory inventory) {
        this.inventory = inventory;
    }

    public static InventoryWrapper wrap(Inventory inventory) {
        return new InventoryWrapper(inventory);
    }

    public static InventoryWrapper wrap(InventoryHolder holder) {
        return wrap(holder.getInventory());
    }

    public int hasSpace(ItemStack item) {
        int result = 0;
        for (ItemStack element : inventory.getStorageContents()) {
            if (element == null || element.getType() == Material.AIR) {
                result += item.getMaxStackSize();
            } else if (item.isSimilar(element)) {
                result += item.getMaxStackSize() - element.getAmount();
            }
        }
        return result;
    }

    public boolean hasSpace(ItemStack item, int amount) {
        return hasSpace(item) >= amount;
    }

    public boolean giveItem(ItemStack item, int amount) {
        if (!hasSpace(item, amount)) {
            return false;
        }
        int maxStack = item.getMaxStackSize();
        if (amount > maxStack) {
            int count = amount / maxStack;
            for (int i = 0; i < count; i++) {
                ItemStack newItem = new ItemStack(item);
                newItem.setAmount(maxStack);
                inventory.addItem(newItem);
            }
            amount = amount % maxStack;
        }
        ItemStack newItem = new ItemStack(item);
        newItem.setAmount(amount);
        inventory.addItem(newItem);
        return true;
    }

    public boolean giveItem(ItemStack item) {
        return giveItem(item, item.getAmount());
    }

    public boolean takeItem(ItemStack item, int amount) {
        List<Integer> slots = new ArrayList<>();
        int count = amount;
        for (int i = 0; i < inventory.getSize() && count > 0; i++) {
            ItemStack element = inventory.getItem(i);
            if (item.isSimilar(element)) {
                int qty = element.getAmount();
                if (count > qty) {
                    count -= qty;
                    slots.add(i);
                } else {
                    if (count < qty) {
                        element.setAmount(qty - count);
                    } else {
                        slots.add(i);
                    }
                    slots.forEach(inventory::clear);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean takeItem(ItemStack item) {
        return takeItem(item, item.getAmount());
    }

    public int hasItem(ItemStack item) {
        int result = 0;
        for (ItemStack element : inventory) {
            if (item.isSimilar(element)) {
                result += element.getAmount();
            }
        }
        return result;
    }

    public boolean hasItem(ItemStack item, int amount) {
        return hasItem(item) >= amount;
    }
}
