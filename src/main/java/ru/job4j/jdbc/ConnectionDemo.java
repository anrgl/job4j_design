package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Config config = new Config("./data/app.properties");
        config.load();
        String url = config.value("jdbc.connection.url");
        String login = config.value("jdbc.connection.login");
        String password = config.value("jdbc.connection.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)){
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
