package JieZhu.algo;

import JieZhu.pojo.Edge;
import JieZhu.pojo.Graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import java.util.*;

public class Dijkstra {
    public List<String> findShortestPath(Graph graph, String src, String dest) {
        Map<String, Double> dist = new HashMap<>();  // 存储从源到每个节点的最小距离
        Map<String, String> prev = new HashMap<>();  // 前驱节点映射
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(node -> node.cost));
        Set<String> visited = new HashSet<>();
        // 初始化距离和队列
        pq.add(new Node(src, 0.0));
        dist.put(src, 0.0);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            visited.add(current.id);

            // 到达目标节点，开始构建路径
            if (current.id.equals(dest)) {
                return reconstructPath(prev, dest);
            }

            // 遍历邻接节点，更新距离
            for (Edge edge : graph.getAdj(current.id)) {
                if (visited.contains(edge.dest)) continue;
                double newDist = dist.get(current.id) + edge.cost;
                if (newDist < dist.getOrDefault(edge.dest, Double.MAX_VALUE)) {
                    dist.put(edge.dest, newDist);
                    prev.put(edge.dest, current.id);  // 更新前驱节点
                    pq.add(new Node(edge.dest, newDist));
                }
            }
        }
        return new ArrayList<>(); // 如果没有到达目标，返回空列表
    }

    private List<String> reconstructPath(Map<String, String> prev, String dest) {
        LinkedList<String> path = new LinkedList<>();
        for (String at = dest; at != null; at = prev.get(at)) {
            path.addFirst(at);
        }
        return path;
    }

    static class Node {
        String id;
        double cost;

        public Node(String id, double cost) {
            this.id = id;
            this.cost = cost;
        }
    }
}

