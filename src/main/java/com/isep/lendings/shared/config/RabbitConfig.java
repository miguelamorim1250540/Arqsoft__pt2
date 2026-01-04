package com.isep.lendings.shared.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "lending.exchange";
    public static final String QUERY_QUEUE = "lending.query.queue";

    @Bean
    public TopicExchange lendingExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue queryQueue() {
        return new Queue(QUERY_QUEUE, true);
    }

    @Bean
    public Binding lendingBinding(Queue queryQueue, TopicExchange lendingExchange) {
        return BindingBuilder
                .bind(queryQueue)
                .to(lendingExchange)
                .with("lending.*");
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
}
