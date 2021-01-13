package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private final Properties cfg;
    private final String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines()
                    .map(line -> line.split(";"))
                    .filter(l -> l.length == 2)
                    .forEach(l -> users.add(new User(l[0], l[1])));
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("spammer.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("spammer.url"),
                cfg.getProperty("spammer.login"),
                cfg.getProperty("spammer.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps =
                             cnt.prepareStatement("insert into users(name, email) values (?, ?)")) {
                    ps.setString(1, user.getName());
                    ps.setString(2, user.getEmail());
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        private final String name;
        private final String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        ClassLoader loader = ImportDB.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./data/dump.txt");
        db.save(db.load());
    }
}
