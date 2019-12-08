package com.zeronework.message.config;

import com.zeronework.message.common.RabbitMQSimpleConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 消息队列 配置类
 *
 * @author Gongshenruju
 * @email lufeng_coder@163.com
 * @data 2019/12/6 22:21
 */
@Configuration
public class RabbitMQSimpleConfig {

    /**
     * 声明交换机: (点对点)
     */
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(RabbitMQSimpleConstant.SIMPLE_EXCHANGE_NAME.getName(), true, false);
    }

    /**
     * 声明队列
     */
    @Bean
    public Queue queue() {
        /**
         * 参数设置
         */
        HashMap<String, Object> args = new HashMap<>();
        //声明队列最大优先级参数
        args.put("x-max-priority", 225);
        return new Queue(RabbitMQSimpleConstant.SIMPLE_QUEUE_NAME.getName(), true, false, false, args);
    }

    /**
     * 声明队列绑定到(Direct)交换机上
     */
    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(RabbitMQSimpleConstant.SIMPLE_QUEUE_ROUTING_KEY.getName());
    }
}
