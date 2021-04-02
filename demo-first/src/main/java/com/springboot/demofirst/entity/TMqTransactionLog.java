package com.springboot.demofirst.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * t_mq_transaction_log
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TMqTransactionLog implements Serializable {
    /**
     * 事务id
     */
    private String transactionId;

    /**
     * 日志
     */
    private String log;

    private static final long serialVersionUID = 1L;
}