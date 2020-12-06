package ru.job4j.io;

import java.io.*;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean flag = false;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
            PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(target)))) {
            for (String line : in.lines().collect(Collectors.toList())) {
                if (!flag && (line.startsWith("400") || line.startsWith("500"))) {
                    flag = true;
                    out.print(line.split(" ")[1]);
                    out.print(";");
                }
                if (flag && (line.startsWith("200") || line.startsWith("300"))) {
                    flag = false;
                    out.println(line.split(" ")[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
