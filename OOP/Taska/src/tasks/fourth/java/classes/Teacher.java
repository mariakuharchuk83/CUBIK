package tasks.fourth.java.classes;

import java.time.Year;

public class Teacher implements Human{
    private int age;
    private String name;

    public void say() {
        System.out.println("You should learn more!");
    }

    public void eat() {
        System.out.println("I eat wow");
    }

    public int countAge(){
        return Year.now().getValue() - age;
    }
}
