package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> values, Comparator<T> comparator) {
        T max = values.get(0);
        for (var value : values) {
            if (comparator.compare(max, value) < 0) {
                max = value;
            }
        }
        return max;
    }

    public <T> T min(List<T> values, Comparator<T> comparator) {
        T min = values.get(0);
        for (var value : values) {
            if (comparator.compare(min, value) > 0) {
                min = value;
            }
        }
        return min;
    }
}
