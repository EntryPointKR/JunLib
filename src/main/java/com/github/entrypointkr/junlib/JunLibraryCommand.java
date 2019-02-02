package com.github.entrypointkr.junlib;

import com.github.entrypointkr.junlib.bukkit.event.listener.EventSkipper;
import com.github.entrypointkr.junlib.bukkit.event.listener.GUIParent;
import com.github.entrypointkr.junlib.bukkit.gui.GUI;
import com.github.entrypointkr.junlib.bukkit.gui.component.PaginationComponent;
import com.github.entrypointkr.junlib.bukkit.gui.handler.CancelHandler;
import com.github.entrypointkr.junlib.bukkit.gui.handler.ClickHandler;
import com.github.entrypointkr.junlib.bukkit.gui.handler.CombinedHandler;
import com.github.entrypointkr.junlib.bukkit.inventory.InventoryBuilder;
import com.github.entrypointkr.junlib.bukkit.item.ItemBuilder;
import com.github.entrypointkr.junlib.bukkit.item.ItemWrapper;
import com.github.entrypointkr.junlib.bukkit.item.SimpleMeta;
import com.github.entrypointkr.junlib.bukkit.wizard.ChatWizard;
import com.github.entrypointkr.junlib.command.*;
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
                    CombinedHandler handler = new CombinedHandler()
                            .add(
                                    CancelHandler.TOP,
                                    new ClickHandler().put(1, 4, (gui1, e) ->
                                            e.getView().getPlayer().sendMessage("Clicked!"))
                            );
                    new GUI(player, builder, handler).open();
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
                    File file = new File(JunLibrary.getPlugin().getDataFolder(), "items/" + fileName + ".yml");
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
        JunLibrary.getCommandManager().registerCommand(plugin, parent, "junlibrary", "junlib", "jl");
    }
}
