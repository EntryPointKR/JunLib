package com.github.entrypointkr.junlib.bukkit.wizard;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-02
 */
public class ChatWizard extends EntityWizard<String, Player> implements StringWizard {
    public ChatWizard(EventPriority priority, Player entity) {
        super(priority, entity);
    }

    public ChatWizard(Player entity) {
        super(entity);
    }

    @Override
    protected void process(Event event, Consumer<String> resultCallback) {
        if (event instanceof AsyncPlayerChatEvent) {
            AsyncPlayerChatEvent e = ((AsyncPlayerChatEvent) event);
            if (e.getPlayer().equals(getEntity())) {
                resultCallback.accept(e.getMessage());
            }
        }
    }
}
