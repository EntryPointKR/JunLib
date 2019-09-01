package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.util.ArrayCollection;
import com.github.entrypointkr.junlib.command.util.Reader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BukkitCommandAdaptor extends Command implements PluginIdentifiableCommand {
    private final Plugin plugin;
    private final com.github.entrypointkr.junlib.command.Command<BukkitSource> command;

    public BukkitCommandAdaptor(String name, Plugin plugin, com.github.entrypointkr.junlib.command.Command<BukkitSource> command) {
        super(name);
        this.plugin = plugin;
        this.command = command;
    }

    public BukkitCommandAdaptor(String name, String description, String usageMessage, List<String> aliases, Plugin plugin, com.github.entrypointkr.junlib.command.Command<BukkitSource> command) {
        super(name, description, usageMessage, aliases);
        this.plugin = plugin;
        this.command = command;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        command.execute(commandLabel, new BukkitSource(sender), new Reader<>(ArrayCollection.of(args)));
        return true;
    }

    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return command.tabComplete(new BukkitSource(sender), new Reader<>(ArrayCollection.of(args)));
    }

    @NotNull
    @Override
    public Plugin getPlugin() {
        return plugin;
    }
}
