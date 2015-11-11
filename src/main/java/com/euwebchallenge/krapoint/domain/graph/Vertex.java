package com.euwebchallenge.krapoint.domain.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Vertex Class
 *
 * @version 11/7/15
 */
public class Vertex implements Comparable<Vertex> {
      public final String name;
      public int dist = Integer.MAX_VALUE;
      public Vertex previous = null;
      public final Map<Vertex, Integer> neighbours = new HashMap<>();

      public Vertex(String name) {
         this.name = name;
      }

      public int getEst() {
          return dist;
      }

      public int compareTo(Vertex other) {
         return Integer.compare(dist, other.dist);
      }
   }
