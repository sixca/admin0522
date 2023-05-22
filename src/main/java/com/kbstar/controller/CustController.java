package com.kbstar.controller;

import com.kbstar.dto.Cust;
import com.kbstar.service.CustService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("/cust")
public class CustController {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    CustService custService;
    String dir = "cust/";

    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("center", dir+"add");
         return "index";
        }

    @RequestMapping("/addimpl")
    public String addimpl(Model model, @Validated Cust cust, Errors errors) throws Exception {   //cust에 문제가 생기면, errors가 난다. validation
       if(errors.hasErrors()){
           List<ObjectError> es = errors.getAllErrors();    //Cust에 담아둔(DefaultMessage) 어노테이션과 message
           String msg ="";
           for(ObjectError e:es){
               msg += "<h4>";
               msg += e.getDefaultMessage();
               msg += "</h4>";
           }
           throw new Exception(msg);
       }
       cust.setPwd(encoder.encode(cust.getPwd()));
       custService.register(cust);  // DB에 짚어 넣고
       return "redirect:/cust/all";
    }

    @RequestMapping("/all")
    public String all(Model model) throws Exception {
        List<Cust> list = null;
        list = custService.get();
        model.addAttribute("clist", list);
        model.addAttribute("center", dir+"all");
        return "index";
    }

    @RequestMapping("/detail")   //로그인 사용자 아이디 클릭 시 정보조회
    public String detail(Model model, String id) throws Exception {   // /cust/detail?id=${obj.id} 써먹으려면 String id를 받아야
        Cust cust =null;
        try {
            cust = custService.get(id);
        } catch (Exception e) {
            throw new Exception("시스템 장애");
        }
        model.addAttribute("custinfo", cust);    //뿌릴 정보를 준비하고
        model.addAttribute("center", dir+"detail");     //센터에 정보를 뿌림. 익숙해 지세요!
        return "index";
    }
    @RequestMapping("/updateimpl")
    public String updateimpl(Model model, @Validated Cust cust, Errors errors) throws Exception {
        if(errors.hasErrors()){
            List<ObjectError> es = errors.getAllErrors();    //Cust에 담아둔(DefaultMessage) 어노테이션과 message
            String msg ="";
            for(ObjectError e:es){
                msg += "<h4>";
                msg += e.getDefaultMessage();
                msg += "</h4>";
            }
            throw new Exception(msg);
        }
         cust.setPwd(encoder.encode(cust.getPwd()));
         custService.modify(cust);
        return "redirect:/cust/detail?id="+cust.getId();         //수정이 일어난 후, "/detail?id="로 다시 보냄
    }

    @RequestMapping("/deleteimpl")
    public String deleteimpl(Model model, String id) throws Exception {
        custService.remove(id);
        return "redirect:/cust/all";
    }

}
