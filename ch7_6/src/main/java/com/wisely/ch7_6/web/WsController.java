package com.wisely.ch7_6.web;

import com.wisely.ch7_6.domain.WiselyMessage;
import com.wisely.ch7_6.domain.WiselyResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WsController {

	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public WiselyResponse say(WiselyMessage message) throws Exception {
		Thread.sleep(3000);
		return new WiselyResponse("Welcome, " + message.getName() + "!");
	}

	@Autowired
	private SimpMessagingTemplate messagingTemplate;//1

    /**
     * 如果使用security 设置用户，那么使用账号名称登录，这里的touser将会发送到对应的用户名下面
     * 并且消息代理configureMessageBroker 不需要设置"/user" ,只需要设置"/queue".
     * 那么前段订阅的消息路径就是固定的 "/user/queue/notifications"
     *
     * @param principal
     * @param msg
     */
//	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg) { //2
		if (principal.getName().equals("wyf")) {//3
			messagingTemplate.convertAndSendToUser("wisely",
					"/queue/notifications", principal.getName() + "-send:"
							+ msg);
		} else {
			messagingTemplate.convertAndSendToUser("wyf",
					"/queue/notifications", principal.getName() + "-send:"
							+ msg);
		}
	}

	@Autowired
	private SimpMessageSendingOperations simpMessageSendingOperations;


    /**
     * 如果不使用security 来进行登录，也可以发送到指定用户，服务器代码还是一样的。
     * 消息代理configureMessageBroker 一定一定一定需要设置"/user"
     * 前端订阅的地址  会因用户名称变化而变化，例如  用户“123”，订阅地址是 "/user/123/message"
     * @param msg
     */
	@MessageMapping("/chat")
	public void handleChat(String msg) { //2
		String name ="123456";
//		simpMessageSendingOperations.convertAndSendToUser(name,"/message",new Greeting("P2P: "+name));
        messagingTemplate.convertAndSendToUser(name,
					"/message",  "-send:"
							+ msg);


	}
}