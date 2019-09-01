package com.github.entrypointkr.junlib.bukkit.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by JunHyeong on 2018-10-29
 */
public class InventoryBuilder implements InventoryFactory {
    private InventoryType type;
    private int size;
    private String title;
    private ItemStack[] contents;
    private int addPosition = 0;

    private InventoryBuilder(InventoryType type) {
        this.type = Objects.requireNonNull(type);
    }

    public static InventoryBuilder of(InventoryType type) {
        return new InventoryBuilder(type)
                .size(type.getDefaultSize())
                .title(type.getDefaultTitle());
    }

    public static InventoryBuilder ofType(InventoryType type, String title) {
        return of(type).title(title);
    }

    public static InventoryBuilder ofChest(String title, int row) {
        return new InventoryBuilder(InventoryType.CHEST).title(title).row(row);
    }

    @Override
    public Inventory create(InventoryHolder holder) {
        Inventory inventory = type == InventoryType.CHEST
                ? Bukkit.createInventory(null, size, title)
                : Bukkit.createInventory(null, type, title);
        inventory.setContents(getContents());
        return inventory;
    }

    public InventoryBuilder type(InventoryType type) {
        this.type = Objects.requireNonNull(type);
        return this;
    }

    public InventoryBuilder size(int size) {
        this.size = size;
        ItemStack[] contents = getContents();
        if (contents.length != size) {
            ItemStack[] newContents = new ItemStack[size];
            System.arraycopy(contents, 0, newContents, 0, Math.min(contents.length, size));
            contents(newContents);
        }
        return this;
    }

    public InventoryBuilder row(int row) {
        return size(row * 9);
    }

    public InventoryBuilder set(ItemStack item, int... indexes) {
        if (item != null) {
            ItemStack[] contents = getContents();
            for (int index : indexes) {
                contents[index] = item;
            }
        }
        return this;
    }

    public InventoryBuilder add(Collection<ItemStack> items) {
        for (ItemStack item : items) {
            set(item, addPosition++);
        }
        return this;
    }

    public InventoryBuilder add(ItemStack... items) {
        return add(Arrays.asList(items));
    }

    public InventoryBuilder set(int row, int column, ItemStack item) {
        return set(item, row * 9 + column);
    }

    public InventoryBuilder contents(ItemStack[] contents) {
        this.contents = Objects.requireNonNull(contents);
        size(contents.length);
        return this;
    }

    public InventoryBuilder title(String title) {
        this.title = title;
        return this;
    }

    public InventoryBuilder position(int addPosition) {
        this.addPosition = addPosition;
        return this;
    }

    public InventoryType getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public ItemStack[] getContents() {
        if (contents == null) {
            contents = new ItemStack[size];
        }
        return contents;
    }
}
