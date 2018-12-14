package com.github.entrypointkr.junlib.bukkit.command;

import com.github.entrypointkr.junlib.bukkit.util.BukkitArrayReader;

/**
 * Created by JunHyeong Lim on 2018-12-14
 */
// For keep arguments name
public abstract class AbstractBukkitCommand implements BukkitCommand {
    @Override
    public abstract boolean checkPermission(CommandSenderEx sender);

    @Override
    public abstract void onPermissionDenied(CommandSenderEx sender, BukkitArrayReader args);

    @Override
    public abstract void doExecute(CommandSenderEx sender, BukkitArrayReader args);
}
