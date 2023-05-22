package com.kbstar.markerdesc;

import com.kbstar.dto.MarkerDesc;
import com.kbstar.service.MarkerDescService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
@SpringBootTest
class UpdateTest {
    @Autowired
    MarkerDescService service;

    @Test
    void contextLoads() throws Exception {
        MarkerDesc obj = new MarkerDesc(105,107,"양꼬치",50000,"yang3.jpg");
        try {
            service.register(obj);
            log.info("등록 정상");
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                log.info("이미 사용 중인 ID입니다!----------------------------------------");
            } else {
                log.info("등록에러..");
            }
        }
    }
}

