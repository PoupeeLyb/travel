package JieZhu.Mapper;

import JieZhu.pojo.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DiaryMapper {
    @Select("SELECT * FROM diarys ORDER BY time DESC")
    List<Diary> selectByDate();


    @Select("SELECT * FROM diarys WHERE scene_id = #{sceneId} ORDER BY time DESC")
    List<Diary> selectBySceneId(@Param("sceneId") int sceneId);
}
