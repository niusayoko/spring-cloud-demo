package com.springboot.demofirst;

import com.springboot.demofirst.entity.MessageDTO;
import com.springboot.demofirst.rocket.RocketTxProducer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoFirstApplication.class)
public class TxMessgeTest {

    @Autowired
    private RocketTxProducer rocketTxProducer;

    @Test
    public void testRocketTxMessage() {
        //充值100，也就是user表中balance余额+100，然后在credit积分表中积分 integration+100
        //first服务负责user中余额+100，并发送消息给second服务，second服务负责接收mq消息，integration+100
        String username = "niuqingsong";
        int number = 100;
        MessageDTO messageDTO = new MessageDTO(username, number);
        rocketTxProducer.sendMsg("niu-tx-topic", messageDTO);
        try {
            //等待业务完成
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

