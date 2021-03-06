package com.github.entrypointkr.junlib.bukkit.event;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by JunHyeong Lim on 2019-05-17
 */
public class EventManager {
    private final Map<EventPriority, Set<EventListener<Event>>> listeners = Collections.synchronizedMap(new EnumMap<>(EventPriority.class));

    @SuppressWarnings("unchecked")
    public void inject(Plugin plugin) {
        try {
            Field field = HandlerList.class.getDeclaredField("allLists");
            field.setAccessible(true);
            List<HandlerList> handlers = (List<HandlerList>) field.get(null);
            ArrayList<HandlerList> newHandlers = new ArrayListHook(plugin);
            newHandlers.addAll(handlers);
            field.set(null, newHandlers);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    public Set<EventListener<Event>> getListeners(EventPriority priority) {
        return listeners.computeIfAbsent(priority, k -> Collections.newSetFromMap(new ConcurrentHashMap<>()));
    }

    public void registerListener(EventPriority priority, EventListener<Event>... listeners) {
        getListeners(priority).addAll(Arrays.asList(listeners));
    }

    public void removeListener(EventListener... listeners) {
        for (Set<EventListener<Event>> value : this.listeners.values()) {
            value.removeAll(Arrays.asList(listeners));
        }
    }

    class ArrayListHook extends ArrayList<HandlerList> {
        private final Plugin plugin;

        public ArrayListHook(Plugin plugin) {
            this.plugin = plugin;
        }

        @Override
        public boolean add(HandlerList handlerList) {
            for (EventPriority priority : EventPriority.values()) {
                handlerList.register(new Notifier(priority, plugin));
            }
            return super.add(handlerList);
        }

        @Override
        public boolean addAll(Collection<? extends HandlerList> c) {
            for (HandlerList list : c) {
                add(list);
            }
            return true;
        }
    }

    class Notifier extends RegisteredListener {
        Notifier(EventPriority priority, Plugin plugin) {
            super(new Listener() {
            }, null, priority, plugin, false);
        }

        @Override
        public void callEvent(Event event) {
            for (EventListener<Event> listener : getListeners(getPriority())) {
                listener.onEvent(event);
            }
        }
    }
}
