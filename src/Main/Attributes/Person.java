package Main.Attributes;

import lombok.Getter;

import java.util.ArrayList;

public class Person extends Human {

    @Getter
    private static ArrayList<Behavior> behaviors = new ArrayList<>();

    public Person() {
        super();
    }

    public Person(String name, Gender gender, int age) {
        super(name, gender, age);
    }


    public static void setBehaviors(ArrayList<Behavior> behaviors) {
        Person.behaviors = behaviors;
    }


    public void happened(Behavior b) {
        if (b == null) {
            throw new IllegalArgumentException("Behavior can't be null");
        }
        Person.behaviors.add(b);
    }

}