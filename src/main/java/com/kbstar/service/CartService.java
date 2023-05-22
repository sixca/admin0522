package com.kbstar.service;

import com.kbstar.dto.Cart;
import com.kbstar.frame.KBService;
import com.kbstar.mapper.CartMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j // 로그 쓸 수 있음
@Service
public class CartService implements KBService<Integer, Cart> {


    /**
     * 등록 및 가입 진행
     * argument : Object
     * return : null
     *
     * @param cart
     */
    @Autowired
    CartMapper mapper;
    @Override
    public void register(Cart cart) throws Exception {
        mapper.insert(cart);
        log.info("Cart Register finished. Send mail......................" + cart.getId());
    }

    @Override
    public void remove(Integer id) throws Exception {
        mapper.delete(id);
    }

    @Override
    public void modify(Cart cart) throws Exception {
        mapper.update(cart);
    }

    @Override
    public Cart get(Integer id) throws Exception {
        return mapper.select(id);
    }

    @Override
    public List<Cart> get() throws Exception {
        return mapper.selectall();
    }

    public List<Cart> getMyCart(String cid) throws Exception {
        return mapper.getmycart(cid);
    }  //기존에 없는 기능 추가. id는 무슨 id일까요? id > cid로 바꿈

    public int SumCart() throws Exception {
        return mapper.sumcart();
    }

}
