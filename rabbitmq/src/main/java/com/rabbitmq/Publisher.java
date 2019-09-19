package com.rabbitmq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


public class Publisher {
	
	
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException ,IOException, InterruptedException {
		
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri("amqp://guest:guest@localhost");
		factory.setConnectionTimeout(300000);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		
		channel.queueDeclare("my-queue", true, false, false, null);
		
		int count= 0;
		
		while(count<5000) {
			
			String message = "Message number" +  count;			
			channel.basicPublish("", "my-queue",null, message.getBytes());
			count++;
			System.out.println("published message: " + message);
			Thread.sleep(5000);
			
			
		}
		
	}

}
