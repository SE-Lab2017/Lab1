package org.yoooo.se1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Random;
import java.util.Scanner;

public class Application {
    private static final String HELP = String.join(System.lineSeparator(),
            "Usage:",
            " program input.txt");
    private static final String COMMAND_HELP = String.join(System.lineSeparator(),
            "help, h  show this help",
            "show-graph, sg filename  show graph from input file and save to filename.png",
            "query-bridge, qb word1 word2  query bridge words from word1 to word2",
            "generate-text, gt  generate new text with next line of input",
            "shortest-path, sp source sink  calculate shortest path from source to sink",
            "random-walk, rw filename  walk randomly and store result to filename.txt");
    private Graph<String, Integer> mGraph;
    private Random mRandom = new Random(System.currentTimeMillis());

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
            System.err.println("IO error while reading input file: " + args[0]);
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
                case "qb":
                case "query-bridge":
                    System.out.println(Main.queryBridgeWords(scanner.next(), scanner.next()));
                    break;
                case "gt":
                case "generate-text":
                    scanner.nextLine();
                    System.out.println(Main.generateNewText(scanner.nextLine()));
                    break;
                case "sp":
                case "shortest-path":
                    System.out.println(Main.calcShortestPath(scanner.next(), scanner.next()));
                    break;
                case "rw":
                case "random-walk":
                    String path = scanner.next() + ".txt";
                    try {
                        writeFile(path, Main.randomWalk());
                    } catch (IOException e) {
                        System.err.println("IO error while writing file: " + path);
                    }
                    break;
            }
        }
    }

    public Graph<String, Integer> getGraph() {
        return mGraph;
    }

    public Random getRandom() {
        return mRandom;
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
        try (Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8")) {
            char[] buffer = new char[0x10000];
            int count;
            while ((count = reader.read(buffer)) != -1) {
                builder.append(buffer, 0, count);
            }
            return builder.toString();
        }
    }

    private void writeFile(String path, String content) throws IOException {
        try {
            byte[] bytes = content.getBytes("UTF-8");
            Files.write(FileSystems.getDefault().getPath(path), bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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