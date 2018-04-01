package com.controller;

import com.model.Greeting;
import com.model.HelloMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by haoyuyang on 2016/11/25.
 */
@RestController
public class GreetingController {

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    /**
     * 表示服务端可以接收客户端通过主题“/app/hello”发送过来的消息，客户端需要在主题"/topic/hello"上监听并接收服务端发回的消息
     */
    @MessageMapping("/hello") //"/hello"为WebSocketConfig类中registerStompEndpoints()方法配置的
//    @SendTo("/topic/greetings")
    public void  greeting(HelloMessage message) {
        System.out.println("connected successfully greeting ....");
        String name = message.getName();
        simpMessageSendingOperations.convertAndSendToUser("123","/message",new Greeting("P2P: "+name));
//        return new Greeting("Hello,"+name);
    }

//    @SubscribeMapping("/macro")
//    @SendTo("/topic/greetings")
//    public Greeting hand(){
//        System.out.println("connected successfully  hand ....");
//        simpMessageSendingOperations.convertAndSend("/topic/greetings",new Greeting("macro"));
//        return new Greeting("I macro");
//    }

    /**
     * 这里用的是@SendToUser，这就是发送给单一客户端的标志。本例中，
     * 客户端接收一对一消息的主题应该是“/user/” + 用户Id + “/message” ,这里的用户id可以是一个普通的字符串，只要每个用户端都使用自己的id并且服务端知道每个用户的id就行。
     * @return
     */
//    @MessageMapping("/message")
//    @SendToUser("/message")
//    public Greeting handleSubscribe() {
//        System.out.println("this is the @SubscribeMapping('/marco')");
//        return new Greeting("I am a msg from SubscribeMapping('/macro').");
//    }

    @RequestMapping(value = "/getId",method = RequestMethod.POST)
    public Map<String, Object> getId() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",String.valueOf(new Random().nextInt(100)));
        return map;
    }

    /**
     * 测试对指定用户发送消息方法
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Greeting send() {
        simpMessageSendingOperations.convertAndSend("/topic/feed", new Greeting("feed"));
        simpMessageSendingOperations.convertAndSend("/topic/greetings", new Greeting("greetings"));
        return new Greeting("TTTTTTTT");
    }
}
