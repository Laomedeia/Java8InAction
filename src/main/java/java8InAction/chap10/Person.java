package java8InAction.chap10;

import java.util.*;

public class Person {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}
