package com.dapao.mapper;

import com.dapao.pojo.Match;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface MatchMapper {

    @Select("select * from `match`.match1 where id = #{id}")
    Match detail(Integer id);

    @Select("select * from `match`.`match1`")
    List<Match> getAllMatch();

    List<Match> page(String matchKeyValue, LocalDate begin, LocalDate end);


}
