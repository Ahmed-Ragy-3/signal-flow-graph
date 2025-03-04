package service;
import java.util.*;

public class NonTouchingLoopsFinder {
    private final List<BitSet> nonTouchingLoops = new ArrayList<>();
    private final int[] arrLoops;
    private final int n;


    public NonTouchingLoopsFinder(int n) {
        this.arrLoops = new int[n];
        this.n = n;
    }

    public void findNonTouchingLoops(int index, BitSet path) {
        if (!path.isEmpty()) {
            nonTouchingLoops.add(path);
        }

        for (int i = index; i < n; i++) {
            boolean nonTouching = true;

            // Check if current loop touches any loop in the path
            for (int loop : path) {
                if ((arrLoops[i] & loop) != 0) {
                    nonTouching = false;
                    break;
                }
            }

            if (nonTouching) {
                path.add(arrLoops[i]);
                findNonTouchingLoops(i + 1, path);
                path.remove(path.size() - 1); // Backtrack
            }
        }
    }
}
