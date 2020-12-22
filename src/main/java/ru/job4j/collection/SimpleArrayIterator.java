package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private final SimpleArray<T> array;
    private int point = 0;
    private final int modCount;

    public SimpleArrayIterator(SimpleArray<T> array, int modCount) {
        this.array = array;
        this.modCount = modCount;
    }

    @Override
    public boolean hasNext() {
        return point < array.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (modCount != array.getModCount()) {
            throw new ConcurrentModificationException();
        }
        return array.get(point++);
    }
}
