package ru.itmo.tpo.lab1.part2;

import java.util.*;

public class GraphAlgorithms {
    public GraphAlgorithms() { }

    /**
     * Returns list of nodes numbers in visiting order
     */
    public static List<Integer> bfs(Map<Integer, List<Integer>> graph, int startNode) {
        if (graph == null || graph.isEmpty() ||
            startNode < 0 || startNode >= graph.size() ||
            graph.get(startNode) == null
        ) {
            return Collections.emptyList();
        }

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> visited = new ArrayList<>();

        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            var neighbors = graph.get(node);
            neighbors.sort(Integer::compareTo);
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return visited;
    }
}
