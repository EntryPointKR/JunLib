package com.github.entrypointkr.junlib.bukkit.util;

import com.github.entrypointkr.junlib.util.Converter;
import com.github.entrypointkr.junlib.util.StringConverter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Created by JunHyeong on 2018-10-26
 */
public class BukkitStringConverter extends StringConverter {
    public BukkitStringConverter(String string) {
        super(string);
    }

    public Converter<Player> player() {
        return Converter.ofCommand(getString(), Bukkit::getPlayer, str -> str + " 는 온라인 플레이어가 아닙니다.");
    }

    public Converter<World> world() {
        return Converter.ofCommand(getString(), Bukkit::getWorld, str -> str + " 는 존재하지 않는 월드입니다.");
    }
}
