package com.github.entrypointkr.junlib.bukkit.gui.component;

import com.github.entrypointkr.junlib.bukkit.gui.GUI;
import com.github.entrypointkr.junlib.bukkit.gui.GUIComponent;
import com.github.entrypointkr.junlib.bukkit.gui.handler.CancelHandler;
import com.github.entrypointkr.junlib.bukkit.gui.handler.ClickHandler;
import com.github.entrypointkr.junlib.bukkit.gui.handler.GUIHandler;
import com.github.entrypointkr.junlib.bukkit.inventory.InventoryBuilder;
import com.github.entrypointkr.junlib.bukkit.inventory.InventoryFactory;
import com.github.entrypointkr.junlib.bukkit.item.ItemBuilder;
import com.github.entrypointkr.junlib.bukkit.item.Meta;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by JunHyeong on 2018-10-31
 */
public class PaginationComponent implements GUIComponent {
    private final InventoryFactory base;
    private final List<ItemStack> items;
    private final GUIHandler<InventoryEvent> preHandler;
    private final ClickHandler clickHandler;
    private final int size;
    private int page = 0;

    public PaginationComponent(InventoryFactory base, List<ItemStack> items, GUIHandler<InventoryEvent> preHandler, ClickHandler clickHandler, int size) {
        this.base = base;
        this.items = items;
        this.preHandler = preHandler;
        this.clickHandler = clickHandler;
        this.size = size;
    }

    public PaginationComponent(String title, int row, List<ItemStack> items, GUIHandler<InventoryEvent> preHandler, ClickHandler clickHandler) {
        this(InventoryBuilder.ofChest(title, row), items, preHandler, clickHandler, row * 9);
    }

    public PaginationComponent(String title, int row, List<ItemStack> items, ClickHandler clickHandler) {
        this(title, row, items, CancelHandler.TOP, clickHandler);
    }

    @Override
    public Inventory create(InventoryHolder holder) {
        Inventory inventory = base.create(holder);
        setPaginationContents(inventory);
        setNevigationContents(inventory);
        return inventory;
    }

    @Override
    public void onEvent(GUI gui, InventoryEvent e) {
        preHandler.onEvent(gui, e);
        if (e instanceof InventoryClickEvent) {
            InventoryClickEvent event = ((InventoryClickEvent) e);
            Inventory inventory = event.getView().getTopInventory();
            if (event.getAction() != InventoryAction.NOTHING) {
                int rawSlot = event.getRawSlot();
                int size = getContentsSize();
                if (rawSlot == size + 3) {
                    setPage(page - 1);
                    inventory.setContents(create(event.getWhoClicked()).getContents());
                } else if (rawSlot == size + 5) {
                    setPage(page + 1);
                    inventory.setContents(create(event.getWhoClicked()).getContents());
                } else if (rawSlot < size) {
                    int adjust = getAdjustSlot(size, rawSlot);
                    clickHandler.notify(adjust, gui, event);
                }
            }
        }
    }

    public int getContentsSize() {
        return size - 9;
    }

    public int getAdjustSlot(int size, int slot) {
        int offset = size * page;
        return offset + slot;
    }

    public int getAdjustSlot(int slot) {
        return getAdjustSlot(getContentsSize(), slot);
    }

    public void setPaginationContents(Inventory inventory) {
        int size = getContentsSize();
        int start = size * page;
        int end = start + size;
        int index = 0;
        for (int i = start; i < items.size() && i < end; i++) {
            ItemStack item = items.get(i);
            inventory.setItem(index++, item);
        }
    }

    public void setNevigationContents(Inventory inventory) {
        int offset = getContentsSize();
        int maxPage = getMaxPage();
        String pageInfo = String.format("%s/%s", page + 1, maxPage + 1);
        inventory.setItem(offset + 3, ItemBuilder.of(Material.STONE_BUTTON)
                .meta(Meta.of().display(pageInfo))
                .create());
        inventory.setItem(offset + 5, ItemBuilder.of(Material.STONE_BUTTON)
                .meta(Meta.of().display(pageInfo))
                .create());
    }

    public int getMaxPage() {
        return items.size() / getContentsSize();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = Math.min(Math.max(page, 0), getMaxPage());
    }
}
