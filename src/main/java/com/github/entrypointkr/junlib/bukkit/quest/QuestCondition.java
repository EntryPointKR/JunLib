package com.github.entrypointkr.junlib.bukkit.quest;

import org.bukkit.entity.Player;

public interface QuestCondition extends QuestCompleter {
    boolean isAcceptable(Quest quest, Player player);
}
