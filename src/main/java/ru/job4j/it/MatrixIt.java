package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row + 1 < data.length && data[row].length == 0) {
            row++;
        }
        if (row + 1 < data.length && data[row].length == 0) {
            return true;
        }
        return row < data.length && column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer point = data[row][column++];
        if (column >= data[row].length) {
            row++;
            column = 0;
        }
        return point;
    }
}
