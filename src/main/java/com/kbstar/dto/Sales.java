package com.kbstar.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Sales {
    //private Date rdate;
    private String rdate; // 2023-01-02   오라클에서는 데이트 타입인데, 자바에서는 스트링
    private int price;
    private String gender;
}
