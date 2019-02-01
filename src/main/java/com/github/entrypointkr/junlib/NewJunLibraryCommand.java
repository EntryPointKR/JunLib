package com.github.entrypointkr.junlib;

import com.github.entrypointkr.junlib.command.BukkitSource;
import com.github.entrypointkr.junlib.command.Command;
import com.github.entrypointkr.junlib.command.CommandBuilder;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

@SuppressWarnings("deprecation")
public class NewJunLibraryCommand {
    public static void register(Plugin plugin) {
        Command<BukkitSource> healCommand = CommandBuilder.ofBukkit()
                .perm("junlibrary.heal")
                .executor((sender, args) -> {
                    Player player = sender.toPlayer();
                    player.setHealth(player.getMaxHealth());
                    player.sendMessage("Done.");
                })
                .build();
        Command<BukkitSource> jumpCommand = CommandBuilder.ofBukkit()
                .perm("junlibrary.jump")
                .executor((sender, args) -> {
                    Player player = sender.toPlayer();
                    Block targetBlock = player.getTargetBlock(null, 100);
                    if (targetBlock != null) {
                        Location loc = player.getLocation();
                        Location targetLoc = targetBlock.getLocation();
                        targetLoc.setYaw(loc.getYaw());
                        targetLoc.setPitch(loc.getPitch());
                        player.sendMessage("Done.");
                    } else {
                        player.sendMessage("No target.");
                    }
                })
                .build();
        Command<BukkitSource> parent = CommandBuilder.ofBukkit()
                .child(healCommand, "heal", "h")
                .child(jumpCommand, "jump", "j")
                .helper()
                .build();
        JunLibrary.getCommandManager().registerCommand(plugin, parent, "junlibrary", "junlib", "jl");
    }
}
