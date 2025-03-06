package ru.itmo.tpo.lab1.part2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphBuilder {
    private final Map<Integer, List<Integer>> data;

    private GraphBuilder(int nodes) {
        if (nodes <= 0) {
            throw new IllegalArgumentException("Number of nodes must be positive");
        }
        this.data = new HashMap<>(Double.valueOf(nodes * 0.75 + 1).intValue());
    }

    public static GraphBuilder newBuilder(int nodes) {
        var builder = new GraphBuilder(nodes);
        for (int i = 0; i < nodes; i++) {
            builder.data.put(i, new ArrayList<>());
        }
        return builder;
    }

    public GraphBuilder addLink(int nodeFrom, int nodeTo) {
        if (nodeFrom < 0 || nodeFrom >= this.data.size() || nodeTo < 0 || nodeTo >= this.data.size()) {
            throw new IllegalArgumentException("Invalid nodes");
        }

        data.get(nodeFrom).add(nodeTo);
        return this;
    }

    public GraphBuilder addUndirectedLink(int node1, int node2) {
        addLink(node1, node2);
        addLink(node2, node1);
        return this;
    }

    public Map<Integer, List<Integer>> build() {
        return data;
    }
}
