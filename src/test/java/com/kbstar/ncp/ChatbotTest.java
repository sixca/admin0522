package com.kbstar.ncp;

import com.kbstar.util.ChatBotUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@Slf4j
@SpringBootTest
class ChatbotTest {
	@Test
	void contextLoads() throws IOException {
		String txt = "너 가족있어";
		String result = ChatBotUtil.chat(txt);
		log.info("-------------------------------");
		log.info(result);
		log.info("-------------------------------");
	}
}
