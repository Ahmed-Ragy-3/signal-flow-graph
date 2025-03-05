package model.graph.impl;

import lombok.Data;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

@Data
public class Path implements Cloneable{
    private BitSet bitSet;
    private int startNode;
    private List<Edge> edges;
    private double gain;

    public Path(int startNode) {
        this.startNode = startNode;
        this.bitSet = new BitSet();
        this.edges = new LinkedList<>();
        this.bitSet.set(startNode);
        this.gain = 1;
    }

    public Iterable<Integer> getNodes() {
        List<Integer> nodes = new LinkedList<>();
        nodes.add(startNode);
        for(Edge edge : edges) {
            nodes.add(edge.getToNode());
        }
        return nodes;
    }

    public boolean isConnectedTo(Path path) {
        BitSet temp = (BitSet) this.bitSet.clone();
        temp.and(path.getBitSet());
        return temp.cardinality() != 0;
    }

    public void addEdge(Edge edge) {
        bitSet.set(edge.getToNode());
        edges.addLast(edge);
        gain *= edge.getGain();
    }

    public void removeLastEdge() {
        Edge edge = edges.removeLast();
        bitSet.clear(edge.getToNode());
        gain /= edge.getGain();
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
