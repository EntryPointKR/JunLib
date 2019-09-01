package com.github.entrypointkr.junlib.bukkit.item;

import org.bukkit.OfflinePlayer;

public class SkullMeta extends ItemMetaModifier<org.bukkit.inventory.meta.SkullMeta> {
    private final Meta meta;
    private OfflinePlayer owner;

    public static SkullMeta of(Meta meta) {
        return new SkullMeta(org.bukkit.inventory.meta.SkullMeta.class, meta);
    }

    public static SkullMeta of() {
        return of(Meta.EMPTY);
    }

    private SkullMeta(Class<org.bukkit.inventory.meta.SkullMeta> type, Meta meta) {
        super(type);
        this.meta = meta;
    }

    public SkullMeta owner(OfflinePlayer owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public void modify(org.bukkit.inventory.meta.SkullMeta meta) {
        if (owner != null) {
            meta.setOwningPlayer(owner);
        }
        this.meta.modify(meta);
    }
}
