package com.demo.mq.conf;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("activemq.queue");
	}

	public static Queue queue2 = new ActiveMQQueue("activemq.queue");

}
