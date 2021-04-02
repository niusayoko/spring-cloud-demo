package com.springboot.demosecond.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * t_credit
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TCredit implements Serializable {
    /**
     * 积分表
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 积分
     */
    private Integer integration;

    private static final long serialVersionUID = 1L;
}