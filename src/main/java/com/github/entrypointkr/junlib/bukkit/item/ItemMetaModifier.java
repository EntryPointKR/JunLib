package com.github.entrypointkr.junlib.bukkit.item;

import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by JunHyeong on 2018-10-31
 */
public interface ItemMetaModifier<T extends ItemMeta> {
    void modify(T meta);
}
