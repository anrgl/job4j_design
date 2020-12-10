package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> messages;
    private List<String> answers;
    private boolean isStopped = false;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        this.messages = new ArrayList<>();
    }

    private void initAnswers(String path) {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            answers = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveMessages(List<String> messages, String path) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true)))) {
            messages.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        initAnswers(botAnswers);
        while (!input.equals(OUT)) {
            input = scanner.nextLine();
            messages.add(input);
            if (input.equals(STOP)) {
                isStopped = true;
            } else if (input.equals(CONTINUE)) {
                isStopped = false;
            }
            if (!isStopped) {
                String answer = answers.get(new Random().nextInt(answers.size()));
                messages.add(answer);
            }
        }
        saveMessages(messages, path);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chat.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
