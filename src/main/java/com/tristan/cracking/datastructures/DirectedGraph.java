package com.tristan.cracking.datastructures;

import java.util.*;
import java.util.LinkedList;


class DirectedGraphNode {
    private String name;
    private HashMap<String, DirectedGraphEdge> edgeMap;

    DirectedGraphNode(String n){
        this.name = n;
        this.edgeMap = new HashMap<>();
    }

    void addEdge(DirectedGraphEdge edge) {
        if(this.edgeMap.containsKey(edge.getTo().name)) {
            if(this.edgeMap.get(edge.getTo().name).getWeight() > edge.getWeight()) {
                this.edgeMap.put(edge.getTo().name, edge);
            }
        } else {
            this.edgeMap.put(edge.getTo().name, edge);
        }
    }

    String getName() {
        return name;
    }

    HashMap<String, DirectedGraphEdge> getEdgeMap() {
        return edgeMap;
    }
}

class DirectedGraphEdge {
    private DirectedGraphNode to;
    private DirectedGraphNode from;
    private int weight;

    DirectedGraphEdge(DirectedGraphNode f, DirectedGraphNode t, int w) {
        this.to = t;
        this.from = f;
        this.weight = w;
    }

    DirectedGraphNode getTo() {
        return this.to;
    }

    DirectedGraphNode getFrom() {
        return from;
    }

    int getWeight() {
        return weight;
    }
}

public class DirectedGraph {



    private HashMap<String, DirectedGraphNode> nodes;

    public DirectedGraph() {
        this.nodes = new HashMap<>();
    }

    DirectedGraph(DirectedGraphEdge[] edges){
        this.nodes = new HashMap<>();
        for(DirectedGraphEdge edge: edges) {
            if(edge.getTo() == null || edge.getFrom() == null) {
                throw new IllegalArgumentException();
            }
            nodes.put(edge.getFrom().getName(), edge.getFrom());
            nodes.put(edge.getTo().getName(), edge.getTo());
            nodes.get(edge.getFrom().getName()).addEdge(edge);
        }
    }

    Optional<Integer> getWeight(String from, String to) {
        if(nodes.containsKey(to) && nodes.containsKey(from)) {
            if(nodes.get(from).getEdgeMap().containsKey(to)) {
                return Optional.of(nodes.get(from).getEdgeMap().get(to).getWeight());
            }
        }
        return Optional.empty();
    }

    Optional<Integer> shortestDistance(String from, String to) {
        if(nodes.containsKey(to) && nodes.containsKey(from)) {
            LinkedList<DirectedGraphNode> checkQueue = new LinkedList<>();
            HashMap<DirectedGraphNode, Integer> distances = new HashMap<>();
            HashSet<DirectedGraphNode> checked = new HashSet<>();

            for(Map.Entry<String, DirectedGraphNode> node: nodes.entrySet()) {
                distances.put(node.getValue(), Integer.MAX_VALUE);
            }

            DirectedGraphNode currentNode = nodes.get(from);
            DirectedGraphNode destination = nodes.get(to);
            distances.put(currentNode, 0);
            checkQueue.push(currentNode);

            while(!checkQueue.isEmpty()) {
                currentNode = checkQueue.poll();
                checked.add(currentNode);
                int distance = distances.get(currentNode);

                if(destination == currentNode) {
                    return Optional.of(distance);
                }

                for(DirectedGraphEdge edge: currentNode.getEdgeMap().values()) {
                    DirectedGraphNode next = edge.getTo();

                    if(!checked.contains(next)) {
                        checkQueue.add(next);
                    }

                    if(distance + edge.getWeight() < distances.get(edge.getTo())) {
                        distances.put(edge.getTo(), distance + edge.getWeight());
                    }

                }
            }

        }
        return Optional.empty();
    }
}
