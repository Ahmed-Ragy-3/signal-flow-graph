package backend.SFG.model.graph.impl;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge {
    private int toNode;
    private String gain;
}