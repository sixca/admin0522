package com.kbstar;
//Exception을 처리 하는 Controller 생성 :: 모든 Exception 발생 시 이 콘트롤러가 받게 됨
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

    @ControllerAdvice
    public class CustomErrorController {
        @ExceptionHandler(Exception.class)   //어떤 상황에서도 Exception이 발생하면 핸들러 작동
        public String except(Exception e, Model model){
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("center","error/error_page1");
            return "index";
        }
    }

