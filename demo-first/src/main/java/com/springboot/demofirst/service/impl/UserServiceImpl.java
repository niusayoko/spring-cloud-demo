package com.springboot.demofirst.service.impl;

import com.springboot.demofirst.entity.TMqTransactionLog;
import com.springboot.demofirst.entity.TUser;
import com.springboot.demofirst.mapper.TMqTransactionLogMapper;
import com.springboot.demofirst.mapper.TUserMapper;
import com.springboot.demofirst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: SpringCloud-Learning
 * @Date: 2021/4/1 18:33
 * @Author: niuqingsong
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Autowired
    private TMqTransactionLogMapper mqTransactionLogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBalance(String username, int balance, String transactionId) {
        TUser user = tUserMapper.selectByName(username);
        int i = tUserMapper.updateBalance(user.getId(), user.getBalance(), balance);
        if (1 == i) {
            TMqTransactionLog tMqTransactionLog = new TMqTransactionLog(transactionId, "用户充值");
            mqTransactionLogMapper.insert(tMqTransactionLog);
        } else {
            throw new RuntimeException("增加用余额失败");
        }
    }
}
