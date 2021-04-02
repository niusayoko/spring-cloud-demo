package com.springboot.demosecond.rocket;

import com.alibaba.fastjson.JSONObject;
import com.springboot.demosecond.entity.MessageDTO;
import com.springboot.demosecond.service.CreditService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 1. topic:和生产者发送的topic相同
 * 2. consumerGroup: 不用和生产者group相同
 * 3. selectorExpression: 匹配tag
 *
 * @author niuqingsong
 * @date 2021/4/1
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "niu-tx-topic", consumerGroup = "tx-consumer", selectorExpression = "*")
public class RocketTxConsumer implements RocketMQListener<MessageDTO> {

    @Autowired
    private CreditService creditService;

    /**
     * 接受rocketmq消息
     * 在这里进行的逻辑是给用户积分+100
     * 在springboot中实现RocketMQListener<T>,默认就是消费者处理时抛出异常时就会自动重试
     *
     * @author niuqingsong
     * @date 2021/4/1
     */
    @Override
    public void onMessage(MessageDTO message) {
        /*
         * 一般真实环境这里消费前，得做幂等性判断，防止重复消费
         * 方法一：如果你的业务中有某个字段是唯一的，有标识性，如订单号，那就可以用此字段来判断
         * 方法二：新建一张消费记录表t_mq_consumer_log，字段consumer_key是唯一性，能插入则表明该消息还未消费，往下走，
         *         否则停止消费
         * 我个人建议用方法二，根据你的项目业务来定义key，这里我就不做幂等判断了，因为此案例只是模拟，重在分布式事务
         */

        // 给用户增加积分
        int i = 0;
        try {
            log.info("【消费消息】message：{}", JSONObject.toJSONString(message));
            i = creditService.addNumber(message.getUsername(), message.getNumber());
            if (1 == i) {
                log.info("【MQ消费】用户增加积分成功，message={}", JSONObject.toJSONString(message));
            } else {
                //抛出异常
                throw new Exception("【MQ消费】用户充值增加积分消费失败，message=" + JSONObject.toJSONString(message));
            }
        } catch (Exception e) {
            log.error("增加积分出现异常！", message.toString(), e);
            //抛出异常后，MQClient会返回ConsumeConcurrentlyStatus.RECONSUME_LATER,这条消息会再次尝试消费
            throw new RuntimeException(e);
        }

    }
}
