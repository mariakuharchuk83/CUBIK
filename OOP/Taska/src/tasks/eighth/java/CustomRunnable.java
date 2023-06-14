package tasks.eighth.java;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomRunnable implements Runnable{
    private static final Logger log = Logger.getLogger(CustomRunnable.class.getName());

    private final CustomLock lockCustom;

    public CustomRunnable(CustomLock lockCustom) {
        this.lockCustom=lockCustom;
    }

    public void run(){
        log.info(Thread.currentThread().getName() +" is waiting to acquire CustomReentrantLock");
        lockCustom.lock();
        log.info(Thread.currentThread().getName() +" has acquired CustomReentrantLock.");
        try {
            Thread.sleep(3000);
            log.info(Thread.currentThread().getName() +" is sleeping.");
        } catch (InterruptedException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
        log.info(Thread.currentThread().getName() +" has released CustomReentrantLock.");
        lockCustom.unlock();
    }
}
