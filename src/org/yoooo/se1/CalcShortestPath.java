package org.yoooo.se1;

import java.util.Set;

public class CalcShortestPath {
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
