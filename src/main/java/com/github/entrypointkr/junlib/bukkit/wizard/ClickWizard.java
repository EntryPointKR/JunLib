package com.github.entrypointkr.junlib.bukkit.wizard;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.function.Consumer;

/**
 * Created by JunHyeong on 2018-11-08
 */
public class ClickWizard extends BukkitWizard<Block, PlayerInteractEvent, Player> {
    public ClickWizard(EventPriority priority, Player human, boolean cancel) {
        super(priority, PlayerInteractEvent.class, human, cancel);
    }

    public ClickWizard(Player human, boolean cancel) {
        this(EventPriority.HIGHEST, human, cancel);
    }

    public ClickWizard(Player human) {
        this(human, true);
    }

    @Override
    protected void process(PlayerInteractEvent event, Consumer<Block> resultCallback) {
        resultCallback.accept(event.getClickedBlock());
    }
}
