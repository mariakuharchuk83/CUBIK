package tasks.first.main;

import java.io.Serializable;
import java.util.Objects;

public class Entity implements Serializable {
    private String name;
    private int age;
    private String nameOfUni;

    public Entity(String name, int age, String nameOfUni) {
        this.name = name;
        this.age = age;
        this.nameOfUni = nameOfUni;
    }

    public Entity() {}

    @Override
    public String toString() {
        return "Entity " +
                "is " + name +
                ", the age is " + age +
                " and the name of university is " + nameOfUni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return age == entity.age &&
                Objects.equals(name, entity.name) &&
                Objects.equals(nameOfUni, entity.nameOfUni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, nameOfUni);
    }
}
