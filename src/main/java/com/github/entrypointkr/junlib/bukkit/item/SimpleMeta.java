package com.github.entrypointkr.junlib.bukkit.item;

import com.github.entrypointkr.junlib.bukkit.util.Bukkits;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        this.displayName = Bukkits.colorize(displayName);
        return this;
    }

    public SimpleMeta name(String displayName) {
        return display(displayName);
    }

    public SimpleMeta lore(String... lores) {
        getLore().addAll(Arrays.stream(lores).map(Bukkits::colorize).collect(Collectors.toList()));
        return this;
    }

    private List<String> getLore() {
        if (lore == null) {
            lore = new ArrayList<>();
        }
        return lore;
    }
}
