package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDemo {
    private static final Properties properties = new Properties();

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        Class.forName("org.postgresql.Driver");
        ClassLoader loader = ConnectionDemo.class.getClassLoader();
        try (InputStream is = loader.getResourceAsStream("app.properties")) {
            properties.load(is);
        }
        String url = properties.getProperty("jdbc.connection.url");
        String login = properties.getProperty("jdbc.connection.login");
        String password = properties.getProperty("jdbc.connection.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
