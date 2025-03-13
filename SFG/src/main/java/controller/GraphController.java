package controller;

import model.dtos.GraphDto;
import model.dtos.SolutionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.GraphService;

@RestController
@CrossOrigin("*")
@RequestMapping("/graph")
public class GraphController {
    @Autowired
    private GraphService graphService;

    @PostMapping(value = "/solve")
    public ResponseEntity<?> solve(@RequestBody GraphDto graph) {

        try {
            SolutionDto solution =  graphService.solve(graph);
            return ResponseEntity.ok(solution);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
