package JieZhu.Controller;
import JieZhu.Service.OpenAIService;
import JieZhu.pojo.GenerateImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OpenAIController {
    @Autowired
    private OpenAIService openAIService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/generate-image")
    public Map<String, String> generateImage(@RequestBody GenerateImageRequest request) {
        String url = openAIService.generateImage(request.getPrompt());
        System.out.println(url);
        Map<String, String> response = new HashMap<>();
        response.put("image_url", url);
        return response;
    }
}

