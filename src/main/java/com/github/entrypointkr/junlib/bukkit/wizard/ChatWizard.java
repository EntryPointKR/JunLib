package com.github.entrypointkr.junlib.bukkit.wizard;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-02
 */
public class ChatWizard extends EntityWizard<String, HumanEntity> implements StringWizard {
    public ChatWizard(EventPriority priority, HumanEntity entity) {
        super(priority, entity);
    }

    public ChatWizard(HumanEntity entity) {
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
