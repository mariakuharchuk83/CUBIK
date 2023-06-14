package tasks.seventh.java;

import java.util.concurrent.atomic.AtomicInteger;

public class MyCyclicBarrier {
    private final int amountOfThreads;
    private final Runnable cyclicBarrierEvent;
    private final AtomicInteger waiting;

    public MyCyclicBarrier(int amountOfThreads, Runnable cyclicBarrierEvent) {
        this.amountOfThreads = amountOfThreads;
        this.cyclicBarrierEvent = cyclicBarrierEvent;
        this.waiting = new AtomicInteger(amountOfThreads);
    }

    public void await() throws InterruptedException {
        if (waiting.decrementAndGet() != 0) {
            synchronized (this) {
                while (waiting.get() != 0) {
                    wait();
                }
            }
        }
        else {
            waiting.set(amountOfThreads);
            synchronized (this) {
                notifyAll();
            }
            cyclicBarrierEvent.run();
        }
    }

    public int getAmountOfThreads() {
        return amountOfThreads;
    }

    public int getWaiting() {
        return waiting.get();
    }
}
