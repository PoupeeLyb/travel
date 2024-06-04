package JieZhu.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JieZhu.Mapper.UserMapper;
import JieZhu.pojo.User;

@Service
public class UserService {

    @Autowired
    private  UserMapper userMapper;

    //查询用户通过id
    public User findById(Integer id){
        return userMapper.findById(id);
    }
//查询用户
    public User findByUsername(String username){
        return userMapper.findByUsername(username);
    }
//注册
    public void register(User user,Integer num){
        //添加
        userMapper.add(user,num);

    }
    // 统计用户数量
    public Integer countUsers() {
        return userMapper.countUsers();
    }
    public void update(User user){
        userMapper.update(user);
    }
}
