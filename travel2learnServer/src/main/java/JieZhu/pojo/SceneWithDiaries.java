package JieZhu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SceneWithDiaries {
    private Scene scene;
    private List<Diary> diaries;
}
