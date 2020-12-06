package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Test
    public void whenServerUnavailableTwoTimes() {
        String serverLog = "./data/server.log";
        String unavailable = "./data/unavailable.cvs";
        Analizy analizy = new Analizy();
        analizy.unavailable(serverLog, unavailable);
        try (BufferedReader in = new BufferedReader(new FileReader(unavailable))) {
            List<String> lines = in.lines().collect(Collectors.toList());
            assertThat(lines.get(0), is("10:57:01;10:59:01"));
            assertThat(lines.get(1), is("11:01:02;11:02:02"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}