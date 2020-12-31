package ru.job4j.collection.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("Ivan", 2,
                new GregorianCalendar(1988, Calendar.JANUARY, 1));
        User user2 = new User("Ivan", 2,
                new GregorianCalendar(1988, Calendar.JANUARY, 1));
        Map<User, Object> userObjectMap = new HashMap<>();
        userObjectMap.put(user1, new Object());
        userObjectMap.put(user2, new Object());
        System.out.println(userObjectMap);
    }
}
