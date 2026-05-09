/**
 * Vertex class represents a node/vertex in the graph.
 * Each vertex has a unique integer identifier.
 */
public class Vertex {

    private int id;

    /**
     * Constructor to create a vertex with a given ID.
     * @param id unique identifier for this vertex
     */
    public Vertex(int id) {
        this.id = id;
    }

    /**
     * Returns the ID of this vertex.
     * @return vertex ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns a string representation of this vertex.
     * @return string in format "V(id)"
     */
    @Override
    public String toString() {
        return "V(" + id + ")";
    }
}