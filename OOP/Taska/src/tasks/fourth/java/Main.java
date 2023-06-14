package tasks.fourth.java;

public class Main {
    public static void main(String[] args) {
        ClassInfo classInfo = new ClassInfo("tasks.fourth.java.classes.Human");
        classInfo.showClassInfo();
        classInfo = new ClassInfo("tasks.fourth.java.classes.Lecturer");
        classInfo.showClassInfo();
        classInfo = new ClassInfo("tasks.fourth.java.classes.Teacher");
        classInfo.showClassInfo();
    }
}
