package com.github.entrypointkr.junlib.bukkit.command;

import com.github.entrypointkr.junlib.bukkit.util.BukkitArrayReader;
import com.github.entrypointkr.junlib.command.CommandHelpWriter;
import com.github.entrypointkr.junlib.command.HelpableCommand;
import com.github.entrypointkr.junlib.command.ICommand;
import com.github.entrypointkr.junlib.command.MapCommand;
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

    public void registerCommand(Plugin plugin, ICommand<CommandSenderEx, BukkitArrayReader> command, CommandHelpWriter<CommandSenderEx, BukkitArrayReader> helper, String... aliases) {
        Validate.notEmpty(aliases);
        String main = aliases[0];
        MapCommand<CommandSenderEx, BukkitArrayReader> newCommand = MapCommand.ofBukkit();
        for (String alias : aliases) {
            newCommand.put(alias, command);
        }
        commandMap.register(main, "junlibrary", new BukkitCommandAdapter(main, "", "/",
                Arrays.asList(aliases), HelpableCommand.ofBukkit(newCommand, helper, plugin.getLogger()), plugin));
    }

    public void registerCommand(Plugin plugin, ICommand<CommandSenderEx, BukkitArrayReader> command, String... aliases) {
        registerCommand(plugin, command, new BukkitCommandHelpWriter(), aliases);
    }
}
