package com.example.eKart.user.messaging.consumer;

import com.example.eKart.user.data.UserRegisteredEvent;
import com.example.eKart.user.messaging.email.EmailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class UserEmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void consumeUserRegisteredEvent(UserRegisteredEvent event) {

        emailService.sendWelcomeEmail(event.getEmail(), event.getFullName());

        log.info("Email successfully send to user :{}", event.getUserId());

    }
}
