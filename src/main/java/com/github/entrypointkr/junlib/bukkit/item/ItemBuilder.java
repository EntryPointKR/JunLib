package com.github.entrypointkr.junlib.bukkit.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.function.Supplier;

/**
 * Created by JunHyeong on 2018-10-29
 */
@SuppressWarnings("deprecation")
public class ItemBuilder implements ItemFactory {
    private final Supplier<ItemStack> baseFactory;
    private MaterialData data;

    private ItemBuilder(Supplier<ItemStack> baseFactory) {
        this.baseFactory = baseFactory;
    }

    public static ItemBuilder of(Supplier<ItemStack> factory) {
        return new ItemBuilder(factory);
    }

    public static ItemBuilder of(ItemStack item) {
        return of(() -> new ItemStack(item));
    }

    public static ItemBuilder of(Material material) {
        return of(() -> new ItemStack(material));
    }

    public static ItemBuilder of(Material material, int amount) {
        return of(() -> new ItemStack(material, amount));
    }

    public ItemBuilder data(MaterialData data) {
        this.data = data;
        return this;
    }

    @Override
    public ItemStack create() {
        ItemStack item = baseFactory.get();
        if (data != null) {
            item.setDurability(data.getData());
        }
        return item;
    }
}
