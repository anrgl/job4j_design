package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrepareStatementDemo {
    private Connection connection;

    public PrepareStatementDemo() throws Exception {
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "postgres";
        connection = DriverManager.getConnection(url, login, password);
    }

    public City insert(City city) throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "insert into cities(name, population) values (?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        }
        return city;
    }

    public boolean update(City city) throws SQLException {
        boolean result = false;
        try (PreparedStatement statement =
                connection.prepareStatement(
                        "update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        }
        return result;
    }

    public boolean delete(int id) throws SQLException {
        boolean result = false;
        try (PreparedStatement statement =
                connection.prepareStatement("delete from cities where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        }
        return result;
    }

    public List<City> findAll() throws SQLException {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement =
                connection.prepareStatement("select * from cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        }
        return cities;
    }

    public static void main(String[] args) {
        List<City> cities = List.of(
                new City(1, "Москва", 12_000_000),
                new City(2, "Санкт-Петербург", 5_398_064),
                new City(3, "Казань", 1_257_391),
                new City(4, "Нефтекамск", 131_138)
        );
        try {
            PrepareStatementDemo psd = new PrepareStatementDemo();
            for (City city : cities) {
                psd.insert(city);
            }

            City moscow = cities.get(0);
            moscow.setPopulation(12_678_079);
            psd.update(moscow);

            psd.delete(4);

            for (City city : psd.findAll()) {
                System.out.println(city.getId() + ". город " + city.getName() + ", население " + city.getPopulation());
            }
        } catch (Exception e) {
            System.out.println("Something wrong");
            e.printStackTrace();
        }
    }
}
