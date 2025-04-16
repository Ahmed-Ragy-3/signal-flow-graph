package backend.SFG.service;

import org.springframework.stereotype.Service;

import backend.SFG.model.dtos.GraphDto;
import backend.SFG.model.dtos.SolutionDto;
import backend.SFG.model.graph.Graph;
import backend.SFG.model.graph.impl.Path;
import backend.SFG.model.graph.impl.SignalFlowGraph;

@Service
public class GraphService {

    public SolutionDto solve(GraphDto graphDto) {
        Graph graph = new SignalFlowGraph(graphDto);
        ForwardPathsFinder forwardPathsFinder = new ForwardPathsFinder(graph);
        LoopsFinder loopsFinder = new LoopsFinder(graph);
        NonTouchingLoopsFinder nonTouchingLoopsFinder = new NonTouchingLoopsFinder(loopsFinder.getLoops());

        for (Path forwardPath : forwardPathsFinder.getAllForwardPaths()) {
            NonTouchingLoopsFilter nonTouchingLoopsFilter = new NonTouchingLoopsFilter(
                    nonTouchingLoopsFinder.getNonTouchingLoops(), forwardPath);
            forwardPath.assignDelta(nonTouchingLoopsFilter.getFilteredNonTouchingLoops());
        }

        String delta = Path.calculateDelta(nonTouchingLoopsFinder.getNonTouchingLoops());
        Double solution = ExpressionEvaluator.evaluateFormula(delta.replaceAll("·", "*"));
        return new SolutionDto(
                graph,
                forwardPathsFinder.getAllForwardPaths(), loopsFinder.getLoops(),
                nonTouchingLoopsFinder.getNonTouchingLoops(), delta, solution);
    }
}
