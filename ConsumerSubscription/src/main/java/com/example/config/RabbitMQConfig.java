package com.example.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.exceptionhandler.ErrorsHandler;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {
	
	@Bean
	public RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
		DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();
		
		factory.setConnectionFactory(connectionFactory);
		factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
		
		factory.setPrefetchCount(20);
		
		//configuração global, demanda consumo de recursos para gerenciamento das threads/consumers do canal
		//factory.setGlobalQos(false);
		
		//Numero de consumidores por fila
		//factory.setConsumersPerQueue(1);
		
		factory.setErrorHandler(new ErrorsHandler());
		
		return factory;
		
	}
	

}
