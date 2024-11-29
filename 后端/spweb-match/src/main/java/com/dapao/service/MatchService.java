package com.dapao.service;

import com.dapao.pojo.Match;
import com.dapao.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface MatchService {
    List<Match> getAllMatch();

    PageBean page(Integer page, Integer pageSize, String matchKeyValue, LocalDate begin,LocalDate end);

    Match detail(Integer id);
}
