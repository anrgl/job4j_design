package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkIterator<T> implements Iterator<T> {
    private final SimpleLink<T> link;
    private int point = 0;
    private final int modCount;

    public SimpleLinkIterator(SimpleLink<T> link, int modCount) {
        this.link = link;
        this.modCount = modCount;
    }

    @Override
    public boolean hasNext() {
        return point < link.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (modCount != link.getModCount()) {
            throw new ConcurrentModificationException();
        }
        return link.get(point++);
    }
}
