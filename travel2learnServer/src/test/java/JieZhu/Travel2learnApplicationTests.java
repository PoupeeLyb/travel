package JieZhu;
import static org.junit.Assert.*;
import JieZhu.Service.OpenAIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Travel2learnApplicationTests {
    @Autowired
    OpenAIService openAIService;
}
