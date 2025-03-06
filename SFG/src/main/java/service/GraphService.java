package service;

import model.dtos.GraphDto;
import model.dtos.SolutionDto;
import model.graph.Graph;
import model.graph.impl.Path;
import model.graph.impl.SignalFlowGraph;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class GraphService {

    public SolutionDto solve(GraphDto graphDto) {
        Graph graph = new SignalFlowGraph(graphDto);
        ForwardPathsFinder forwardPathsFinder = new ForwardPathsFinder(graph);
        LoopsFinder loopsFinder = new LoopsFinder(graph);
        NonTouchingLoopsFinder nonTouchingLoopsFinder = new NonTouchingLoopsFinder(loopsFinder.getLoops());

        List<List<List<List<Path>>>> nonTouchingLoops = new ArrayList<>();
        for(Path forwardPath : forwardPathsFinder.getAllForwardPaths()) {
            NonTouchingLoopsFilter nonTouchingLoopsFilter = new NonTouchingLoopsFilter(nonTouchingLoopsFinder.getNonTouchingLoops(), forwardPath);
            nonTouchingLoops.add(nonTouchingLoopsFilter.getFilteredNoneTouchingLoops());
        }

        SolutionDto solution = new SolutionDto(forwardPathsFinder.getAllForwardPaths(),
                loopsFinder.getLoops(), nonTouchingLoopsFinder.getNonTouchingLoops());

    }


}
