package com.tristan.cracking;

import java.util.ArrayList;
import java.util.EmptyStackException;

class MyStack<T> {
    private static class StackNode<T> {
        private T data;
        private StackNode<T> next;

        public StackNode(T data) {
            this.data = data;
        }
    }

    private StackNode<T> top;

    public T pop(){
        if (top == null) throw new EmptyStackException();
        T item = top.data;

        top = top.next;
        return item;
    }

    public void push(T item) {
        StackNode<T> t = new StackNode<T>(item);
        t.next = top;
        top = t;
    }

    public T peek() {
        if (top == null) throw new EmptyStackException();
        return top.data;
    }

    public boolean usEmpty() {
        return top == null;
    }
}

class MyQueue<T> {
    private static class QueueNode<T> {
        private T data;
        private QueueNode<T> next;

        public QueueNode(T data) {
            this.data = data;
        }
    }

    private QueueNode<T> first;
    private QueueNode<T> last;

    public void add(T item) {
        QueueNode<T> i = new QueueNode<T>(item);

        if(last != null) {
            last.next = i;
        }
        last = i;
        if(first == null){
            first = last;
        }
    }

    public T remove(){
        if(first == null) throw new EmptyStackException();
        T data = first.data;
        first = first.next;
        if(first == null) {
            last = null;
        }
        return data;
    }

    public T peek() {
        if (first == null) throw new EmptyStackException();
        return first.data;
    }

    public boolean isEmpty() {
        return first == null;
    }
}


public class StacksAndQueues {
    public static void main(String[] args) {

    }
}
