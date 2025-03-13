package model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GraphDto {
    private String inputNode;
    private String outputNode;
    private List<String> nodes;
    private List<EdgeDto> edges;
}