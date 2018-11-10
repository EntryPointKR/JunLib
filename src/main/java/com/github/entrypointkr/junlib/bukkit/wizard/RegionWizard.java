package com.github.entrypointkr.junlib.bukkit.wizard;

import com.github.entrypointkr.junlib.bukkit.location.Region;
import com.github.entrypointkr.junlib.wizard.Wizard;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by JunHyeong on 2018-11-08
 */
public class RegionWizard implements Wizard<Region> {
    private final HumanEntity player;
    private final Consumer<Block> whenPosA;
    private final Consumer<Block> whenPosB;
    private final Function<HumanEntity, ClickWizard> wizardFactory;

    public RegionWizard(HumanEntity player, Consumer<Block> whenPosA, Consumer<Block> whenPosB, Function<HumanEntity, ClickWizard> wizardFactory) {
        this.player = player;
        this.whenPosA = whenPosA;
        this.whenPosB = whenPosB;
        this.wizardFactory = wizardFactory;
    }

    public RegionWizard(HumanEntity player) {
        this(player, block -> {
            Location loc = block.getLocation();
            player.sendMessage(String.format("x: %s, y: %s, z: %s 를 클릭했습니다. 다음 지점을 선택하세요.", loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
        }, block -> {
            Location loc = block.getLocation();
            player.sendMessage(String.format("x: %s, y: %s, z: %s 를 클릭했습니다.", loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
        }, ClickWizard::new);
    }

    @Override
    public void run(Consumer<Region> consumer) {
        ClickWizard wizard = wizardFactory.apply(player);
        wizard.run(blockA -> {
            whenPosA.accept(blockA);
            wizard.run(blockB -> {
                whenPosB.accept(blockB);
                consumer.accept(Region.of(blockA.getLocation(), blockB.getLocation()));
            });
        });
    }
}
