package com.example.spring.rabbitmq.consumer.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
	
	@RabbitListener(queues = MQConfig.MESSAGE_QUEUE)
	public void listerner(CustomMessage message) {
		System.out.println(message);
	}

}
