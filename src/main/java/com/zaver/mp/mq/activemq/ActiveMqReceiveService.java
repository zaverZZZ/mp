package com.zaver.mp.mq.activemq;
/*

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Service;

*/
/**
 * @ClassName : ActiveMqReceiveService
 * @Description TODO
 * @Date : 2019/7/7 19:43
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 *//*

@Service
public class ActiveMqReceiveService {

    @JmsListener(destination = "queue-1",containerFactory ="jmsListenerContainerQueue")
    public void reciveQueue1(String msg){
        System.out.println("----------------------------------");
        System.out.println("接收到1类queue消息："+msg);
        System.out.println("----------------------------------");
    }

    @JmsListener(destination = "queue-1",containerFactory ="jmsListenerContainerQueue")
    public void reciveQueue11(String msg){
        System.out.println("----------------------------------");
        System.out.println("1类备胎方法,接收到1类queue消息："+msg);
        System.out.println("----------------------------------");
    }

    @JmsListener(destination = "queue-2",containerFactory ="jmsListenerContainerQueue")
    public void reciveQueue2(String msg){
        System.out.println("----------------------------------");
        System.out.println("接收到2类queue消息："+msg);
        System.out.println("----------------------------------");
    }

    @JmsListener(destination = "topic-1",containerFactory ="jmsListenerContainerTopic")
    @JmsListener(destination = "topic-2",containerFactory ="jmsListenerContainerTopic")
    public void reciveTopicAll(String msg){
        System.out.println("----------------------------------");
        System.out.println("All类，接收topic到消息："+msg);
        System.out.println("----------------------------------");
    }

    @JmsListener(destination = "topic-1",containerFactory ="jmsListenerContainerTopic")
    public void reciveTopic1(String msg){
        System.out.println("----------------------------------");
        System.out.println("接收到1类topic消息："+msg);
        System.out.println("----------------------------------");
    }

    @JmsListener(destination = "topic-2",containerFactory ="jmsListenerContainerTopic")
    public void reciveTopic2(String msg){
        System.out.println("----------------------------------");
        System.out.println("接收到2类topic消息："+msg);
        System.out.println("----------------------------------");
    }

}
*/
