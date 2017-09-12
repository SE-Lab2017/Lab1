package org.yoooo.se1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomWalk {
    public static String randomWalk() {
        Graph<String, Integer> graph = Application.getInstance().getGraph();
        String[] vertices = graph.vertexSet().toArray(new String[0]);
        List<String> result = new ArrayList<>();
        Random random = Application.getInstance().getRandom();
        result.add(vertices[random.nextInt(vertices.length)]);
        Set<Integer> visited = new HashSet<>();
        while (true) {
            Integer[] edges = graph.outgoingEdgesOf(result.get(result.size() - 1))
                    .toArray(new Integer[0]);
            if (edges.length == 0) {
                break;
            }
            Integer edge = edges[random.nextInt(edges.length)];
            if (visited.contains(edge)) {
                break;
            }
            visited.add(edge);
            result.add(graph.getEdgeTarget(edge));
        }
        return String.join(" ", result);
    }
}
