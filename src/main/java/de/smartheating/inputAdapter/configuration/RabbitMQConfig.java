package de.smartheating.inputAdapter.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RABBITMQ_ROUTINGKEY);
    }
   
}
