package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private final SimpleArray<T> array;
    private int point = 0;

    public SimpleArrayIterator(SimpleArray<T> array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return point < array.length();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array.get(point++);
    }
}
