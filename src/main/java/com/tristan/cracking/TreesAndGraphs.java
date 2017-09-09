package com.tristan.cracking;

import sun.awt.image.ImageWatched;

import java.util.*;

class GraphNode {
    private String name;
    private ArrayList<GraphNode> adjacent;
    private boolean visited = false;

    GraphNode(String name) {
        this.name = name;
        this.adjacent = new ArrayList<GraphNode>();
    }

    void setVisited(boolean visited) {
        this.visited = visited;
    }

    boolean getVisited() {
        return this.visited;
    }

    ArrayList<GraphNode> getAdjacent() {
        return this.adjacent;
    }

    void updateAdjacent(GraphNode node) {
        if(!this.adjacent.contains(node))
            this.adjacent.add(node);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append(name);
        s.append(": ");
        for(GraphNode n: this.adjacent) {
            s.append(n.name);
            s.append(" ");
        }

        return s.toString();
    }
}

class Graph {
    private ArrayList<GraphNode> nodes;

    Graph() {
        nodes = new ArrayList<GraphNode>();
    }

    void addNode(GraphNode node, GraphNode[] adjacentNodes) {
        if(!this.nodes.contains(node)) {
            for (GraphNode n : adjacentNodes) {
                node.updateAdjacent(n);
            }
            this.nodes.add(node);
        }

    }

    boolean routeExists(GraphNode source, GraphNode destination) {
        if(!nodes.contains(source) || !nodes.contains(destination)) {
            return false;
        } else {
            MyQueue<GraphNode> q = new MyQueue<GraphNode>();
            q.add(source);

            for(GraphNode n: nodes) {
                n.setVisited(false);
            }
            source.setVisited(true);

            while(!q.isEmpty()) {
                GraphNode v = q.remove();
                for(GraphNode n: v.getAdjacent()) {
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

    static public void main(String[] args) {
        BinaryNode<Character> H = new BinaryNode<Character>('H', null, null);

        BinaryNode<Character> E = new BinaryNode<Character>('E', null, null);
        BinaryNode<Character> F = new BinaryNode<Character>('F', null, null);
        BinaryNode<Character> G = new BinaryNode<Character>('G', null, null);
        BinaryNode<Character> D = new BinaryNode<Character>('D', H, null);


        BinaryNode<Character> C = new BinaryNode<Character>('C', F, G);
        BinaryNode<Character> B = new BinaryNode<Character>('B', D, E);

        BinaryNode<Character> A = new BinaryNode<Character>('A', B, C);

        System.out.println(inOrderSuccesor(A));
        System.out.println(inOrderSuccesor(B));
        System.out.println(inOrderSuccesor(C));
        System.out.println(inOrderSuccesor(D));
        System.out.println(inOrderSuccesor(E));
        System.out.println(inOrderSuccesor(F));
        System.out.println(inOrderSuccesor(G));
        System.out.println(inOrderSuccesor(H));
    }
}
