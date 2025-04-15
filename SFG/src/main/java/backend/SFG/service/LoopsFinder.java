package backend.SFG.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import backend.SFG.model.graph.Graph;
import backend.SFG.model.graph.impl.Edge;
import backend.SFG.model.graph.impl.Path;

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
            Path loop = new Path(node, Path.Type.LOOP, allLoops.size());
            findLoops(node, node, loop);
        }
    }

    public List<Path> getLoops() {
        return this.allLoops.stream().sorted((a, b) -> a.getName().compareTo(b.getName())).toList();
    }

    // private boolean contains(Path completeLoop) {
    // if(allLoops.contains(completeLoop)) {
    // for(Path loop : allLoops) {
    // if(loop.equals(completeLoop)) {
    // return Path.equalEdges(loop.getEdges(), completeLoop.getEdges());
    // }
    // }
    // }
    // return false;
    // }

    private void findLoops(int node, int start, Path loop) {
        visited[node] = true;

        for (Edge edge : graph.getEdges(node)) {
            if (edge.getToNode() == start) {
                Path completeLoop = loop.clone();
                completeLoop.addEdge(edge);
                if (!allLoops.contains(completeLoop)) {
                    allLoops.add(completeLoop);
                    completeLoop.setName("L" + Integer.toString(allLoops.size()));
                } else {
                    System.out.println(completeLoop.getEdges().toString());
                    // System.out.println(completeLoop.getEdges());
                    System.out.println();
                }
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
