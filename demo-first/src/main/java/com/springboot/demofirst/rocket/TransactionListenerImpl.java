package com.springboot.demofirst.rocket;

import com.alibaba.fastjson.JSONObject;
import com.springboot.demofirst.entity.MessageDTO;
import com.springboot.demofirst.entity.TMqTransactionLog;
import com.springboot.demofirst.service.MqTransactionLogService;
import com.springboot.demofirst.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

@Slf4j
@RocketMQTransactionListener()
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    @Autowired
    private UserService userService;

    @Autowired
    private MqTransactionLogService mqTransactionLogService;

    /**
     *  执行业务逻辑
     *  producer发送消息后，开始执行本地业务并提交事务
     *
     *
     * @param message
     * @param obj
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object obj) {
        //获取事务id
        String transactionId = (String) message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);

        try {
           MessageDTO messageDTO = (MessageDTO) obj;
            log.info("【执行本地事务】消息体参数：transactionId={}, message:{}", transactionId, messageDTO);

            //模拟失败
            //int i = 3/0;

            //开始执行本地事务,用户充值100的余额，也就是余额+100，同时在一个事务里将事务id插入事务日志表中
            userService.addBalance(messageDTO.getUsername(), messageDTO.getNumber(), transactionId);

            log.info("【执行本地事务】正常结束：transactionId={}", transactionId);
            // 返回事务状态给生产者
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            log.error("【执行本地事务】发生异常，消息将被回滚", e);
            //如果本地执行事务出现异常，向MQ Server发送rollback消息
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 回查
     * Message Status Check:消息状态回查。
     * 网络断开连接或生产者应用程序重新启动可能会丢失对事务性消息的第二次确认，
     * 当MQ服务器发现某条消息长时间保持半消息状态时，它会向消息生产者发送一个请求，
     * 去检查消息的最终状态（“提交”或“回滚”）
     *
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        //获取事务id
        String transactionId = (String) message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        log.info("【回查本地事务】 transactionId={}", transactionId);

        // 根据事务id查询事务日志表
        TMqTransactionLog mqTransactionLog = mqTransactionLogService.selectByPrimaryKey(transactionId);
        if (null == mqTransactionLog) {
            log.info("【回查本地事务】 没查到表明本地事务执行失败,通知回滚 transactionId={}", transactionId);
            // 没查到表明本地事务执行失败,通知回滚
            return RocketMQLocalTransactionState.ROLLBACK;
        }

        log.info("【回查本地事务】 查到表明本地事务执行成功，提交 transactionId={}", transactionId);
        // 查到表明本地事务执行成功，提交
        return RocketMQLocalTransactionState.COMMIT;
    }
}
