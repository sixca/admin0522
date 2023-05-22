package com.kbstar.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Item {
    private int id;
    private String name;
    private int price;
    private String imgname;
    private Date rdate;

    private MultipartFile img; //추가 :: imgname을 파일 덩어리 자체로 변환

    public Item(int id, String name, int price, String imgname, Date rdate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgname = imgname;
        this.rdate = rdate;
    }  // constructor 추가했으니, 기존 컨스트럭터도 하나 더 생성해줘야지.
}
