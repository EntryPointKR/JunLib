package com.github.entrypointkr.junlib.bukkit.command;

import com.github.entrypointkr.junlib.bukkit.util.BukkitArrayReader;
import com.github.entrypointkr.junlib.bukkit.util.Bukkits;
import com.github.entrypointkr.junlib.command.PermissibleCommand;

/**
 * Created by JunHyeong on 2018-10-26
 */
public interface BukkitCommand extends PermissibleCommand<CommandSenderEx, BukkitArrayReader> {
    default String permission() {
        return null;
    }

    @Override
    default boolean checkPermission(CommandSenderEx sender) {
        String permission = permission();
        return permission == null || sender.hasPermission(permission);
    }

    @Override
    default void onPermissionDenied(CommandSenderEx sender, BukkitArrayReader args) {
        sender.sendMessage(Bukkits.colorize(String.format("&f권한 &e%s &f가 없습니다.", permission())));
    }
}
