package backend.SFG.model.graph.impl;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupOfLoops {
    private List<Path> loops;
    private String totalGain;

    public GroupOfLoops() {
        this.loops = new LinkedList<>();
        this.totalGain = "";
    }

    public GroupOfLoops(List<Path> loops) {
        this.loops = new LinkedList<>();
        this.totalGain = "";
        loops.forEach(loop -> this.addLoop(loop));
    }

    public void addLoop(Path loop) {
        loops.add(loop);
        if (!totalGain.isEmpty())
            totalGain += Path.DELIMITER;
        totalGain += "(" + loop.getGain() + ")";
    }

    public int size() {
        return loops.size();
    }

}