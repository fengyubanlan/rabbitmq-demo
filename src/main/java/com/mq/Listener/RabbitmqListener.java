package com.mq.Listener;

import com.mq.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @ClassName RabbitListener
 * @Description rabbitmq消息监听器
 * @Author Jade
 * @Date 2020/12/01 16:37:40
 * @Version 1.0
 **/
@Slf4j
@Component
public class RabbitmqListener {

    /**
     * 配置监听器
     * @param msg
     * @param message
     * @param channel
     */
    @RabbitListener(queues = {RabbitConfig.QUEUE_TEST})
    public void receiveTest(String msg, Message message, Channel channel){
        log.info("receiveTest:"+msg);
    }

}
