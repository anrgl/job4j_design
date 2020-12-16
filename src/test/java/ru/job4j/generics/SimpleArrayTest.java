package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {
    @Test
    public void whenEmptySimpleArrayThenLengthZero() {
        SimpleArray<Integer> array = new SimpleArray<>(0);
        assertThat(array.length(), is(0));
    }

    @Test
    public void whenSimpleArrayWithOneElementThenLengthOne() {
        SimpleArray<Integer> array = new SimpleArray<>(1);
        array.add(42);
        assertThat(array.length(), is(1));
        assertThat(array.get(0), is(42));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddTwoElementInSimpleArrayWithOneLength() {
        SimpleArray<Integer> array = new SimpleArray<>(1);
        array.add(1);
        array.add(2);
    }

    @Test
    public void whenSetElement() {
        SimpleArray<Integer> array = new SimpleArray<>(1);
        array.set(0, 24);
        assertThat(array.length(), is(1));
        assertThat(array.get(0), is(24));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetElementThenException() {
        SimpleArray<Integer> array = new SimpleArray<>(1);
        array.add(23);
        array.set(1, 44);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfBounds() {
        SimpleArray<Integer> array = new SimpleArray<>(3);
        array.add(1);
        array.add(2);
        array.add(3);
        array.get(3);
    }

    @Test
    public void whenRemoveFirstElement() {
        SimpleArray<Integer> array = new SimpleArray<>(3);
        array.add(1);
        array.add(2);
        array.add(3);
        array.remove(0);
        assertThat(array.get(0), is(2));
        assertThat(array.get(1), is(3));
        assertThat(array.get(2), is(nullValue()));
    }

    @Test
    public void whenRemoveSecondElement() {
        SimpleArray<Integer> array = new SimpleArray<>(3);
        array.add(1);
        array.add(2);
        array.add(3);
        array.remove(1);
        assertThat(array.get(0), is(1));
        assertThat(array.get(1), is(3));
        assertThat(array.get(2), is(nullValue()));
    }

    @Test
    public void whenRemoveLastElement() {
        SimpleArray<Integer> array = new SimpleArray<>(3);
        array.add(1);
        array.add(2);
        array.add(3);
        array.remove(2);
        assertThat(array.get(0), is(1));
        assertThat(array.get(1), is(2));
        assertThat(array.get(2), is(nullValue()));
    }
}