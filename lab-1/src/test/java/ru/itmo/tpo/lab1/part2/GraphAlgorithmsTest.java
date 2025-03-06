package ru.itmo.tpo.lab1.part2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class GraphAlgorithmsTest {

    public static Stream<Arguments> getBfsDirectedGraph() {
        var graph = GraphBuilder.newBuilder(8)
                .addLink(0, 3)
                .addLink(1, 0)
                .addLink(1, 3)
                .addLink(1, 6)
                .addLink(2, 1)
                .addLink(2, 5)
                .addLink(4, 7)
                .addLink(5, 1)
                .addLink(5, 6)
                .addLink(6, 4)
                .addLink(7, 5)
                .addLink(7, 6)
                .build();

        return Stream.of(
                Arguments.of(graph, 0, List.of(0, 3)),
                Arguments.of(graph, 1, List.of(1, 0, 3, 6, 4, 7, 5)),
                Arguments.of(graph, 2, List.of(2, 1, 5, 0, 3, 6, 4, 7)),
                Arguments.of(graph, 3, List.of(3)),
                Arguments.of(graph, 4, List.of(4, 7, 5, 6, 1, 0, 3)),
                Arguments.of(graph, 5, List.of(5, 1, 6, 0, 3, 4, 7)),
                Arguments.of(graph, 6, List.of(6, 4, 7, 5, 1, 0, 3)),
                Arguments.of(graph, 7, List.of(7, 5, 6, 1, 4, 0, 3))
        );
    }

    public static Stream<Arguments> getBfsUndirectedGraph() {
        var graph = GraphBuilder.newBuilder(8)
                .addUndirectedLink(0, 1)
                .addUndirectedLink(0, 2)
                .addUndirectedLink(0, 3)
                .addUndirectedLink(1, 2)
                .addUndirectedLink(1, 3)
                .addUndirectedLink(1, 6)
                .addUndirectedLink(2, 5)
                .addUndirectedLink(3, 1)
                .addUndirectedLink(3, 7)
                .addUndirectedLink(4, 6)
                .addUndirectedLink(5, 6)
                .build();

        return Stream.of(
                Arguments.of(graph, 0, List.of(0, 1, 2, 3, 6, 5, 7, 4)),
                Arguments.of(graph, 1, List.of(1, 0, 2, 3, 6, 5, 7, 4)),
                Arguments.of(graph, 2, List.of(2, 0, 1, 5, 3, 6, 7, 4)),
                Arguments.of(graph, 3, List.of(3, 0, 1, 7, 2, 6, 5, 4)),
                Arguments.of(graph, 4, List.of(4, 6, 1, 5, 0, 2, 3, 7)),
                Arguments.of(graph, 5, List.of(5, 2, 6, 0, 1, 4, 3, 7)),
                Arguments.of(graph, 6, List.of(6, 1, 4, 5, 0, 2, 3, 7)),
                Arguments.of(graph, 7, List.of(7, 3, 0, 1, 2, 6, 5, 4))
        );
    }

    public static Stream<Arguments> getBfsInvalidData() {
        var graph = GraphBuilder.newBuilder(3)
                .addLink(0, 1)
                .addLink(1, 2)
                .addLink(2, 0)
                .build();

        return Stream.of(
                Arguments.of(null, 0, List.of()),
                Arguments.of(new HashMap<>(), 0, List.of()),
                Arguments.of(graph, -1, List.of()),
                Arguments.of(graph, Integer.MAX_VALUE, List.of()),
                Arguments.of(graph, 3, List.of())
        );
    }

    @ParameterizedTest(name = "{index} BFS test")
    @MethodSource({"getBfsDirectedGraph", "getBfsUndirectedGraph", "getBfsInvalidData"})
    public void bfsTest(Map<Integer, List<Integer>> graph, int startNode, List<Integer> expectedOrder) {
        Assertions.assertIterableEquals(expectedOrder, GraphAlgorithms.bfs(graph, startNode));
    }
}
