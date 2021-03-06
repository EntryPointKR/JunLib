package com.github.entrypointkr.junlib.command.util;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by JunHyeong Lim on 2019-01-02
 */
public class ArrayCollection<T> implements SimpleCollection<T> {
    private final T[] array;

    private ArrayCollection(T[] array) {
        this.array = array;
    }

    public static <T> ArrayCollection<T> of(T[] array) {
        return new ArrayCollection<>(array);
    }

    public static ArrayCollection<String> ofSpaced(String string) {
        return of(string.split(" "));
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public int size() {
        return array.length;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return Arrays.asList(array).iterator();
    }
}
