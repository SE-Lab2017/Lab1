package org.yoooo.se1;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * A implementation of Dijkstra's shortest path algorithm.
 *
 * @param <V> the graph vertex type
 * @param <E> the graph edge type
 */
public class DijkstraShortestPath<V, E> {
    private Graph<V, E> mGraph;

    /**
     * Constructs a new instance of the algorithm for a given graph.
     *
     * @param graph the graph
     */
    public DijkstraShortestPath(Graph<V, E> graph) {
        mGraph = graph;
    }

    /**
     * Get a shortest path from a source vertex to a sink vertex.
     *
     * @param source source vertex
     * @param sink sink vertex
     * @return a shortest path or null if no path exists
     */
    public GraphPath<V, E> getPath(V source, V sink) {
        Set<V> vertices = mGraph.vertexSet();
        if (!vertices.contains(source) || !vertices.contains(sink) || source.equals(sink)) {
            return null;
        }
        PriorityQueue<Node> queue = new PriorityQueue<>(
                (Comparator<Node>) (lhs, rhs) -> -Double.compare(lhs.distance, rhs.distance));
        Map<V, Double> distanceMap = new HashMap<>();
        Map<V, V> predecessorMap = new HashMap<>();
        for (V vertex : vertices) {
            distanceMap.put(vertex, Double.POSITIVE_INFINITY);
            queue.add(new Node(Double.POSITIVE_INFINITY, vertex));
        }
        distanceMap.put(source, 0.0);
        queue.add(new Node(0.0, source));
        while (!queue.isEmpty()) {
            V u = queue.peek().vertex;
            double dist = queue.peek().distance;
            queue.poll();
            if (dist < distanceMap.get(u)) {
                continue;
            }
            for (E edge : mGraph.outgoingEdgesOf(u)) {
                V v = mGraph.getEdgeTarget(edge);
                double alt = distanceMap.get(u) + mGraph.getEdgeWeight(edge);
                if (alt < distanceMap.get(v)) {
                    distanceMap.put(v, alt);
                    predecessorMap.put(v, u);
                    queue.add(new Node(alt, v));
                }
            }
        }
        Stack<V> stack = new Stack<>();
        V vertex = sink;
        stack.push(vertex);
        while (!vertex.equals(source)) {
            if (!predecessorMap.containsKey(vertex)) {
                return null;
            }
            vertex = predecessorMap.get(vertex);
            stack.push(vertex);
        }
        GraphPath<V, E> path = new GraphPath<>(mGraph);
        vertex = stack.pop();
        while (!stack.isEmpty()) {
            V next = stack.pop();
            path.addEdge(mGraph.getEdge(vertex, next));
            vertex = next;
        }
        return path;
    }

    private class Node {
        private double distance;
        private V vertex;

        private Node(double distance, V vertex) {
            this.distance = distance;
            this.vertex = vertex;
        }
    }
}
