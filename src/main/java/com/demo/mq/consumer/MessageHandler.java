package com.demo.mq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

	@JmsListener(destination = "activemq.queue")
	public void receiveQueue(String text) {
		System.out.println("##activemq.queue#" + text);
	}
}
