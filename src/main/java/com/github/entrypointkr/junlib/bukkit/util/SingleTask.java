package com.github.entrypointkr.junlib.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Function;

/**
 * Created by JunHyeong Lim on 2018-12-13
 */
public class SingleTask implements BukkitTask {
    private BukkitTask current;
    private Runnable whenCancel;

    public void schedule(Function<BukkitScheduler, BukkitTask> callback, Runnable whenCancel) {
        set(callback.apply(Bukkit.getScheduler()), whenCancel);
    }

    public void set(BukkitTask newTask, Runnable whenCancel) {
        cancel();
        this.current = newTask;
        this.whenCancel = whenCancel;
    }

    @Override
    public int getTaskId() {
        return current != null ? current.getTaskId() : -1;
    }

    @Override
    public Plugin getOwner() {
        return current != null ? current.getOwner() : null;
    }

    @Override
    public boolean isSync() {
        return current != null && current.isSync();
    }

    @Override
    public boolean isCancelled() {
        return current == null || current.isCancelled();
    }

    @Override
    public void cancel() {
        if (current != null) {
            current.cancel();
            whenCancel.run();
        }
    }
}
