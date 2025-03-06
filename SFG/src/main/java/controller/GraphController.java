package controller;

import model.dtos.GraphDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/graph")
public class GraphController {
    @PostMapping(value = "/solve")
    public ResponseEntity<?> solve(@RequestBody GraphDto graph) {

        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
