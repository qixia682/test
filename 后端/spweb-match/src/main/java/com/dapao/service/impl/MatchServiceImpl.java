package com.dapao.service.impl;

import com.dapao.mapper.MatchMapper;
import com.dapao.pojo.Match;
import com.dapao.pojo.PageBean;
import com.dapao.service.MatchService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchMapper matchMapper;

    @Override
    public List<Match> getAllMatch() {
        return matchMapper.getAllMatch();
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String matchKeyValue, LocalDate begin, LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page, pageSize);

        //2.执行查询
        List<Match> empList = matchMapper.page(matchKeyValue,begin,end);
        Page<Match> p = (Page<Match>) empList;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public Match detail(Integer id) {
        return matchMapper.detail(id);
    }
}
