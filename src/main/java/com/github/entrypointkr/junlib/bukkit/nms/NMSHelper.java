package com.github.entrypointkr.junlib.bukkit.nms;

import org.bukkit.Bukkit;

public class NMSHelper {
    private static String versionPackage = null;
    private static String cachedNMSPackage = null;
    private static String cachedOBCPackage = null;

    public static String getVersionPackage() {
        if (versionPackage == null) {
            String className = Bukkit.getServer().getClass().getName();
            int start = className.indexOf(".v");
            if (start < 0) {
                throw new IllegalStateException();
            }
            start += 1; // Skip dot
            int end = className.indexOf('.', start);
            if (end < 0) {
                throw new IllegalStateException();
            }
            versionPackage = className.substring(start, end); // Ex) v1_13_R1
        }
        return versionPackage;
    }

    public static String getNMSPackageName() {
        if (cachedNMSPackage == null) {
            cachedNMSPackage = "net.minecraft.server." + getVersionPackage();
        }
        return cachedNMSPackage;
    }

    public static String getOBCPackage() {
        if (cachedOBCPackage == null) {
            cachedOBCPackage = "org.bukkit.craftbukkit." + getVersionPackage();
        }
        return cachedOBCPackage;
    }

    public static String getFullNMSClassName(String className) {
        return getNMSPackageName() + '.' + className;
    }

    public static String getFullOBCClassName(String className) {
        return getOBCPackage() + '.' + className;
    }

    public static Class<?> getNMSClass(String className) {
        try {
            return Class.forName(getFullNMSClassName(className));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Class<?> getOBCClass(String className) {
        try {
            return Class.forName(getFullOBCClassName(className));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
