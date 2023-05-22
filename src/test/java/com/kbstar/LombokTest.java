package com.kbstar;

import com.kbstar.dto.Cust;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

//테스트 프로그램입니다. junit 테스트가 세팅되어있네요.
// > 녹색화살표 클릭해서 실행하니, 하단 창에 LombokTest 콘솔이 나오며 테스트 출력이 되네요.

// 롬복으로 가져온 CustDTO 활용

@SpringBootTest
class LombokTest {
	Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());    //org.slfg 머시기 import
	@Test
	void contextLoads() {
		Cust cust = new Cust("id01","pwd01","이말숙");
		logger.info(cust.toString());
	}
}
