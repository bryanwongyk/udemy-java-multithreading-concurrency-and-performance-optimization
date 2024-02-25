package com.bryanwongyk.section2.threadinheritance;

import java.util.List;
import java.util.ArrayList;

// Exercise 1
public class MultiExecutor {

    // Add any necessary member variables here
    List<Runnable> tasks;

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        // Complete your code here
        this.tasks = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        // complete your code here
        // can also be done with a thread pool
        List<Thread> threads = new ArrayList<>();
        for (Runnable task: tasks) {
            // add each runnable to a separate thread to maximise parallelisation
            Thread thread = new Thread(task);
            threads.add(thread);
        }
        for (Thread thread: threads) {
            thread.start();
        }
    }
}