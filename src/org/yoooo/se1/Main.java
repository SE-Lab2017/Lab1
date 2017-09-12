package org.yoooo.se1;

public class Main {
    public static void main(String[] args) {
        Application.getInstance().run(args);
    }

    public static void showDirectedGraph(Graph<String, Integer> graph, String path) {
        ShowDirectedGraph.showDirectedGraph(graph, path);
    }

    public static String calcShortestPath(String source, String sink) {
        return CalcShortestPath.calcShortestPath(source, sink);
    }
}
