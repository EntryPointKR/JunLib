package com.github.entrypointkr.junlib.util;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by JunHyeong on 2018-10-14
 */
public abstract class AbstractArrayReader<T> implements ArrayReader<T> {
    protected final T[] array;
    protected int position = 0;

    public AbstractArrayReader(T[] array) {
        this.array = array;
    }

    @Override
    public int remain() {
        return array.length - position;
    }

    @Override
    public boolean hasRemain() {
        return remain() > 0;
    }

    @Override
    public void reset() {
        position = 0;
    }

    @Override
    public T read() {
        return array[position++];
    }

    @Override
    public T peek() {
        return array[position];
    }

    @Override
    public T get(int index) {
        return getExplicit(index + position);
    }

    @Override
    public T getExplicit(int index) {
        return array[index];
    }

    @Override
    public Optional<T> getOptional(int index) {
        return index >= 0 && remain() > index
                ? Optional.of(get(index))
                : Optional.empty();
    }

    @Override
    public T[] getInternalArray() {
        return array;
    }

    @Override
    public String toString() {
        return "AbstractArrayReader{" +
                "array=" + Arrays.toString(array) +
                ", position=" + position +
                '}';
    }
}
