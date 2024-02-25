package com.bryanwongyk.section3;

public class JoinThreadsTimeoutExperiment {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Thread " + Thread.currentThread().getName() + " has completed!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.setDaemon(true); // set as daemon thread so the JVM can terminate even if its still running
        thread.start();
        thread.join(500); // time out to 500 seconds which is less than the Thread sleep, meaning the JVM will terminate
    }
}
