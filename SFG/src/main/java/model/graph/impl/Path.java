package model.graph.impl;

import lombok.Data;
import java.util.*;

@Data
public class Path implements Cloneable {
    private BitSet bitSet;
    private int startNode;
    private List<Edge> edges;

    public Path(int startNode) {
        this.startNode = startNode;
        this.bitSet = new BitSet();
        this.edges = new LinkedList<>();
    }

    public boolean isNonTouching(Path path) {
        BitSet temp = (BitSet) bitSet.clone();
        temp.and(path.getBitSet());
        return temp.isEmpty();
    }

    public Iterable<Integer> getNodes() {
        List<Integer> nodes = new ArrayList<>();
        nodes.add(startNode);
        for (Edge edge : edges) {
            nodes.add(edge.getToNode());
        }
        return nodes;
    }

    public void addEdge(Edge edge) {
        bitSet.set(edge.getToNode());
        edges.add(edge);
    }

    public void removeLastEdge() {
        if (!edges.isEmpty()) {
            Edge removed = edges.removeLast();
            bitSet.clear(removed.getToNode());
        }
    }

    @Override
    public Path clone() {
        try {
            Path clone = (Path) super.clone();
            clone.bitSet = (BitSet) this.bitSet.clone();
            clone.edges = new LinkedList<>(this.edges);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
