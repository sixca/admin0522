package com.kbstar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class StomWebSocketConfig implements WebSocketMessageBrokerConfigurer{

    @Value("${serviceserver}")
    String serviceserver;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("http://127.0.0.1",serviceserver).withSockJS();  //첫 웹소캣.. to me all 채팅
        registry.addEndpoint("/chbot").setAllowedOrigins("http://127.0.0.1", serviceserver).withSockJS(); // serviceServer에 접속한 사용자들이 우리 쪽으로 들어올 수 있게 열어줌.
        registry.addEndpoint("/wss").setAllowedOrigins("http://127.0.0.1").withSockJS();    // 대시보드 데이터 실시간 연결되던..
    } //서버에 웹소캣 3개를 만드는 것입니다.

    /* 어플리케이션 내부에서 사용할 path를 지정할 수 있음 */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/send","/sendadm", "/chsend");
    }  //밖으로 내보내는 기능 담당::   /send, /broadcast, /sendadm    /chsned 추가..
}