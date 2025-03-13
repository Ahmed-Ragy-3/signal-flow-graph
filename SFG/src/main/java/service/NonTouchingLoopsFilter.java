package service;

import lombok.Getter;
import model.graph.impl.GroupOfLoops;
import model.graph.impl.Path;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NonTouchingLoopsFilter {
    private final List<List<GroupOfLoops>> nonTouchingLoops;
    @Getter
    private final List<List<GroupOfLoops>> filteredNonTouchingLoops;
    private final Path forwardPath;

    public NonTouchingLoopsFilter(List<List<GroupOfLoops>> nonTouchingLoops, Path forwardPath) {
        this.nonTouchingLoops = nonTouchingLoops;
        this.filteredNonTouchingLoops = new ArrayList<>();
        this.forwardPath = forwardPath;

        filterNonTouchingLoops();
    }

    private void filterNonTouchingLoops() {
        for (List<GroupOfLoops> nonTouchingLoop : nonTouchingLoops) {
            List<GroupOfLoops> currentFilteredGroup = new LinkedList<>();
            for (GroupOfLoops group : nonTouchingLoop) {
                boolean isNonTouching = true;
                for (Path loop : group.getLoops()) {
                    if (!forwardPath.isNonTouching(loop)) {
                        isNonTouching = false;
                        break;
                    }
                }

                if (isNonTouching) {
                    currentFilteredGroup.add(new GroupOfLoops(group.getLoops()));
                }
            }
            filteredNonTouchingLoops.add(currentFilteredGroup);
        }

//        forwardPath.
    }
}
