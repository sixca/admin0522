package com.kbstar.service;

import com.kbstar.dto.Cust;
import com.kbstar.frame.KBService;
import com.kbstar.mapper.CustMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j   //로그사용
@Service
public class CustService implements KBService<String, Cust> {

    @Autowired
    CustMapper mapper;

    @Override
    public void register(Cust cust) throws Exception {
        mapper.insert(cust);
//        mapper.insert(cust);    //중복 실행 오류 test.
        log.info("Sned Mail..."+cust.getId());
    }

    @Override
    public void remove(String s) throws Exception {
        mapper.delete(s);
    }

    @Override
    public void modify(Cust cust) throws Exception {
        mapper.update(cust);
    }

    @Override
    public Cust get(String s) throws Exception {
        return mapper.select(s);
    }

    @Override
    public List<Cust> get() throws Exception {
        return mapper.selectall();
    }


}
