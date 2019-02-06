package com.github.entrypointkr.junlib.bukkit.quest;

import com.github.entrypointkr.junlib.bukkit.quest.display.QuestDisplay;
import com.github.entrypointkr.junlib.bukkit.util.RegisterableListener;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Quest implements RegisterableListener {
    private final String name;
    private final String description;
    private final QuestCondition condition;
    private final QuestCompleter whenComplete;
    private final QuestDisplayFactory displayFactory;

    public Quest(String name, String description, QuestCondition condition, QuestCompleter whenComplete, QuestDisplayFactory displayFactory) {
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.whenComplete = whenComplete;
        this.displayFactory = displayFactory;
    }

    public Quest(String name, String description, QuestCondition condition, QuestCompleter whenComplete) {
        this(name, description, condition, whenComplete, new QuestDisplay());
    }

    public void complete(Player player) {
        if (isAcceptable(player)) {
            condition.complete(this, player);
            whenComplete.complete(this, player);
        }
    }

    public boolean isAcceptable(Player player) {
        return condition.isAcceptable(this, player);
    }

    public ItemStack createSymbolItem() {
        return displayFactory.create(this);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
