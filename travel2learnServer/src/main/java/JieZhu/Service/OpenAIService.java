package JieZhu.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class OpenAIService {
    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.model}")
    private String model;

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    public String generateImage(String prompt) {
        System.out.println("begin generate");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        String requestJson = "{\"model\":\"" + model + "\",\"prompt\":\"" + prompt + "\",\"n\":1,\"size\":\"1024x1024\"}";
        System.out.println("Headers: " + headers);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        String response = restTemplate.postForObject(apiUrl, entity, String.class);
        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode urlNode = rootNode.path("data").get(0).path("url");
            return urlNode.asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
