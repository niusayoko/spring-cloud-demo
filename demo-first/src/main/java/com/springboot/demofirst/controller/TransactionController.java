package com.springboot.demofirst.controller;

import com.springboot.demofirst.entity.MessageDTO;
import com.springboot.demofirst.rocket.RocketTxProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloud-Learning
 * @Date: 2021/4/1 19:51
 * @Author: niuqingsong
 * @Description:
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    
    @Autowired
    private RocketTxProducer rocketTxProducer;

    @GetMapping("/test")
    public Boolean testTransaction() {
        String username = "niuqingsong";
        int number = 100;
        MessageDTO messageDTO = new MessageDTO(username, number);
        rocketTxProducer.sendMsg("niu-tx-topic", messageDTO);
        return true;
    }
}
