package com.zeronework.message.common;

import lombok.Getter;

/**
 * @author Gongshenruju lufeng_coder@163.com
 * @create 2019/12/7 20:53
 * @Description 延迟队列 常量枚举 枚举
 * @since 1.0.0
 */
@Getter
public enum RabbitMQDelayedConstant {

    /**
     * 延迟队列交换机名
     */
    DELAYED_EXCHANGE_NAME("direct-delayed-exchange"),

    /**
     * 延迟队列名
     */
    DELAYED_QUEUE_NAME("delayed-queue"),

    /**
     * 延迟队列 路由routing-key
     */
    DELAYED_QUEUE_ROUTING_KEY("delayed-queue-routing-key");

    private final String name;

    RabbitMQDelayedConstant(String name) {
        this.name = name;
    }
}
