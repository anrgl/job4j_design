package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table if not exists demo_table(%s, %s);",
                        "id serial primary key",
                        "name varchar(255)"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }

    private static Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
        Class.forName("org.postgresql.Driver");
        Properties properties = new Properties();
        ClassLoader loader = ConnectionDemo.class.getClassLoader();
        try (InputStream is = loader.getResourceAsStream("app.properties")) {
            properties.load(is);
        }
        String url = properties.getProperty("jdbc.connection.url");
        String login = properties.getProperty("jdbc.connection.login");
        String password = properties.getProperty("jdbc.connection.password");
        return DriverManager.getConnection(url, login, password);
    }

    public static String getTableScheme(Connection connection, String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }
}
