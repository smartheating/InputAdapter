package de.smartheating.inputAdapter.rabbitmq;

import org.springframework.stereotype.Component;

import de.smartheating.SmartHeatingCommons.persistedData.Event;

@Component
public class MessageConsumer {

	public void consumeEvent(Event event) {
		System.out.println("Incoming event with value: " + event.getValue());
	}
}
