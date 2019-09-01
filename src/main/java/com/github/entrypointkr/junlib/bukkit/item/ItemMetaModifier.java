package com.github.entrypointkr.junlib.bukkit.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class ItemMetaModifier<T extends ItemMeta> implements ItemModifier {
    private final Class<T> type;

    public ItemMetaModifier(Class<T> type) {
        this.type = type;
    }

    @Override
    public final void modify(ItemStack item) {
        if (isPrepared()) {
            ItemMeta meta = item.getItemMeta();
            if (type.isInstance(meta)) {
                modify(type.cast(meta));
                item.setItemMeta(meta);
            }
        }
    }

    public boolean isPrepared() {
        return true;
    }

    public abstract void modify(T meta);
}
