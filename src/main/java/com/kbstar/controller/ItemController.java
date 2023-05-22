package com.kbstar.controller;

import com.kbstar.dto.Item;
import com.kbstar.dto.ItemSearch;
import com.kbstar.service.CartService;
import com.kbstar.service.ItemService;
import com.kbstar.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("/item")

public class ItemController {

    @Autowired
    ItemService itemService;
    @Autowired
    CartService cartService;

    String dir = "item/";

    @Value("${uploadimgdir}")
    String imgdir;
    // Application.properties 에 셋팅된 uploadimgdir (업로드 폴더 경로)

    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("center", dir+"add");
        return "index";
    }

    @RequestMapping("/addimpl")
    public String addimpl(Model model, Item item) throws Exception {

        MultipartFile mf = item.getImg();   // 파일덩어리
        String imgname = mf.getOriginalFilename();   // 파일덩어리에서 이름을 꺼내온다

//        log.info("-----------------------------------------");
//        log.info(imgname);   // 이미지 이름 끄집어 내졌는지 체크.
//        log.info("-----------------------------------------");

        item.setImgname(imgname);
        itemService.register(item);
        FileUploadUtil.saveFile(mf, imgdir);
        return "redirect:/item/all";
    }

    @RequestMapping("/all")
    public String all(Model model) throws Exception {
        List<Item> list = null;
        list = itemService.get();
        model.addAttribute("ilist", list);
        model.addAttribute("center", dir+"all");
        return "index";
    }


    @RequestMapping("/detail")
    public String detail(Model model, int id) throws Exception {
        Item item = null;
        item = itemService.get(id);

        model.addAttribute("gitem", item);
        model.addAttribute("center", dir+"detail");
        return "index";
    }

//    @RequestMapping("/detail")   //로그인 사용자 아이디 클릭 시 정보조회
//    public String detail(Model model, Integer id) throws Exception {   // /item/detail?id=${obj.id} 써먹으려면 Integer id를 받아야
//        Item item =null;
//        try {
//            item = itemService.get(id);
//        } catch (Exception e) {
//            throw new Exception("시스템 장애");
//        }
//        model.addAttribute("iteminfo", item);    //뿌릴 정보를 준비하고
//        model.addAttribute("center", dir+"detail");     //센터에 정보를 뿌림. 익숙해 지세요!
//        return "index";
//    }

//    @RequestMapping("/updateimpl")
//    public String updateimpl(Model model, Item item) throws Exception {
//        itemService.modify(item);
//        return "redirect:/item/detail?id="+item.getId();         //수정이 일어난 후, "/detail?id="로 다시 보냄
//    }

    @RequestMapping("/updateimpl")
    public String updateimpl(Model model, Item item) throws Exception {

        MultipartFile mf = item.getImg();   // 파일덩어리
        String new_imgname = mf.getOriginalFilename();   // 파일덩어리에서 이름을 꺼내온다
//        log.info("-----------------------------------------");
//        log.info(imgname);   // 이미지 이름 끄집어 내졌는지 체크.
//        log.info("-----------------------------------------");

        if (new_imgname.equals("") || new_imgname == null) {   //이미지 지정이 따로 없으면, 기존 이미지를 그대로 쓰겠다
            itemService.modify(item);
        }else {
            item.setImgname(new_imgname); //아니면 (새 이미지를 지정했다면) item 전체 업데이트 쳐라.
            itemService.modify(item);
            FileUploadUtil.saveFile(mf, imgdir);     // 경로에 업로드
        }
        return "redirect:/item/detail?id="+item.getId();
    }

    @RequestMapping("/deleteimpl")
    public String deleteimpl(Model model, Integer id) throws Exception {
        itemService.remove(id);
        return "redirect:/item/all";
    }


    @RequestMapping("/search")
    public String search(Model model, ItemSearch is) throws Exception {
        log.info("-----------------------------------------");
        log.info(is.getStartdate());
        log.info(is.getEnddate());
        log.info("-----------------------------------------");

        List<Item> list = null;
        list = itemService.search(is);
        model.addAttribute("is", is);   // 원상복귀하기 위해 넣음
        model.addAttribute("ilist", list);
        model.addAttribute("center", dir+"all");
        return "index";
    }

//    @RequestMapping("/search")
//    public String search(Model model, ItemSearch is) throws Exception {
//        List<Item> list = itemService.search(is);
//        model.addAttribute("is", is);      //is 값을 박아줘서, 화면에서 검색버튼이 눌러도 데이터가 사라지지 않고 떠있도록 구현 >> all.jsp
//        model.addAttribute("ilist", list);
//        model.addAttribute("center", dir+"all");
//        return "index";
//    }

    //sumcart용 그리기용 log찍기
    @Scheduled(cron = "*/20 * * * * *")
    public void cronSumCartUpdate() throws Exception {
        int num = cartService.SumCart();
        log.info(num+"");
    }


}
