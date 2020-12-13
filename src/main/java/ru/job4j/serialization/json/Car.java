package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
    private final boolean isSportCar;
    private final int maxSpeed;
    private final Person owner;
    private final String[] checkPoints;

    public Car(boolean isSportCar, int maxSpeed, Person owner, String... checkPoints) {
        this.isSportCar = isSportCar;
        this.maxSpeed = maxSpeed;
        this.owner = owner;
        this.checkPoints = checkPoints;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isSportCar=" + isSportCar
                + ", maxSpeed=" + maxSpeed
                + ", owner=" + owner
                + ", checkPoints=" + Arrays.toString(checkPoints)
                + '}';
    }

    public static void main(String[] args) {
        final Car car = new Car(
                false,
                180,
                new Person(
                        true,
                        35,
                        new Contact("8-800-2000-600"),
                        "Worker", "Married"),
                "Moscow", "Saint Petersburg"
                );
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String sportCar =
                "{"
                    + "\"isSportCar\":true,"
                    + "\"maxSpeed\":320,"
                    + "\"owner\":"
                            + "{"
                                + "\"sex\":true,"
                                + "\"age\":42,"
                                + "\"contact\":"
                                    + "{"
                                        + "\"phone\":\"8-800-2000-600\""
                                    + "},"
                                + "\"statuses\":"
                                    + "[\"Dev\",\"Free\"]"
                            + "},"
                        + "\"checkPoints\":"
                            + "[\"Moscow\",\"Saint Petersburg\"]"
                + "}";
        System.out.println(gson.fromJson(sportCar, Car.class));
    }
}
