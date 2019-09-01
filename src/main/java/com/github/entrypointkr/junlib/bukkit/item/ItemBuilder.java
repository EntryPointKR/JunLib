package com.github.entrypointkr.junlib.bukkit.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by JunHyeong on 2018-10-29
 */
public class ItemBuilder implements ItemFactory {
    private final ItemStack base;
    private ItemModifier modifier;

    private ItemBuilder(ItemStack base) {
        this.base = base;
    }

    public static ItemBuilder of(ItemStack item) {
        return new ItemBuilder(item);
    }

    public static ItemBuilder of(Material material) {
        return new ItemBuilder(new ItemStack(material));
    }

    public static ItemBuilder of(Material material, int amount) {
        return new ItemBuilder(new ItemStack(material, amount));
    }

    public ItemBuilder modifier(ItemModifier modifier) {
        this.modifier = modifier;
        return this;
    }

    public ItemBuilder meta(ItemMetaModifier meta) {
        this.modifier = meta;
        return this;
    }

    @Override
    public ItemStack create() {
        ItemStack item = new ItemStack(base);
        if (modifier != null) {
            modifier.modify(item);
        }
        return item;
    }
}
