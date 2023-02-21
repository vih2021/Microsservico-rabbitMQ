package com.example.consumer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.example.ModelSaveDatabase.ModelSaveSubscription;
import com.example.constants.RabbitMQConstants;
import com.example.inscricao.Subscription;
import com.example.jparepository.JPARepository;

@Component
public class SubscriptionConsumer {
	
	@Autowired
	JPARepository Repository;

	@RabbitListener(queues = RabbitMQConstants.QUEUE_SUBSCRIPTION)
	public void consumer(Subscription subscription) {
		
		String receivedName = subscription.getName();
		String receivedDate = subscription.getDate();
		
		ModelSaveSubscription sub = new ModelSaveSubscription.ModelSaveSubscriptionBuilder().builder()
				.setName(receivedName)
				.setDate(receivedDate)
				.build();
		
		Repository.save(sub);
		
		System.out.println("saved");
		
		//FAZER A PERSISTENCIA NO BANCO DE DADOS  E CRIAR GETTERS PARA CLASS SAVESUBSCRIPTION
		//OPCIONAL: PROIBIR O ENVIO DE OUTROS PARAMETROS SE NÃO REQUISITADOS
		// criar builder para SAVESUBSCRIPTION;
		
	}
}
