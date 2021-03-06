package com.tristan.cracking;

import java.lang.reflect.Array;
import java.util.*;


class Tuple<X, Y> {
    private final X x;
    private final Y y;
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X _1() {
        return x;
    }

    public Y _2() {
        return y;
    }
}

class DependencyGraph {
    private ArrayList<GraphNode<Character>> nodes = new ArrayList<GraphNode<Character>>();

    DependencyGraph(List<Character> n, List<Tuple<Character, Character>> links) {
        for(Character c: n) {
            this.nodes.add(new GraphNode<Character>(c));
        }

        for(Tuple<Character, Character> l: links) {
            GraphNode<Character> dependency = getByName(l._1());
            GraphNode<Character> dependent = getByName(l._2());
            if(dependency != null && dependent != null) {
                dependent.updateAdjacent(dependency);
            }
        }
    }

    private GraphNode<Character> getByName(Character c) {
        for(GraphNode<Character> n: nodes) {
            if(n.getName() == c) {
                return n;
            }
        }
        return null;
    }

    public List<Character> buildOrder() {
        ArrayList<GraphNode<Character>> order = new ArrayList<GraphNode<Character>>();

        while(order.size() < nodes.size()) {
            boolean nodeAdded = false;
            for (GraphNode<Character> node : nodes) {
                if(order.contains(node))
                    continue;

                ArrayList<GraphNode<Character>> temp = new ArrayList<GraphNode<Character>>(node.getAdjacent());
                temp.removeAll(order);

                if (temp.isEmpty()) {
                    order.add(node);
                    nodeAdded = true;
                }
            }

            if(!nodeAdded) {
                throw new NoSuchElementException();
            }

        }

        ArrayList<Character> x= new ArrayList<Character>();
        for(GraphNode<Character> n: order) {
            x.add(n.getName());
        }
        return x;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(GraphNode<Character> n: nodes) {
            s.append(n.getName());
            s.append(": ");
            for(GraphNode<Character> z: n.getAdjacent()) {
                s.append(z.getName());
            }
            s.append("\n");
        }
        return s.toString();
    }
}

class GraphNode<T> {
    private T name;
    private ArrayList<GraphNode<T>> adjacent;
    private boolean visited = false;

    GraphNode(T name) {
        this.name = name;
        this.adjacent = new ArrayList<GraphNode<T>>();
    }

    T getName() {
        return this.name;
    }

    void setVisited(boolean visited) {
        this.visited = visited;
    }

    boolean getVisited() {
        return this.visited;
    }

    ArrayList<GraphNode<T>> getAdjacent() {
        return this.adjacent;
    }

    void updateAdjacent(GraphNode<T> node) {
        if(!this.adjacent.contains(node))
            this.adjacent.add(node);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append(name.toString());
        s.append(": ");
        for(GraphNode n: this.adjacent) {
            s.append(n.name.toString());
            s.append(" ");
        }

        return s.toString();
    }
}

class Graph<T> {
    private ArrayList<GraphNode<T>> nodes;

    Graph() {
        nodes = new ArrayList<GraphNode<T>>();
    }

    void addNode(GraphNode<T> node, GraphNode<T>[] adjacentNodes) {
        if(!this.nodes.contains(node)) {
            for (GraphNode<T> n : adjacentNodes) {
                node.updateAdjacent(n);
            }
            this.nodes.add(node);
        }

    }

    boolean routeExists(GraphNode<T> source, GraphNode<T> destination) {
        if(!nodes.contains(source) || !nodes.contains(destination)) {
            return false;
        } else {
            MyQueue<GraphNode<T>> q = new MyQueue<GraphNode<T>>();
            q.add(source);

            for(GraphNode<T> n: nodes) {
                n.setVisited(false);
            }
            source.setVisited(true);

            while(!q.isEmpty()) {
                GraphNode<T> v = q.remove();
                for(GraphNode<T> n: v.getAdjacent()) {
                    if(n == destination) {
                        return true;
                    } else if (!n.getVisited()){
                        q.add(n);
                    }
                }
            }

            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(GraphNode n: this.nodes) {
            s.append(n.toString());
            s.append("\n");
        }
        return s.toString();
    }

}


class BinaryNode<T extends Comparable<T>> {
    private T data;
    private BinaryNode<T> parent;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        if(left != null) {
            left.setParent(this);
        }
        if(right != null) {
            right.setParent(this);
        }
        this.left = left;
        this.right = right;
    }

    T getData() {
        return this.data;
    }

    BinaryNode<T> getParent() {
        return parent;
    }

    BinaryNode<T> getLeft() {
        return left;
    }

    BinaryNode<T> getRight() {
        return right;
    }

    void setParent(BinaryNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(this.data.toString());
        s.append(" ");
        if(this.left != null) s.append(this.left.toString());
        if(this.right != null) s.append(this.right.toString());
        return s.toString();
    }
}


public class TreesAndGraphs {
    private static BinaryNode<Integer> buildBinarySearchTreeFromAscendingUniqueList(List<Integer> list) {
        if(list.isEmpty()) {
            return null;
        } else if (list.size() == 1){
            return new BinaryNode<Integer>(list.get(0), null, null);
        } else {
            int half = list.size() / 2;
            BinaryNode<Integer> left = buildBinarySearchTreeFromAscendingUniqueList(list.subList(0, half));
            BinaryNode<Integer> right = buildBinarySearchTreeFromAscendingUniqueList(list.subList(half + 1, list.size()));
            return new BinaryNode<Integer>(list.get(half), left, right);
        }
    }

