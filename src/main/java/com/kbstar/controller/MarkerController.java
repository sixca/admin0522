package com.kbstar.controller;

import com.kbstar.dto.Marker;
import com.kbstar.dto.MarkerSearch;
import com.kbstar.service.MarkerService;
import com.kbstar.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/marker")

public class MarkerController {

    @Autowired
    MarkerService markerService;

    String dir = "marker/";

    @Value("${uploadimgdir}")
    String imgdir;
    // Application.properties 에 셋팅된 uploadimgdir (업로드 폴더 경로)

    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("center", dir+"add");
        return "index";
    }

    @RequestMapping("/addimpl")
    public String addimpl(Model model, Marker marker) throws Exception {

        MultipartFile mf;
        mf = marker.getImgMarker();   // 파일덩어리
        String img = mf.getOriginalFilename();   // 파일덩어리에서 이름을 꺼내온다

        log.info("-----------------------------------------");
        log.info(img);   // 이미지 이름 끄집어 내졌는지 체크.
        log.info("-----------------------------------------");

        marker.setImg(img);
        markerService.register(marker);
        FileUploadUtil.saveFile(mf, imgdir);
        return "redirect:/marker/all";
    }

    @RequestMapping("/all")
    public String all(Model model) throws Exception {
        List<Marker> list = null;
        list = markerService.get();
        model.addAttribute("mlist", list);
        model.addAttribute("center", dir+"all");
        return "index";
    }


    @RequestMapping("/detail")
    public String detail(Model model, int id) throws Exception {
        Marker marker = null;
        marker = markerService.get(id);

        model.addAttribute("gmarker", marker);
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
    public String updateimpl(Model model, Marker marker) throws Exception {

        MultipartFile mf = marker.getImgMarker();   // 파일덩어리
        String new_img = mf.getOriginalFilename();   // 파일덩어리에서 이름을 꺼내온다
//        log.info("-----------------------------------------");
//        log.info(imgname);   // 이미지 이름 끄집어 내졌는지 체크.
//        log.info("-----------------------------------------");

        if (new_img.equals("") || new_img == null) {   //기존 이미지를 그대로 쓰겠다
            markerService.modify(marker);
        }else {
            marker.setImg(new_img); //아니면 (새 이미지를 선택했다면) item 전체 업데이트 쳐라.
            markerService.modify(marker);
            FileUploadUtil.saveFile(mf, imgdir);     // 경로에 업로드
        }
        return "redirect:/marker/detail?id="+marker.getId();
    }

    @RequestMapping("/deleteimpl")
    public String deleteimpl(Model model, Integer id) throws Exception {
        markerService.remove(id);
        return "redirect:/marker/all";
    }


    @RequestMapping("/search")
    public String search(Model model, MarkerSearch ms) throws Exception {
//        if(ms.getLoc() == null){
//            ms.setLoc(null);
//        }
//        if(ms.getTitle().isEmpty()){
//            ms.setTitle(null);
//        }
        List<Marker> list = markerService.search(ms);
        model.addAttribute("ms", ms);      //ms 값을 박아줘서, 화면에서 검색버튼이 눌러도 데이터가 사라지지 않고 떠있도록 구현 >> all.jsp
        model.addAttribute("mlist", list);
        model.addAttribute("center", dir+"all");
        return "index";
    }
}
