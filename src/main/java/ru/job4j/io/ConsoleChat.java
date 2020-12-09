package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    private List<String> answers;
    private boolean isStopped = false;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            answers = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (!input.equals(OUT)) {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(
                    new FileWriter(path, StandardCharsets.UTF_8, true)))) {
                input = scanner.nextLine();
                out.println(input);
                if (input.equals(STOP)) {
                    isStopped = true;
                } else if (input.equals(CONTINUE)) {
                    isStopped = false;
                }
                if (!isStopped) {
                    String answer = answers.get(new Random().nextInt(answers.size()));
                    System.out.println(answer);
                    out.println(answer);
                }
                out.println("---");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chat.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
