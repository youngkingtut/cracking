package com.tristan.cracking.datastructures;

public class LinkedListNode<T> {
    private T val;
    private LinkedListNode<T> next;
    private LinkedListNode<T> prev;

    LinkedListNode(T v, LinkedListNode<T> next, LinkedListNode<T> previous) {
        this.val = v;
        this.next = next;
        this.prev = previous;
    }

    public T getVal() {
        return val;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public LinkedListNode<T> getPrev() {
        return prev;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    public void setPrev(LinkedListNode<T> prev) {
        this.prev = prev;
    }
}
