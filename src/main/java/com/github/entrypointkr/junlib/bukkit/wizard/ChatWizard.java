package com.github.entrypointkr.junlib.bukkit.wizard;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-02
 */
public class ChatWizard extends BukkitWizard<String, HumanEntity> implements StringWizard {
    private final boolean cancel;

    public ChatWizard(EventPriority priority, HumanEntity entity, boolean cancel) {
        super(priority, entity);
        this.cancel = cancel;
    }

    public ChatWizard(EventPriority priority, HumanEntity entity) {
        this(priority, entity, true);
    }

    public ChatWizard(HumanEntity entity, boolean cancel) {
        super(entity);
        this.cancel = cancel;
    }

    public ChatWizard(HumanEntity entity) {
        this(entity, true);
    }

    @Override
    protected void process(Event event, Consumer<String> resultCallback) {
        if (event instanceof AsyncPlayerChatEvent) {
            AsyncPlayerChatEvent e = ((AsyncPlayerChatEvent) event);
            if (e.getPlayer().equals(getPlayer())) {
                resultCallback.accept(e.getMessage());
                if (cancel) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
