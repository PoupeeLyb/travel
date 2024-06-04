package JieZhu.Service;

import JieZhu.Mapper.SceneMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Service
public class LocationService {

    @Value("${amap.apikey}")
    private String apiKey;

    private final RestTemplate restTemplate;

    private final SceneMapper sceneMapper;

    public LocationService(RestTemplate restTemplate, SceneMapper sceneMapper) {
        this.restTemplate = restTemplate;
        this.sceneMapper = sceneMapper;
    }

    public String getCoordinates(int sceneId) {
        Map<String, String> sceneInfo = sceneMapper.findNameAndRegionById(sceneId);
        String locationName = sceneInfo.get("name");
        String region = sceneInfo.get("region");
        String url = String.format("https://restapi.amap.com/v3/geocode/geo?key=%s&address=%s&city=%s",
                apiKey, locationName, region);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
