package com.springboot.demofirst.rocket;

import com.alibaba.fastjson.JSON;
import com.springboot.demofirst.entity.MessageDTO;
import com.springboot.demofirst.entity.TUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RocketTxProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送消息，也就是发送halfMessage
     *
     *  Half Message:
     *  也叫 Prepare Message，翻译为 “半消息”或“准备消息”，
     *  指的是暂时无法投递的消息，即消息成功发送到MQ服务器，
     *  暂时还不能给消费者进行消费，只有当服务器接收到生产者传来的二次确认时，才能被消费者消费
     *
     * @param topic
     * @param msg
     */
    public TransactionSendResult  sendMsg(String topic, MessageDTO msg) {
        log.info("rocketmq topic：{}",topic);
        log.info("rocketmq消息msg：{}",msg);

        //生成事务id
        String transactionId = UUID.randomUUID().toString().replace("-", "");
        log.info("【发送Half Message】transactionId={}", transactionId);

        //封装message，设置消息体以及header信息，header中存储我们生成的事务id
        Message message = MessageBuilder
                .withPayload(msg)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                .build();

        //发送消息
        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(
                topic, message, msg);

        log.info("【发送Half Message】sendResult={}", JSON.toJSONString(sendResult));
        return sendResult;
    }
}
