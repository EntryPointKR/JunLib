package com.github.entrypointkr.junlib.bukkit.wizard;

import com.github.entrypointkr.junlib.JunLibrary;
import com.github.entrypointkr.junlib.bukkit.event.EventManager;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-02
 */
public class ChatWizard extends BukkitWizard<String, AsyncPlayerChatEvent, HumanEntity> implements StringWizard {
    public ChatWizard(EventManager manager, EventPriority priority, Class<AsyncPlayerChatEvent> type, HumanEntity human, boolean cancel, long timeoutTick, Runnable whenTimeout, boolean runOnMain) {
        super(manager, priority, type, human, cancel, timeoutTick, whenTimeout, runOnMain);
    }

    public ChatWizard(EventPriority priority, HumanEntity human, boolean cancel, long timeoutTick, Runnable whenTimeout, boolean runOnMain) {
        this(JunLibrary.get().getEventManager(), priority, AsyncPlayerChatEvent.class, human, cancel, timeoutTick, whenTimeout, runOnMain);
    }

    public ChatWizard(EventPriority priority, HumanEntity human, boolean cancel, long timeoutTick, Runnable whenTimeout) {
        this(priority, human, cancel, timeoutTick, whenTimeout, true);
    }

    public ChatWizard(EventPriority priority, HumanEntity human, boolean cancel, long timeoutTick) {
        this(priority, human, cancel, timeoutTick, () -> {
            // Cast to Player for legacy bukkit
            if (human instanceof Player) {
                human.sendMessage("입력할 시간이 초과되었습니다.");
            }
        });
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
