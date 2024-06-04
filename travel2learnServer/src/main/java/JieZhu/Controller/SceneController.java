package JieZhu.Controller;

import JieZhu.Service.SceneService;
import JieZhu.pojo.Scene;
import JieZhu.pojo.SceneWithDiaries;  // 确保导入了新的 SceneWithDiaries 类
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class SceneController {
    @Autowired
    private SceneService sceneService;

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping("/scenelist")
    public List<Scene> getAll() {
        return sceneService.getAllImages();
    }

    // 新增方法用于获取景点及其日记
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/scene/{id}")  // 使用@GetMapping注解和动态路径变量
    public SceneWithDiaries getSceneWithDiaries(@PathVariable("id") int sceneId) {
        return sceneService.findSceneWithDiaries(sceneId);
    }
}
