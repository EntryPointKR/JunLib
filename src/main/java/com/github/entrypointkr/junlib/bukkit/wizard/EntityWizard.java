package com.github.entrypointkr.junlib.bukkit.wizard;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventPriority;

/**
 * Created by JunHyeong on 2018-11-02
 */
public abstract class EntityWizard<T, E extends Entity> extends EventWizard<T> {
    private final E entity;

    public EntityWizard(EventPriority priority, E entity) {
        super(priority);
        this.entity = entity;
    }

    public EntityWizard(E entity) {
        this.entity = entity;
    }

    public E getEntity() {
        return entity;
    }
}
