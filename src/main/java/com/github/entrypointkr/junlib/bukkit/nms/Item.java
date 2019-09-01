package com.github.entrypointkr.junlib.bukkit.nms;

import org.bukkit.Material;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Item {
    private static Class<?> type = null;
    private final Object handle;
    private String name = null;

    public static Class<?> getType() {
        if (type == null) {
            type = NMSHelper.getNMSClass("Item");
        }
        return type;
    }

    public Item(Object handle) {
        this.handle = handle;
    }

    public String getName() {
        if (name == null) {
            try {
                Method method = handle.getClass().getDeclaredMethod("getName");
                method.setAccessible(true);
                return (String) method.invoke(handle);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException(e);
            }
        }
        return name;
    }

    public Material getMaterial() {
        try {
            Method method = NMSHelper.getOBCClass("util.CraftMagicNumbers").getDeclaredMethod(
                    "getMaterial",
                    getType());
            method.setAccessible(true);
            Object mat = method.invoke(null, handle);
            if (mat instanceof Material) {
                return (Material) mat;
            }
        } catch (Exception ex) {
            // Ignore
        }
        throw new IllegalStateException();
    }
}
