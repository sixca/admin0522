package com.kbstar.controller;

import com.kbstar.dto.Sales;
import com.kbstar.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/sales")

public class SalesController {

//    @Autowired
//    ItemService itemService;
//    @Autowired
//    CartService cartService;

    @Autowired
    SalesService salesService;

    @Value("${adminserver}")
    String adminserver;

    String dir = "sales/";

//    @RequestMapping("/groupsales")
//    public String groupsales(Model model){
//        model.addAttribute("adminserver", adminserver);
//        model.addAttribute("center", "livechart");
//        return "index";
//    }
//
//    @RequestMapping("/sumcart")
//    public String sumcart(Model model){
//        model.addAttribute("center", "sumcart");
//        return "index";
//    }

    @RequestMapping("/groupsales")
    public String groupsales(Model model) throws Exception {
        List<Sales> list = null;
        try {
            log.info("..................등록완료.............");
            list = salesService.groupsales();
        } catch (Exception e) {
            log.info("..................에러..................");
            e.printStackTrace();
        }

        model.addAttribute("slist", list);
        return "index";

/////////////////////////////////////////////////////

        // {'category':[], 'datas':[]} :: 복수의 배열 데이터를 받을 때, 객체로 받기
//        JSONObject jo = new JSONObject();
//        JSONArray jaGender = new JSONArray();
//        for(int i=0; i<=16; i++){ //data가 16개임
//            String gender = salesService.groupsales();
//            jaCnt.add(cnt);
//        }
//        JSONArray jaYear = new JSONArray();
//        for(int i=2011; i<=2023; i++){
//            jaYear.add(i);
//        }   // 2011~2023년
//        jo.put("category", jaYear);
//        jo.put("datas", jaCnt);
//        // {'category':[], 'datas':[]}
//        return jo;







    }
}
