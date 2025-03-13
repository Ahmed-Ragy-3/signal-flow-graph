package model.graph.impl;

import lombok.Data;
import java.util.*;

@Data
public class Path implements Cloneable {
//    public static final String[] subscripts = {
//        "₀", "₁", "₃", "₆", "₉", "₇", "₄", "₅", "₂", "₈"
//    };

    public enum Type {
        LOOP,
        PATH
    }

    public static final String DELIMITER = " * ";

    private static int numberOfPaths = 0;
    private static int numberOfLoops = 0;

    private String name;
    private BitSet bitSet;
    private int startNode;
    private List<Edge> edges;
    private String delta;
    private String gain;

    public Path(int startNode, Type type) {
        if(type == Type.LOOP) {
            this.name = "L" + numberOfLoops++;
        } else if (type == Type.PATH){
            this.name = "P" + numberOfPaths++;
        }

        this.startNode = startNode;
        this.bitSet = new BitSet();
        this.edges = new LinkedList<>();
        this.gain = "";
    }

    public boolean isNonTouching(Path path) {
        BitSet temp = (BitSet) bitSet.clone();
        temp.and(path.getBitSet());
        return temp.isEmpty();
    }

    public List<Integer> getNodes() {
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

        if(!gain.isEmpty()) gain += DELIMITER;
        gain += edge.getGain();
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

    public String assignDelta(List<List<GroupOfLoops>> groups) {
        return delta = calculateDelta(groups);
    }

    public static String calculateDelta(List<List<GroupOfLoops>> groups) {
        boolean sign = true;
        StringBuilder stringBuilder = new StringBuilder("1");
        for (List<GroupOfLoops> groupOfLoops : groups) {
            stringBuilder.append(sign ? " - " : " + " ).append("(");
            sign = !sign;

            for (GroupOfLoops group : groupOfLoops) {
                stringBuilder.append(" + ")
                             .append("(")
                             .append(group.getTotalGain())
                             .append(")");
            }
            stringBuilder.append(")");
        }

        return stringBuilder.toString();
    }
}
