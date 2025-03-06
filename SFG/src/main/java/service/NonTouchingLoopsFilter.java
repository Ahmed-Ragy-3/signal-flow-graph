package service;

import lombok.Getter;
import model.graph.impl.Path;

import java.util.ArrayList;
import java.util.List;

public class NonTouchingLoopsFilter {
    private final List<List<List<Path>>> nonTouchingLoops;
    @Getter
    private final List<List<List<Path>>> filteredNoneTouchingLoops;
    private final Path forwardPath;

    public NonTouchingLoopsFilter(List<List<List<Path>>> nonTouchingLoops, Path forwardPath) {
        this.nonTouchingLoops = nonTouchingLoops;
        this.filteredNoneTouchingLoops = new ArrayList<>();
        this.forwardPath = forwardPath;

        filterNonTouchingLoops();
    }

    private void filterNonTouchingLoops() {
        for (List<List<Path>> nonTouchingLoop : nonTouchingLoops) {
            List<List<Path>> currentFilteredGroup = new ArrayList<>();
            for (List<Path> paths : nonTouchingLoop) {
                boolean isNonTouching = true;
                for (Path loop : paths) {
                    if (!forwardPath.isNonTouching(loop)) {
                        isNonTouching = false;
                        break;
                    }
                }
                if (isNonTouching) {
                    currentFilteredGroup.add(new ArrayList<>(paths));
                }
            }
            filteredNoneTouchingLoops.add(currentFilteredGroup);
        }
    }
}
