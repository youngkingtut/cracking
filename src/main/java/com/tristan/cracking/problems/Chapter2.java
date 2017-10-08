package com.tristan.cracking.problems;

import com.tristan.cracking.datastructures.LinkedList;
import com.tristan.cracking.datastructures.LinkedListNode;

import java.util.HashSet;
import java.util.NoSuchElementException;

class Chapter2 {
    static void removeDups(LinkedList<Integer> l){
        if(l.isEmpty()) {
            throw new NoSuchElementException();
        }

        LinkedListNode<Integer> node = l.getFirstNode();
        HashSet<Integer> listSet = new HashSet<>();

        while(node != null) {
            if(!listSet.contains(node.getVal())) {
                listSet.add(node.getVal());
            } else {
                LinkedListNode<Integer> temp = node.getPrev();
                if(node.getNext() != null) {
                    node.getNext().setPrev(temp);
                    temp.setNext(node.getNext());
                } else {
                    temp.setNext(null);
                }
            }
            node = node.getNext();
        }
    }

    static private class KthElement<T> {
        int index = 0;
    }

    static private LinkedListNode<Integer> removeKthToLast(LinkedListNode<Integer> node, int k, KthElement idx) {
        if(node == null) {
            return null;
        }
        LinkedListNode<Integer> next = removeKthToLast(node.getNext(), k, idx);
        if(idx.index == k){
            idx.index++;
            return node;
        } else {
            idx.index++;
            return next;
        }
    }

    // assuming single linked link
    static Integer removeKthToLast(LinkedList<Integer> list, int k) {
        LinkedListNode<Integer> node = removeKthToLast(list.getFirstNode(), k, new KthElement());
        if(node == null)
            throw new NoSuchElementException();
        return node.getVal();
    }


}
