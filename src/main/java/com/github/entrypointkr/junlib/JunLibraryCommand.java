package com.github.entrypointkr.junlib;

import com.github.entrypointkr.junlib.bukkit.command.BukkitCommand;
import com.github.entrypointkr.junlib.bukkit.command.CommandSenderEx;
import com.github.entrypointkr.junlib.bukkit.gui.GUI;
import com.github.entrypointkr.junlib.bukkit.gui.component.PaginationComponent;
import com.github.entrypointkr.junlib.bukkit.gui.handler.CancelHandler;
import com.github.entrypointkr.junlib.bukkit.gui.handler.ClickHandler;
import com.github.entrypointkr.junlib.bukkit.gui.handler.CombinedHandler;
import com.github.entrypointkr.junlib.bukkit.inventory.InventoryBuilder;
import com.github.entrypointkr.junlib.bukkit.util.BukkitArrayReader;
import com.github.entrypointkr.junlib.command.MapCommand;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunHyeong on 2018-10-23
 */
class JunLibraryCommand {
    static void register(Plugin plugin) {
        JunLibrary.getCommandManager().registerCommand(plugin, MapCommand.ofBukkit()
                .put(new HealCommand(), "heal", "h")
                .put(new JumpCommand(), "jump", "j")
                .put(new EntitiesKillCommand(), "killall")
                .put(new GUIDemoCommand(), "gui")
                .put(new PaginationGUIDemoCommand(), "pagegui"), "junlibrary", "junlib", "jl");
    }

    @SuppressWarnings("deprecation")
    static class HealCommand implements BukkitCommand {
        @Override
        public void doExecute(CommandSenderEx sender, BukkitArrayReader args) {
            Player player = sender.toPlayerOrThrow();
            player.setHealth(player.getMaxHealth());
            player.sendMessage("Done.");
        }
    }

    static class JumpCommand implements BukkitCommand {
        @Override
        public void doExecute(CommandSenderEx sender, BukkitArrayReader args) {
            Player player = sender.toPlayerOrThrow();
            player.teleport(player.getEyeLocation());
            player.sendMessage("Done.");
        }
    }

    static class EntitiesKillCommand implements BukkitCommand {
        @Override
        public void doExecute(CommandSenderEx sender, BukkitArrayReader args) {
            World world = sender.isPlayer()
                    ? sender.toPlayerOrThrow().getWorld()
                    : args.readConvert().get().world().get();
            int counter = 0;
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Monster) {
                    entity.remove();
                    counter++;
                }
            }
            sender.sendMessage(String.format("%s entity removed in %s.", counter, world.getName()));
        }
    }

    static class GUIDemoCommand implements BukkitCommand {
        @Override
        public void doExecute(CommandSenderEx sender, BukkitArrayReader args) {
            Player player = sender.toPlayerOrThrow();
            InventoryBuilder builder = InventoryBuilder.ofChest("JunLibrary GUI Demo", 3)
                    .set(1, 4, new ItemStack(Material.DIRT));
            CombinedHandler handler = new CombinedHandler()
                    .add(
                            CancelHandler.TOP,
                            new ClickHandler().put(1, 4, (gui1, e) ->
                                    e.getView().getPlayer().sendMessage("Clicked!"))
                    );
            GUI gui = new GUI(builder, handler);
            gui.open(player);
        }
    }

    static class PaginationGUIDemoCommand implements BukkitCommand {
        @Override
        public void doExecute(CommandSenderEx sender, BukkitArrayReader args) {
            Player player = sender.toPlayerOrThrow();
            List<ItemStack> items = new ArrayList<>();
            ClickHandler handler = new ClickHandler();
            for (int i = 0; i < 50; i++) {
                String indexStr = String.valueOf(i);
                items.add(new ItemStack(Material.DIRT));
                handler.put((gui, e) -> e.getView().getPlayer().sendMessage("Clicked: " + indexStr), i);
            }
            GUI gui = new GUI(new PaginationComponent("JunLibrary Pagination GUI Demo", 3, items, handler));
            gui.open(player);
        }
    }
}
