package com.github.entrypointkr.junlib.bukkit.quest.display;

import com.github.entrypointkr.junlib.bukkit.item.ItemBuilder;
import com.github.entrypointkr.junlib.bukkit.item.SimpleMeta;
import com.github.entrypointkr.junlib.bukkit.quest.Quest;
import com.github.entrypointkr.junlib.bukkit.quest.QuestDisplayFactory;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class QuestDisplay implements QuestDisplayFactory {
    @Override
    public ItemStack create(Quest quest) {
        String name = quest.getName();
        String description = quest.getDescription();
        SimpleMeta meta = new SimpleMeta().display(name);
        int limit = 15;
        int remain = description.length() % limit;
        int count = description.length() / limit;
        if (remain > 0) {
            count++;
        }
        for (int i = 0; i < count; i++) {
            int start = limit * i;
            int end = Math.min(limit * (i + 1), description.length());
            meta.lore(description.substring(start, end));
        }
        return ItemBuilder.of(Material.DIAMOND_SWORD)
                .create(meta);
    }
}
