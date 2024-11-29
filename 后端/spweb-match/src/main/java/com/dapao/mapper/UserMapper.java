package com.dapao.mapper;

import com.dapao.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Insert("insert into `match`.user(username, password, gender, grade, birthday, img, email)" +
            " VALUE (#{username},#{password},#{gender},#{grade},#{birthday},#{img},#{email})")
    void register(User user);

    @Select("select * from `match`.user where email = #{email} and password = #{password}")
    User getByEmailAndPassword(User user);


    void updateUser(User user);

    @Select("select * from user where id = #{id}")
    User getById(int id);
}
