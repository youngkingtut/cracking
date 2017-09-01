package com.tristan.cracking;

import java.util.EmptyStackException;
import java.util.ArrayList;

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

    public boolean isEmpty() {
        return top == null;
    }
}

class MinStack {
    private static class StackNode<T> {
        private int data;
        private StackNode<T> next;
        private int min;

        public StackNode(int data, int min) {
            this.data = data;
            this.min = min;
        }
    }

    private StackNode top;

    public int pop(){
        if (top == null) throw new EmptyStackException();
        int item = top.data;

        top = top.next;
        return item;
    }

    public void push(int item) {
        StackNode t = new StackNode(item, top==null? item : (top.min > item ? item : top.min) );
        t.next = top;
        top = t;
    }

    public int peek() {
        if (top == null) throw new EmptyStackException();
        return top.data;
    }

    public int minValue() {
        if (top == null) throw new EmptyStackException();
        return top.min;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

class SetOfStacks<T> {
    private static class StackNode<T> {
        private T data;
        private StackNode<T> next;

        public StackNode(T data) {
            this.data = data;
        }
    }

    private int stackSize;
    private ArrayList<StackNode<T>> tops;
    private ArrayList<Integer> size;

    SetOfStacks(int stackSize) {
        this.stackSize = stackSize;
        tops = new ArrayList<StackNode<T>>();
        size = new ArrayList<Integer>();
    }

    public T pop(){
        return this.popAt(tops.size() - 1);
    }

    public T popAt(int index){
        if(this.isEmpty()) throw new EmptyStackException();
        if(index >= tops.size() || index < 0) throw new IndexOutOfBoundsException();

        StackNode<T> node = tops.get(index);
        T item = node.data;

        node = node.next;

        if(node == null) {
            tops.remove(index);
            size.remove(index);
        } else {
            tops.set(index, node);
            size.set(index, size.get(index) - 1);
        }

        return item;
    }

    public void push(T item) {
        int endIndex = tops.size() - 1;
        if(this.isEmpty() || size.get(endIndex) >= stackSize) {
            tops.add(new StackNode<T>(item));
            size.add(1);
        } else {
            StackNode<T> newTop = new StackNode<T>(item);
            newTop.next = tops.get(endIndex);
            tops.set(endIndex, newTop);
            size.set(endIndex, size.get(endIndex) + 1);
        }
    }

    public T peek() {
        if (this.isEmpty()) throw new EmptyStackException();
        return tops.get(tops.size() - 1).data;
    }

    public boolean isEmpty() {
        return tops.isEmpty();
    }

    @Override
    public String toString() {
        return tops.toString();
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


class ArrayStack {
    int[] basePointer;
    int[] stackPointer;
    int[] stack;
    int stackSize;

    ArrayStack(int size, int stackSize) {
        this.stackSize = stackSize;
        this.stack = new int[size];
        this.stackPointer = new int[stackSize];
        this.basePointer = new int[stackSize];

        for(int i=0; i<stackSize; i++){
            int temp = i * (size / stackSize);
            this.stackPointer[i] = temp;
            this.basePointer[i] = temp;
        }
    }

    public void push(int stack, int value) {
        if (stackPointer[stack] == stackPointer[(stack + 1) % stackSize]) throw new StackOverflowError();
        this.stack[stackPointer[stack]] = value;
        stackPointer[stack]++;
    }

    public int pop(int stack) {
        if(stackPointer[stack] == basePointer[stack]) throw new EmptyStackException();
        stackPointer[stack]--;
        return this.stack[stackPointer[stack]];
    }

    public int peek(int stack) {
        if(stackPointer[stack] == basePointer[stack]) throw new EmptyStackException();
        return this.stack[stackPointer[stack]];
    }

    public boolean isEmpty(int stack) {
        return stackPointer[stack] == basePointer[stack];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int aStack : this.stack) {
            s.append(aStack);
            s.append(", ");
        }

        return s.toString();
    }
}


public class StacksAndQueues {
    public static void main(String[] args) {
    }
}
