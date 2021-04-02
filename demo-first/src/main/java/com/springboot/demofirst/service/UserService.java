package com.springboot.demofirst.service;

/**
 * @program: SpringCloud-Learning
 * @Date: 2021/4/1 18:33
 * @Author: niuqingsong
 * @Description:
 */
public interface UserService {
    /**
     * 给用户充值
     *
     * @param username
     * @param balance
     * @param transactionId
     * @return void
     * @author niuqingsong
     * @date 2021/4/1
     */
    void addBalance(String username, int balance, String transactionId);
}
