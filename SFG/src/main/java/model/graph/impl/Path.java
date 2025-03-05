package model.graph.impl;

import lombok.Data;
import java.util.*;

@Data
public class Path implements Cloneable {
    private BitSet bitSet;
    private int startNode;
    private LinkedList<Edge> edges;
    private LinkedList<String> symbolicTerms;
    private Map<String, Integer> symbolValues;
    private double gain;

    public Path(int startNode) {
        this.startNode = startNode;
        this.bitSet = new BitSet();
        this.edges = new LinkedList<>();
        this.symbolicTerms = new LinkedList<>();
        this.symbolValues = new HashMap<>();
        this.gain = 1;
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

        symbolicTerms.add(edge.getGainSymbol());
        symbolValues.put(edge.getGainSymbol(), edge.getGain()); // Ensure unique symbols
        gain *= edge.getGain();
    }

    public void removeLastEdge() {
        if (!edges.isEmpty()) {
            Edge removed = edges.removeLast();
            bitSet.clear(removed.getToNode());

            symbolicTerms.removeLast();
            gain /= removed.getGain();
            symbolValues.remove(removed.getGainSymbol());
        }
    }

    public String getSymbolicGain() {
        return String.join(" * ", symbolicTerms);
    }

    public String getSubstitutedGain() {
        String substitutedExpression = getSymbolicGain();
        for (Map.Entry<String, Integer> entry : symbolValues.entrySet()) {
            substitutedExpression = substitutedExpression.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue().toString());
        }
        return substitutedExpression;
    }

    public double getNumericGain() {
        return gain;
    }

    @Override
    public Path clone() {
        try {
            Path clone = (Path) super.clone();
            clone.bitSet = (BitSet) this.bitSet.clone();
            clone.edges = new LinkedList<>(this.edges);
            clone.symbolicTerms = new LinkedList<>(this.symbolicTerms);
            clone.symbolValues = new HashMap<>(this.symbolValues);
            clone.gain = this.gain;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
