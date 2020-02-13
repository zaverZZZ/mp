package com.zaver.mp.mq.activemq;
/*

import com.zaver.mp.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

*/
/**
 * @ClassName : ActiveMqTestController
 * @Description TODO
 * @Date : 2019/7/7 19:06
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 *//*

@RestController
@RequestMapping("/activemq")
public class ActiveMqTestController {

    @Autowired
    private JmsMessagingTemplate messaging;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;

    @RequestMapping("/sendQueue")
    public Result sendMsgQueue(@RequestParam String msg,@RequestParam Integer type){
        messaging.convertAndSend("queue-"+type,msg);
        return Result.ok();
    }

    @RequestMapping("/sendTopic")
    public Result sendMsgTopic(@RequestParam String msg,@RequestParam Integer type){
        messaging.convertAndSend("topic-"+type,msg);
        return Result.ok();
    }
}
*/
