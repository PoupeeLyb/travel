package JieZhu.Service;

import JieZhu.Mapper.DiaryMapper;
import JieZhu.pojo.Diary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {
    @Autowired
    private DiaryMapper diaryMapper;
    public List<Diary> selectData()
    {
        return diaryMapper.selectByDate();
    }
}
