package com.bryanwongyk.section9;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class LockFreeStack<T> {
    private AtomicReference<StackNode<T>> head = new AtomicReference<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public void push(T value) {
        StackNode<T> newHeadNode = new StackNode<>(value);
        while (true) {
            StackNode<T> currentHeadNode = head.get();
            newHeadNode.next = currentHeadNode;
            // atomically check and set head. If it is equal to currentHeadNode then update it to newHeadNode.
            if (head.compareAndSet(currentHeadNode, newHeadNode)) {
                break;
            }
        }
        counter.incrementAndGet();
    }

    public T pop() {
        StackNode<T> currentHeadNode = head.get();
        StackNode<T> newHeadNode;

        while (currentHeadNode != null) {
            newHeadNode = currentHeadNode.next;
            if (head.compareAndSet(currentHeadNode, newHeadNode)) {
                break;
            } else {
                // the head was changed by another thread as it is not what we expect
                // let's stop for a nanosecond and try again
                LockSupport.parkNanos(1);
                currentHeadNode = head.get();
            }
        }
        return currentHeadNode != null ? currentHeadNode.value : null;
    }

    public int getCounter() {
        return counter.get();
    }

    private static class StackNode<T> {
        public T value;
        public StackNode<T> next;

        public StackNode(T value) {
            this.value = value;
            this.next = next;
        }
    }
}
