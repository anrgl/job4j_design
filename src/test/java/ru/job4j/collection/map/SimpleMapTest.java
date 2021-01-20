package ru.job4j.collection.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    @Test
    public void whenInsertElement() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.insert("firstKey", "firstValue");
        assertThat(map.getSize(), is(1));
    }

    @Test
    public void whenInsertMoreElements() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        for (int i = 0; i < 25; i++) {
            map.insert(i, i * i);
        }
        assertThat(map.getSize(), is(25));
    }

    @Test
    public void whenGetElement() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.insert("foo", "bar");
        assertThat(map.get("foo"), is("bar"));
    }

    @Test
    public void whenGetNull() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.insert("foo", "bar");
        assertNull(map.get("None"));
    }

    @Test
    public void whenRemoveElement() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.insert("first", "Hello");
        map.insert("second", "world");
        assertTrue(map.delete("first"));
        assertTrue(map.delete("second"));
        assertFalse(map.delete("third"));
        assertThat(map.getSize(), is(0));
    }

    @Test
    public void whenItWithSingleElement() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.insert("foo", "first");
        Iterator<String> it = map.iterator();
        assertThat(it.next(), is("first"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenItWithoutElements() {
        SimpleMap<String, String> map = new SimpleMap<>();
        Iterator<String> it = map.iterator();
        it.next();
    }
}