    private static HashMap<Integer, LinkedList<BinaryNode<Integer>>> depthListBuilder(HashMap<Integer, LinkedList<BinaryNode<Integer>>> m, BinaryNode<Integer> node, Integer depth) {
        if(node != null) {
            if (m.containsKey(depth)) {
                m.get(depth).add(node);
            } else {
                LinkedList<BinaryNode<Integer>> l = new LinkedList<BinaryNode<Integer>>();
                l.add(node);
                m.put(depth, l);
            }
            m = depthListBuilder(m, node.getLeft(), depth + 1);
            m = depthListBuilder(m, node.getRight(), depth + 1);
        }
        return m;
    }

    private static ArrayList<LinkedList<BinaryNode<Integer>>> depthLists(BinaryNode<Integer> root) {
        HashMap<Integer, LinkedList<BinaryNode<Integer>>> m = new HashMap<Integer, LinkedList<BinaryNode<Integer>>>();

        m = depthListBuilder(m, root, 0);

        ArrayList<LinkedList<BinaryNode<Integer>>> l = new ArrayList<LinkedList<BinaryNode<Integer>>>();
        for(Map.Entry<Integer, LinkedList<BinaryNode<Integer>>> e: m.entrySet()) {
            l.add(e.getValue());
        }
        return l;
    }

    private static <T extends Comparable<T>> int depth(BinaryNode<T> node) {
        if(node == null) {
            return 0;
        } else {
            int depthRight = depth(node.getRight());
            int depthLeft = depth(node.getLeft());
            int childDepth = depthLeft > depthRight? depthLeft: depthRight;
            return ++childDepth;
        }
    }

    private static <T extends Comparable<T>> boolean isBalanced(BinaryNode<T> node) {
        return node == null ||Math.abs(depth(node.getLeft()) - depth(node.getRight())) <= 1 && isBalanced(node.getLeft()) && isBalanced(node.getRight());
    }

    private static <T extends Comparable<T>> boolean isBinarySearchTree(BinaryNode<T> node) {
        if(node == null) {
            return true;
        } else {
            if(node.getLeft() != null && node.getData().compareTo(node.getLeft().getData()) < 0) {
                return false;
            } else if (node.getRight() != null && node.getData().compareTo(node.getRight().getData()) >= 0) {
                return false;
            } else {
                return isBinarySearchTree(node.getLeft()) && isBinarySearchTree(node.getRight());
            }
        }
    }

    private static <T extends Comparable<T>> BinaryNode<T> inOrderSuccesor(BinaryNode<T> node) {
        if(node.getRight() != null) {
            node = node.getRight();
            while(true){
                if(node.getLeft() == null) {
                    return node;
                } else {
                    node = node.getLeft();
                }
            }
        } else {
            if(node.getParent() == null) {
                return null;
            } else if (node.getParent().getLeft() == node) {
                return node.getParent();
            }

            BinaryNode<T> temp = node;
            while(true) {
                if(node.getParent() == null) {
                    if(node.getRight() == temp) {
                        return null;
                    } else {
                        return node;
                    }
                } else {
                    temp = node;
                    node = node.getParent();
                }
            }
        }

    }

    private static <T extends Comparable<T>> BinaryNode<T> firstCommonAncestor(BinaryNode<T> a, BinaryNode<T> b) {
        if(a.getParent() == null || b.getParent() == null) {
            return null;
        } else {
            a = a.getParent();
            b = b.getParent();
            BinaryNode<T> temp = b;

            while(a != null) {
                b = temp;
                while(b != null) {
                    if(a == b) {
                        return a;
                    }
                    b = b.getParent();
                }
                a = a.getParent();
            }
            return null;
        }
    }

    private static <T extends Comparable<T>> void weave(LinkedList<BinaryNode<T>> first, LinkedList<BinaryNode<T>> second,
                                                        ArrayList<LinkedList<BinaryNode<T>>> results, LinkedList<BinaryNode<T>> prefix) {
        if(first.isEmpty() || second.isEmpty()) {
            LinkedList<BinaryNode<T>> result = (LinkedList<BinaryNode<T>>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        BinaryNode<T> headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weave(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        BinaryNode<T> headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weave(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headSecond);
    }

    private static <T extends Comparable<T>> ArrayList<LinkedList<BinaryNode<T>>> allSequences(BinaryNode<T> bst) {
        ArrayList<LinkedList<BinaryNode<T>>> result = new ArrayList<LinkedList<BinaryNode<T>>>();

        if(bst == null){
            result.add(new LinkedList<BinaryNode<T>>());
            return result;
        }

        LinkedList<BinaryNode<T>> prefix = new LinkedList<BinaryNode<T>>();
        prefix.add(bst);

        ArrayList<LinkedList<BinaryNode<T>>> l = allSequences(bst.getLeft());
        ArrayList<LinkedList<BinaryNode<T>>> r = allSequences(bst.getRight());

        for(LinkedList<BinaryNode<T>> n1: l) {
            for(LinkedList<BinaryNode<T>> n2: r) {
                ArrayList<LinkedList<BinaryNode<T>>> weaved = new ArrayList<LinkedList<BinaryNode<T>>>();
                weave(n1, n2, weaved, prefix);
                result.addAll(weaved);
            }
        }

        return result;
    }


    static public void main(String[] args) {
        ArrayList<Integer> x = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7));
        BinaryNode<Integer> t = buildBinarySearchTreeFromAscendingUniqueList(x);
        System.out.println(t);

        System.out.println(allSequences(t));
    }
}
