package com.example.eKart.user.messaging.producer;

import com.example.eKart.user.data.UserRegisteredEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserEventProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    public UserEventProducer(RabbitTemplate rabbitTemplate, Jackson2JsonMessageConverter converter) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMessageConverter(converter);
    }

    public void sendUserRegisteredEvent(UserRegisteredEvent event) {
        log.info("User with id :{} created successfully, and process message to Email Service", event.getUserId());
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }
}
