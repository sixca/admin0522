package com.kbstar.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class MsgAdm {
    private int content1;
    private int content2;
    private int content3;
    private int content4;
}
// 관리자에게 정보를 보내는 역할. ScheduleController.java
