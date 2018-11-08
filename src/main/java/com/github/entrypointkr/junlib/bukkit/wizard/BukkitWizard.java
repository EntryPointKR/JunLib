package com.github.entrypointkr.junlib.bukkit.wizard;

import com.github.entrypointkr.junlib.bukkit.event.EventListener;
import com.github.entrypointkr.junlib.bukkit.event.Events;
import com.github.entrypointkr.junlib.wizard.Wizard;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-02
 */
public abstract class BukkitWizard<T, H extends HumanEntity> implements Wizard<T> {
    private static final Map<String, EventListener<Event>> notifierMap = new ConcurrentHashMap<>();
    private final EventPriority priority;
    private final H human;

    public BukkitWizard(EventPriority priority, H human) {
        this.priority = priority;
        this.human = human;
    }

    public BukkitWizard(H human) {
        this(EventPriority.LOWEST, human);
    }

    @Override
    public void run(Consumer<T> consumer) {
        EventListener<Event> notifier = new EventListener<Event>() {
            @Override
            public void onEvent(Event event) {
                process(event, data -> {
                    consumer.accept(data);
                    Events.removeListener(this);
                    notifierMap.remove(getPlayer().getName());
                });
            }
        };
        EventListener<Event> prev = notifierMap.put(getPlayer().getName(), notifier);
        if (prev != null) {
            Events.removeListener(prev);
        }
        Events.registerListener(priority, notifier);
    }

    protected abstract void process(Event event, Consumer<T> resultCallback);

    public H getPlayer() {
        return human;
    }
}
