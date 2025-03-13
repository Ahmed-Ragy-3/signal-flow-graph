package model.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import model.graph.impl.GroupOfLoops;
import model.graph.impl.Path;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class SolutionDto {
    private List<PathDto> forwardPaths;
    private List<LoopDto> loops;
    private List<List<GroupDto>> nonTouchingLoops;
    private String delta;
    private String formula;
    private Double solution;

    public SolutionDto(List<Path> forwardPaths, List<Path> loops, List<List<GroupOfLoops>> groups,
                       String delta, String formula, Double solution) {

        this.forwardPaths = forwardPaths.stream()
                .map(PathDto::new)
                .collect(Collectors.toList());

        this.loops = loops.stream()
                .map(LoopDto::new)
                .collect(Collectors.toList());

        this.nonTouchingLoops = groups.stream()
                                      .map(groupList -> groupList.stream()
                                      .map(GroupDto::new)
                                      .collect(Collectors.toList()))
                                      .collect(Collectors.toList());

        this.delta = delta;
        this.formula = formula;
        this.solution = solution;
    }
}

@Data
@AllArgsConstructor
class PathDto {
    private String nodes;
    private String gain;
    private String delta;

    public PathDto(Path path) {
        this.gain = path.getGain();
        this.delta = path.getDelta();
        this.nodes = path.getNodes().stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}

@Data
@AllArgsConstructor
class LoopDto {
    private String name;
    private String nodes;
    private String gain;

    public LoopDto(Path loop) {
        this.name = loop.getName();
        this.nodes = loop.getNodes().stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));

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
