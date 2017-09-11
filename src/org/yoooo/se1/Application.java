package org.yoooo.se1;

import java.util.Scanner;

public class Application {
    public void run(String[] args) {

    }

    public SimpleDirectedWeightGraph<String, Integer> getGraph() {
        return null;
    }

    private static Application sInstance = new Application();

    public static Application getInstance() {
        return sInstance;
    }

    private Application() {
    }

    /**
     * Parses string and generates graph from the string as specified in feature 1. The string
     * should only contain lowercase letters and spaces.
     *
     * @param string string from input file
     * @return graph created from string
     */
    private SimpleDirectedWeightGraph<String, Integer> stringToGraph(String string) {
        Scanner scanner = new Scanner(string);
        SimpleDirectedWeightGraph<String, Integer> graph = new SimpleDirectedWeightGraph<>(
                new EdgeFactory<String, Integer>() {
                    private int mNext;

                    @Override
                    public Integer createEdge(String source, String target) {
                        return mNext++;
                    }
                });
        if (!scanner.hasNext()) {
            return graph;
        }
        String prev = scanner.next();
        graph.addVertex(prev);
        while (scanner.hasNext()) {
            String vertex = scanner.next();
            graph.addVertex(vertex);
            Integer edge = graph.getEdge(prev, vertex);
            if (edge != null) {
                graph.setEdgeWeight(edge, graph.getEdgeWeight(edge) + 1);
            } else {
                graph.addEdge(prev, vertex);
            }
        }
        return graph;
    }
}
