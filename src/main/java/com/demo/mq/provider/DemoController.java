package com.demo.mq.provider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Queue;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.demo.mq.conf.MQConfig;

@RestController
public class DemoController {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Queue queue;

	@RequestMapping(value = "/")
	public String home(HttpServletResponse response) throws IOException {
		Map<String, String> msg = new HashMap<>();
		msg.put("key1", "hi,activeMQ");
		jmsMessagingTemplate.convertAndSend(queue, JSON.toJSONString(msg));
		return "hello springboot";
	}

	@RequestMapping(value = "/2")
	public String home2(HttpServletResponse response) throws IOException {
		Map<String, String> msg = new HashMap<>();
		msg.put("key2", "##hi,activeMQ2");
		jmsMessagingTemplate.convertAndSend(MQConfig.queue2, JSON.toJSONString(msg));
		return "hello springboot";
	}


}
