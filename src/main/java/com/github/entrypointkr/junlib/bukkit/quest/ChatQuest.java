package com.github.entrypointkr.junlib.bukkit.quest;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatQuest extends Quest {
    private final String chat;

    public ChatQuest(String name, String description, QuestCondition condition, QuestCompleter whenComplete, QuestDisplayFactory displayFactory, String chat) {
        super(name, description, condition, whenComplete, displayFactory);
        this.chat = chat;
    }

    public ChatQuest(String name, String description, QuestCondition condition, QuestCompleter whenComplete, String chat) {
        super(name, description, condition, whenComplete);
        this.chat = chat;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent e) {
        if (chat.equalsIgnoreCase(e.getMessage())) {
            complete(e.getPlayer());
        }
    }
}
