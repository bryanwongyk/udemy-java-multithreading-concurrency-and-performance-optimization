package com.bryanwongyk.section6;

public class MinMaxMetrics {

    // Add all necessary member variables
    private volatile long minSample;
    private volatile long maxSample;

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        // Add code here
        minSample = Long.MAX_VALUE;
        maxSample = Long.MIN_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public void addSample(long newSample) {
        // Add code here
        /*
        We need synchronized here because:
        Multiple threads could be writing to the min and max values concurrently.
        For example, what if two threads update the minSample but they both see the old min sample.
        Original min sample = Long.MAX_VALUE;
        Thread 1: 5
        Thread 2: 10
        We know the min sample should be 5, but what if 10 overwrote it?
         */
        synchronized (this) {
            minSample = Math.min(minSample, newSample);
            maxSample = Math.max(maxSample, newSample);
        }
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        // Add code here
        return this.minSample;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        // Add code here
        return this.maxSample;
    }
}
