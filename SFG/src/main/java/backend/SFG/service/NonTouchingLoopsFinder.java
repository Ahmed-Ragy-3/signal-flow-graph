package backend.SFG.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import backend.SFG.model.graph.impl.GroupOfLoops;
import backend.SFG.model.graph.impl.Path;
import lombok.Getter;

public class NonTouchingLoopsFinder {
    @Getter
    private final List<List<GroupOfLoops>> nonTouchingLoops;
    private final List<Path> loops;

    public NonTouchingLoopsFinder(List<Path> loops) {
        this.nonTouchingLoops = new LinkedList<>();
        this.loops = loops;
        findNonTouchingLoops(0, new GroupOfLoops());
    }

    private void findNonTouchingLoops(int index, GroupOfLoops group) {
        if (nonTouchingLoops.size() <= group.size()) {
            nonTouchingLoops.add(new ArrayList<>());
        }

        nonTouchingLoops.get(group.size()).add(new GroupOfLoops(group.getLoops()));

        for (int i = index; i < loops.size(); i++) {
            boolean nonTouching = true;

            for (Path loop : group.getLoops()) {
                if (!loop.isNonTouching(loops.get(i))) {
                    nonTouching = false;
                    break;
                }
            }

            if (nonTouching) {
                group.getLoops().add(loops.get(i));
                findNonTouchingLoops(i + 1, group);
                group.getLoops().remove(group.size() - 1);
            }
        }
    }
}
