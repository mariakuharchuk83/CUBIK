package tasks.eighth.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.eighth.java.CustomReentrantLock;

import static org.junit.jupiter.api.Assertions.*;

class CustomReentrantLockTest {
    private CustomReentrantLock customReentrantLock;

    @BeforeEach
    public void init(){
        customReentrantLock = new CustomReentrantLock();
    }

    @Test
    public void shouldLockAndRelease() throws InterruptedException {
        Thread thread = new Thread(() -> {
            customReentrantLock.lock();
            customReentrantLock.lock();
            customReentrantLock.unlock();
            customReentrantLock.unlock();
        });
        thread.start();
        thread.join();
        assertTrue(customReentrantLock.tryLock());
        customReentrantLock.unlock();
    }

    @Test
    public void shouldReturnFalseBecauseTwoLocksOneUnlock(){
        customReentrantLock.lock();
        customReentrantLock.lock();
        customReentrantLock.unlock();
        assertFalse(customReentrantLock.tryLock());
    }

}