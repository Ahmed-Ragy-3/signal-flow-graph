package backend.SFG.model.graph.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import backend.SFG.model.dtos.EdgeDto;
import backend.SFG.model.dtos.GraphDto;
import backend.SFG.model.graph.Graph;
import lombok.Data;

@Data
public class SignalFlowGraph implements Graph {
    public static final String ARROW = " → ";

    private List<List<Edge>> graph;
    private Map<String, Integer> positions;
    private Map<Integer, String> positions2;

    private String inputNode;
    private String outputNode;
    private int numberOfNodes;

    private int numberOfPaths = 0;
    private int numberOfLoops = 0;

    public SignalFlowGraph(GraphDto graphDto) {
        this.numberOfNodes = graphDto.getNodes().size();
        this.graph = new ArrayList<>(numberOfNodes);
        this.positions = new HashMap<>();
        this.positions2 = new HashMap<>();

        this.inputNode = graphDto.getInputNode();
        this.outputNode = graphDto.getOutputNode();

        for (int i = 0; i < numberOfNodes; i++) {
            positions.put(graphDto.getNodes().get(i), i);
            graph.add(new LinkedList<>());
            positions2.put(i, graphDto.getNodes().get(i));
        }

        for (EdgeDto edge : graphDto.getEdges()) {
            this.addDirectedEdge(edge);
        }
    }

    public int InputNode() {
        return positions.get(inputNode);
    }

    public int OutputNode() {
        return positions.get(outputNode);
    }

    public int numberOfNodes() {
        return numberOfNodes;
    }

    public int numberOfLoops() {
        return numberOfLoops;
    }

    public int numberOfPaths() {
        return numberOfPaths;
    }

    public String nodesOf(Path path) {
        return positions2.get(path.getStartNode()) + ARROW + path.getEdges()
                .stream()
                .map(edge -> positions2.get(edge.getToNode()))
                .collect(Collectors.joining(ARROW));
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
        final int src = positions.get(edge.getSource());
        final int target = positions.get(edge.getTarget());

        graph.get(src).add(new Edge(target, edge.getGain()));
    }
}
