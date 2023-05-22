package com.kbstar.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Msg {
    private String sendid;
    private String receiveid;
    private String content1;
}
// msg를 주고받는 매개체 역할을 할 것임.
