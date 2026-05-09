/**
 * Edge class represents a directed connection between two vertices in the graph.
 * Each edge has a source vertex and a destination vertex.
 */
public class Edge {

    private Vertex source;
    private Vertex destination;

    /**
     * Constructor to create a directed edge from source to destination.
     * @param source      the starting vertex
     * @param destination the ending vertex
     */
    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
    }

    /**
     * Returns the source (starting) vertex of this edge.
     * @return source vertex
     */
    public Vertex getSource() {
        return source;
    }

    /**
     * Returns the destination (ending) vertex of this edge.
     * @return destination vertex
     */
    public Vertex getDestination() {
        return destination;
    }

    /**
     * Returns a string representation of this edge.
     * @return string in format "V(source) -> V(destination)"
     */
    @Override
    public String toString() {
        return source.toString() + " -> " + destination.toString();
    }
}