package com.kbstar.controller;

import com.kbstar.dto.Msg;
import com.kbstar.util.ChatBotUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class ChatbotController {

    @Autowired
    SimpMessagingTemplate template;

    //@MessageMapping :: Web Socket 개발을 위해 Client 요청을 받아서 처리 하는 Mapping 선언
    @MessageMapping("/chatbotme") // 모두에게 전송
    public void chatbotme(Msg msg, SimpMessageHeaderAccessor headerAccessor) throws IOException {
        String target = msg.getSendid(); // 누가보냈는지 가져오고
        // NCP chatbot 연동.... 추가합시다
        String txt = msg.getContent1();  // 텍스트를 끄집어내서
        String result = ChatBotUtil.chat(txt); // 결과에 박고
        msg.setContent1(result); // 결과를 메세지로
        template.convertAndSend("/chsend/"+target,msg);  //받은 메세지를 /chsend를 통해 target에게 다시 내보내고 있음.
    }
}