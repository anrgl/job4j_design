package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {
    @Test
    public void whenAddUniqueItems() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        assertThat(set.getSize(), is(2));
    }

    @Test
    public void whenAddEqualItems() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);
        assertThat(set.getSize(), is(1));
    }

    @Test
    public void whenIterateSet() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateThenException() {
        SimpleSet<Integer> set = new SimpleSet<>();
        Iterator<Integer> it = set.iterator();
        it.next();
    }
}