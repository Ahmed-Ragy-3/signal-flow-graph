package model.graph.impl;

import lombok.Data;
import model.dtos.EdgeDto;
import model.dtos.GraphDto;
import model.graph.Graph;

import java.util.*;

@Data
public class SignalFlowGraph implements Graph {
    private List<List<Edge>> graph;
    private Map<String, Integer> positions;
    private String inputNode;
    private String outputNode;
    private int numberOfNodes;

    public SignalFlowGraph(GraphDto graphDto) {
        this.numberOfNodes = graphDto.getNodes().size();
        this.graph = new ArrayList<>(numberOfNodes);
        this.positions = new HashMap<>();
        this.inputNode = graphDto.getInputNode();
        this.outputNode = graphDto.getOutputNode();

        for (int i = 0; i < numberOfNodes; i++) {
            positions.put(graphDto.getNodes().get(i), i);
            graph.set(i ,new LinkedList<>());
        }
        for (EdgeDto edge : graphDto.getEdges()) {
            this.addDirectedEdge(edge);
        }
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

    public List<Edge> getEdges(int node) {
        if (!positions.containsValue(node)) {
            throw new IllegalArgumentException("Node does not exist: " + node);
        }
        return graph.get(node);
    }

    private void addDirectedEdge(EdgeDto edge) {
        if (!positions.containsKey(edge.getSource()) || !positions.containsKey(edge.getTarget())) {
            throw new IllegalArgumentException("Invalid edge: " + edge.getSource() + " -> " + edge.getTarget());
        }
        graph.get(positions.get(edge.getSource())).add(new Edge(positions.get(edge.getTarget()), edge.getGainSymbol(), edge.getGain()));
    }
}
