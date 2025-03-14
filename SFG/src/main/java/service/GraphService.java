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

        String delta = Path.calculateDelta(nonTouchingLoopsFinder.getNonTouchingLoops());
        String formula = ExpressionEvaluator.findFormula(forwardPathsFinder.getAllForwardPaths(), delta);
        double solution = ExpressionEvaluator.evaluateFormula(formula);

        return new SolutionDto(
                forwardPathsFinder.getAllForwardPaths()
                , loopsFinder.getLoops()
                , nonTouchingLoopsFinder.getNonTouchingLoops()
                , delta
                , formula
                , solution
        );
    }
}
