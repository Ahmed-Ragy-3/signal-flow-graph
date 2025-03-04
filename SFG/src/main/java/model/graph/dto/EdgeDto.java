package model.graph.dto;

import lombok.Data;

@Data
public class EdgeDto {
    private String source;
    private String target;
    private String gainSymbol;
    private int gain;
}
