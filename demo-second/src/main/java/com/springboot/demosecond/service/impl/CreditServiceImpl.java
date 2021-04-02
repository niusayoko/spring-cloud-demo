package com.springboot.demosecond.service.impl;

import com.springboot.demosecond.entity.TCredit;
import com.springboot.demosecond.mapper.TCreditMapper;
import com.springboot.demosecond.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: SpringCloud-Learning
 * @Date: 2021/4/1 19:03
 * @Author: niuqingsong
 * @Description:
 */
@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private TCreditMapper creditMapper;

    @Override
    public int addNumber(String username, int number) throws Exception {
        TCredit credit = creditMapper.selectByUserName(username);
        if (credit != null) {
            return creditMapper.addNumber(credit.getId(), credit.getIntegration(), number);
        } else {
            throw new Exception();
        }
    }
}
