package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleLink<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> first;
    private Node<T> last;

    public void add(T item) {
        Node<T> node;
        if (size == 0) {
            node = new Node<>(null, item, null);
            first = node;
        } else {
            node = new Node<>(last, item, null);
            last.setNext(node);
        }
        last = node;
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> node = first;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                break;
            }
            node = node.getNext();
        }
        return node.getItem();
    }

    public int size() {
        return size;
    }

    public int getModCount() {
        return modCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleLinkIterator<>(this, modCount);
    }

    private static class Node<T> {
        private final T item;
        private Node<T> next;
        private Node<T> prev;

        Node(Node<T> prev, T item, Node<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public T getItem() {
            return item;
        }

        public Node<T> getNext() {
            return next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
