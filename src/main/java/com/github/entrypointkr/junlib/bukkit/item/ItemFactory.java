package com.github.entrypointkr.junlib.bukkit.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by JunHyeong on 2018-11-03
 */
public interface ItemFactory {
    ItemStack create();

    default <T extends ItemMeta> ItemStack create(Class<T> metaType, ItemMetaModifier<T> modifier) {
        ItemStack item = create();
        T meta = metaType.cast(item.getItemMeta());
        if (meta != null) {
            modifier.modify(meta);
            item.setItemMeta(meta);
        }
        return item;
    }

    default ItemStack create(ItemMetaModifier<ItemMeta> modifier) {
        return create(ItemMeta.class, modifier);
    }
}
