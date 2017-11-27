package org.yoooo.se1;

public final class Main {
    public static void main(final String[] args) {
        try {
            Application.getInstance().run(args);
        } catch (NoticeException e) {
            System.err.println(e.getMessage());
        }
    }
    private Main() {
    }
    public static void showDirectedGraph(final Graph<String, Integer> graph, final String path) {
        new ShowDirectedGraph(graph).showDirectedGraph(path);
    }

    public static String queryBridgeWords(final String word1, final String word2) {
        return new QueryBridgeWords().queryBridgeWords(word1, word2);
    }

    public static String generateNewText(final String inputText) {
        return GenerateNewText.generateNewText(inputText);
    }

    public static String calcShortestPath(final String source, final String sink) {
        return CalcShortestPath.calcShortestPath(source, sink);
    }

    public static String randomWalk() {
        return RandomWalk.randomWalk();
    }
}
