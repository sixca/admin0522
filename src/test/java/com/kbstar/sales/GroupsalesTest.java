package com.kbstar.sales;

import com.kbstar.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class GroupsalesTest {

	@Autowired
	SalesService service;

	@Test
	void contextLoads() {
		try {
			log.info("..................등록완료.............");
		  service.groupsales();
		} catch (Exception e) {
			log.info("에러..");
			e.printStackTrace();
		}
	}
}

