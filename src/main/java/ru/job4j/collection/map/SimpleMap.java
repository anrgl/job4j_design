package ru.job4j.collection.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Iterable<V> {
    private Object[] table = new Object[16];
    private int size = 0;

    private int getIndex(K key) {
        int length = table.length;
        int hashCode = Objects.hash(key);
        return (length - 1) & (hashCode ^ (hashCode >> length));
    }

    public boolean insert(K key, V value) {
        int s = table.length;
        if ((double) size / s >= 0.75) {
            table = Arrays.copyOf(table, s + 16);
        }
        int index = getIndex(key);
        if (table[index] != null) {
            return false;
        }
        table[index] = value;
        size++;
        return true;
    }

    public V get(K key) {
        int index = getIndex(key);
        return (V) table[index];
    }

    public boolean delete(K key) {
        int index = getIndex(key);
        if (table[index] == null) {
            return false;
        }
        table[index] = null;
        size--;
        return true;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[point] == null) {
                    point++;
                }
                return (V) table[point++];
            }
        };
    }
}
