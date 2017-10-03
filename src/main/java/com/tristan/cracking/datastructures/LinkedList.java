package com.tristan.cracking.datastructures;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;

public class LinkedList<T> {
    private class Node {
        T val;
        Node next;
        Node prev;
        Node(T v, Node n, Node l) {
            this.val = v;
            this.next = n;
            this.prev = l;
        }
    }

    private Node first;
    private Node last;

    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    public void add(T v) {
        if(first == null) {
            first = new Node(v, null, null);
            last = first;
        } else if(first == last) {
            first.next = new Node(v, null, first);
            last = first.next;
        } else {
            last.next = new Node(v, null, last);
            last = last.next;
        }
    }

    public T removeFirst() {
        if(first == null) {
            throw new EmptyStackException();
        }
        T v = first.val;

        if(first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
        }

        return v;
    }

    public T removeLast() {
        if(last == null) {
            throw new EmptyStackException();
        }
        T v = last.val;

        if(first == last){
            first = null;
            last = null;
        } else {
            last = last.prev;
        }
        return v;
    }
}


