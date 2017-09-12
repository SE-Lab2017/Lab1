package org.yoooo.se1;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Application {
    private static final String HELP = String.join(System.lineSeparator(),
            "Usage:",
            " program input.txt");
    private static final String COMMAND_HELP = String.join(System.lineSeparator(),
            "help, h  show this help",
            "show-graph, sg filename  show graph from input file and save to filename.png",
            "shortest-path, sp source sink  calculate shortest path from source to sink");
    private Graph<String, Integer> mGraph;

    /**
     * Entry point for entire application. Should only be called from main.
     *
     * @param args command line arguments
     */
    public void run(String[] args) {
        if (args.length != 1) {
            System.out.println(HELP);
            return;
        }
        String input;
        try {
            input = readFile(args[0]);
        } catch (IOException e) {
            System.out.println("IO error while reading input file: " + args[0]);
            return;
        }
        input = convertInputFileContent(input);
        mGraph = stringToGraph(input);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.next();
            switch (command) {
                case "h":
                case "help":
                    System.out.println(COMMAND_HELP);
                    break;
                case "sg":
                case "show-graph":
                    Main.showDirectedGraph(getGraph(), scanner.next());
                    break;
                case "sp":
                case "shortest-path":
                    System.out.println(Main.calcShortestPath(scanner.next(), scanner.next()));
                    break;
            }
        }
    }

    public Graph<String, Integer> getGraph() {
        return mGraph;
    }

    private static Application sInstance = new Application();

    public static Application getInstance() {
        return sInstance;
    }

    private Application() {
    }

    private Graph<String, Integer> stringToGraph(String string) {
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
            prev = vertex;
        }
        return graph;
    }

    private String readFile(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        Reader reader = new FileReader(path);
        char[] buffer = new char[0x10000];
        int count;
        while ((count = reader.read(buffer)) != -1) {
            builder.append(buffer, 0, count);
        }
        return builder.toString();
    }

    private String convertInputFileContent(String input) {
        StringBuilder builder = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {
                    builder.append(Character.toLowerCase(ch));
                } else {
                    builder.append(ch);
                }
            } else {
                builder.append(' ');
            }
        }
        return builder.toString();
    }
}
