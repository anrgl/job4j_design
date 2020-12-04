package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTableFile {
    public static void main(String[] args) {
        String line;
        try (FileOutputStream out = new FileOutputStream("table.txt")) {
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    line = i + " x " + j + " = " + (i * j) + "\n";
                    out.write(line.getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
