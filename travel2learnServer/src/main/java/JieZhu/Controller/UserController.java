package JieZhu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import JieZhu.pojo.User;
import JieZhu.pojo.UserLogin;
import JieZhu.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/register")
    public String register(@RequestBody String requestBody){
        try {
            User user = objectMapper.readValue(requestBody, User.class);
            System.out.println("数据是: " + requestBody);
            System.out.println(user.getUsername());
            User findUser = userService.findByUsername(user.getUsername());
            if (findUser == null) {
                // 用户名未被占用
                Integer num = userService.countUsers() + 1;
                userService.register(user, num);
                return "注册成功";
            } else {
                // 用户名已被占用
                return "用户名已注册";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "User转换失败";
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLogin requestBody) {
        System.out.println(requestBody.getUsername() + requestBody.getPassword());
        User loginUser = userService.findByUsername(requestBody.getUsername());
        if (loginUser == null) {
            return "用户名不存在";
        } else if (requestBody.getPassword().equals(loginUser.getPassword())) {
            try {
                return objectMapper.writeValueAsString(loginUser);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return "User无法转换";
            }
        } else {
            return "密码错误";
        }
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam String username) {
        User findUser = userService.findByUsername(username);
        System.out.println("用户是: " + username);
        if (findUser == null) {
            return "未找到该用户";
        } else {
            try {
                String jsonString = objectMapper.writeValueAsString(findUser);
                System.out.println("返回的代码是: " + jsonString);
                return jsonString;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return "User转换失败";
            }
        }
    }

    @GetMapping("/userInfoById")
    public String findUserById(@RequestParam Integer id) {
        User findUser = userService.findById(id);
        System.out.println("用户是: " + id);
        if (findUser == null) {
            return "未找到该用户";
        } else {
            try {
                String jsonString = objectMapper.writeValueAsString(findUser);
                System.out.println("返回的代码是: " + jsonString);
                return jsonString;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return "User转换失败";
            }
        }
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        try {
            String jsonString = objectMapper.writeValueAsString(user);
            System.out.println("更新的代码是: " + jsonString);
            userService.update(user);
            return "更新成功";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "User转换失败";
        }
    }
}
