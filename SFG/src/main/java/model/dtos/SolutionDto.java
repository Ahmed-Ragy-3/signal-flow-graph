package model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import model.graph.impl.Path;

@Data
@AllArgsConstructor
public class SolutionDto {
    private List<Path> forwardPaths;
    private List<Path> loops;
    private List<List<List<Path>>> nonTouchingLoops;
}
