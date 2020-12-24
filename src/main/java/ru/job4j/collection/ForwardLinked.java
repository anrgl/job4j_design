package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        Node<T> first = head;
        if (first == null) {
            throw new NoSuchElementException();
        }
        head = first.next;
        return first.value;
    }

    public T deleteLast() {
        T value;
        if (head.next == null) {
            value = head.value;
            head = null;
            return value;
        }
        Node<T> prev = head;
        Node<T> curr = prev.next;
        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        value = curr.value;
        prev.next = null;
        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}