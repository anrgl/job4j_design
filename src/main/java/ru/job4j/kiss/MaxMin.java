package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> values, Comparator<T> comparator) {
        return min(values, comparator.reversed());
    }

    public <T> T min(List<T> values, Comparator<T> comparator) {
        T result = values.get(0);
        for (var value : values) {
            if (comparator.compare(result, value) > 0) {
                result = value;
            }
        }
        return result;
    }
}
