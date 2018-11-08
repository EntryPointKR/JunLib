package com.github.entrypointkr.junlib.bukkit.item;

import org.apache.commons.lang.StringUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by JunHyeong on 2018-11-03
 */
public class ItemWrapper {
    private final ItemStack item;

    private ItemWrapper(ItemStack item) {
        this.item = item;
    }

    public static ItemWrapper of(ItemStack item) {
        return new ItemWrapper(item);
    }

    public Optional<ItemMeta> getItemMeta() {
        return Optional.ofNullable(item.getItemMeta());
    }

    public Optional<String> getName() {
        return getItemMeta().map(ItemMeta::getDisplayName).filter(StringUtils::isNotEmpty);
    }

    public String getNameOrDefault(String def) {
        return getName().orElse(def);
    }

    public String getNameOrDefault() {
        return getNameOrDefault(item.getType().name());
    }

    public List<String> getLore() {
        return getItemMeta().map(ItemMeta::getLore).orElse(Collections.emptyList());
    }

    public ItemStack getItem() {
        return item;
    }
}
