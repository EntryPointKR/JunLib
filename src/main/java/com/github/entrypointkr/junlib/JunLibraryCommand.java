package com.github.entrypointkr.junlib;

import com.github.entrypointkr.junlib.bukkit.event.listener.EventSkipper;
import com.github.entrypointkr.junlib.bukkit.gui.GUI;
import com.github.entrypointkr.junlib.bukkit.gui.component.PaginationComponent;
import com.github.entrypointkr.junlib.bukkit.gui.handler.CancelHandler;
import com.github.entrypointkr.junlib.bukkit.gui.handler.ClickHandler;
import com.github.entrypointkr.junlib.bukkit.gui.handler.CombinedHandler;
import com.github.entrypointkr.junlib.bukkit.inventory.InventoryBuilder;
import com.github.entrypointkr.junlib.bukkit.item.ItemBuilder;
import com.github.entrypointkr.junlib.bukkit.item.ItemWrapper;
import com.github.entrypointkr.junlib.bukkit.item.Meta;
import com.github.entrypointkr.junlib.bukkit.wizard.ChatWizard;
import com.github.entrypointkr.junlib.command.Argument;
import com.github.entrypointkr.junlib.command.BukkitSource;
import com.github.entrypointkr.junlib.command.Command;
import com.github.entrypointkr.junlib.command.CommandBuilder;
import com.github.entrypointkr.junlib.command.exception.CommandException;
import com.github.entrypointkr.junlib.util.FileUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class JunLibraryCommand {
    public static void main(String[] args) {
        int total = 0;
        String str = "testStr";
        for (int i = 0; i < 1000; i++) {
            long time = System.currentTimeMillis();
            switch (str) {
                case "testStr":

            }
            total += System.currentTimeMillis() - time;
        }
        System.out.println("Switch: " + total);
        total = 0;

        for (int i = 0; i < 1000; i++) {
            long time = System.currentTimeMillis();
            switch (str) {
                case "testStr":

            }
            total += System.currentTimeMillis() - time;
        }
        System.out.println("Switch: " + total);
        total = 0;

        for (int i = 0; i < 1000; i++) {
            long time = System.currentTimeMillis();
            if (str.equals("testStr")) {

            }
            total += System.currentTimeMillis() - time;
        }
        System.out.println("If: " + total);
        total = 0;
    }

    public static void register(Plugin plugin) {
        Command<BukkitSource> healCommand = CommandBuilder.ofBukkit()
                .perm("junlibrary.heal")
                .executor((sender, args) -> {
                    Player player = sender.toPlayer();
                    player.setHealth(player.getMaxHealth());
                    player.setFoodLevel(20);
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
        Command<BukkitSource> killallCommand = CommandBuilder.ofBukkit()
                .perm("junlibrary.killall")
                .args(Argument.world("world").optional())
                .executor((sender, args) -> {
                    World world = args.get("world", World.class)
                            .orElseGet(() -> sender.toPlayer().getWorld());
                    int count = 0;
                    for (Entity entity : world.getEntities()) {
                        if (entity instanceof Monster) {
                            entity.remove();
                            count++;
                        }
                    }
                    sender.sendMessage(String.format("%s entity removed in %s.", count, world.getName()));
                })
                .build();
        Command<BukkitSource> guiDemo = CommandBuilder.ofBukkit()
                .perm("junlibrary.gui")
                .executor((sender, args) -> {
                    Player player = sender.toPlayer();
                    InventoryBuilder builder = InventoryBuilder.ofChest("JunLibrary GUI Demo", 3)
                            .set(1, 4, new ItemStack(Material.DIRT));
                    CombinedHandler handler = CombinedHandler.of(
                            CancelHandler.TOP,
                            new ClickHandler().put(1, 4, (gui1, e) ->
                                    e.getView().getPlayer().sendMessage("Clicked!"))
                    );
                    GUI.of(builder, handler).open(player);
                })
                .build();
        Command<BukkitSource> paginationGuiDemo = CommandBuilder.ofBukkit()
                .perm("junlibrary.pagination-gui")
                .executor((sender, args) -> {
                    Player player = sender.toPlayer();
                    List<ItemStack> items = new ArrayList<>();
                    ClickHandler handler = new ClickHandler();
                    for (int i = 0; i < 50; i++) {
                        ItemStack item = ItemBuilder.of(Material.DIRT)
                                .meta(Meta.of().name("#" + i))
                                .create();
                        items.add(item);
                        handler.put((_gui, e) -> {
                            ClickHandler clickHandler = new ClickHandler();
                            InventoryBuilder builder = InventoryBuilder.ofChest("JunLibrary Pagination Sub Demo", 3)
                                    .set(1, 4, item);
                            EventSkipper skipper = EventSkipper.ofParent(_gui, player, CombinedHandler.of(CancelHandler.TOP, clickHandler));
                            GUI gui = GUI.of(builder, skipper);
                            clickHandler.put(1, 4, (__gui, _e) -> {
                                skipper.add(InventoryCloseEvent.class);
                                player.closeInventory();
                                new ChatWizard(player).run(input -> {
                                    player.sendMessage("Your input: " + input);
                                    gui.open(player);
                                });
                                player.sendMessage("Enter your input");
                            });
                            gui.open(player);
                        }, i);
                    }
                    GUI.of(new PaginationComponent(
                            "JunLibrary Pagination GUI Demo", 3, items, handler
                    )).open(player);
                })
                .build();
        Command<BukkitSource> itemSerialize = CommandBuilder.ofBukkit()
                .perm("junlibrary.serialize")
                .args(Argument.string("file-name").optional())
                .executor((sender, args) -> {
                    ItemStack item = sender.toPlayer().getItemInHand();
                    if (ItemWrapper.isEmpty(item)) {
                        throw new CommandException("손에 아이템을 들어주세요.");
                    }
                    String fileName = args.get("file-name", String.class)
                            .orElse("item");
                    File file = new File(JunLibrary.get().getDataFolder(), "items/" + fileName + ".yml");
                    try {
                        YamlConfiguration config = new YamlConfiguration();
                        config.set("item", item);
                        config.save(FileUtils.writeEnsure(file));
                    } catch (IOException e) {
                        throw new IllegalStateException("아이템 저장에 실패했습니다.", e);
                    }
                    sender.sendMessage("완료.");
                })
                .build();
        Command<BukkitSource> parent = CommandBuilder.ofBukkit()
                .child(healCommand, "heal", "h")
                .child(jumpCommand, "jump", "j")
                .child(killallCommand, "killall")
                .child(guiDemo, "gui")
                .child(paginationGuiDemo, "pagegui")
                .child(itemSerialize, "serialize")
                .helper()
                .build();
        JunLibrary.get().getCommandManager().registerCommand(plugin, parent, "junlibrary", "junlib", "jl");
    }
}
