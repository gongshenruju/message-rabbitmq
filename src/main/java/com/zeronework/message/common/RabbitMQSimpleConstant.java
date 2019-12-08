package com.zeronework.message.common;

import lombok.Getter;

/**
 * @author Gongshenruju lufeng_coder@163.com
 * @create 2019/12/6 22:41
 * @Description 消息常量 枚举
 * @since 1.0.0
 */
@Getter
public enum RabbitMQSimpleConstant {
    /**
     * 简单交换机名
     */
    SIMPLE_EXCHANGE_NAME("simple-direct-exchange"),

    /**
     * 简答队列名
     */
    SIMPLE_QUEUE_NAME("simple-queue"),

    /**
     * 简单队列 routing-key
     */
    SIMPLE_QUEUE_ROUTING_KEY("simple-queue-routing-key");


    private final String name;

    RabbitMQSimpleConstant(String name) {
        this.name = name;
    }
}
