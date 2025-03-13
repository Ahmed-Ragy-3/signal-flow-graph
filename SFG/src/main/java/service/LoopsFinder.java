package service;

import model.graph.Graph;
import model.graph.impl.Edge;
import model.graph.impl.Path;

import java.util.*;

public class LoopsFinder {
    private final Graph graph;
    private final boolean[] visited;

    private final Set<Path> allLoops = new HashSet<>();

    public LoopsFinder(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.numberOfNodes()];
        findLoops();
    }

    private void findLoops() {
        for (int node = 0; node < graph.numberOfNodes(); node++) {
            Path loop = new Path(node, Path.Type.LOOP);
            findLoops(node, node, loop);
        }
    }

    public List<Path> getLoops() {
        return this.allLoops.stream().toList();
    }

    private void findLoops(int node, int start, Path loop) {
        visited[node] = true;

        for (Edge edge : graph.getEdges(node)) {
            if (edge.getToNode() == start) {
                Path completeLoop = loop.clone();
                completeLoop.addEdge(edge);
                allLoops.add(completeLoop);
                continue;
            }

            if (!visited[edge.getToNode()]) {
                loop.addEdge(edge);
                findLoops(edge.getToNode(), start, loop);
                loop.removeLastEdge();
            }
        }

        visited[node] = false;
    }
}
