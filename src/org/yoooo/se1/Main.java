package org.yoooo.se1;

public class Main {
    public static void main(String[] args) {
        Application.getInstance().run(args);
    }

    public static void showDirectedGraph(Graph<String, Integer> graph, String path) {
        ShowDirectedGraph.showDirectedGraph(graph, path);
    }
}
