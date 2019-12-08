package com.zeronework.message.controller;

import com.zeronework.message.common.RabbitMQDelayedConstant;
import com.zeronework.message.common.RabbitMQSimpleConstant;
import com.zeronework.message.component.Producer;
import com.zeronework.message.utils.Result;
import com.zeronework.message.utils.ResultGenerator;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 消息 Controller
 *
 * @author Gongshenruju
 * @email lufeng_coder@163.com
 * @data 2019/12/6 23:28
 */
@Api("RabbitMQ消息接口")
@RequestMapping("/msg-rabbitmq")
@RestController
public class MessageController {

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    Producer producer;

    /**
     * 延迟队列:
     * 延迟10秒    http://localhost:8090/msg-rabbitmq/delayMsg?msg=%E6%B5%8B%E8%AF%95&delayMillis=10000
     * 测试: 消息粒度
     *      http://localhost:8090/msg-rabbitmq/delayMsg?msg=A20000&delayMillis=20000
     *      http://localhost:8090/msg-rabbitmq/delayMsg?msg=B10&delayMillis=10
     * 原理: 基于RabbitMQ-plugins 插件
     *
     * @param msg    消息
     * @param delayMillis 延迟时间(毫秒)
     * @return
     */
    @ApiOperation("延迟消息发送")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg", value = "消息", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "delayMillis", value = "延迟时间(毫秒)", required = true, paramType = "query", dataType = "long")
    })
    @GetMapping("/delayMsg")
    public Result delayMsg(String msg, long delayMillis) {
        logger.info("当前时间: {},收到请求, msg: {}, 延迟时间: {}", new Date(), msg, delayMillis);
        //发送延迟消息
        producer.delayQueueSend(RabbitMQDelayedConstant.DELAYED_EXCHANGE_NAME.getName(),
                RabbitMQDelayedConstant.DELAYED_QUEUE_ROUTING_KEY.getName(), msg, delayMillis);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 队列:
     * 优先级0      http://localhost:8090/msg-rabbitmq/priorityMsg?msg=priority0&priority=0
     * 优先级225    http://localhost:8090/msg-rabbitmq/priorityMsg?msg=priority225&priority=225
     *
     * @param msg    消息
     * @param priority 优先级
     * @return
     */
    @ApiOperation("优先级消息发送")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg", value = "消息", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "priority", value = "优先级", required = true, paramType = "query", dataType = "int")
    })
    @GetMapping("/priorityMsg")
    public Result priorityMsg(String msg, int priority) {
        logger.info("当前时间: {},收到请求, msg: {}, 优先级: {}", new Date(), msg, priority);
        //发送延迟消息
        producer.priorityQueueSend(RabbitMQSimpleConstant.SIMPLE_EXCHANGE_NAME.getName(),
                RabbitMQSimpleConstant.SIMPLE_QUEUE_ROUTING_KEY.getName(), msg, priority);
        return ResultGenerator.genSuccessResult();
    }
}
