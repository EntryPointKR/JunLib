package com.github.entrypointkr.junlib.bukkit.command;

import com.github.entrypointkr.junlib.bukkit.util.BukkitArrayReader;
import com.github.entrypointkr.junlib.bukkit.util.BukkitArrayReaderImpl;
import com.github.entrypointkr.junlib.command.TabCompleteCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Created by JunHyeong on 2018-10-26
 */
public class BukkitCommandAdapter extends Command implements PluginIdentifiableCommand {
    private final TabCompleteCommand<CommandSenderEx, BukkitArrayReader> command;
    private final Plugin plugin;

    public BukkitCommandAdapter(String name, TabCompleteCommand<CommandSenderEx, BukkitArrayReader> command, Plugin plugin) {
        super(name);
        this.command = command;
        this.plugin = plugin;
    }

    public BukkitCommandAdapter(String name, String description, String usageMessage, List<String> aliases, TabCompleteCommand<CommandSenderEx, BukkitArrayReader> command, Plugin plugin) {
        super(name, description, usageMessage, aliases);
        this.command = command;
        this.plugin = plugin;
    }

    public static String[] merge(String label, String[] args) {
        String[] newArgs = new String[args.length + 1];
        newArgs[0] = label;
        System.arraycopy(args, 0, newArgs, 1, args.length);
        return newArgs;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        command.execute(new CommandSenderExImpl(sender), new BukkitArrayReaderImpl(merge(commandLabel, args)));
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        List<String> tabCompleted = command.tabComplete(new CommandSenderExImpl(sender), new BukkitArrayReaderImpl(merge(alias, args)));
        return tabCompleted != null ? tabCompleted : super.tabComplete(sender, alias, args);
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }
}
