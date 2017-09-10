package org.yoooo.se1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of DirectedGraph and WeightedGraph using adjacency list.
 *
 * @param <V> the graph vertex type
 * @param <E> the graph edge type
 */
public class SimpleDirectedWeightGraph<V, E> implements DirectedGraph<V, E>, WeightedGraph<V, E> {
    private Map<V, Set<E>> mOutgoingEdges = new HashMap<>();
    private Map<E, V> mSourceVertexMap = new HashMap<>();
    private Map<E, V> mTargetVertexMap = new HashMap<>();

    /**
     * @see DirectedGraph#outgoingEdgesOf(Object)
     */
    @Override
    public Set<E> outgoingEdgesOf(V v) {
        return null;
    }

    @Override
    public void setEdgeWeight(E e, double weight) {

    }

    @Override
    public E getEdge(V source, V target) {
        return null;
    }

    @Override
    public E addEdge(V source, V target) {
        return null;
    }

    @Override
    public boolean addVertex(V v) {
        return false;
    }

    @Override
    public V getEdgeSource(E e) {
        return null;
    }

    @Override
    public V getEdgeTarget(E e) {
        return null;
    }

    @Override
    public double getEdgeWeight(E e) {
        return 0;
    }
}
