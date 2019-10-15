package com.github.entrypointkr.junlib.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by JunHyung Lim on 2019-10-16
 */
public class CooldownMap {
    private final Map<UUID, Cooldown> map = new HashMap<>();

    public Cooldown get(UUID id) {
        return map.computeIfAbsent(id, k -> new Cooldown());
    }

    public Cooldown remove(UUID id) {
        return map.remove(id);
    }

    public boolean action(UUID id, long coolMillis) {
        return get(id).action(coolMillis);
    }
}
