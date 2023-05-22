package com.kbstar.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Marker {
    private int id;
    private String title;
    private String target;
    private double lat;
    private double lng;   //float 도 가능요
    private String img;
    private String loc;

    private MultipartFile imgMarker; //추가 :: img(이미지 네임)를 파일 덩어리 자체로 변환

    public Marker(int id, String title, String target, double lat, double lng, String img, String loc) {
        this.id = id;
        this.title = title;
        this.target = target;
        this.lat = lat;
        this.lng = lng;
        this.img = img;
        this.loc = loc;
    }
}