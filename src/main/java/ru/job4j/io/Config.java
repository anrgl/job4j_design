package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String[] pairs;
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            for (String line : in.lines().collect(Collectors.toList())) {
                if (!line.startsWith("#") && !line.isEmpty()) {
                    pairs = line.split("=");
                    if (pairs.length != 2) {
                        throw new IllegalArgumentException();
                    }
                    values.put(pairs[0], pairs[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("./data/app.properties");
        config.load();
        String value = config.value("hibernate.connection.password");
        System.out.println(value);
        System.out.println(config);
    }
}
