package model.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.graph.dto.EdgeDto;

import java.util.*;

@Data
public class Graph {
    @Data
    @AllArgsConstructor
    public static class Edge {
        private String toNode;
        private String gainSymbol; // ex. G1
        private int gain;
    }

    private List<List<Edge>> graph;
    private Map<String, Integer> positions;
    private String inputNode;
    private String outputNode;
    private int numberOfNodes;

    public Graph(String inputNode, String outputNode, List<String> nodes, List<EdgeDto> edges) {
        this.graph = new ArrayList<>();
        this.positions = new HashMap<>();
        this.numberOfNodes = nodes.size();
        this.outputNode = outputNode;
        this.inputNode = inputNode;

        for(int i=0; i < nodes.size(); i++) {
            positions.put(nodes.get(i), i);
            graph.add(i, new ArrayList<>());
        }
        for(EdgeDto edge : edges) {
            this.addDirectedEdge(edge);
        }
    }

    public boolean isOutputNode(String node) {
        return outputNode.equals(node);
    }

    public boolean isInputNode(String node) {
        return inputNode.equals(node);
    }

    public int getInputNode() {
        return positions.get(inputNode);
    }

    public int getOutputNode() {
        return positions.get(outputNode);
    }

    public int numberOfNodes() {
        return numberOfNodes;
    }

    public int position(String node) {
        return this.positions.get(node);
    }

    private Iterable<Edge> getEdges(String node) {
        if (!positions.containsKey(node)) {
            throw new IllegalArgumentException("Node does not exist: " + node);
        }
        return graph.get(positions.get(node));
    }

    public Iterable<Integer> adjacentNodes(int node) {
        return graph.get(node).stream()
                .map(edge -> positions.get(edge.getToNode())).toList();
    }

    private void addDirectedEdge(EdgeDto edge) throws  IllegalArgumentException {
        this.addDirectedEdge(edge.getSource(), edge.getTarget(), edge.getGainSymbol(), edge.getGain());
    }

    private void addDirectedEdge(String source, String target, String gainSymbol, int gain) {
        if (!positions.containsKey(source) || !positions.containsKey(target)) {
            throw new IllegalArgumentException("Invalid edge: " + source + " -> " + target);
        }
        this.graph.get(positions.get(source)).add(new Edge(target, gainSymbol, gain));
    }
}
