package com.github.entrypointkr.junlib.bukkit.nms;

import org.bukkit.Material;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TileFurnace {
    private static final Map<Material, Integer> fuels = new HashMap<>();

    public static Map<Material, Integer> getFuels() {
        if (fuels.isEmpty()) {
            try {
                Method method = NMSHelper.getNMSClass("TileEntityFurnace")
                        .getDeclaredMethod("f");
                method.setAccessible(true);
                Object fuelsObj = method.invoke(null);
                Map<?, ?> fuels = ((Map<?, ?>) fuelsObj);
                for (Map.Entry<?, ?> entry : fuels.entrySet()) {
                    Object itemObj = entry.getKey();
                    Object valueObj = entry.getValue();
                    if (!(valueObj instanceof Number)) {
                        throw new IllegalStateException();
                    }
                    TileFurnace.fuels.put(new Item(itemObj).getMaterial(), ((Number) valueObj).intValue());
                }
            } catch (Exception ex) {
                // Ignore
            }
        }
        return fuels;
    }
}
