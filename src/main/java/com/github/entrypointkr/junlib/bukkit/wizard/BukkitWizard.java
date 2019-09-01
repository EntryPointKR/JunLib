package com.github.entrypointkr.junlib.bukkit.wizard;

import com.github.entrypointkr.junlib.JunLibrary;
import com.github.entrypointkr.junlib.bukkit.event.EventListener;
import com.github.entrypointkr.junlib.bukkit.event.EventManager;
import com.github.entrypointkr.junlib.bukkit.util.Runnables;
import com.github.entrypointkr.junlib.wizard.Wizard;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-02
 */
public abstract class BukkitWizard<T, E extends Event, H extends HumanEntity> implements Wizard<T> {
    private static final Map<String, BukkitWizard.Processor> notifierMap = new ConcurrentHashMap<>();
    private final EventManager manager;
    private final EventPriority priority;
    private final Class<E> type;
    private final H human;
    private final boolean cancel;
    private final long timeoutTick;
    private final Runnable whenTimeout;
    private final boolean runOnMain;

    public BukkitWizard(EventManager manager, EventPriority priority, Class<E> type, H human, boolean cancel, long timeoutTick, Runnable whenTimeout, boolean runOnMain) {
        this.manager = manager;
        this.priority = priority;
        this.type = type;
        this.human = human;
        this.cancel = cancel;
        this.timeoutTick = timeoutTick;
        this.whenTimeout = whenTimeout;
        this.runOnMain = runOnMain;
    }

    public BukkitWizard(EventPriority priority, Class<E> type, H human, boolean cancel, long timeoutTick, Runnable whenTimeout) {
        this(JunLibrary.get().getEventManager(), priority, type, human, cancel, timeoutTick, whenTimeout, true);
    }

    public BukkitWizard(EventPriority priority, Class<E> type, H human, boolean cancel, long timeoutTick) {
        this(priority, type, human, cancel, timeoutTick, Runnables.EMPTY);
    }

    public BukkitWizard(EventPriority priority, Class<E> type, H human, boolean cancel) {
        this(priority, type, human, cancel, 20L * 15L);
    }

    public BukkitWizard(H human, Class<E> type, boolean cancel) {
        this(EventPriority.HIGHEST, type, human, cancel);
    }

    public BukkitWizard(H human, Class<E> type) {
        this(human, type, true);
    }

    public static boolean cancel(String name) {
        BukkitWizard.Processor removed = notifierMap.remove(name);
        if (removed != null) {
            removed.cancel();
            return true;
        }
        return false;
    }

    public static boolean cancel(HumanEntity entity) {
        return cancel(entity.getName());
    }

    @Override
    public void run(Consumer<T> consumer) {
        String name = getPlayer().getName();
        BukkitWizard.Processor processor = new Processor(consumer);
        BukkitWizard.Processor prev = notifierMap.put(name, processor);
        if (prev != null) {
            prev.cancel();
        }
        if (timeoutTick >= 0) {
            processor.timeoutTask = Bukkit.getScheduler().runTaskLater(JunLibrary.get(),
                    () -> {
                        cancel(name);
                        whenTimeout.run();
                    }, timeoutTick);
        }
        manager.registerListener(priority, processor);
    }

    protected abstract void process(E event, Consumer<T> resultCallback);

    public H getPlayer() {
        return human;
    }

    class Processor implements EventListener<Event> {
        private final Consumer<T> consumer;
        private BukkitTask timeoutTask;

        Processor(Consumer<T> consumer) {
            this.consumer = consumer;
        }

        @Override
        public void onEvent(Event event) {
            if (event.getClass().isAssignableFrom(type)) {
                E e = type.cast(event);
                if (e instanceof PlayerEvent && !getPlayer().equals(((PlayerEvent) e).getPlayer())) {
                    return;
                }
                process(e, data -> {
                    BukkitWizard.cancel(getPlayer());
                    if (e.isAsynchronous() && runOnMain) {
                        Bukkit.getScheduler().runTask(JunLibrary.get(), () -> consumer.accept(data));
                    } else {
                        consumer.accept(data);
                    }
                    if (e instanceof Cancellable && cancel) {
                        ((Cancellable) e).setCancelled(true);
                    }
                });
            }
        }

        void cancel() {
            if (timeoutTask != null) {
                timeoutTask.cancel();
            }
            manager.removeListener(this);
        }
    }
}
