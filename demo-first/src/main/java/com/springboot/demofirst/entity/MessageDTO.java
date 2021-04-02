package com.springboot.demofirst.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: SpringCloud-Learning
 * @Date: 2021/4/1 18:56
 * @Author: niuqingsong
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    /**
     * 姓名
     */
    private String username;
    /**
     * 充值金额
     */
    private int number;
}
