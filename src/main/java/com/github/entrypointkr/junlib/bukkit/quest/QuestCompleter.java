package com.github.entrypointkr.junlib.bukkit.quest;

import org.bukkit.entity.Player;

public interface QuestCompleter {
    void complete(Quest quest, Player player);
}
