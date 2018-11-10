package com.github.entrypointkr.junlib.bukkit.wizard;

import com.github.entrypointkr.junlib.bukkit.util.Runnables;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-08
 */
public class ClickWizard extends BukkitWizard<Block, PlayerInteractEvent, HumanEntity> {
    public ClickWizard(EventPriority priority, HumanEntity human, boolean cancel, long timeoutTick, Runnable timeout) {
        super(priority, PlayerInteractEvent.class, human, cancel, timeoutTick, timeout);
    }

    public ClickWizard(EventPriority priority, HumanEntity human, boolean cancel, long timeoutTick) {
        this(priority, human, cancel, timeoutTick, Runnables.EMPTY);
    }

    public ClickWizard(EventPriority priority, HumanEntity human, boolean cancel) {
        this(priority, human, cancel, 20L * 15L);
    }

    public ClickWizard(HumanEntity human, boolean cancel) {
        this(EventPriority.HIGHEST, human, cancel);
    }

    public ClickWizard(HumanEntity human) {
        this(human, true);
    }

    @Override
    protected void process(PlayerInteractEvent event, Consumer<Block> resultCallback) {
        resultCallback.accept(event.getClickedBlock());
    }
}
