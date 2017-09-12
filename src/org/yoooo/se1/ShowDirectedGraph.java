package org.yoooo.se1;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class ShowDirectedGraph {
    /**
     * Saves a graph to filename.png
     *
     * @param graph graph to show
     * @param filename filename of generated picture(without extension)
     * @exception RuntimeException throw when file operation failed
     */
    public static void showDirectedGraph(Graph<String, Integer> graph, String filename) throws RuntimeException{
        StringBuilder dotContent = new StringBuilder();
        dotContent.append(String.format("digraph {%n"));
        Set<String> vertexSet = graph.vertexSet();
        for (String currentVertex : vertexSet) {
            Set<Integer> outgoingEdges = graph.outgoingEdgesOf(currentVertex);
            for (Integer currentEdge : outgoingEdges) {
                dotContent.append(String.format("\t\"%s\" -> \"%s\"", currentVertex, graph.getEdgeTarget(currentEdge)));
                dotContent.append(String.format(" [label = \"%.0f\"]", graph.getEdgeWeight(currentEdge)));
                dotContent.append(String.format("%n"));
            }
        }
        dotContent.append(String.format("}%n"));
        try (FileWriter writer = new FileWriter(filename + ".dot")) {
            try {
                writer.write(dotContent.toString());
            } catch (IOException e) {
                throw new RuntimeException("fail to write " + filename + ".dot");
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to open " + filename + ".dot");
        }
        try {
            try {
                Runtime.getRuntime().exec("dot -Tpng -o" + filename + ".png " + filename + ".dot").waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Desktop.getDesktop().open(new File(filename + ".png"));
        } catch (IOException e) {
            throw new RuntimeException("file to write " + filename + ".png");
        }
    }
}
