package backend.SFG;

import model.graph.Graph;
import model.graph.impl.Edge;
import model.graph.impl.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import service.ForwardPathsFinder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class ForwardPathsFinderTest {

    private Graph mockGraph;
    private ForwardPathsFinder forwardPathsFinder;

    @BeforeEach
    void setUp() {
        mockGraph = mock(Graph.class);
        when(mockGraph.numberOfNodes()).thenReturn(4);
        when(mockGraph.getInputNode()).thenReturn(0);
        when(mockGraph.getOutputNode()).thenReturn(3);

        Edge edge1 = new Edge(0, "G1", 5.0);
        Edge edge2 = new Edge(1, "G2", 3.0);
        Edge edge3 = new Edge(2, "G3", 2.0);
        Edge edge4 = new Edge(0, "G3", 10.0);

        when(mockGraph.getEdges(0)).thenReturn(List.of(edge1, edge4));
        when(mockGraph.getEdges(1)).thenReturn(List.of(edge2));
        when(mockGraph.getEdges(2)).thenReturn(List.of(edge3));
        when(mockGraph.getEdges(3)).thenReturn(List.of());

        forwardPathsFinder = new ForwardPathsFinder(mockGraph);
    }

    @Test
    void testForwardPathsFinderInitializesCorrectly() {
        assertNotNull(forwardPathsFinder.getAllForwardPaths(), "Forward paths list should not be null");
    }

    @Test
    void testFindForwardPaths() {
        List<Path> paths = forwardPathsFinder.getAllForwardPaths();
        assertEquals(2, paths.size(), "There should be 2 forward paths");

        Path path1 = paths.get(0);
        Path path2 = paths.get(1);

        assertEquals(3, path1.getEdges().size(), "First path should have 3 edges");
        assertEquals(1, path2.getEdges().size(), "Second path should have 1 edge");
    }

    @Test
    void testEmptyGraph() {
        Graph emptyGraph = mock(Graph.class);
        when(emptyGraph.numberOfNodes()).thenReturn(0);
        when(emptyGraph.getInputNode()).thenReturn(0);
        when(emptyGraph.getOutputNode()).thenReturn(0);
        when(emptyGraph.getEdges(anyInt())).thenReturn(List.of());

        ForwardPathsFinder emptyPathsFinder = new ForwardPathsFinder(emptyGraph);
        assertEquals(1, emptyPathsFinder.getAllForwardPaths().size(), "Empty graph should have one path (trivial path)");
    }
}
