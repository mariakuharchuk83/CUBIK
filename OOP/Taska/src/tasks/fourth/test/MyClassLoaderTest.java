package tasks.fourth.test;

import org.junit.Assert;
import org.junit.Test;
import tasks.fourth.java.MyClassLoader;

import static org.junit.Assert.*;

public class MyClassLoaderTest {
    @Test
    public void checkLoadingClass(){
        MyClassLoader myClassLoader = new MyClassLoader();
        Class foundClass = myClassLoader.findClass("tasks.fourth.java.classes.Human");
        Assert.assertEquals(foundClass.getName(), "tasks.fourth.java.classes.Human");
    }

    @Test
    public void checkSelfClass(){
        MyClassLoader myClassLoader = new MyClassLoader();
        Class foundClass = myClassLoader.findClass("tasks.fourth.java.MyClassLoader");
        Assert.assertEquals(foundClass.getName(), "tasks.fourth.java.MyClassLoader");
    }


}