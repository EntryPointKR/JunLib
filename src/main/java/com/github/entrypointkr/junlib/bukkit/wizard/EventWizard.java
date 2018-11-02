package com.github.entrypointkr.junlib.bukkit.wizard;

import com.github.entrypointkr.junlib.bukkit.event.EventListener;
import com.github.entrypointkr.junlib.bukkit.event.Events;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;

import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-02
 */
public abstract class EventWizard<T> implements Wizard<T> {
    private final EventPriority priority;

    public EventWizard(EventPriority priority) {
        this.priority = priority;
    }

    public EventWizard() {
        this(EventPriority.LOWEST);
    }

    @Override
    public void run(Consumer<T> consumer) {
        EventListener<Event> listener = new EventListener<Event>() {
            @Override
            public void onEvent(Event event) {
                process(event, data -> {
                    consumer.accept(data);
                    Events.removeListener(this);
                });
            }
        };
        Events.registerListener(priority, listener);
    }

    protected abstract void process(Event event, Consumer<T> resultCallback);
}
