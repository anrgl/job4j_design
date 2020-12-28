package ru.job4j.it;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {
    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterFirst() {
        List<Integer> input = new ArrayList<>(List.of(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertThat(List.of(1, 2, 3), is(input));
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(List.of(1, 3));
        ListUtils.addAfter(input, 1, 4);
        assertThat(List.of(1, 3, 4), is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(List.of(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3, 4));
        List<Integer> output = ListUtils.removeIf(input, (i) -> i % 2 == 0);
        assertThat(List.of(1, 3), is(output));
    }

    @Test
    public void whenRemoveIfNothing() {
        List<Integer> input = new ArrayList<>(List.of(1, 3, 5, 7));
        List<Integer> output = ListUtils.removeIf(input, (i) -> i % 2 == 0);
        assertThat(List.of(1, 3, 5, 7), is(output));
    }

    @Test
    public void whenRemoveIfEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> output = ListUtils.removeIf(input, (i) -> i % 3 == 0);
        assertThat(List.of(), is(output));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3, 4));
        List<Integer> output = ListUtils.replaceIf(input, (i) -> i % 2 == 0, 42);
        assertThat(List.of(1, 42, 3, 42), is(output));
    }

    @Test
    public void whenReplaceIfNothing() {
        List<Integer> input = new ArrayList<>(List.of(1, 3, 5, 7));
        List<Integer> output = ListUtils.replaceIf(input, (i) -> i % 2 == 0, 42);
        assertThat(List.of(1, 3, 5, 7), is(output));
    }

    @Test
    public void whenReplaceIfEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> output = ListUtils.replaceIf(input, (i) -> i % 3 == 0, 42);
        assertThat(List.of(), is(output));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3, 4));
        List<Integer> elements = List.of(1, 3);
        List<Integer> output = ListUtils.removeAll(input, elements);
        assertThat(List.of(2, 4), is(output));
    }
}