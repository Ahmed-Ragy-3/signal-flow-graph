package backend.SFG.model.dtos;

import java.util.List;
import java.util.stream.Collectors;

import backend.SFG.model.graph.Graph;
import backend.SFG.model.graph.impl.GroupOfLoops;
import backend.SFG.model.graph.impl.Path;
import backend.SFG.service.ExpressionEvaluator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SolutionDto {
    private List<PathDto> forwardPaths;
    private List<LoopDto> loops;
    private List<List<GroupDto>> nonTouchingLoops;
    private String delta;
    private FormulaDto formula;
    private Double solution;

    public SolutionDto(Graph graph, List<Path> forwardPaths,
            List<Path> loops, List<List<GroupOfLoops>> groups, String delta, Double solution) {

        this.forwardPaths = forwardPaths.stream()
                .map(path -> new PathDto(path, graph))
                .collect(Collectors.toList());

        this.loops = loops.stream()
                .map(loop -> new LoopDto(loop, graph))
                .collect(Collectors.toList());

        this.nonTouchingLoops = groups.stream()
                .map(groupList -> groupList.stream()
                        .map(GroupDto::new)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        this.nonTouchingLoops.get(0).clear();

        this.delta = delta;
        this.formula = new FormulaDto(forwardPaths, delta);
        this.solution = ExpressionEvaluator
                .evaluateFormula("(" + formula.getNumerator() + ") / (" + formula.getDenomenator() + ")");
    }
}

@Data
@AllArgsConstructor
class PathDto {
    private String nodes;
    private String gain;
    private String delta;

    public PathDto(Path path, Graph graph) {
        this.gain = path.getGain();
        this.delta = path.getDelta();
        this.nodes = graph.nodesOf(path);
    }
}

@Data
@AllArgsConstructor
class LoopDto {
    private String name;
    private String nodes;
    private String gain;

    public LoopDto(Path loop, Graph graph) {
        this.name = loop.getName();
        this.nodes = graph.nodesOf(loop);

        this.gain = loop.getGain();
    }
}

@Data
@AllArgsConstructor
class GroupDto {
    private String name;
    private String gain;

    public GroupDto(GroupOfLoops group) {
        this.name = group.getLoops().stream()
                .map(Path::getName) // Extract `name` attribute
                .collect(Collectors.joining(" "));
        this.gain = group.getTotalGain();
    }
}

@Data
@AllArgsConstructor
class FormulaDto {
    private String numerator;
    private String denomenator;

    public FormulaDto(List<Path> forwardPaths, String delta) {
        String[] formula = ExpressionEvaluator.findFormula(forwardPaths, delta);
        this.numerator = formula[0];
        this.denomenator = formula[1];
    }
}
