package com.zeronework.message.component;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消费者
 *
 * @author Gongshenruju
 * @email lufeng_coder@163.com
 * @data 2019/12/7 0:31
 */
@Component
public class Consumer {

    private Logger logger = LoggerFactory.getLogger(Consumer.class);

    /**
     * 监听 延迟队列: delayed-queue
     */
    @RabbitListener(queues = "delayed-queue")
    public void processDelayedMessage(String msg, Message message, Channel channel) {
        logger.info("[delayed-queue] 延迟队列收到消息: {}", msg);

        //消息确认
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听 简单队列: simple-queue
     */
    @RabbitListener(queues = "simple-queue")
    public void processPriorityMessage(String msg, Message message, Channel channel) {
        logger.info("[simple-queue] Receive: {}", msg);

        //消息确认
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
