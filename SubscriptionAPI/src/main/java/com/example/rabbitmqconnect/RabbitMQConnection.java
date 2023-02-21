package com.example.rabbitmqconnect;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;
import com.example.constants.*;

import jakarta.annotation.PostConstruct;

@Component
public class RabbitMQConnection {
	private static final String EXCHANGE = "amq.direct";

	private AmqpAdmin amqpAdmin;
	
	public RabbitMQConnection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}
	
	private Queue queue(String queue) {
		return new Queue(queue, true, false, false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(EXCHANGE);
	}
	
	private Binding relationship(Queue queue, DirectExchange directExchange) {
		return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
	}
	
	@PostConstruct
	private void addict() {
		Queue queue_subscription = this.queue(RabbitMQConstants.QUEUE_SUBSCRIPTION);
	
		DirectExchange exchange_subscription = this.directExchange();
		
		Binding relationship_subscription = this.relationship(queue_subscription, exchange_subscription);
		
		//Creating Queue at RabbitMQ
		this.amqpAdmin.declareQueue(queue_subscription); 
		
		//Creating Exchange at RabbitMQ
		this.amqpAdmin.declareExchange(exchange_subscription);
		
		//Creating Relationship at RabbitMQ
		this.amqpAdmin.declareBinding(relationship_subscription);
		
	}
}
