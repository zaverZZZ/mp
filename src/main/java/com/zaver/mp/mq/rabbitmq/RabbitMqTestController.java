package com.zaver.mp.mq.rabbitmq;

import com.zaver.mp.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : RabbitMqTestController
 * @Description TODO
 * @Date : 2019/7/21 23:31
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqTestController {

    @Autowired
    private RabbitSender rabbitSender;
    @RequestMapping("/send")
    public Result send(@RequestParam String msg) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("number", "12345");
        map.put("send_time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS")));
        rabbitSender.send(msg,map);
        return Result.ok();
    }
}
