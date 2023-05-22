package com.kbstar.chart;

import com.kbstar.dto.Chart;
import com.kbstar.service.ChartService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class MonthlyTotalTest {

    @Autowired
    ChartService service;

    @Test
    void contextLoads(){
        // [{[]},{[]}]  :: 내가 넣을 차트에서 원하는 형식으로 데이터를 뽑는다
        try {
            List<Chart> list = service.getMonthlyTotal();
            JSONArray fma = new JSONArray();
            JSONArray ma = new JSONArray();
            for(Chart c:list){
                if(c.getGender().toUpperCase().equals("F")){
                    fma.add(c.getTotal());
                }else{
                    ma.add(c.getTotal());
                }
            }
            JSONObject fmo = new JSONObject();
            JSONObject mo = new JSONObject();
            fmo.put("name", "Female");
            mo.put("name", "Male");
            fmo.put("data", fma);
            mo.put("data", ma);
            JSONArray data = new JSONArray();
            data.add(fmo);
            data.add(mo);
            log.info(data.toJSONString());
        } catch (Exception e) {
            log.info("에러 ..... ");
          // e.printStackTrace();
        }
    }
}
