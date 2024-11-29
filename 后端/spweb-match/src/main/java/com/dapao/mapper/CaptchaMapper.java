package com.dapao.mapper;

import com.dapao.pojo.Captcha;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CaptchaMapper {

    //根据email查询验证码
    @Select("select * from `match`.captcha where email = #{email}")
    Captcha getByEmail(String email);

    //根据email储存验证码
    @Insert("insert into `match`.captcha(email, captcha) value (#{email} , #{captcha})")
    void insert(Captcha captcha);

    //根据email更新验证码
    @Update("update `match`.captcha set captcha = #{captcha} where email = #{email}")
    void update(Captcha captcha);
}
