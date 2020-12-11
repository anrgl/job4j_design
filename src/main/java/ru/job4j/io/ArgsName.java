package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Pattern pattern = Pattern.compile("^-[\\w]+=[\\w\\-.]+$");
        for (String arg : args) {
            if (!pattern.matcher(arg).find()) {
                throw new IllegalArgumentException("Wrong argument: " + arg);
            }
            String[] pair = arg.substring(1).split("=");
            values.put(pair[0], pair[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));

        ArgsName exp = ArgsName.of(new String[]{"key=42"});
        System.out.println(exp.get("key"));
    }
}
