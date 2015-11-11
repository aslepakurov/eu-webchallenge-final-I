package com.euwebchallenge.krapoint.domain.graph;

import com.euwebchallenge.krapoint.domain.Path;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Graph Class
 *
 * @version 11/7/15
 */
public class Graph {
    private final Map<String, Vertex> graph;

    public Graph(Path[] paths) {
        graph = new HashMap<>(paths.length);

        for (Path path : paths) {
            if (!graph.containsKey(path.getPointA())) graph.put(path.getPointA(), new Vertex(path.getPointA()));
            if (!graph.containsKey(path.getPointB())) graph.put(path.getPointB(), new Vertex(path.getPointB()));
        }

        for (Path path : paths) {
            graph.get(path.getPointA()).neighbours.put(graph.get(path.getPointB()), path.getEstimate());
            graph.get(path.getPointB()).neighbours.put(graph.get(path.getPointB()), path.getEstimate());
        }
    }


    public void dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            return;
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> q = new TreeSet<>();

        for (Vertex v : graph.values()) {
            v.previous = v == source ? source : null;
            v.dist = v == source ? 0 : Integer.MAX_VALUE;
            q.add(v);
        }

        dijkstra(q);
    }

    private void dijkstra(final NavigableSet<Vertex> q) {
        Vertex u, v;
        while (!q.isEmpty()) {

            u = q.pollFirst();
            if (u.dist == Integer.MAX_VALUE)
                break;

            for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey();

                final int alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) {
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }

    public int printPath(String endName) {
        if (!graph.containsKey(endName)) {
            return -1;
        }
        return graph.get(endName).getEst();
    }

}