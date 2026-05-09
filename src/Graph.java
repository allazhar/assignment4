import java.util.*;

/**
 * Graph class represents a directed graph using an adjacency list.
 *
 * Adjacency list: a HashMap where each key is a Vertex,
 * and the value is a list of neighboring vertices (those reachable by one edge).
 *
 * This representation is memory-efficient for sparse graphs: O(V + E) space.
 */
public class Graph {

    // adjacencyList maps each vertex to its list of neighbors
    private Map<Vertex, List<Vertex>> adjacencyList;

    // vertexMap allows quick lookup of Vertex objects by their integer ID
    private Map<Integer, Vertex> vertexMap;

    /**
     * Constructor initializes empty graph data structures.
     */
    public Graph() {
        adjacencyList = new LinkedHashMap<>();
        vertexMap = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     * If the vertex already exists (by ID), it is not added again.
     * @param v the Vertex to add
     */
    public void addVertex(Vertex v) {
        if (!vertexMap.containsKey(v.getId())) {
            adjacencyList.put(v, new ArrayList<>());
            vertexMap.put(v.getId(), v);
        }
    }

    /**
     * Adds a directed edge from vertex with ID 'from' to vertex with ID 'to'.
     * Both vertices must already be added to the graph.
     * @param from ID of the source vertex
     * @param to   ID of the destination vertex
     */
    public void addEdge(int from, int to) {
        Vertex source = vertexMap.get(from);
        Vertex destination = vertexMap.get(to);

        if (source == null || destination == null) {
            System.out.println("Error: vertex not found for edge " + from + " -> " + to);
            return;
        }

        adjacencyList.get(source).add(destination);
    }

    /**
     * Prints the adjacency list of the entire graph.
     * Each line shows a vertex and all its neighbors.
     */
    public void printGraph() {
        System.out.println("Graph adjacency list:");
        for (Map.Entry<Vertex, List<Vertex>> entry : adjacencyList.entrySet()) {
            System.out.print("  " + entry.getKey() + " : ");
            List<Vertex> neighbors = entry.getValue();
            if (neighbors.isEmpty()) {
                System.out.print("(no neighbors)");
            } else {
                for (int i = 0; i < neighbors.size(); i++) {
                    System.out.print(neighbors.get(i));
                    if (i < neighbors.size() - 1) System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Breadth-First Search (BFS) traversal starting from vertex with given ID.
     *
     * How BFS works:
     * 1. Start at the given vertex, mark it visited, add to queue.
     * 2. While queue is not empty:
     *    a. Dequeue a vertex and process (print) it.
     *    b. For each unvisited neighbor, mark visited and enqueue it.
     * BFS explores level by level (closest vertices first).
     *
     * Time complexity: O(V + E)
     *
     * @param start ID of the starting vertex
     */
    public void bfs(int start) {
        Vertex startVertex = vertexMap.get(start);
        if (startVertex == null) {
            System.out.println("BFS: start vertex " + start + " not found.");
            return;
        }

        Set<Integer> visited = new HashSet<>();   // tracks visited vertex IDs
        Queue<Vertex> queue = new LinkedList<>();  // FIFO queue for BFS

        // Initialize: visit the start vertex
        visited.add(startVertex.getId());
        queue.add(startVertex);

        System.out.print("BFS from " + start + ": ");

        while (!queue.isEmpty()) {
            // Dequeue the front vertex
            Vertex current = queue.poll();
            System.out.print(current.getId() + " ");

            // Enqueue all unvisited neighbors
            List<Vertex> neighbors = adjacencyList.get(current);
            if (neighbors != null) {
                for (Vertex neighbor : neighbors) {
                    if (!visited.contains(neighbor.getId())) {
                        visited.add(neighbor.getId());
                        queue.add(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }

    /**
     * Depth-First Search (DFS) traversal starting from vertex with given ID.
     *
     * How DFS works:
     * 1. Start at the given vertex, mark it visited, push to stack.
     * 2. While stack is not empty:
     *    a. Pop a vertex and process (print) it.
     *    b. For each unvisited neighbor, mark visited and push to stack.
     * DFS explores as deep as possible before backtracking.
     *
     * Time complexity: O(V + E)
     *
     * @param start ID of the starting vertex
     */
    public void dfs(int start) {
        Vertex startVertex = vertexMap.get(start);
        if (startVertex == null) {
            System.out.println("DFS: start vertex " + start + " not found.");
            return;
        }

        Set<Integer> visited = new HashSet<>();  // tracks visited vertex IDs
        Deque<Vertex> stack = new ArrayDeque<>(); // LIFO stack for DFS

        // Initialize: push the start vertex
        visited.add(startVertex.getId());
        stack.push(startVertex);

        System.out.print("DFS from " + start + ": ");

        while (!stack.isEmpty()) {
            // Pop the top vertex
            Vertex current = stack.pop();
            System.out.print(current.getId() + " ");

            // Push all unvisited neighbors (in reverse order to maintain natural order)
            List<Vertex> neighbors = adjacencyList.get(current);
            if (neighbors != null) {
                List<Vertex> reversed = new ArrayList<>(neighbors);
                Collections.reverse(reversed);
                for (Vertex neighbor : reversed) {
                    if (!visited.contains(neighbor.getId())) {
                        visited.add(neighbor.getId());
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }

    /**
     * Returns the number of vertices in the graph.
     * @return vertex count
     */
    public int getVertexCount() {
        return adjacencyList.size();
    }

    /**
     * Returns the number of edges in the graph.
     * @return edge count
     */
    public int getEdgeCount() {
        int count = 0;
        for (List<Vertex> neighbors : adjacencyList.values()) {
            count += neighbors.size();
        }
        return count;
    }
}