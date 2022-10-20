package com.example.spring.rabbitmq.consumer.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

	public static final String MESSAGE_ROUTING_KEY = "message_routingKey";
	public static final String MESSAGE_EXCHANGE = "message_exchange";
	public static final String MESSAGE_QUEUE = "message_queue";

	@Bean
	public Queue getQueue() {
		return new Queue(MESSAGE_QUEUE);
	}

	@Bean
	public DirectExchange getExchange() {
		return new DirectExchange(MESSAGE_EXCHANGE);
	}

	@Bean
	public Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(MESSAGE_ROUTING_KEY);
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter());
		return template;
	}
}
