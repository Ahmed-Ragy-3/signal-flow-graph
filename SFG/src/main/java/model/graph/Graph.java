package model.graph;

import model.graph.impl.Edge;
import model.graph.impl.SignalFlowGraph;

import java.util.List;

public interface Graph {
    List<Edge> getEdges(int node);
    int numberOfNodes();
    int getOutputNode();
    int getInputNode();
}
