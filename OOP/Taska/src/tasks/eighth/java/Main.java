package tasks.eighth.java;

public class Main {
    public static void main(String[] args) {
        CustomLock lockCustom=new CustomReentrantLock();
        CustomRunnable customRunnable=new CustomRunnable(lockCustom);
        new Thread(customRunnable,"Thread-1").start();
        new Thread(customRunnable,"Thread-2").start();
    }
}
