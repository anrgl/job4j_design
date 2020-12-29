package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray() {
        container = new Object[10];
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        modCount++;
        if (size == container.length) {
            container = Arrays.copyOf(container, size * 2);
        }
        container[size++] = model;
    }

    public void add(int index, T model) {
        Objects.checkIndex(index, size);
        modCount++;
        if (size == container.length) {
            container = Arrays.copyOf(container, size * 2);
        }
        System.arraycopy(container, index, container, index + 1, size - index);
        container[index] = model;
        size++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        modCount++;
        container[index] = model;
    }

    public void delete(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        System.arraycopy(container, index + 1, container, index, size - index);
        container[--size] = null;
    }

    public boolean contains(T item) {
        for (T i : this) {
            if (i.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return container.length;
    }

    public int getModCount() {
        return modCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>(this, modCount);
    }

    public static void main(String[] args) {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("one");
        array.add("two");
        array.add("three");
        array.add("four");
        array.add("five");
        array.add("six");
        array.add("seven");
        array.add("eight");
        array.add("nine");
        array.add("ten");
        array.add("11");
        System.out.println(array.size());
        System.out.println(array.get(10));
        System.out.println(array.capacity());
    }
}
