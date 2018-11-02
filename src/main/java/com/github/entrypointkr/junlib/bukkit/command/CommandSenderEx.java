package com.github.entrypointkr.junlib.bukkit.command;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * Created by JunHyeong on 2018-10-24
 */
public interface CommandSenderEx extends CommandSender {
    boolean isPlayer();

    Optional<Player> toPlayer();

    default Player toPlayerOrThrow(String message) {
        return toPlayer().orElseThrow(() -> new CommandException(message));
    }

    default Player toPlayerOrThrow() {
        return toPlayerOrThrow("플레이어가 아닙니다.");
    }
}
