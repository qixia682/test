package com.dapao.controller;

import com.dapao.pojo.Match;
import com.dapao.pojo.PageBean;
import com.dapao.pojo.Result;
import com.dapao.service.MatchService;
import com.dapao.service.impl.MatchServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

//    //查询全部比赛
//    @GetMapping("/match")
//    public Result getAllMatch() {
//        log.info("查询全部比赛---getAllMatch");
//        List<Match> matchList = matchService.getAllMatch();
//        return Result.success(matchList);
//    }
    //分页查询
    @GetMapping("/match")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String matchKeyValue,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询，参数：{}，{}，{}，{}，{}---page", page, pageSize, matchKeyValue, begin, end);
        PageBean pagebean = matchService.page(page,pageSize, matchKeyValue, begin, end);
        return Result.success(pagebean);
    }

    @GetMapping("/matchdetail/{id}")
    public Result detail(@PathVariable Integer id) {
        log.info("查询比赛细节，id：{}---detail",id);
        Match match = matchService.detail(id);
        return Result.success(match);
    }
}
