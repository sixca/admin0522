package com.kbstar.marker;

import com.kbstar.service.MarkerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class SelectLocTest {

    @Autowired
    MarkerService service;

    @Test
    void contextLoads() throws Exception {
        try {
            service.getLoc("s");
        }catch (Exception e) {
            log.info("에러");
            e.printStackTrace();
        }
    }
}

