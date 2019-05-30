package de.smartheating.inputAdapter.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public final static String RABBITMQ_QUEUE = "processing";
	public final static String RABBITMQ_EXCHANGE = "directexchange";
	public final static String RABBITMQ_ROUTINGKEY = "to.processing";
	
    @Bean
    Queue queue() {
        return new Queue(RABBITMQ_QUEUE, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(RABBITMQ_EXCHANGE, true, false);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RABBITMQ_ROUTINGKEY);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RABBITMQ_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }
   
}
