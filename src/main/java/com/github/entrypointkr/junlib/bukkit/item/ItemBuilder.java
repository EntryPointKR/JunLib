package com.github.entrypointkr.junlib.bukkit.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by JunHyeong on 2018-10-29
 */
public class ItemBuilder {
    private final Material material;

    public ItemBuilder(Material material) {
        this.material = material;
    }

    public ItemStack create() {
        return new ItemStack(material);
    }

    public <T extends ItemMeta> ItemStack create(Class<T> metaType, ItemMetaModifier<T> modifier) {
        ItemStack item = create();
        T meta = metaType.cast(item.getItemMeta());
        modifier.modify(meta);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack create(ItemMetaModifier<ItemMeta> modifier) {
        return create(ItemMeta.class, modifier);
    }
}
