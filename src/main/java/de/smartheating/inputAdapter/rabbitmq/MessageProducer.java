package de.smartheating.inputAdapter.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.smartheating.SmartHeatingCommons.persistedData.Event;
import de.smartheating.inputAdapter.configuration.RabbitMQConfig;

@Component
public class MessageProducer {
	
	Logger logger = LoggerFactory.getLogger(MessageProducer.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendEvent(Event event) {
		logger.info("Publishing event to rabbitmq");
		rabbitTemplate.convertAndSend(RabbitMQConfig.RABBITMQ_EXCHANGE, RabbitMQConfig.RABBITMQ_ROUTINGKEY, event);
	}
}
