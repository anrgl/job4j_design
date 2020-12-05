package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines()
                    .filter(line -> line.split(" ")[8].equals("404"))
                    .forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            StringBuilder text = new StringBuilder();
            log.forEach(l -> text.append(l).append(System.lineSeparator()));
            out.write(text.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
