package com.kbstar.mapper;

import com.kbstar.dto.Cart;
import com.kbstar.frame.KBMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CartMapper extends KBMapper<Integer, Cart> {
    public List<Cart> getmycart(String cid);   //CartMapper에 이 기능 추가. 내 장바구니 기능을 넣겠습니다. > CartService
    public int sumcart();
}
