package ru.job4j.collection;

public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<>();
    private int size = 0;

    public T pop() {
        size--;
        return linked.deleteLast();
    }

    public void push(T value) {
        linked.add(value);
        size++;
    }

    public int getSize() {
        return size;
    }
}
