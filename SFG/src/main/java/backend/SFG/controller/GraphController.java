package backend.SFG.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.SFG.model.dtos.GraphDto;
import backend.SFG.model.dtos.SolutionDto;
import backend.SFG.service.GraphService;

@RestController
@CrossOrigin("*")
@RequestMapping("/graph")
public class GraphController {
    @Autowired
    private GraphService graphService;

    @PostMapping(value = "/solve")
    public ResponseEntity<?> solve(@RequestBody GraphDto graph) {
        try {
            SolutionDto solution = graphService.solve(graph);
            System.out.println("enter 1");
            return ResponseEntity.ok(solution);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
