package com.kbstar.sales;


import com.kbstar.dto.Sales;
import com.kbstar.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class InsertTest {

	@Autowired
	SalesService service;

	@Test
	void contextLoads() {
//		Date date = new Date();
		Sales obj = new Sales("2023-04-28", 30000, "M" );
		try {
			service.register(obj);
			service.get();   //확인
			log.info("등록 정상..");
		} catch (Exception e) {
				log.info("시스템 장애입니다-----------------------");
				e.printStackTrace();  //에러를 자세히 보려면 요렇게
			}
		}
	}
