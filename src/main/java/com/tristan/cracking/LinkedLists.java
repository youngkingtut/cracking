package com.tristan.cracking;


class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> next;

    Node(T val){
        value = val;
        next = null;
    }

    Node(T val, Node<T> n) {
        value = val;
        next = n;
    }

    T getValue() {
        return value;
    }

    void setValue(T v) {
        value = v;
    }

    Node<T> getNext() {
        return next;
    }

    void setNext(Node<T> n) {
        next = n;
    }
}

class SingleLinkedList<T extends Comparable<T>> {
    private Node<T> head = null;
    private int size = 0;

    SingleLinkedList() {
        head = null;
        size = 0;
    }

    SingleLinkedList(T h) {
        head = new Node<T>(h);
        size++;
    }

    Node<T> getHead(){
        return head;
    }

    void add(T val) {
        if(head == null) {
            head = new Node<T>(val);
        } else {
            Node<T> iter = head;
            Node<T> prev = null;
            while(iter != null) {
                prev = iter;
                iter = iter.getNext();
            }
            prev.setNext(new Node<T>(val));
        }
        size++;
    }

    void add(Node<T> node) {
        if(head == null) {
            head = node;
        } else {
            Node<T> iter = head;
            Node<T> prev = null;
            while(iter != null) {
                prev = iter;
                iter = iter.getNext();
            }
            prev.setNext(node);
        }
        size++;
    }

    void deleteDups(){
        if(head != null) {
            Node<T> current = head;
            Node<T> next;
            Node<T> prev;

            while(current != null) {
                prev = current;
                next = current.getNext();
                while(next != null) {
                    if(current.getValue() == next.getValue()) {
                        size--;
                        next = next.getNext();
                        prev.setNext(next);
                    } else {
                        prev = next;
                        next = next.getNext();
                    }
                }
                current = current.getNext();
            }
        }
    }

    void deleteKthToLast(int k) {
        if(size - k == 1) {
            head = head.getNext();
        } else if (k < size && k >= 0) {
            int count = size - 1;
            Node<T> prev = head;
            Node<T> next = head.getNext();

            while(next != null) {
                count--;
                if(count == k) {
                    prev.setNext(next.getNext());
                    size--;
                    break;
                }
                prev = next;
                next = next.getNext();
            }
        }
    }

    void deleteMiddleElement() {
        if(size > 2) {
            int middle = (size / 2) - (size % 2 == 0? 1: 0);
            Node<T> prev = head;
            Node<T> next = head.getNext();
            int count = 1;
            while(next != null) {
                if(count == middle) {
                    prev.setNext(next.getNext());
                    size--;
                    break;
                }
                count++;
                prev = next;
                next = next.getNext();
            }
        }
    }

    void deleteMiddleWithTwoRunners() {
        if(size > 2) {
            Node<T> prevTurtle = head;
            Node<T> turtle = head.getNext();
            Node<T> rabbit = head.getNext();
            boolean isEven = size % 2 == 0;

            while(turtle != null) {
                for(int i = 0; i < 2; i++){
                    rabbit = rabbit.getNext();
                    if(rabbit == null){
                        if(isEven){

                        }
                        prevTurtle.setNext(turtle.getNext());
                        size--;
                        return;
                    }
                }
                prevTurtle = turtle;
                turtle = turtle.getNext();
            }

        }
    }

    void partition(T partitionValue) {
        if(head != null) {
            Node<T> temp;
            Node<T> walker = head;
            Node<T> lessHead = null;
            Node<T> greatHead = null;
            Node<T> lessWalk = null;
            Node<T> greatWalk = null;

            while(walker != null) {
                temp = walker.getNext();
                if(partitionValue.compareTo(walker.getValue()) > 0) {
                    if(lessHead == null) {
                        lessHead = walker;
                        lessWalk = lessHead;
                    } else {
                        lessWalk.setNext(walker);
                        lessWalk = walker;
                    }
                } else {
                    if(greatHead == null) {
                        greatHead = walker;
                        greatWalk = greatHead;
                    } else {
                        greatWalk.setNext(walker);
                        greatWalk = walker;
                    }
                }
                walker = temp;
            }

            if(lessHead == null){
                head = greatHead;
                greatWalk.setNext(null);
            } else {
                head = lessHead;
                lessWalk.setNext(greatHead);
                if(greatWalk != null) {
                    greatWalk.setNext(null);
                }
            }
        }
    }

    boolean isPalindrome(){
        boolean isPalindrome = true;

        if(head != null){
            Node<T> first = head;
            Node<T> last;

            for(int i = 0; i < size / 2; i++){
                last = first;

                for(int j = i + 1; j < size - i; j++) {
                    last = last.getNext();
                }
                if(first.getValue() != last.getValue()){
                    isPalindrome = false;
                    break;
                }
                first = first.getNext();
            }
        }

        return isPalindrome;
    }

    boolean intersecting(SingleLinkedList<T> other){
        if(other.getHead() != null && this.head != null) {
            Node<T> first = head;

            while(first != null) {
                Node<T> second = other.getHead();

                while(second != null) {
                    if(first == second){
                        return true;
                    }
                    second = second.getNext();
                }

                first = first.getNext();
            }
        }

        return false;
    }

    Node<T> isLoop() {
        Node<T> tail = head;
        Node<T> hare = head;

        while(hare != null && hare.getNext() != null) {
            hare = hare.getNext();

            if(hare == tail) {
                tail = head;
                hare = hare.getNext();
                while(hare != tail) {
                    hare = hare.getNext();
                    tail = tail.getNext();
                }
                return hare;
            }
            hare = hare.getNext();
            tail = tail.getNext();
        }

        return null;
    }

//    @Override
//    public String toString(){
//        if(head == null) {
//            return "empty";
//        } else {
//            StringBuilder string = new StringBuilder();
//            Node<T> iter = head;
//            while(iter != null) {
//                string.append(iter.getValue());
//                string.append("/");
//                iter = iter.getNext();
//            }
//            return string.toString();
//        }
//    }
}


public class LinkedLists {
    public static void main(String args[]) {
        SingleLinkedList<Integer> x = new SingleLinkedList<Integer>(1);
        x.add(2);
        x.add(3);
        x.add(4);
        x.add(5);
        x.add(x.getHead().getNext().getNext().getNext().getNext());
        SingleLinkedList<Integer> y = new SingleLinkedList<Integer>(1);
        y.add(1);
        y.add(3);

//        x.add(y.getHead().getNext());

//        System.out.println(x.toString());
//        System.out.println(y.toString());
        System.out.println(x.isLoop().getValue());

    }

    static SingleLinkedList<Integer> sumLists(SingleLinkedList<Integer> a, SingleLinkedList<Integer> b) {
        int multFactor = 1;

        Node<Integer> aRunner = a.getHead();
        Node<Integer> bRunner = b.getHead();

        Integer total = 0;

        while(!(aRunner == null && bRunner == null)) {
            if(aRunner != null) {
                total += multFactor * aRunner.getValue();
                aRunner = aRunner.getNext();
            }
            if(bRunner != null) {
                total += multFactor * bRunner.getValue();
                bRunner = bRunner.getNext();
            }
            multFactor *= 10;
        }

        multFactor = 1;
        SingleLinkedList<Integer> sum = new SingleLinkedList<Integer>();

        for(int i = 0; i < total.toString().length(); i++, multFactor *= 10) {
            sum.add(((total % (multFactor * 10)) - (total % multFactor)) / multFactor);
        }

        return sum;
    }

}
