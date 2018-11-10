package com.github.entrypointkr.junlib.bukkit.wizard;

import com.github.entrypointkr.junlib.bukkit.util.Runnables;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-02
 */
public class ChatWizard extends BukkitWizard<String, AsyncPlayerChatEvent, HumanEntity> implements StringWizard {
    public ChatWizard(EventPriority priority, HumanEntity human, boolean cancel, long timeoutTick, Runnable whenTimeout) {
        super(priority, AsyncPlayerChatEvent.class, human, cancel, timeoutTick, whenTimeout);
    }

    public ChatWizard(EventPriority priority, HumanEntity human, boolean cancel, long timeoutTick) {
        this(priority, human, cancel, timeoutTick, Runnables.EMPTY);
    }

    public ChatWizard(EventPriority priority, HumanEntity human, boolean cancel) {
        this(priority, human, cancel, 20L * 15L);
    }

    public ChatWizard(EventPriority priority, HumanEntity entity) {
        this(priority, entity, true);
    }

    public ChatWizard(HumanEntity entity, boolean cancel) {
        this(EventPriority.HIGHEST, entity, cancel);
    }

    public ChatWizard(HumanEntity entity) {
        this(entity, true);
    }

    @Override
    protected void process(AsyncPlayerChatEvent event, Consumer<String> resultCallback) {
        resultCallback.accept(event.getMessage());
    }
}
