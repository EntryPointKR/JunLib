package com.github.entrypointkr.junlib.bukkit.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

/**
 * Created by JunHyeong on 2018-10-29
 */
public class ItemBuilder implements ItemFactory {
    private final Supplier<ItemStack> baseFactory;

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

    @Override
    public ItemStack create() {
        return baseFactory.get();
    }
}
