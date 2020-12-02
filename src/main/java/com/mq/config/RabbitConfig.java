package com.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitConfig
 * @Description rabbitmq配置文件
 * @Author Jade
 * @Date 2020/12/01 16:07:10
 * @Version 1.0
 **/
@Configuration
public class RabbitConfig {
    //交换机
    public static final String TOPIC_EXCHANGE = "TOPIC_EXCHANGE_TEST";
    //队列
    public static final String QUEUE_TEST = "QUEUE_TEST";
    //队列key
    public static final String QUEUE_TEST_KEY = "QUEUE_TEST_KEY";

    /**
     * 声明topic类型的交换机
     * @return
     */
    @Bean(TOPIC_EXCHANGE)
    public Exchange TOPIC_EXCHANGE(){
        //durable(true) 持久化,消息队列重启后交换机仍然存在
        return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE).durable(true).build();
    }

    /**
     * 声明队列
     * @return
     */
    @Bean(QUEUE_TEST)
    public Queue QUEUE_TEST(){
        return new Queue(QUEUE_TEST);
    }

    /**
     * 绑定队列到交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding BINDING_QUEUE_TEST(@Qualifier(QUEUE_TEST) Queue queue,@Qualifier(TOPIC_EXCHANGE) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_TEST_KEY).noargs();
    }

}


