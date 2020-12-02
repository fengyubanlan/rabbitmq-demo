package com.mq.controller;

import com.mq.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RabbitController
 * @Description 测试简单的rabbitmq消息
 * @Author Jade
 * @Date 2020/12/01 16:31:59
 * @Version 1.0
 **/

@Slf4j
@RestController
@RequestMapping("/rabbit")
public class RabbitmqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试消息发送
     * @return
     */
    @RequestMapping("/test")
    public String test(){
        try{
            log.info("start");
            for(int i = 1;i<=5;i++){
                String message = "this is message:"+i;
                log.info(message+":send start");
                rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,RabbitConfig.QUEUE_TEST_KEY,message);
                log.info(message+":send stop");
                Thread.sleep(10000);//休眠10秒
            }
            log.info("stop");
        }catch (Exception e){
            log.error("test:error:"+e.getMessage(),e);
            return "error";
        }

        return "success";
    }

}
