package com.isep.lendings.command.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LendingEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "lending.exchange";

    public void publishCreated(Object event) {
        rabbitTemplate.convertAndSend(
                EXCHANGE,
                "lending.created",
                event
        );
    }

    public void publishReturned(Object event) {
        rabbitTemplate.convertAndSend(
                EXCHANGE,
                "lending.returned",
                event
        );
    }
}
