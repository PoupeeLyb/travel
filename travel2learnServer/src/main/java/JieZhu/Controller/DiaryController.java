package JieZhu.Controller;

import JieZhu.Mapper.DiaryMapper;
import JieZhu.Service.DiaryService;
import JieZhu.pojo.Diary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiaryController {
    @Autowired
    private DiaryService diaryService;
    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping("/diary")
    public List<Diary> select()
    {
        return diaryService.selectData();
    }
    @PostMapping("/diary/post")
    public String postDiary(@RequestBody Diary diary){

        return "success";
    }
}
