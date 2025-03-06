package service;
import lombok.Getter;
import model.graph.impl.Path;

import java.util.*;

public class NonTouchingLoopsFinder {
    @Getter
    private final List<List<List<Path>>> nonTouchingLoops;
    private final List<Path> loops;

    public NonTouchingLoopsFinder(List<Path> loops) {
        this.nonTouchingLoops = new LinkedList<>();
        this.loops = loops;
        findNonTouchingLoops(0, new ArrayList<>());
    }

    private void findNonTouchingLoops(int index, List<Path> group) {
        if(nonTouchingLoops.size() <= group.size()) {
            nonTouchingLoops.add(new ArrayList<>());
        }

        nonTouchingLoops.get(group.size()).add(new ArrayList<>(group));

        for (int i = index; i < loops.size(); i++) {
            boolean nonTouching = true;

            for (Path loop : group) {
                if (!loop.isNonTouching(loops.get(i))) {
                    nonTouching = false;
                    break;
                }
            }

            if (nonTouching) {
                group.add(loops.get(i));
                findNonTouchingLoops(i + 1, group);
                group.remove(group.size() - 1);
            }
        }
    }
}
