package service;

import lombok.Getter;
import model.graph.impl.Edge;
import model.graph.impl.Path;
import model.graph.Graph;
import java.util.LinkedList;
import java.util.List;

public class ForwardPathsFinder {
    private final Graph graph;
    private final boolean[] visited;
    @Getter
    private final List<Path> allForwardPaths;

    public ForwardPathsFinder(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.numberOfNodes()];
        this.allForwardPaths = new LinkedList<>();
        findForwardPaths(graph.getInputNode(), graph.getOutputNode());
    }

    private void findForwardPaths(int inputNode, int outputNode) {
        Path path = new Path(inputNode);
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
