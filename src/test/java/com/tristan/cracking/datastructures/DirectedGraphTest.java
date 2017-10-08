package com.tristan.cracking.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DirectedGraphTest {
    private DirectedGraph g;

    @BeforeEach
    void init() {
        DirectedGraphNode a = new DirectedGraphNode("A");
        DirectedGraphNode b = new DirectedGraphNode("B");
        DirectedGraphNode c = new DirectedGraphNode("C");
        DirectedGraphNode d = new DirectedGraphNode("D");
        DirectedGraphEdge e0 = new DirectedGraphEdge(a, b, 1);
        DirectedGraphEdge e1 = new DirectedGraphEdge(b, c, 2);
        DirectedGraphEdge e2 = new DirectedGraphEdge(c, d, 3);
        DirectedGraphEdge e3 = new DirectedGraphEdge(d, a, 4);
        DirectedGraphEdge[] edges = {e0, e1, e2, e3};
        g = new DirectedGraph(edges);
    }

    @Test
    void testContainsEdge() {
        Optional<Integer> good = g.getWeight("A", "B");
        Optional<Integer> bad = g.getWeight("B", "D");
        assertEquals(good.isPresent(), true);
        assertEquals(good.orElse(Integer.MAX_VALUE), new Integer(1));
        assertEquals(bad.isPresent(), false);
    }

    @Test
    void testShortestDistance() {
        Optional<Integer> good = g.shortestDistance("A", "C");
        Optional<Integer> bad = g.shortestDistance("A", "F");
        assertEquals(good.isPresent(), true);
        assertEquals(good.orElse(Integer.MIN_VALUE), new Integer(3));
        assertEquals(bad.isPresent(), false);
    }


}
