package model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EdgeDto {
    private String source;
    private String target;
    @JsonProperty("label")
    private String gain;
}
