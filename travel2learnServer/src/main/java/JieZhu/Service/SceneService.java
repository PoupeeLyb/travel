    package JieZhu.Service;

    import JieZhu.Mapper.SceneMapper;
    import JieZhu.pojo.Diary;
    import JieZhu.pojo.Scene;
    import JieZhu.pojo.SceneWithDiaries;  // 确保导入了新的 SceneWithDiaries 类
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;

    @Service
    public class SceneService {
        @Autowired
        private SceneMapper sceneMapper;

        public List<Scene> getAllImages() {
            return sceneMapper.findAllScenes();
        }

        // 新增方法来获取景点及其关联日记
        public SceneWithDiaries findSceneWithDiaries(int sceneId) {
            List<SceneWithDiaries> results = sceneMapper.findSceneWithDiaries(sceneId);
            if (!results.isEmpty()) {
                SceneWithDiaries combined = new SceneWithDiaries();
                combined.setScene(results.get(0).getScene()); // 假设所有结果都有相同的 Scene
                List<Diary> combinedDiaries = new ArrayList<>();
                for (SceneWithDiaries item : results) {
                    if (item.getDiaries() != null) { // 检查是否为 null
                        combinedDiaries.addAll(item.getDiaries());
                    }
                }
                combined.setDiaries(combinedDiaries);
                return combined;
            }
            return null;
        }
    }