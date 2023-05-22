package com.kbstar.service;

import com.kbstar.dto.MarkerDesc;
import com.kbstar.frame.KBService;
import com.kbstar.mapper.MarkerDescMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j // 로그 쓸 수 있음
@Service
public class MarkerDescService implements KBService<Integer, MarkerDesc> {

    @Autowired
    MarkerDescMapper mapper;

    @Override
    public void register(MarkerDesc markerDesc) throws Exception {
        mapper.insert(markerDesc);
    }

    @Override
    public void remove(Integer id) throws Exception {
        mapper.delete(id);
    }

    @Override
    public void modify(MarkerDesc markerDesc) throws Exception {
        mapper.update(markerDesc);
    }

    @Override
    public MarkerDesc get(Integer id) throws Exception {
        return mapper.select(id);
    }

    @Override
    public List<MarkerDesc> get() throws Exception {
        return mapper.selectall();
    }

    public List<MarkerDesc> getMarkerDesc(int id) throws Exception{
        return mapper.getmarkerdesc(id);
    }
}
