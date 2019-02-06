package com.github.entrypointkr.junlib.bukkit.quest.condition;

import com.github.entrypointkr.junlib.bukkit.quest.Quest;
import com.github.entrypointkr.junlib.bukkit.quest.QuestCondition;
import org.bukkit.entity.Player;

public class SimpleCondition implements QuestCondition {
    private final boolean acceptable;

    public SimpleCondition(boolean acceptable) {
        this.acceptable = acceptable;
    }

    @Override
    public boolean isAcceptable(Quest quest, Player player) {
        return acceptable;
    }

    @Override
    public void complete(Quest quest, Player player) {
        // Ignore
    }
}
