package JieZhu.pojo;

import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjList = new HashMap<>();

    public void addEdge(String src, String dest, double cost) {
        this.adjList.putIfAbsent(src, new ArrayList<>());
        this.adjList.get(src).add(new Edge(dest, cost));
    }
    public void printGraph() {
        for (String node : adjList.keySet()) {
            System.out.print("Node " + node + " has edges to: ");
            for (Edge edge : adjList.get(node)) {
                System.out.print("(" + edge.dest + ", " + edge.cost + ") ");
            }
            System.out.println();
        }
    }
    public List<Edge> getAdj(String node) {
        return this.adjList.getOrDefault(node, new ArrayList<>());
    }
}

