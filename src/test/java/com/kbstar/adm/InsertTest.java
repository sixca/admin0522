package com.kbstar.adm;

import com.kbstar.dto.Adm;
import com.kbstar.service.AdmService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
@SpringBootTest
class InsertTest {

	@Autowired
	AdmService service;

	@Test
	void contextLoads() {
		Adm obj = new Adm("admin04","pwd04",4);
		try {
			service.register(obj);
			log.info("등록 정상..");
		} catch (Exception e) {
			if(e instanceof DuplicateKeyException){
				log.info("ID가 중복 되었습니다------------------");
				//기 존재 아이디를 등록하면 dupl 오류뜸.
			}else{
				log.info("시스템 장애입니다-----------------------");
				e.printStackTrace();  //에러를 자세히 보려면 요렇게
			}
		}
	}
}
