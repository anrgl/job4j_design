package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isSportCar() {
        return isSportCar;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public Person getOwner() {
        return owner;
    }

    public String[] getCheckPoints() {
        return checkPoints;
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
        JSONObject jsonOwner = new JSONObject("{\"sex\":true,"
                + "\"age\":35,"
                + "\"contact\":{\"phone\":\"8-800-2000-600\"},"
                + "\"statuses\":[\"Worker\", \"Married\"]}");

        List<String> checkPoints = new ArrayList<>();
        checkPoints.add("Moscow");
        checkPoints.add("Saint Petersburg");
        JSONArray jsonCheckPoints = new JSONArray(checkPoints);

        System.out.println(jsonOwner.toString());
        System.out.println(jsonCheckPoints);

        final Car car = new Car(
                false,
                150,
                new Person(false, 42, new Contact("11-123"), "Free"),
                "Moscow");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSportCar", car.isSportCar());
        jsonObject.put("maxSpeed", car.getMaxSpeed());
        jsonObject.put("owner", jsonOwner);
        jsonObject.put("checkPoints", jsonCheckPoints);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(car).toString());
    }
}
