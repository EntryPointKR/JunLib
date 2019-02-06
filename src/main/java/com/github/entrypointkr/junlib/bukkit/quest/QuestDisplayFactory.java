package com.github.entrypointkr.junlib.bukkit.quest;

import org.bukkit.inventory.ItemStack;

public interface QuestDisplayFactory {
    ItemStack create(Quest quest);
}
