package com.example.exceptionhandler;

import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class ErrorsHandler implements ErrorHandler{

	@Override
	public void handleError(Throwable t) {
		// TODO Auto-generated method stub
		String QueueName = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue();
		System.out.println(QueueName);
		
		String message = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());
		System.out.println(message);
		
		
		String stack = t.getCause().getMessage();
		System.out.println(stack);
		
	}

}
