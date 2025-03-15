package backend.SFG.service;

import java.util.LinkedList;
import java.util.List;

import backend.SFG.model.graph.Graph;
import backend.SFG.model.graph.impl.Edge;
import backend.SFG.model.graph.impl.Path;
import lombok.Getter;

public class ForwardPathsFinder {
    private final Graph graph;
    private final boolean[] visited;
    @Getter
    private final List<Path> allForwardPaths;

    public ForwardPathsFinder(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.numberOfNodes()];
        this.allForwardPaths = new LinkedList<>();
        findForwardPaths(graph.InputNode(), graph.OutputNode());
    }

    private void findForwardPaths(int inputNode, int outputNode) {
        Path path = new Path(inputNode, Path.Type.PATH, graph.numberOfPaths());
        dfs(inputNode, outputNode, path);
    }

    private void dfs(int node, int destination, Path path) {
        if (node == destination) {
            allForwardPaths.add(path.clone());
            return;
        }

        visited[node] = true;

        for (Edge edge : graph.getEdges(node)) {

            if (!visited[edge.getToNode()]) {
                path.addEdge(edge);
                dfs(edge.getToNode(), destination, path);
                path.removeLastEdge();
            }
        }

        visited[node] = false;
    }
}
