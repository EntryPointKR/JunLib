package com.github.entrypointkr.junlib;

import com.github.entrypointkr.junlib.bukkit.command.BukkitCommand;
import com.github.entrypointkr.junlib.bukkit.command.CommandSenderEx;
import com.github.entrypointkr.junlib.bukkit.event.listener.EventSkipper;
import com.github.entrypointkr.junlib.bukkit.event.listener.GUIParent;
import com.github.entrypointkr.junlib.bukkit.gui.GUI;
import com.github.entrypointkr.junlib.bukkit.gui.component.PaginationComponent;
import com.github.entrypointkr.junlib.bukkit.gui.handler.CancelHandler;
import com.github.entrypointkr.junlib.bukkit.gui.handler.ClickHandler;
import com.github.entrypointkr.junlib.bukkit.gui.handler.CombinedHandler;
import com.github.entrypointkr.junlib.bukkit.inventory.InventoryBuilder;
import com.github.entrypointkr.junlib.bukkit.item.ItemBuilder;
import com.github.entrypointkr.junlib.bukkit.item.SimpleMeta;
import com.github.entrypointkr.junlib.bukkit.util.BukkitArrayReader;
import com.github.entrypointkr.junlib.bukkit.wizard.ChatWizard;
import com.github.entrypointkr.junlib.command.MapCommand;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
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
                        .put(new PaginationGUIDemoCommand(), "pagegui"),
                "junlibrary", "junlib", "jl");
    }

    @SuppressWarnings("deprecation")
    static class HealCommand implements BukkitCommand {
        @Override
        public void doExecute(CommandSenderEx sender, BukkitArrayReader args) {
            Player player = sender.toPlayerOrThrow();
            player.setHealth(player.getMaxHealth());
            player.sendMessage("Done.");
        }

        @Override
        public String permission() {
            return "junlibrary.heal";
        }
    }

    static class JumpCommand implements BukkitCommand {
        @Override
        public void doExecute(CommandSenderEx sender, BukkitArrayReader args) {
            Player player = sender.toPlayerOrThrow();
            player.teleport(player.getEyeLocation());
            player.sendMessage("Done.");
        }

        @Override
        public String permission() {
            return "junlibrary.jump";
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

        @Override
        public String permission() {
            return "junlibrary.killall";
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
            new GUI(player, builder, handler).open();
        }

        @Override
        public String permission() {
            return "junlibrary.gui";
        }
    }

    static class PaginationGUIDemoCommand implements BukkitCommand {
        @Override
        public void doExecute(CommandSenderEx sender, BukkitArrayReader args) {
            Player player = sender.toPlayerOrThrow();
            List<ItemStack> items = new ArrayList<>();
            ClickHandler handler = new ClickHandler();
            for (int i = 0; i < 50; i++) {
                ItemStack item = ItemBuilder.of(Material.DIRT)
                        .create(new SimpleMeta().name("#" + i));
                items.add(item);
                handler.put((_gui, e) -> {
                    ClickHandler clickHandler = new ClickHandler();
                    InventoryBuilder builder = InventoryBuilder.ofChest("JunLibrary Pagination Sub Demo", 3)
                            .set(1, 4, item);
                    EventSkipper skipper = new EventSkipper(new GUIParent(new CombinedHandler().add(CancelHandler.TOP, clickHandler), _gui));
                    GUI gui = new GUI(player, builder, skipper);
                    clickHandler.put(1, 4, (__gui, _e) -> {
                        skipper.add(InventoryCloseEvent.class);
                        player.closeInventory();
                        new ChatWizard(player).run(input -> {
                            player.sendMessage("Your input: " + input);
                            gui.open();
                        });
                        player.sendMessage("Enter your input");
                    });
                    gui.open();
                }, i);
            }
            new GUI(player, new PaginationComponent(
                    "JunLibrary Pagination GUI Demo", 3, items, handler
            )).open();
        }

        @Override
        public String permission() {
            return "junlibrary.pagination-gui";
        }
    }
}
