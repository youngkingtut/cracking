package com.tristan.cracking;

import java.util.ArrayList;

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


public class TreesAndGraphs {
    static public void main(String[] args) {
    }
}
