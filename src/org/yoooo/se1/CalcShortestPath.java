package org.yoooo.se1;

import java.util.Set;

public class CalcShortestPath {
    /**
     * Returns the shortest path from source to sink, represented by vertices joined by "->",
     * or "Unreachable" sink is not reachable from source.
     *
     * @param source source vertex
     * @param sink sink vertex
     * @return shortest path represented by vertices joined by "->"
     */
    public static String calcShortestPath(String source, String sink) {
        Set<String> vertices = Application.getInstance().getGraph().vertexSet();
        if (!vertices.contains(source)) {
            return source + " does not exist";
        }
        if (!vertices.contains(sink)) {
            return sink + " does not exist";
        }
        DijkstraShortestPath<String, Integer> algorithm = new DijkstraShortestPath<>(
                Application.getInstance().getGraph());
        GraphPath<String, Integer> path = algorithm.getPath(source, sink);
        if (path == null) {
            return "Unreachable";
        }
        return String.join("->", path.getVertexList());
    }
}
