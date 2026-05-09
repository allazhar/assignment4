/**
 * Experiment class handles graph traversal experiments and performance analysis.
 *
 * It runs BFS and DFS on graphs of different sizes, measures execution time
 * using System.nanoTime(), and prints a comparison table.
 */
public class Experiment {

    // Stores results for up to 3 graph sizes (small, medium, large)
    private String[] labels    = new String[3];
    private long[]   bfsTimes  = new long[3];
    private long[]   dfsTimes  = new long[3];
    private int[]    vertices  = new int[3];
    private int[]    edges     = new int[3];
    private int      resultCount = 0;

    /**
     * Runs both BFS and DFS traversals on the given graph,
     * measures their execution times in nanoseconds,
     * and stores the result for later printing.
     *
     * @param g     the Graph to traverse
     * @param label a label for this experiment (e.g., "Small (10 vertices)")
     */
    public void runTraversals(Graph g, String label) {
        System.out.println("\n--- " + label + " ---");
        System.out.println("Vertices: " + g.getVertexCount() + ", Edges: " + g.getEdgeCount());

        // --- BFS timing ---
        long bfsStart = System.nanoTime();
        g.bfs(0);
        long bfsEnd = System.nanoTime();
        long bfsTime = bfsEnd - bfsStart;

        // --- DFS timing ---
        long dfsStart = System.nanoTime();
        g.dfs(0);
        long dfsEnd = System.nanoTime();
        long dfsTime = dfsEnd - dfsStart;

        System.out.println("BFS time: " + bfsTime + " ns");
        System.out.println("DFS time: " + dfsTime + " ns");

        // Store result
        if (resultCount < 3) {
            labels[resultCount]   = label;
            bfsTimes[resultCount] = bfsTime;
            dfsTimes[resultCount] = dfsTime;
            vertices[resultCount] = g.getVertexCount();
            edges[resultCount]    = g.getEdgeCount();
            resultCount++;
        }
    }

    /**
     * Builds and runs experiments on three graph sizes:
     * small (10 vertices), medium (30 vertices), and large (100 vertices).
     *
     * Each graph is populated with edges following a pattern where
     * each vertex connects to the next few vertices (simulating a realistic graph).
     */
    public void runMultipleTests() {
        // Small graph: 10 vertices
        Graph small = buildGraph(10, 3);
        runTraversals(small, "Small (10 vertices)");

        // Medium graph: 30 vertices
        Graph medium = buildGraph(30, 3);
        runTraversals(medium, "Medium (30 vertices)");

        // Large graph: 100 vertices
        Graph large = buildGraph(100, 3);
        runTraversals(large, "Large (100 vertices)");
    }

    /**
     * Helper method to build a graph with a given number of vertices.
     * Each vertex i gets edges to the next 'degree' vertices (if they exist).
     * Also adds some cross-edges to make the graph more realistic.
     *
     * @param numVertices number of vertices to create
     * @param degree      number of forward neighbors per vertex
     * @return the constructed Graph
     */
    private Graph buildGraph(int numVertices, int degree) {
        Graph g = new Graph();

        // Add all vertices
        for (int i = 0; i < numVertices; i++) {
            g.addVertex(new Vertex(i));
        }

        // Add forward edges: vertex i -> i+1, i+2, ..., i+degree
        for (int i = 0; i < numVertices; i++) {
            for (int d = 1; d <= degree; d++) {
                if (i + d < numVertices) {
                    g.addEdge(i, i + d);
                }
            }
        }

        // Add some cross-edges (every 5th vertex connects to vertex 2 steps back)
        for (int i = 5; i < numVertices; i += 5) {
            g.addEdge(i, i - 2);
        }

        return g;
    }

    /**
     * Prints a formatted results table comparing BFS and DFS execution times
     * across all tested graph sizes.
     */
    public void printResults() {
        System.out.println("\n========================================");
        System.out.println("       PERFORMANCE RESULTS TABLE        ");
        System.out.println("========================================");
        System.out.printf("%-22s %-8s %-8s %-14s %-14s%n",
                "Graph Size", "V", "E", "BFS (ns)", "DFS (ns)");
        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < resultCount; i++) {
            System.out.printf("%-22s %-8d %-8d %-14d %-14d%n",
                    labels[i], vertices[i], edges[i], bfsTimes[i], dfsTimes[i]);
        }

        System.out.println("========================================");
        System.out.println("Both BFS and DFS have time complexity O(V + E).");
        System.out.println("Larger graphs take longer due to more vertices and edges.");
        System.out.println("========================================\n");
    }
}