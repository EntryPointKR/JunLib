package com.github.entrypointkr.junlib.bukkit.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

/**
 * Created by JunHyeong on 2018-11-03
 */
public class ItemWrapper {
    private final ItemStack item;

    private ItemWrapper(ItemStack item) {
        this.item = item;
    }

    public static ItemWrapper wrap(ItemStack item) {
        return new ItemWrapper(item);
    }

    public Optional<ItemMeta> getItemMeta() {
        return Optional.ofNullable(item.getItemMeta());
    }

    public Optional<String> getName() {
        return getItemMeta().map(ItemMeta::getDisplayName);
    }

    public String getNameOrDefault(String def) {
        return getName().orElse(def);
    }

    public String getNameOrDefault() {
        return getNameOrDefault(item.getType().name());
    }

    public ItemStack getItem() {
        return item;
    }
}
