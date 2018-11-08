package com.github.entrypointkr.junlib.bukkit.wizard;

import com.github.entrypointkr.junlib.bukkit.event.EventListener;
import com.github.entrypointkr.junlib.bukkit.event.Events;
import com.github.entrypointkr.junlib.wizard.Wizard;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-02
 */
public abstract class BukkitWizard<T, E extends Event, H extends HumanEntity> implements Wizard<T> {
    private static final Map<String, EventListener<Event>> notifierMap = new ConcurrentHashMap<>();
    private final EventPriority priority;
    private final Class<E> type;
    private final H human;
    private final boolean cancel;

    public BukkitWizard(EventPriority priority, Class<E> type, H human, boolean cancel) {
        this.priority = priority;
        this.type = type;
        this.human = human;
        this.cancel = cancel;
    }

    public BukkitWizard(H human, Class<E> type, boolean cancel) {
        this(EventPriority.HIGHEST, type, human, cancel);
    }

    public BukkitWizard(H human, Class<E> type) {
        this(human, type, true);
    }

    @Override
    public void run(Consumer<T> consumer) {
        EventListener<Event> notifier = new EventListener<Event>() {
            @Override
            public void onEvent(Event event) {
                if (event.getClass().isAssignableFrom(type)) {
                    E e = type.cast(event);
                    if (e instanceof PlayerEvent && !getPlayer().equals(((PlayerEvent) e).getPlayer())) {
                        return;
                    }
                    process(e, data -> {
                        Events.removeListener(this);
                        consumer.accept(data);
                        notifierMap.remove(getPlayer().getName());
                        if (e instanceof Cancellable && cancel) {
                            ((Cancellable) e).setCancelled(true);
                        }
                    });
                }
            }
        };
        EventListener<Event> prev = notifierMap.put(getPlayer().getName(), notifier);
        if (prev != null) {
            Events.removeListener(prev);
        }
        Events.registerListener(priority, notifier);
    }

    protected abstract void process(E event, Consumer<T> resultCallback);

    public H getPlayer() {
        return human;
    }
}
