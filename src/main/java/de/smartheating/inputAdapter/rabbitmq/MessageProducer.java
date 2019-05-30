package de.smartheating.inputAdapter.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.smartheating.SmartHeatingCommons.persistedData.Event;
import de.smartheating.inputAdapter.configuration.RabbitMQConfig;

@Component
public class MessageProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendEvent(Event event) {
		rabbitTemplate.convertAndSend(RabbitMQConfig.RABBITMQ_EXCHANGE, RabbitMQConfig.RABBITMQ_ROUTINGKEY, event);
		System.out.println("Published event to rabbitmq");
	}
}
