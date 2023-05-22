package com.kbstar.controller;

import com.kbstar.dto.Adm;
import com.kbstar.dto.Item;
import com.kbstar.service.AdmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    AdmService admService;
    @Autowired
    private BCryptPasswordEncoder encoder;  //암호화

    @Value("${adminserver}")
    String adminserver;

    // http://127.0.0.1/
    @RequestMapping("/")
    public String main(Model model){
        model.addAttribute("adminserver", adminserver);
        return "index";
    }

    @RequestMapping("/charts")
    public String charts(Model model){
        model.addAttribute("center", "charts");
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("center", "login");
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session){
        if(session != null){             //로긴 상태면
            session.invalidate();        //세션에서 정보를 없애라
        }
        return "redirect:/";
    }


    @RequestMapping("/loginimpl")
    public String loginimpl(Model model, String id, String pwd
            ,HttpSession session) throws Exception {      //로그인 성공 시 session에다가 넣어둔다
        Adm adm = null;
        String nextPage = "loginfail";
        try {
            adm = admService.get(id);
            if(adm != null && encoder.matches(pwd, adm.getPwd())){    //id가 있고, 해당하는 아이디의 pwd를 비교 후 일치하면
                nextPage = "loginok";
                session.setMaxInactiveInterval(1000);  //세션 유지 시간 설정 :: 1000초 지나면 세션 종료(로긴 튕김)
                session.setAttribute("loginadm", adm);   //쎄션에 로긴정보를 박아두는 것  .. index에도 나오는 loginadm
                return "redirect:/";
            }
        } catch (Exception e) {
            throw new Exception("시스템 장애, 잠시 후 다시 로그인 하세요");
        }
//        model.addAttribute("radm", adm);
        model.addAttribute("center", nextPage);
        return "index";
    }

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("center", "register");
        return "index";
    }

    @RequestMapping("/registerimpl")
    public String registerimpl(Model model, Adm adm
            ,HttpSession session) throws Exception {
        try {
            adm.setPwd(encoder.encode(adm.getPwd()));  //화면에서 입력한 pwd를 가져와서 암호화 한 다음에 다시 adm 객체에 넣어서 register
            admService.register(adm);
            session.setAttribute("loginadm", adm);   //회원가입 시 바로 로그인 처리 되도록하기
        } catch (Exception e) {
            throw new Exception("가입오류");
        }
//        model.addAttribute("radm", adm);
//        model.addAttribute("center", "registerok");
        // 성공 시 registerok 화면 띄운 것 지우고, 루트화면으로 가도록.
        //return "index";
        return "redirect:/";
    }

    @RequestMapping("/websocket")
    public String websocket(Model model){
        model.addAttribute("adminserver", adminserver);
        model.addAttribute("center", "websocket");
        return "index";
    }

    @RequestMapping("/callcenter")
    public String callcenter(Model model){
        model.addAttribute("adminserver", adminserver);
        model.addAttribute("center", "callcenter");
        return "index";
    }

    @RequestMapping("/chatbot")
    public String chatbot(Model model){
        model.addAttribute("adminserver", adminserver);
        model.addAttribute("center", "chatbot");
        return "index";
    }

    @RequestMapping("/tables")
    public String tables(Model model){
        List<Item> list = new ArrayList<>();
//            list.add(new ItemDTO(100,"사탕",1000,"a.jpg",new Date()));
//            list.add(new ItemDTO(101,"초코릿",2000,"b.jpg",new Date()));
//            list.add(new ItemDTO(102,"아스크림",3000,"c.jpg",new Date()));
//            list.add(new ItemDTO(103,"호떡",4000,"d.jpg",new Date()));
//            list.add(new ItemDTO(104,"커피",5000,"e.jpg",new Date()));
        //위아래 리스트를 둘 다 사용하려면, list1, list2으로 각각 저장하고, 또 각각 foreach로 jsp에 구현하면 가능

//        for(int i=1 ; i < 100 ; i++ ){
//            list.add(new Item(i, "상품"+ i, (int)Math.round(Math.random() * 10000), "a.jpg", new Date()));
//        }
        model.addAttribute("allitem", list);
        model.addAttribute("center", "tables");
        return "index";
    }

    @RequestMapping("/dashchart")
    public String dashchart(Model model){
        model.addAttribute("center", "dashchart");
        return "index";
    }

    @RequestMapping("/livechart")
    public String livechart(Model model){
        model.addAttribute("adminserver", adminserver);
        model.addAttribute("center", "livechart");
        return "index";
    }

    @RequestMapping("/sumcart")
    public String sumcart(Model model){
        model.addAttribute("center", "sumcart");
        return "index";
    }
}
