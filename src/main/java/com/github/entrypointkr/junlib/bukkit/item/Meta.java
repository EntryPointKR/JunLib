package com.github.entrypointkr.junlib.bukkit.item;

import com.github.entrypointkr.junlib.bukkit.util.Bukkits;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JunHyeong on 2018-10-31
 */
public class Meta extends ItemMetaModifier<ItemMeta> {
    public static final Meta EMPTY = of();
    private String displayName = null;
    private List<String> lore = null;
    private boolean unBreakable = false;
    private List<ItemFlag> flags = null;

    public static Meta of() {
        return new Meta(ItemMeta.class);
    }

    private Meta(Class<ItemMeta> type) {
        super(type);
    }

    public Meta display(String displayName) {
        this.displayName = Bukkits.colorize(displayName);
        return this;
    }

    public Meta name(String displayName) {
        return display(displayName);
    }

    public Meta lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public Meta lore(String... lores) {
        return lore(Arrays.asList(lores));
    }

    public Meta addLore(List<String> lore) {
        if (this.lore == null) {
            this.lore = lore;
        } else {
            this.lore.addAll(lore);
        }
        return this;
    }

    public Meta addLore(String... lores) {
        return addLore(Arrays.asList(lores));
    }

    public Meta addFlag(ItemFlag... flags) {
        if (this.flags == null) {
            this.flags = new ArrayList<>();
        }
        this.flags.addAll(Arrays.asList(flags));
        return this;
    }

    public Meta unbreakable(boolean unBreakable) {
        this.unBreakable = unBreakable;
        return this;
    }


    @Override
    public void modify(ItemMeta meta) {
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        if (flags != null) {
            meta.addItemFlags(flags.toArray(new ItemFlag[0]));
        }
        meta.setUnbreakable(unBreakable);
    }

    @Override
    public boolean isPrepared() {
        return displayName != null || lore != null || flags != null || unBreakable;
    }
}
