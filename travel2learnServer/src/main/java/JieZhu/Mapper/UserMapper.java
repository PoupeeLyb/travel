package JieZhu.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import JieZhu.pojo.User;

@Mapper
public interface  UserMapper {
    //根据id查找用户
    @Select("select * from user where id=#{id}")
    User findById(Integer id);
    //根据用户名查询用户
    @Select("select * from user where username=#{username}")
    User findByUsername(String username);
    //统计user里的元素个数
    @Select ("select count(*) from user")
    Integer countUsers();
    //添加
    @Insert("insert into user(id,username,password)"+"values(#{num},#{user.username},#{user.password})")
    void add(@Param("user")User user,@Param("num")Integer num);
    //更新
    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    void update(User user);
}