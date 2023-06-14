package tasks.eighth.java;

public interface CustomLock {
    void lock();
    void unlock();
    boolean tryLock();
}
