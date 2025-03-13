package service;

import model.dtos.GraphDto;
import model.dtos.SolutionDto;
import model.graph.Graph;
import model.graph.impl.Path;
import model.graph.impl.SignalFlowGraph;
import org.springframework.stereotype.Service;

@Service
public class GraphService {

    public SolutionDto solve(GraphDto graphDto) {
        Graph graph = new SignalFlowGraph(graphDto);
        ForwardPathsFinder forwardPathsFinder = new ForwardPathsFinder(graph);
        LoopsFinder loopsFinder = new LoopsFinder(graph);
        NonTouchingLoopsFinder nonTouchingLoopsFinder = new NonTouchingLoopsFinder(loopsFinder.getLoops());

        for(Path forwardPath : forwardPathsFinder.getAllForwardPaths()) {
            NonTouchingLoopsFilter nonTouchingLoopsFilter = new NonTouchingLoopsFilter(nonTouchingLoopsFinder.getNonTouchingLoops(), forwardPath);
            forwardPath.assignDelta(nonTouchingLoopsFilter.getFilteredNonTouchingLoops());
        }

        return new SolutionDto(forwardPathsFinder.getAllForwardPaths(),
                loopsFinder.getLoops(),
                nonTouchingLoopsFinder.getNonTouchingLoops(),
                Path.calculateDelta(nonTouchingLoopsFinder.getNonTouchingLoops()),
                "2.0", 2.0);
    }
}
