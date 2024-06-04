package JieZhu.Mapper;

import JieZhu.pojo.Diary;
import JieZhu.pojo.Scene;
import JieZhu.pojo.SceneWithDiaries;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SceneMapper {
    @Select("SELECT * FROM scenelist ORDER BY clicks DESC")
    List<Scene> findAllScenes();

    @Select("SELECT s.id as 'scene_id', s.name, s.imgUrl, s.clicks, s.introduction, s.imgUrls, s.region, s.position, " +
            "d.id as 'diary_id', d.username, d.avatarUrl, d.time, d.title, d.content, d.imgUrls as 'diary_imgUrls', d.clicks as 'diary_clicks' " +
            "FROM scenelist s " +
            "LEFT JOIN diarys d ON s.id = d.scene_id " +
            "WHERE s.id = #{sceneId}")
    @Results({
            @Result(property = "scene.id", column = "scene_id"),
            @Result(property = "scene.name", column = "name"),
            @Result(property = "scene.imgUrl", column = "imgUrl"),
            @Result(property = "scene.clicks", column = "clicks"),
            @Result(property = "scene.introduction", column = "introduction"),
            @Result(property = "scene.imgUrls", column = "imgUrls"),
            @Result(property = "scene.region", column = "region"),
            @Result(property = "scene.position", column = "position"),
            @Result(property = "diaries", javaType = List.class, column = "diary_id",
                    many = @Many(select = "selectDiaryById"))
    })
    List<SceneWithDiaries> findSceneWithDiaries(@Param("sceneId") int sceneId);
    @Select("SELECT id, username, avatarUrl, time, title, content, imgUrls, clicks FROM diarys WHERE id = #{diaryId}")
    Diary selectDiaryById(@Param("diaryId") int diaryId);

    @Select("SELECT name, region FROM scenelist WHERE id = #{id}")
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "region", column = "region")
    })
    Map<String, String> findNameAndRegionById(int id);


}