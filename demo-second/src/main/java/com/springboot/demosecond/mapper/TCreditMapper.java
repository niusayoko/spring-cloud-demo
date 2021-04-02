package com.springboot.demosecond.mapper;

import com.springboot.demosecond.entity.TCredit;

public interface TCreditMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCredit record);

    int insertSelective(TCredit record);

    TCredit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCredit record);

    int updateByPrimaryKey(TCredit record);

    TCredit selectByUserName(String username);

    int addNumber(Integer id, Integer oldIntegration, int number);
}