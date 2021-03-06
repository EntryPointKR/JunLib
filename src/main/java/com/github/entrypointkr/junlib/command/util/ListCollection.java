package com.github.entrypointkr.junlib.command.util;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

/**
 * Created by JunHyeong Lim on 2019-01-02
 */
public class ListCollection<T> implements SimpleCollection<T> {
    private final List<T> list;

    public ListCollection(List<T> list) {
        this.list = list;
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
