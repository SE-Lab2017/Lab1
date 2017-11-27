package org.yoooo.se1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class CalcShortestPath {
    /**
     * Returns the shortest path from source to sink, represented by vertices joined by "->",
     * or "Unreachable" sink is not reachable from source.
     *
     * @param source source vertex
     * @param sink   sink vertex
     * @return shortest path represented by vertices joined by "->"
     */
    public static String calcShortestPath(final String source, final String sink) {
        Set<String> vertices = Application.getInstance().getGraph().vertexSet();
        if (!vertices.contains(source)) {
            return source + " does not exist";
        }
        if (!vertices.contains(sink)) {
            return sink + " does not exist";
        }
        if (source.equals(sink)) {
            new ShowDirectedGraph(Application.getInstance().getGraph()).showDirectedGraph(
                    UUID.randomUUID().toString(), Collections.singleton(source),
                    Collections.emptySet());
            return source;
        }
        DijkstraShortestPath<String, Integer> algorithm = new DijkstraShortestPath<>(
                Application.getInstance().getGraph());
        GraphPath<String, Integer> path = algorithm.getPath(source, sink);
        if (path == null) {
            return "Unreachable";
        }
        new ShowDirectedGraph(Application.getInstance().getGraph()).showDirectedGraph(
                UUID.randomUUID().toString(), new HashSet<>(path.getVertexList()),
                new HashSet<>(path.getEdgeList()));
        return String.join("->", path.getVertexList());
    }

    /**
     * Returns the shortest path from source to all other vertices.
     *
     * @param source source vertex
     * @return shortest path to other vertices
     */
    public static SingleSourcePaths<String, Integer> calcShortestPath(final String source) {
        Set<String> vertices = Application.getInstance().getGraph().vertexSet();
        if (!vertices.contains(source)) {
            throw new IllegalArgumentException(source + " does not exist");
        }
        DijkstraShortestPath<String, Integer> algorithm = new DijkstraShortestPath<>(
                Application.getInstance().getGraph());
        return algorithm.getPaths(source);
    }
    private CalcShortestPath() {
    }
}
