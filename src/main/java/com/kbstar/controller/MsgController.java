package com.kbstar.controller;

import com.kbstar.dto.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MsgController {
    @Autowired
    SimpMessagingTemplate template;

    //@MessageMapping :: Web Socket 개발을 위해 Client 요청을 받아서 처리 하는 Mapping 선언
    @MessageMapping("/receiveall") // 모두에게 전송
    public void receiveall(Msg msg, SimpMessageHeaderAccessor headerAccessor) {
        template.convertAndSend("/send",msg);  //받은 메세지를 /send를 통해 밖으로 내보내고 있음
    }
    @MessageMapping("/receiveme") // 나에게만 전송 ex)Chatbot
    public void receiveme(Msg msg, SimpMessageHeaderAccessor headerAccessor) {
        String id = msg.getSendid();
        template.convertAndSend("/send/"+id,msg);
    }
    @MessageMapping("/receiveto") // 특정 Id에게 전송
    public void receiveto(Msg msg, SimpMessageHeaderAccessor headerAccessor) {
        String target = msg.getReceiveid();
        template.convertAndSend("/send/to/"+target,msg);
    }
}