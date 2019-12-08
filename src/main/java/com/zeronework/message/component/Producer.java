package com.zeronework.message.component;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author Gongshenruju
 * @email lufeng_coder@163.com
 * @data 2019/12/6 23:13
 */
@Component
public class Producer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到延迟队列
     */
    public void delayQueueSend(String exchange, String routingKey, String msg, long millis) {
        //消息属性处理类: 设置延迟时间
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay((int) millis);
                return message;
            }
        };
        //发送延迟消息
        rabbitTemplate.convertAndSend(exchange, routingKey, msg, processor);
    }

    /**
     * 发送消息到简单队列
     */
    public void priorityQueueSend(String exchange, String routingKey, String msg, int priority) {
        //消息属性处理类: 设置延迟时间
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setPriority(priority);
                return message;
            }
        };
        //发送优先级消息
        rabbitTemplate.convertAndSend(exchange, routingKey, msg, processor);
    }
}
