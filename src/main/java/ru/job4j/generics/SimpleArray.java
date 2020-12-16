package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final T[] data;
    private final int size;
    private int point = 0;

    public SimpleArray(int size) {
        this.size = size;
        data = (T[]) new Object[size];
    }

    public void add(T elem) {
        data[point++] = elem;
    }

    public void set(int index, T elem) {
        Objects.checkIndex(index, size);
        data[index] = elem;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[data.length - 1] = null;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return data[index];
    }

    public int length() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(this.data).iterator();
    }

    public static void main(String[] args) {
        SimpleArray<Integer> numbers = new SimpleArray<>(5);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        for (int number : numbers) {
            System.out.println(number);
        }
    }
}
