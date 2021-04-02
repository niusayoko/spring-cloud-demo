package com.springboot.demofirst.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * t_user
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TUser {
    /**
     * 用户表
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 余额
     */
    private Integer balance;

    /**
     * 状态（1在线，0离线）
     */
    private Boolean state;

    /**
     * VIP用户标识（1是，0否）
     */
    private Boolean vipFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;
}