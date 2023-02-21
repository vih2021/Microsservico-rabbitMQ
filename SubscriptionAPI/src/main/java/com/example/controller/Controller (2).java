package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.constants.RabbitMQConstants;
import com.example.inscricao.Subscription;
import com.example.rabbitmqservice.RabbitMQService;

//import com.example.controller.RegistroUsuario;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Subscription Rest Api")
@CrossOrigin(origins="*")
public class Controller {

	@Autowired
	private RabbitMQService rabbitService;
	
	
	@PostMapping("/subscribe")
	//@ApiOperation(value="Insert a new subscription at the database")
	//@ApiResponse(responseCode = "201", description = "Created")
	//@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
	public ResponseEntity<String> createSubscription(@RequestBody Subscription subscription) {
		
		String name = subscription.getName();
		String date = subscription.getDate();
		
		if(name == null) {
			return (ResponseEntity<String>) ResponseEntity.badRequest().body("Param 'name' cannot be null");
		}else if(name.isEmpty()) {
			return (ResponseEntity<String>) ResponseEntity.badRequest().body("Param 'name' cannot be empty");
		}else if(name.length() < 2) {
			return (ResponseEntity<String>) ResponseEntity.badRequest().body("Value param 'name' is too short, need to be greater than 2");
		}
		
		try {
			
			Subscription sub = Subscription.SubscriptionBuilder.builder()
			.setName(name)
			.setDate(date)
			.build();
			
			this.rabbitService.sendMessage(RabbitMQConstants.QUEUE_SUBSCRIPTION, sub);
			
			return ResponseEntity.created(null).body("Subscription Created");
			
		}catch (Exception e) {
			
			return (ResponseEntity<String>) ResponseEntity.badRequest().body("Subscription failed    -    " + e.getMessage());
		}
	}
}
