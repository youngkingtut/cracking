package com.tristan.cracking.datastructures;

import java.util.EmptyStackException;

public class LinkedList<T> {
    private LinkedListNode<T> first;
    private LinkedListNode<T> last;

    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    public LinkedList(LinkedList<T> other) {
        this.first = null;
        this.last = null;

        LinkedListNode<T> runner = other.first;
        while(runner != null) {
            this.addLast(runner.getVal());
            runner = runner.getNext();
        }
    }

    public LinkedListNode<T> getFirstNode() {
        return first;
    }

    public LinkedListNode<T> getLastNode() {
        return last;
    }

    public void addFirst(T v) {
        if(first == null) {
            addLast(v);
        } else if(first == last) {
            first = new LinkedListNode<>(v, first, null);
            last = first.getNext();
        } else {
            first = new LinkedListNode<>(v, first, null);
        }
    }

    public void addLast(T v) {
        if(first == null) {
            first = new LinkedListNode<>(v, null, null);
            last = first;
        } else if(first == last) {
            first.setNext(new LinkedListNode<>(v, null, first));
            last = first.getNext();
        } else {
            last.setNext(new LinkedListNode<>(v, null, last));
            last = last.getNext();
        }
    }

    public T removeFirst() {
        if(first == null) {
            throw new EmptyStackException();
        }
        T v = first.getVal();

        if(first == last) {
            first = null;
            last = null;
        } else {
            first = first.getNext();
        }

        return v;
    }

    public T removeLast() {
        if(last == null) {
            throw new EmptyStackException();
        }
        T v = last.getVal();

        if(first == last){
            first = null;
            last = null;
        } else {
            last = last.getPrev();
        }
        return v;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int getSize(){
        if(isEmpty()){
            return 0;
        } else {
            LinkedListNode temp = first;
            int count = 0;
            while(temp != null) {
                count++;
                temp = temp.getNext();
            }
            return count;
        }
    }
}


