package service;

import lombok.Getter;
import model.graph.Graph;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class ForwardPathsFinder {
    private final Graph graph;
    private final boolean[] visited;
    @Getter
    private List<BitSet> allPaths;

    public ForwardPathsFinder(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.numberOfNodes()];
        findForwardPaths(graph.getInputNode(), graph.getOutputNode());
    }

    private void findForwardPaths(int inputNode, int outputNode) {
        this.allPaths = new ArrayList<>();
        BitSet path = new BitSet(graph.numberOfNodes());
        dfs(inputNode, outputNode, path);
    }

    private void dfs(int node, int destination, BitSet path) {
        if (node == destination) {
            allPaths.add((BitSet) path.clone());
            return;
        }

        path.set(node);
        visited[node] = true;

        for (Integer adjacentNode : graph.adjacentNodes(node)) {
            if (!visited[adjacentNode]) {
                dfs(adjacentNode, destination, path);
            }
        }

        visited[node] = false;
        path.clear(node);
    }
}
