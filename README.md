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
##3.消息队列实例化配置ActiveMQQueue
	@Configuration
	public class MQConfig {
		@Bean
		public Queue queue() {
			return new ActiveMQQueue("activemq.queue");
		}
	}
	//或者用静态实例
	//public static Queue queue2 = new ActiveMQQueue("activemq.queue");
##4.消息生产者配置(消息内容可以jsonString格式，传更多参数)
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Queue queue;
	@RequestMapping(value = "/")
	public String home(HttpServletResponse response) throws IOException {
		jmsMessagingTemplate.convertAndSend(queue, "hi,activeMQ");
		return "hello springboot";
	}
##5.消息消费者配置（即消息队列监听JmsListener）
	@JmsListener(destination = "activemq.queue")
	public void receiveQueue(String text) {
		System.out.println("##activemq.queue#" + text);
	}