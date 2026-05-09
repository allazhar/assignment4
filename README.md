Assignment 4: Graph Traversal and Representation System
A. Project Overview
This project implements a graph traversal system in Java. A graph is a data structure made up of vertices (nodes) and edges (connections between nodes). Graphs are used in many real-world scenarios: social networks, maps, scheduling systems, and more.
This project implements:

Adjacency list representation of a directed graph
Breadth-First Search (BFS) traversal
Depth-First Search (DFS) traversal
Performance experiments on graphs of different sizes


B. Class Descriptions
Vertex
Represents a single node in the graph. Each vertex has a unique integer id.
Edge
Represents a directed connection from a source vertex to a destination vertex.
Graph
The core class. Stores the graph using an adjacency list — a HashMap where each key is a Vertex and its value is a List<Vertex> of neighbors.
Why adjacency list?

Space-efficient for sparse graphs: O(V + E)
Fast neighbor lookup: O(degree)
Easy to add vertices and edges

Experiment
Handles performance testing. Creates graphs of 3 sizes (10, 30, 100 vertices), runs BFS and DFS on each, measures execution time with System.nanoTime(), and prints a results table.
Main
Entry point. Demonstrates the small graph structure, traversal order, and then runs all experiments.

C. Algorithm Descriptions
Breadth-First Search (BFS)
How it works:

Start at the given vertex. Mark it visited. Add to a queue (FIFO).
While the queue is not empty:

Dequeue the front vertex and process it.
Add all unvisited neighbors to the queue and mark them visited.



BFS explores all vertices at distance 1, then distance 2, etc. — level by level.
Use cases:

Finding the shortest path in an unweighted graph
Web crawlers
Social network friend suggestions (closest connections first)

Time complexity: O(V + E)

Every vertex is visited once: O(V)
Every edge is checked once: O(E)


Depth-First Search (DFS)
How it works:

Start at the given vertex. Mark it visited. Push to a stack (LIFO).
While the stack is not empty:

Pop the top vertex and process it.
Push all unvisited neighbors to the stack and mark them visited.



DFS goes as deep as possible into one path before backtracking.
Use cases:

Detecting cycles in a graph
Topological sorting
Maze solving
Finding connected components

Time complexity: O(V + E)

Every vertex is visited once: O(V)
Every edge is examined once: O(E)


D. Experimental Results
Execution Time Comparison
Graph SizeVerticesEdgesBFS Time (ns)DFS Time (ns)Small (10 vertices)1025~641,314~1,664,434Medium (30 vertices)3089~2,836,958~2,582,863Large (100 vertices)100313~1,957,536~14,694,815

Note: Times in nanoseconds. Results may vary between runs due to JVM optimization.

Observations

Both BFS and DFS have the same theoretical complexity: O(V + E).
In practice, execution time grows as the number of vertices and edges increases.
BFS tends to be slightly slower in practice due to queue overhead; DFS uses a stack which is cache-friendlier.
For dense graphs, both algorithms become slower as E grows much larger than V.


E. Screenshots

Add screenshots of your console output here. Suggested sections:


Graph structure output (from printGraph())
BFS traversal order (small graph)
DFS traversal order (small graph)
Performance results table


F. Reflection
What I learned
Working on this project gave me a solid understanding of graph data structures and how different traversal strategies explore a graph in fundamentally different ways. The adjacency list representation was intuitive to implement and clearly shows the relationship between vertices and their neighbors.
BFS vs DFS
BFS is ideal when you need the shortest path or want to explore nodes closest to the source first. DFS is better suited for tasks like cycle detection or exploring all possible paths. The order in which nodes are visited differs significantly: BFS proceeds level-by-level while DFS dives as deep as possible before backtracking. Despite these differences, both have the same time and space complexity of O(V + E).
Challenges
The main challenge was ensuring that the visited set was correctly maintained across both algorithms to avoid revisiting nodes, especially in graphs with cycles. Using System.nanoTime() for performance measurement also required care — JVM warm-up effects can skew results for small graphs, so the measurements on larger graphs tend to be more reliable.
