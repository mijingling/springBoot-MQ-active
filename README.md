##1.在pom.xml中引入相关jar包（version至少1.4.0以上）
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-activemq</artifactId>
		<version>1.4.0.RELEASE</version>
	</dependency>
##2.在application.properties中配置activemq连接参数
	#activemq-config
	spring.activemq.brokerUrl=tcp://127.0.0.1:61616
	spring.activemq.user=admin
	spring.activemq.password=admin
##3.消息生产者配置(如果传更多参数，消息内容可以jsonString格式，也可以用序列化对象传参)
	public static final String BUSINESS1 = "activemq.queue";
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;// 注入消息模板实例
	@RequestMapping(value = "/")
	public String home() throws IOException {
		Map<String, String> msg = new HashMap<>();
		msg.put("key", "hi,activeMQ");
		jmsMessagingTemplate.convertAndSend(QueueList.BUSINESS1, JSON.toJSONString(msg));
		return "hello springboot";
	}
##4.消息消费者配置（即配置消息队列监听JmsListener）
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
	