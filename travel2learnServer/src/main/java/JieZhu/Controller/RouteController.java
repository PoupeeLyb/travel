package JieZhu.Controller;

import JieZhu.algo.Dijkstra;
import JieZhu.algo.GraphBuilder;
import JieZhu.pojo.Graph;
import JieZhu.pojo.RouteRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class RouteController {

    @Value("${amap.apikey}")
    private String apiKey; // 从配置文件读取API密钥
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/route")
    public String queryRoute(@RequestBody RouteRequest routeRequest) {
        System.out.println("start route");
        String baseUrl = "https://restapi.amap.com/v3/direction/driving";
        String requestUrl = String.format("%s?origin=%s&destination=%s&key=%s",
                baseUrl, routeRequest.getOrigin(), routeRequest.getDestination(), apiKey);

        RestTemplate restTemplate = new RestTemplate();
        String routeStr=restTemplate.getForObject(requestUrl, String.class);
        System.out.println("begin !!!");
        System.out.println(routeStr);
        return routeStr;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/routediy")
    public String queryRouteDiy(@RequestBody RouteRequest routeRequest) {
        System.out.println("start route");
        String baseUrl = "https://restapi.amap.com/v3/direction/driving";
        String requestUrl = String.format("%s?origin=%s&destination=%s&key=%s",
                baseUrl, routeRequest.getOrigin(), routeRequest.getDestination(), apiKey);

        RestTemplate restTemplate = new RestTemplate();
        String routeStr = restTemplate.getForObject(requestUrl, String.class);
        System.out.println(routeStr);
        try {
            Graph graph = GraphBuilder.buildGraphFromJson(routeStr);
            graph.printGraph();
            Dijkstra dijkstra = new Dijkstra();
            List<String> shortestPath = dijkstra.findShortestPath(graph, routeRequest.getOrigin(), routeRequest.getDestination());
            System.out.println(shortestPath);
            return routeStr;
        } catch (Exception e) {
            e.printStackTrace();
            return routeStr;
        }
    }
}




