package org.yoooo.se1;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Set;

public final class ShowDirectedGraph {
    /**
     * Converts a graph to dot file.
     *
     * @param graph graph to convert
     * @param coloredVertex vertices to color
     * @param coloredEdge edges to color
     * @return content of dot file
     */
    private static String getDotContent(final Graph<String, Integer> graph, final Set<String> coloredVertex, final Set<Integer> coloredEdge) {
        StringBuilder dotContent = new StringBuilder();
        dotContent.append(String.format("digraph {%n"));
        Set<String> vertexSet = graph.vertexSet();
        for (String currentVertex : vertexSet) {
            Set<Integer> outgoingEdges = graph.outgoingEdgesOf(currentVertex);
            for (Integer currentEdge : outgoingEdges) {
                dotContent.append(String.format("\t\"%s\" -> \"%s\"", currentVertex, graph.getEdgeTarget(currentEdge)));
                dotContent.append(String.format(" [label = \"%.0f\"]", graph.getEdgeWeight(currentEdge)));
                if (coloredEdge != null && coloredEdge.contains(currentEdge)) {
                    dotContent.append(" [style = bold, color = dodgerblue]");
                }
                dotContent.append(String.format("%n"));
            }
        }
        if (coloredVertex != null) {
            for (String currentVertex : coloredVertex) {
                dotContent.append(String.format("\t%s [style = filled, fillcolor = greenyellow]%n", currentVertex));
            }
        }
        dotContent.append(String.format("}%n"));
        return dotContent.toString();
    }

    /**
     * Saves a graph with vertices/edges colored to filename.png and open it.
     *
     * @param graph graph to show
     * @param filename filename of generated picture(without extension)
     * @param coloredVertex vertices to color
     * @param coloredEdge edges to color
     * @exception RuntimeException throw when file operation failed
     */
    public static void showDirectedGraph(final Graph<String, Integer> graph, final String filename,
                                         final Set<String> coloredVertex, final Set<Integer> coloredEdge) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filename + ".dot"), "UTF-8")) {
            try {
                writer.write(getDotContent(graph, coloredVertex, coloredEdge));
            } catch (IOException e) {
                throw new NoticeException("fail to write " + filename + ".dot");
            }
        } catch (IOException e) {
            throw new NoticeException("fail to open " + filename + ".dot");
        }
        try {
            try {
                Runtime.getRuntime().exec("dot -Tpng -o" + filename + ".png " + filename + ".dot").waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Desktop.getDesktop().open(new File(filename + ".png"));
        } catch (IOException e) {
            throw new NoticeException("fail to write " + filename + ".png");
        }
    }
    /**
     * Saves a graph to filename.png and open it.
     *
     * @param graph graph to show
     * @param filename filename of generated picture(without extension)
     * @exception RuntimeException throw when file operation failed
     */
    public static void showDirectedGraph(final Graph<String, Integer> graph, final String filename) {
        showDirectedGraph(graph, filename, null, null);
    }
    private ShowDirectedGraph() {
    }
}
