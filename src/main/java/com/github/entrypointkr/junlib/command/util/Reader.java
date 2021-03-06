package com.github.entrypointkr.junlib.command.util;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public class Reader<T> implements SimpleCollection<T> {
    private final SimpleCollection<T> collection;
    private int position;

    public Reader(SimpleCollection<T> collection) {
        this.collection = collection;
    }

    public T read() {
        return collection.get(position++);
    }

    public int remain() {
        return size() - position;
    }

    public boolean isEmpty() {
        return remain() <= 0;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Reader<T> copy() {
        Reader<T> copied = new Reader<>(collection);
        copied.position = position;
        return copied;
    }

    @Override
    public T get(int index) {
        return collection.get(position + index);
    }

    @Override
    public int size() {
        return collection.size();
    }

    public String getPreviousArguments(int head) {
        StringBuilder builder = new StringBuilder();
        int prevSize = getPosition();
        for (int i = head; i < prevSize; i++) {
            if (builder.length() > 0) {
                builder.append(' ');
            }
            builder.append(collection.get(i));
        }
        return builder.toString();
    }

    public String getPreviousArguments() {
        return getPreviousArguments(0);
    }

    public SimpleCollection<T> getCollection() {
        return collection;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return getCollection().iterator();
    }
}
