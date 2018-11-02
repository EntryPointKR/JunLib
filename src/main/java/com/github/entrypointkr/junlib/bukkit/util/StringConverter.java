package com.github.entrypointkr.junlib.bukkit.util;

import com.github.entrypointkr.junlib.util.Converter;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Created by JunHyeong on 2018-10-26
 */
public class StringConverter {
    private final String string;

    public StringConverter(String string) {
        this.string = string;
    }

    public Converter<Player> player() {
        return Converter.ofCommand(string, Bukkit::getPlayer, str -> str + " 는 온라인 플레이어가 아닙니다.");
    }

    public Converter<World> world() {
        return Converter.ofCommand(string, Bukkit::getWorld, str -> str + " 는 존재하지 않는 월드입니다.");
    }

    public Converter<Number> number() {
        return Converter.ofCommand(string, string -> {
            try {
                return NumberUtils.createNumber(string);
            } catch (Exception ex) {
                return null;
            }
        }, str -> str + " 는 숫자가 아닙니다.");
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return "StringConverter{" +
                "string='" + string + '\'' +
                '}';
    }
}
