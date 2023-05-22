package com.kbstar.cart;

import com.kbstar.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class CartSumTest {

    @Autowired
    CartService service;

    @Test
    void contextLoads(){
        try {
            log.info("....... CartSumTest 완료완료 ..... ");
            service.SumCart();
        } catch (Exception e) {
            log.info("에러 ..... ");
           e.printStackTrace();
        }
    }
}
