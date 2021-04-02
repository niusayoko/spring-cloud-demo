package com.springboot.demofirst.service.impl;

import com.springboot.demofirst.entity.TMqTransactionLog;
import com.springboot.demofirst.mapper.TMqTransactionLogMapper;
import com.springboot.demofirst.service.MqTransactionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: SpringCloud-Learning
 * @Date: 2021/4/1 18:48
 * @Author: niuqingsong
 * @Description:
 */
@Service
public class MqTransactionLogServiceImpl implements MqTransactionLogService {

    @Autowired
    private TMqTransactionLogMapper mqTransactionLogMapper;

    @Override
    public TMqTransactionLog selectByPrimaryKey(String transactionId) {
        return mqTransactionLogMapper.selectByPrimaryKey(transactionId);
    }
}
