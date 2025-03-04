package service;

import model.graph.Graph;
import java.util.*;

public class LoopsFinder {
    private final Graph graph;
    private final boolean[] visited;

    private final Set<BitSet> allLoops = new HashSet<>();

    public LoopsFinder(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.numberOfNodes()];
        findLoops();
    }

    private void findLoops() {
        for (int node = 0; node < graph.numberOfNodes(); node++) {
            BitSet loop = new BitSet(graph.numberOfNodes());
            findLoops(node, node, loop);
        }
    }

    public Iterable<BitSet> getLoops() {
        return this.allLoops.stream().toList();
    }

    private void findLoops(int node, int start, BitSet loop) {
        visited[node] = true;
        loop.set(node);

        for (int neighbor : graph.adjacentNodes(node)) {
            if (neighbor == start) {
                allLoops.add((BitSet) loop.clone());
                continue;
            }

            if (!visited[neighbor]) {
                findLoops(neighbor, start, loop);
            }
        }

        visited[node] = false;
        loop.clear(node);
    }
}
