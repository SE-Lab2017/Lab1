package org.yoooo.se1;

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
}
