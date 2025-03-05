package model.graph;

import model.graph.impl.Edge;
import model.graph.impl.SignalFlowGraph;

import java.util.List;

public interface Graph {
    List<Integer> adjacentNodes(int node);
    List<Edge> getEdges(int node);
    int numberOfNodes();
    int getOutputNode();
    int getInputNode();
}
