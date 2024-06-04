package JieZhu.pojo;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scene {
    private int id;
    private String name;
    private String imgUrl;
    private int clicks;
    private String introduction;
    private String imgUrls;
    private String region;
    private String position;
}
