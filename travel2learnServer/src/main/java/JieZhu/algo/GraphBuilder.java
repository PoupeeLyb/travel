package JieZhu.algo;
import JieZhu.pojo.Graph;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {
    public static Graph buildGraphFromJson(String jsonStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonStr);

        Graph graph = new Graph();
        JsonNode steps = root.path("route").path("paths").get(0).path("steps");

        for (JsonNode step : steps) {
            String polyline = step.path("polyline").asText();
            double distance = step.path("distance").asDouble();
            List<String> nodes = decodePolyline(polyline);
            processStep(graph, nodes, distance);
        }

        return graph;
    }

    private static List<String> decodePolyline(String encoded) {
        List<String> nodeList = new ArrayList<>();
        String[] points = encoded.split(";");
        for (String point : points) {
            nodeList.add(point);
        }
        return nodeList;
    }

    private static void processStep(Graph graph, List<String> nodes, double totalDistance) {
        if (nodes.size() < 2) return;

        double distancePerSegment = totalDistance / (nodes.size() - 1);
        for (int i = 0; i < nodes.size() - 1; i++) {
            String src = nodes.get(i);
            String dest = nodes.get(i + 1);
            graph.addEdge(src, dest, distancePerSegment);
        }
    }
}
