package com.bryanwongyk.section8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleCountDownLatchExplicitCondition {
    private int count;
    private Lock lock;
    private Condition condition;

    public SimpleCountDownLatchExplicitCondition(int count) {
        this.count = count;
        this.lock = new ReentrantLock();
        this.condition = this.lock.newCondition();
        if (count < 0) {
            throw new IllegalArgumentException("count cannot be negative");
        }
    }

    /**
     * Causes the current thread to wait until the latch has counted down to zero.
     * If the current count is already zero then this method returns immediately.
     */
    public void await() throws InterruptedException {
        /**
         * Fill in your code
         */
        lock.lock();
         try {
             while (count > 0) {
                 condition.await();
             }
         } finally {
             lock.unlock();
         }
    }

    /**
     *  Decrements the count of the latch, releasing all waiting threads when the count reaches zero.
     *  If the current count already equals zero then nothing happens.
     */
    public void countDown() {
        /**
         * Fill in your code
         */
        lock.lock();
        try {
            if (count > 0) {
                count--;
            }
            if (count <= 0) {
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns the current count.
     */
    public int getCount() {
        /**
         * Fill in your code
         */
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
