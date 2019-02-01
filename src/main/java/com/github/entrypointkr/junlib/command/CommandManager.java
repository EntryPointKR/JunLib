package com.github.entrypointkr.junlib.command;

import org.apache.commons.lang.Validate;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

/**
 * Created by JunHyeong on 2018-10-26
 */
public class CommandManager {
    private final CommandMap commandMap;

    public CommandManager(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    public void registerCommand(Plugin plugin, Command<BukkitReceiver> command, String... aliases) {
        Validate.notEmpty(aliases);
        String main = aliases[0];
        commandMap.register(main, "junlibrary", new BukkitCommandAdaptor(main, "", "/", Arrays.asList(aliases), plugin, command));
    }
}
