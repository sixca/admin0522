package com.kbstar.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


//메인페이지에 삽입한 하이차트의 데이트 부분

@RestController
public class ChartImplController {

    @RequestMapping("/dashchart01")
    public Object dashchart01(){
        // [[],[]] :: 배열 안에 배열
        JSONArray ja = new JSONArray();
        for(int i=1; i<=5; i++){
            Random r = new Random();
            int num = r.nextInt(20)+1;
            JSONArray jadata = new JSONArray();
            jadata.add("data:"+num); // 첫번째 요소
            jadata.add(num);   // 두번째 요소
            ja.add(jadata);    //메인 배열(ja)에 위 2개 요소를 담은 배열(jadata)을 넣음
        }
        return ja;
    }
    @RequestMapping("/dashchart02")
    public Object dashchart02(){
        // [] :: 배열 하나
        JSONObject jo = new JSONObject();

        JSONArray jaCnt = new JSONArray();
        for(int i=0; i<=10; i++){
            Random r = new Random();
            int cnt = r.nextInt(2000)+1;
            jaCnt.add(cnt);
        }
        jo.put("datas", jaCnt);
        return jo;
    }
}
