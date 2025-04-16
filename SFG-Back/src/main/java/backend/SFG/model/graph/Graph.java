package backend.SFG.model.graph;

import java.util.List;

import backend.SFG.model.graph.impl.Edge;
import backend.SFG.model.graph.impl.Path;

public interface Graph {
    List<Edge> getEdges(int node);

    int numberOfNodes();

    String getOutputNode();

    String getInputNode();

    int OutputNode();

    int InputNode();

    String nodesOf(Path path);

    int numberOfPaths();

    int numberOfLoops();
}
