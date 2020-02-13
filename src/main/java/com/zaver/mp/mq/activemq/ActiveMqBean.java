package com.zaver.mp.mq.activemq;
/*

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

*/
/**
 * @ClassName : Queue
 * @Description TODO
 * @Date : 2019/7/7 19:18
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 *//*

@Configuration
public class ActiveMqBean {
    public static final String QUEUE_TEST_NAME = "myTestQue";
    public static final String TOPIC_TEST_NAME = "myTestTopics";

    @Bean
    public Queue getTestQueue() {
        Queue queue = new ActiveMQQueue(QUEUE_TEST_NAME);
        return queue;
    }

    @Bean
    public Topic getTestTopic() {
        Topic topic = new ActiveMQTopic(TOPIC_TEST_NAME);
        return topic;
    }
    // topic模式的消息监听容器工厂
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }
    // queue模式的消息监听容器工厂
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }
}
*/
