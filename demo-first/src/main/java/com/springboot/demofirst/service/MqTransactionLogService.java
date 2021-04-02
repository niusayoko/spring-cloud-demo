package com.springboot.demofirst.service;

import com.springboot.demofirst.entity.TMqTransactionLog;

/**
 * @program: SpringCloud-Learning
 * @Date: 2021/4/1 18:48
 * @Author: niuqingsong
 * @Description:
 */
public interface MqTransactionLogService {

    TMqTransactionLog selectByPrimaryKey(String transactionId);
}
