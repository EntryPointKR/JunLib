package com.github.entrypointkr.junlib.bukkit.command;

import com.github.entrypointkr.junlib.bukkit.util.BukkitArrayReader;
import com.github.entrypointkr.junlib.bukkit.util.Bukkits;
import com.github.entrypointkr.junlib.command.PermissibleCommand;

/**
 * Created by JunHyeong on 2018-10-26
 */
public abstract class BukkitCommand extends PermissibleCommand<CommandSenderEx, BukkitArrayReader> {
    public String permission() {
        return null;
    }

    @Override
    public boolean checkPermission(CommandSenderEx sender) {
        String permission = permission();
        return permission == null || sender.hasPermission(permission);
    }

    @Override
    public void onPermissionDenied(CommandSenderEx sender, BukkitArrayReader args) {
        sender.sendMessage(Bukkits.colorize(String.format("&f권한 &e%s &f가 없습니다.", permission())));
    }
}
