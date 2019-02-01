package com.github.entrypointkr.junlib.reflection;

import java.lang.reflect.Field;

/**
 * Created by JunHyeong Lim on 2019-01-05
 */
public class FieldEx {
    private final Field field;

    private FieldEx(Field field) {
        this.field = field;
        field.setAccessible(true);
    }

    public static FieldEx of(Field field) {
        return new FieldEx(field);
    }

    public static FieldEx of(Class aClass, String fieldName) {
        return ClassEx.of(aClass).findField(fieldName)
                .orElseThrow(IllegalStateException::new);
    }

    public <T> T get(Object instance, Class<T> type) {
        try {
            return type.cast(field.get(instance));
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public Object get(Object instance) {
        return get(instance, Object.class);
    }

    public <T> T get(Class<T> type) {
        return get(null, type);
    }

    public Object get() {
        return get(Object.class);
    }

    public void set(Object instance, Object value) {
        try {
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public void set(Object value) {
        set(null, value);
    }
}
