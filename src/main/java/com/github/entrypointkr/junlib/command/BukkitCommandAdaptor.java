package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.util.ArrayCollection;
import com.github.entrypointkr.junlib.command.util.Reader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class BukkitCommandAdaptor extends Command implements PluginIdentifiableCommand {
    private final Plugin plugin;
    private final com.github.entrypointkr.junlib.command.Command<BukkitReceiver> command;

    public BukkitCommandAdaptor(String name, Plugin plugin, com.github.entrypointkr.junlib.command.Command<BukkitReceiver> command) {
        super(name);
        this.plugin = plugin;
        this.command = command;
    }

    public BukkitCommandAdaptor(String name, String description, String usageMessage, List<String> aliases, Plugin plugin, com.github.entrypointkr.junlib.command.Command<BukkitReceiver> command) {
        super(name, description, usageMessage, aliases);
        this.plugin = plugin;
        this.command = command;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (command instanceof HelperCommand) {
            ((HelperCommand<BukkitReceiver>) command).setLabel(commandLabel);
        }
        command.execute(new BukkitReceiver(sender), new Reader<>(ArrayCollection.of(args)));
        return true;
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }
}
