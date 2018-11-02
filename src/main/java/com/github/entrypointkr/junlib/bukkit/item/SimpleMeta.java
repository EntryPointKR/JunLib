package com.github.entrypointkr.junlib.bukkit.item;

import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JunHyeong on 2018-10-31
 */
public class SimpleMeta implements ItemMetaModifier<ItemMeta> {
    private String displayName = null;
    private List<String> lore = null;

    @Override
    public void modify(ItemMeta meta) {
        if (displayName != null) {
            meta.setDisplayName(displayName);
        }
        if (lore != null && !lore.isEmpty()) {
            meta.setLore(lore);
        }
    }

    public SimpleMeta display(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public SimpleMeta lore(String... lores) {
        getLore().addAll(Arrays.asList(lores));
        return this;
    }

    private List<String> getLore() {
        if (lore == null) {
            lore = new ArrayList<>();
        }
        return lore;
    }
}
