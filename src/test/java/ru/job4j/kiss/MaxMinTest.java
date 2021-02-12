package ru.job4j.kiss;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MaxMinTest {
    @Test
    public void when3GreeterThan2And1() {
        MaxMin maxMin = new MaxMin();
        int max = maxMin.max(List.of(1, 2, 3), Integer::compareTo);
        assertThat(max, is(3));
    }

    @Test
    public void when1LessThan2And3() {
        MaxMin maxMin = new MaxMin();
        int min = maxMin.min(List.of(1, 2, 3), Integer::compareTo);
        assertThat(min, is(1));
    }
}