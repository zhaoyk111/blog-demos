package com.wooyoo.blog.spring_boot.rabbitmq.delay.queue;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * 设置队列消息的失效时间
 */
public class ExpirationMessagePostProcessor implements MessagePostProcessor {
    private static final String EXPIRATION_HEADER = "expiration";
    private final Integer ttl;

    public ExpirationMessagePostProcessor(Integer ttl) {
        this.ttl = ttl;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties()
               .getHeaders()
               .putIfAbsent(EXPIRATION_HEADER, ttl.toString());
        return message;
    }
}