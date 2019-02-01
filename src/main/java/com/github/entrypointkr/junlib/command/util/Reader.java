package com.github.entrypointkr.junlib.command.util;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public class Reader<T> implements SimpleCollection<T> {
    private final SimpleCollection<T> collection;
    private int position;

    public Reader(SimpleCollection<T> collection) {
        this.collection = collection;
    }

    public static String getPreviousArguments(Reader<?> args) {
        StringBuilder builder = new StringBuilder();
        int prevSize = args.getPosition();
        for (int i = 0; i < prevSize; i++) {
            if (builder.length() > 0) {
                builder.append(' ');
            }
            builder.append(args.collection.get(i));
        }
        return builder.toString();
    }

    public T read() {
        return collection.get(position++);
    }

    public int remain() {
        return size() - position;
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
}
