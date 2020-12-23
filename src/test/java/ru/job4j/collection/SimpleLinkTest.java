package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkTest {
    @Test
    public void whenAddElement() {
        SimpleLink<String> link = new SimpleLink<>();
        link.add("first");
        link.add("second");
        assertThat(link.size(), is(2));
    }

    @Test
    public void whenGetElement() {
        SimpleLink<String> link = new SimpleLink<>();
        link.add("first");
        link.add("second");
        assertThat(link.get(0), is("first"));
        assertThat(link.get(1), is("second"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetElementThenException() {
        SimpleLink<String> link = new SimpleLink<>();
        link.get(0);
    }

    @Test
    public void whenIterator() {
        SimpleLink<String> link = new SimpleLink<>();
        link.add("first");
        link.add("second");
        Iterator<String> it = link.iterator();
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorThenNoSuchElementException() {
        SimpleLink<String> link = new SimpleLink<>();
        Iterator<String> it = link.iterator();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorThenConcurrentModificationException() {
        SimpleLink<String> link = new SimpleLink<>();
        Iterator<String> it = link.iterator();
        link.add("first");
        it.next();
    }
}