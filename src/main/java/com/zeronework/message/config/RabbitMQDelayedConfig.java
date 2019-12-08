package com.zeronework.message.config;

import com.zeronework.message.common.RabbitMQDelayedConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 延迟队列 配置类
 *
 * @author Gongshenruju
 * @email lufeng_coder@163.com
 * @data 2019/12/7 20:51
 */
@Configuration
public class RabbitMQDelayedConfig {

    /**
     * 声明延迟队列交换机
     */
    @Bean
    public CustomExchange customExchange() {
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(RabbitMQDelayedConstant.DELAYED_EXCHANGE_NAME.getName(), "x-delayed-message", true, false, args);
    }

    /**
     * 声明延迟队列
     */
    @Bean
    public Queue delayedQueue() {
        return new Queue(RabbitMQDelayedConstant.DELAYED_QUEUE_NAME.getName(), true, false, false);
    }

    /**
     * 将延迟队列绑定到延迟队列交换机上
     */
    /*@Bean
    public Binding delayedBinding() {
        return BindingBuilder.bind(delayedQueue()).to(customExchange()).with(RabbitMQDelayedConstant.DELAYED_QUEUE_ROUTING_KEY.getName()).noargs();
    }*/
    @Bean
    public Binding delayedBinding(@Qualifier("delayedQueue") Queue queue,
                                  @Qualifier("customExchange") CustomExchange customExchange) {
        return BindingBuilder.bind(queue).to(customExchange).with(RabbitMQDelayedConstant.DELAYED_QUEUE_ROUTING_KEY.getName()).noargs();
    }
}
