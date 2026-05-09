/**
 * Main class — entry point of the Graph Traversal and Representation System.
 *
 * This program:
 * 1. Creates a small graph (10 vertices) and prints its structure,
 *    then shows BFS and DFS traversal order.
 * 2. Runs experiments on small, medium, and large graphs,
 *    measuring BFS and DFS execution times with System.nanoTime().
 * 3. Prints a performance comparison table.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("  Graph Traversal and Representation System  ");
        System.out.println("==============================================\n");

        // -------------------------------------------------------
        // PART 1: Small graph — show structure and traversal order
        // -------------------------------------------------------
        System.out.println(">>> SMALL GRAPH (10 vertices) — Structure and Traversals\n");

        Graph smallGraph = new Graph();

        // Add 10 vertices (IDs 0-9)
        for (int i = 0; i < 10; i++) {
            smallGraph.addVertex(new Vertex(i));
        }

        // Add directed edges
        smallGraph.addEdge(0, 1);
        smallGraph.addEdge(0, 2);
        smallGraph.addEdge(1, 3);
        smallGraph.addEdge(1, 4);
        smallGraph.addEdge(2, 5);
        smallGraph.addEdge(2, 6);
        smallGraph.addEdge(3, 7);
        smallGraph.addEdge(4, 8);
        smallGraph.addEdge(5, 9);
        smallGraph.addEdge(6, 7);
        smallGraph.addEdge(7, 8);
        smallGraph.addEdge(8, 9);
        smallGraph.addEdge(9, 0); // cycle back to 0

        // Print the graph structure
        smallGraph.printGraph();

        System.out.println();

        // Run and time BFS on small graph
        long bfsStart = System.nanoTime();
        smallGraph.bfs(0);
        long bfsEnd = System.nanoTime();

        // Run and time DFS on small graph
        long dfsStart = System.nanoTime();
        smallGraph.dfs(0);
        long dfsEnd = System.nanoTime();

        System.out.println("\nBFS execution time: " + (bfsEnd - bfsStart) + " ns");
        System.out.println("DFS execution time: " + (dfsEnd - dfsStart) + " ns");

        // -------------------------------------------------------
        // PART 2: Performance experiments on 3 graph sizes
        // -------------------------------------------------------
        System.out.println("\n>>> PERFORMANCE EXPERIMENTS (Small / Medium / Large)\n");

        Experiment experiment = new Experiment();
        experiment.runMultipleTests();
        experiment.printResults();
    }
}