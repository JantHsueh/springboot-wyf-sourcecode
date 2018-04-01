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


	@MessageMapping("/chat")
	public void handleChat(String msg) { //2
		String name ="123456";
//		simpMessageSendingOperations.convertAndSendToUser(name,"/message",new Greeting("P2P: "+name));
		simpMessageSendingOperations.convertAndSendToUser(name,
					"/message",  "-send:"
							+ msg);


	}
}