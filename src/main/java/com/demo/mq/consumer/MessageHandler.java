package com.demo.mq.consumer;

import java.util.Map;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.demo.mq.util.QueueList;
import com.demo.mq.vo.ParamVo;

@Component
public class MessageHandler {

	@JmsListener(destination = QueueList.BUSINESS2)
	public void receiveQueueObj(String txtMsg) {
		// 消息内容转为具体对象，数据类型更明晰
		ParamVo paramVo = JSON.parseObject(txtMsg, ParamVo.class);// 推荐该转换方案
		System.out.println("##activemq.queue#" + JSON.toJSONString(paramVo));
	}

	@JmsListener(destination = QueueList.BUSINESS1)
	public void receiveQueue(String txtMsg) {
		// 消息内容转为Map<String,Object>
		Map<String, Object> map = JSON.parseObject(txtMsg);
		System.out.println("##activemq.queue#" + map);
	}

}
