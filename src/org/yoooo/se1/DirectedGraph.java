package org.yoooo.se1;

import java.util.Set;

/**
 * A graph whose all edges are directed.
 *
 * @param <V> the graph vertex type
 * @param <E> the graph edge type
 */
public interface DirectedGraph<V, E> extends Graph<V, E> {
    /**
     * Returns a set of all edges outgoing from the vertex.
     *
     * @param v the vertex
     * @return the set of edges
     */
    Set<E> outgoingEdgesOf(V v);
}
